package eu.ttbox.domain.dao.menu;

import java.util.List;

import eu.ttbox.domain.dao.IHibernateDAO;
import eu.ttbox.domain.model.service.Menu;

public interface MenuDAO extends IHibernateDAO<Menu, Long> {

	List<Menu> findAllActive();

}