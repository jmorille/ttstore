package eu.ttbox.domain.dao;

import java.util.Collection;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("ttboxDAO")
@Scope("singleton")
public class TtBoxDAOImpl extends HibernateDaoSupport implements TtBoxDAO {

	@Autowired
	public TtBoxDAOImpl(
			@Qualifier("ttboxSessionFactory") SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
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
	public void saveObject(Object entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}
 
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public void saveObjects(@SuppressWarnings("rawtypes") Collection entities) {
		getHibernateTemplate().saveOrUpdateAll(entities);
	}


	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public void deleteAll(@SuppressWarnings("rawtypes") Collection entities) {
		getHibernateTemplate().deleteAll(entities);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public void deleteAll(Object entity) {
		getHibernateTemplate().delete(entity);
	}

}
