package eu.ttbox.core.dao;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.util.StopWatch;

import eu.ttbox.model.IBoxPersistantModelObject;

public abstract class AbstractPagedQueryJPABuilder<E extends IBoxPersistantModelObject, CRITERIA> implements
		ISearchService<E, CRITERIA> {
	
	Logger log = LoggerFactory.getLogger(getClass());

	private Class<E> entityClass;

	@SuppressWarnings("unchecked")
	public AbstractPagedQueryJPABuilder() {
		this.entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public AbstractPagedQueryJPABuilder(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	@SuppressWarnings("unchecked")
	public ModelMap doInEntityManager(EntityManager em, CRITERIA searchCriteria, //
			Integer first, //
			Integer max, //
			String[] order, //
			Boolean[] desc) {
		Integer firstResult = first == null ? Integer.valueOf(0) : first;
		Integer maxResult = max;
		boolean isStopWatch = log.isDebugEnabled();
		StopWatch stopWatch = null;
		if (isStopWatch) {
			stopWatch = new StopWatch("List " + entityClass);
			stopWatch.start("Count result");
		}
		// Count
		TypedQuery<Long> countQuery = createCountQuery(em, searchCriteria);
		Long resultCount = countQuery.getSingleResult();
		if (isStopWatch) {
			stopWatch.stop();
			stopWatch.start("Select paging result with page size " + maxResult);
		}
		// Select Result
		List<E> resultList = Collections.EMPTY_LIST;
		if (resultCount.longValue() > 0) {
			TypedQuery<E> selectQuery = createSelectQuery(em, searchCriteria, order, desc);
			if (firstResult.intValue() > 0) {
				selectQuery.setFirstResult(firstResult);
			}
			if (maxResult != null && resultCount.longValue() > (maxResult.longValue() + firstResult.longValue())) {
				selectQuery.setMaxResults(maxResult);
			}
			resultList = selectQuery.getResultList();
		}

		// Trace
		if (log.isInfoEnabled()) {
			log.info("Search list (first, maxResult, totalCount, countSend) = ( {}, {}, {}, {} )", new Object[] {
					firstResult, maxResult, resultCount, resultList.size() });
		}
		if (isStopWatch) {
			stopWatch.stop();
			log.debug(stopWatch.prettyPrint());
		}
		// Return Result
		ModelMap result = new ModelMap();
		result.addAttribute(ISearchService.RESULT_MAX_KEY, maxResult);
		result.addAttribute(ISearchService.RESULT_FIRST_KEY, firstResult);
		result.addAttribute(ISearchService.RESULT_COUNT_KEY, resultCount);
		result.addAttribute(ISearchService.RESULT_LIST_KEY, resultList);
		return result;
	}

	protected List<Order> createSelectOrderClause(CriteriaBuilder selectBuilder, Root<E> selectRoot, String[] order,
			Boolean[] desc) {
		int orderLength = order == null ? 0 : order.length;
		int orderDescLength = desc == null ? 0 : desc.length;

		List<Order> orders = null;
		if (orderLength > 0) {
			orders = new ArrayList<Order>();
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
		} else {
			orders = getListDefaultOrders(selectBuilder, selectRoot);
		}
		return orders;
	}

	protected List<Order> getListDefaultOrders(CriteriaBuilder selectBuilder, Root<E> selectRoot) {
		return null;
	}

	protected TypedQuery<Long> createCountQuery(EntityManager em, CRITERIA searchCriteria) {
		CriteriaBuilder countBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Long> countCriteria = countBuilder.createQuery(Long.class);
		Root<E> selectRoot = countCriteria.from(entityClass);
		countCriteria.select(countBuilder.count(selectRoot));
		// Where Clause
		Predicate restrictions = createListWhereClause(countBuilder, selectRoot, searchCriteria);
		if (restrictions != null) {
			countCriteria.where(restrictions);
		}
		TypedQuery<Long> countQuery = em.createQuery(countCriteria);
		setListWhereClauseParameter(countQuery, searchCriteria);
		return countQuery;
	}

	protected TypedQuery<E> createSelectQuery(EntityManager em, CRITERIA searchCriteria, String[] order, //
			Boolean[] desc) {
 
		CriteriaBuilder selectBuilder = em.getCriteriaBuilder();
		CriteriaQuery<E> selectCriteria = selectBuilder.createQuery(entityClass);
		Root<E> selectRoot = selectCriteria.from(entityClass);

		// Order Clause
		List<Order> orders = createSelectOrderClause(selectBuilder, selectRoot, order, desc);
		if (orders != null) {
			selectCriteria.orderBy(orders);
		}
		// Where Clause
		Predicate restrictions = createListWhereClause(selectBuilder, selectRoot, searchCriteria);
		if (restrictions != null) {
			selectCriteria.where(restrictions);
		}
		// Create Query
		TypedQuery<E> selectQuery = em.createQuery(selectCriteria);
		setListWhereClauseParameter(selectQuery, searchCriteria);
		return selectQuery;
	}

	protected abstract Predicate createListWhereClause(CriteriaBuilder criteriaBuilder, Root<E> fromRoot,
			CRITERIA searchCriteria);

	protected abstract void setListWhereClauseParameter(@SuppressWarnings("rawtypes") TypedQuery selectQuery,
			CRITERIA searchCriteria);

}
