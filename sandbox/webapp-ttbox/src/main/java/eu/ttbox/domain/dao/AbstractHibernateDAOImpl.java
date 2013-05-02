package eu.ttbox.domain.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import eu.ttbox.domain.model.PersistantModelObject;

public class AbstractHibernateDAOImpl<ENTITY extends PersistantModelObject<? extends Serializable>, ID extends Serializable>
		extends HibernateDaoSupport implements IHibernateDAO<ENTITY, ID> {

	@SuppressWarnings("unchecked")
	private Class<ENTITY> entityClass = (Class<ENTITY>) ((ParameterizedType) getClass()
			.getGenericSuperclass()).getActualTypeArguments()[0];

	@Autowired
	@Qualifier("ttboxSessionFactory")
	public void setHibernateSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	public Class<ENTITY> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<ENTITY> entityClass) {
		this.entityClass = entityClass;
	}

	@Override
	public void flushAndClear() {
		getHibernateTemplate().flush();
		getHibernateTemplate().clear();
	}

	@Override
	public void flush() {
		getHibernateTemplate().flush();
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public void persist(ENTITY entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public void persistAll(Collection<ENTITY> entities) {
		getHibernateTemplate().saveOrUpdateAll(entities);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public void removeAll(Collection<ENTITY> entities) {
		getHibernateTemplate().deleteAll(entities);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public void remove(ENTITY entity) {
		getHibernateTemplate().delete(entity);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public ENTITY findById(ID id) {
		if (id == null)
			return null;
		ENTITY result = (ENTITY) getHibernateTemplate().get(entityClass, id);
		return result;
	}

	/**
	 * Methode to Override in order to define the default order of the abstract
	 * search method.
	 * 
	 * @return Order Criteria 
	 */
	protected Order getOrderDefault() {
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<ENTITY> findAll() {
		Criteria query = getSession().createCriteria(getEntityClass());
		Order orderDefault = getOrderDefault();
		if (orderDefault != null) {
			query.addOrder(orderDefault);
		}
		List<ENTITY> result = (List<ENTITY>) query.list();
		return result;
	}

}
