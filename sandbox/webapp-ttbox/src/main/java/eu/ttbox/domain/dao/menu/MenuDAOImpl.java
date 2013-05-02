package eu.ttbox.domain.dao.menu;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import eu.ttbox.domain.dao.AbstractHibernateDAOImpl;
import eu.ttbox.domain.model.service.Menu;

@Repository("menuDAO")
public class MenuDAOImpl extends AbstractHibernateDAOImpl<Menu, Long> implements
		MenuDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<Menu> findAllActive() {
		Query query = getSession().createQuery("from Menu");
		return query.list();
	}
}
