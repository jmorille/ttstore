package eu.ttbox.service.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.granite.messaging.amf.io.util.externalizer.annotation.IgnoredProperty;
import org.granite.messaging.service.annotations.RemoteDestination;
import org.granite.tide.annotations.TideEnabled;
import org.granite.tide.data.DataEnabled;
import org.granite.tide.data.DataEnabled.PublishMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.StopWatch;

import eu.ttbox.core.listener.ObserveAllPublishAll;
import eu.ttbox.model.user.User;
import eu.ttbox.model.user.User_;
import eu.ttbox.service.bean.SearchCriteria;

@Service("userService")
@Transactional
@TideEnabled
@RemoteDestination(id = "userService", source = "userService", channel = "graniteamf", securityRoles = { "ROLE_ADMIN" })
@DataEnabled(topic = "dataTopic", params = ObserveAllPublishAll.class, publish = PublishMode.ON_SUCCESS)
public class UserService // extends AbstractPagedQueryJPABuilder<User,
// SearchCriteria>
{

	@IgnoredProperty
	Logger log = LoggerFactory.getLogger(getClass());

	@IgnoredProperty
	@PersistenceContext
	EntityManager entityManager;

	// @Transactional(readOnly = true)
	// public ModelMap list( //
	// SearchCriteria searchCriteria, //
	// Integer first, Integer max, //
	// String[] order, Boolean[] desc) {
	// ModelMap result = doInEntityManager(entityManager, searchCriteria, first,
	// max, order, desc);
	// return result;
	// }
	//
	// @Override
	// protected Predicate createListWhereClause(CriteriaBuilder selectBuilder,
	// Root<User> selectRoot,
	// SearchCriteria searchCriteria) {
	// Predicate predicateFirstName =
	// selectBuilder.like(selectBuilder.lower(selectRoot.get(User_.firstName)),
	// "%:searchCriteria%");
	// Predicate predicateLastName =
	// selectBuilder.like(selectBuilder.lower(selectRoot.get(User_.lastName)),
	// "%:searchCriteria%");
	// Predicate predicate = selectBuilder.or(predicateFirstName,
	// predicateLastName);
	// return predicate;
	// }
	//
	// @Override
	// protected void setListWhereClauseParameter(@SuppressWarnings("rawtypes")
	// TypedQuery selectQuery,
	// SearchCriteria searchCriteria) {
	// if (searchCriteria.isCriteriaSet()) {
	// String searchValue = searchCriteria.getValue().toLowerCase().trim();
	// selectQuery.setParameter("searchCriteria", searchValue);
	// }
	// }

	@Transactional(propagation = Propagation.REQUIRED)
	public void persist(User user) {
		entityManager.persist(user);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void merge(User entity) {
		entityManager.merge(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(User entity) {
		User userLocal = entityManager.merge(entity);
		entityManager.remove(userLocal);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public User getById(Long id) {
		User userLocal = entityManager.find(User.class, id);
		return userLocal;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<User> getAll() {
		Query q = entityManager.createQuery("Select p from User p");
		List<User> users = q.getResultList();
		log.info("Get User list for {} users ", users.size());
		return users;
	}

	@Transactional(readOnly = true)
	public ModelMap findByControlerUsers( //
			Map<String, Object> params, //
			Boolean whatItIs) {

		SearchCriteria filter = (SearchCriteria) params.get("filter");
		Integer first = (Integer) params.get("first");
		Integer maxResults = (Integer) params.get("max");
		log.info("Order type " + params.get("order"));
		log.info("Desc type " + params.get("desc"));

		// TODO
		String[] order = null;
		Boolean[] desc = null;
		return findUsers(filter, first, maxResults, order, desc);
	}

	@Transactional(readOnly = true)
	public ModelMap findUsers( //
			SearchCriteria searchCriteria, //
			Integer first, //
			Integer max, //
			String[] order, //
			Boolean[] desc) {

		boolean isStopWatch = log.isDebugEnabled();
		StopWatch stopWatch = null;
		if (isStopWatch) {
			stopWatch = new StopWatch("findUsers");
		}
		// Init
		Integer maxResults = max;
		if (maxResults == null || maxResults.equals(Integer.valueOf(0))) {
			maxResults = 20;
		}
		log.info("Search findUsers " + first + " / " + maxResults);

		// Init
		ModelMap result = new ModelMap();
		result.addAttribute("maxResults", maxResults);
		result.addAttribute("firstResult", first);

		// Count
		CriteriaBuilder countBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> countCriteria = countBuilder.createQuery(Long.class);
		countCriteria.select(countBuilder.count(countCriteria.from(User.class)));

		// Select
		CriteriaBuilder selectBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> selectCriteria = selectBuilder.createQuery(User.class);
		Root<User> selectRoot = selectCriteria.from(User.class);

		// Add Where
		if (searchCriteria != null && searchCriteria.isCriteriaSet()) {
			// TODO cq.where(//your stuff);
			selectCriteria.where(selectBuilder.like(selectRoot.get(User_.firstName), "%:searchCriteria%"));
			countCriteria.where(countBuilder.like(selectRoot.get(User_.firstName), "%:searchCriteria%"));
		}

		// Execute Query Count
		if (isStopWatch) {
			stopWatch.start("Select count");
		}
		TypedQuery<Long> countQuery = entityManager.createQuery(countCriteria);
		Long resultCount = countQuery.getSingleResult();
		result.addAttribute("resultCount", resultCount);
		if (isStopWatch) {
			stopWatch.stop();
		}
		List<User> resultList;
		if (resultCount.longValue() > 0) {
			if (isStopWatch) {
				stopWatch.start("Select Entities");
			}
			// Add Order
			int orderLength = order == null ? 0 : order.length;
			int orderDescLength = desc == null ? 0 : desc.length;
			if (orderLength > 0) {
				List<Order> orders = new ArrayList<Order>();
				for (int i = 0; i < orderLength; i++) {
					boolean isDesc = false;
					if (i < orderDescLength) {
						Boolean descOrder = desc[i];
						if (descOrder != null && descOrder.booleanValue()) {
							isDesc = descOrder.booleanValue();
						}
					}
					Order orderCriteria = null;
					if (isDesc) {
						orderCriteria = selectBuilder.desc(selectRoot.get(order[i]));
					} else {
						orderCriteria = selectBuilder.asc(selectRoot.get(order[i]));
					}
					orders.add(orderCriteria);
				}
				selectCriteria.orderBy(orders);
			}

			// Execute Select Query
			TypedQuery<User> selectQuery = entityManager.createQuery(selectCriteria);
			selectQuery.setFirstResult(first);
			if (resultCount > maxResults + first) {
				selectQuery.setMaxResults(maxResults);
			}
			resultList = selectQuery.getResultList();
			if (isStopWatch) {
				stopWatch.stop();
			}
		} else {
			resultList = new ArrayList<User>();
		}
		result.addAttribute("resultList", resultList);
		// trace
		log.info("Search findUsers (first, maxResult, totalCount, countSend) = ( {}, {}, {}, {} )", new Object[] {
				first, maxResults, resultCount, resultList.size() });
		if (isStopWatch) {
			log.debug(stopWatch.prettyPrint());
		}
		return result;
	}

	@Transactional(readOnly = true)
	public ModelMap findCriteriaUsers( //
			User examplePerson, //
			Integer first, //
			Integer max, //
			String[] order, //
			Boolean[] desc) {

		int maxResults = max;
		if (maxResults == 0) {
			maxResults = 36;
		}

		// Init
		ModelMap result = new ModelMap();
		result.addAttribute("maxResults", maxResults);
		result.addAttribute("firstResult", first);

		// Query String
		String from = "from User e ";
		String where = "where lower(e.lastName) like :lastName ";
		String orderBy = "";
		int orderLength = order == null ? 0 : order.length;
		int orderDescLength = desc == null ? 0 : desc.length;
		if (orderLength > 0) {
			StringBuilder sbOrder = new StringBuilder("order by ");
			for (int i = 0; i < orderLength; i++) {
				if (i > 0) {
					sbOrder.append(", ");
				}
				sbOrder.append("e.").append(order[i]);
				if (i < orderDescLength) {
					Boolean descOrder = desc[i];
					if (descOrder != null && descOrder.booleanValue()) {
						sbOrder.append(" desc");
					}
				}
			}
			orderBy = sbOrder.toString();
		}

		String lastName = examplePerson.getLastName() != null ? examplePerson.getLastName() : "";

		// Create Query count
		Query qc = entityManager.createQuery("select count(e) " + from + where);
		qc.setParameter("lastName", "%" + lastName.toLowerCase() + "%");
		long resultCount = (Long) qc.getSingleResult();

		result.addAttribute("resultCount", resultCount);

		if (resultCount > 0) {
			// Create Query
			Query ql = entityManager.createQuery("select e " + from + where + orderBy);
			ql.setFirstResult(first);
			ql.setMaxResults(maxResults);
			ql.setParameter("lastName", "%" + lastName.toLowerCase() + "%");
			@SuppressWarnings("unchecked")
			List<User> resultList = ql.getResultList();
			result.addAttribute("resultList", resultList);
			entityManager.flush();
		} else {
			result.addAttribute("resultList", new ArrayList<User>());
		}
		// Return Model Map
		return result;

	}
}
