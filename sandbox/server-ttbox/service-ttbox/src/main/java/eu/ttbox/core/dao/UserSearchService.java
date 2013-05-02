package eu.ttbox.core.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.granite.messaging.amf.io.util.externalizer.annotation.IgnoredProperty;
import org.granite.tide.annotations.TideEnabled;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import eu.ttbox.model.user.User;
import eu.ttbox.model.user.User_;
import eu.ttbox.service.bean.SearchCriteria;


@TideEnabled
@Service("userSearchService")
@Transactional
public class UserSearchService extends AbstractPagedQueryJPABuilder<User, SearchCriteria>
       // implements ISearchService<User, SearchCriteria>
{

	@IgnoredProperty
	@PersistenceContext
	EntityManager entityManager;

    public UserSearchService() {
        super(User.class);
    }

    @Override
	@Secured(value = { "ROLE_USER" })
	public ModelMap list( //
			SearchCriteria searchCriteria, //
			Integer first, Integer max, //
			String[] order, Boolean[] desc) {
		ModelMap result = doInEntityManager(entityManager, searchCriteria, first, max, order, desc);
		return result;
	}

	@Override
	protected Predicate createListWhereClause(CriteriaBuilder selectBuilder, Root<User> selectRoot,
			SearchCriteria searchCriteria) {
		Predicate predicate = null;
		if (searchCriteria != null && searchCriteria.isCriteriaSet()) {

			Predicate predicateFirstName = selectBuilder.like(selectBuilder.lower(selectRoot.get(User_.firstName)),
					"%:searchCriteria%");
			Predicate predicateLastName = selectBuilder.like(selectBuilder.lower(selectRoot.get(User_.lastName)),
					"%:searchCriteria%");
			predicate = selectBuilder.or(predicateFirstName, predicateLastName);
		}
		return predicate;
	}

	@Override
	protected List<Order> getListDefaultOrders(CriteriaBuilder selectBuilder, Root<User> selectRoot) {
		List<Order> orders = new ArrayList<Order>();
		orders.add(selectBuilder.asc(selectRoot.get(User_.lastName)));
		orders.add(selectBuilder.asc(selectRoot.get(User_.firstName)));
		return orders;
	}

	@Override
	protected void setListWhereClauseParameter(@SuppressWarnings("rawtypes") TypedQuery selectQuery,
			SearchCriteria searchCriteria) {
		if (searchCriteria != null && searchCriteria.isCriteriaSet()) {
			String searchValue = searchCriteria.getValue().toLowerCase().trim();
			selectQuery.setParameter("searchCriteria", searchValue);
		}
	}

}
