package eu.ttbox.domain.dao.user;

import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import eu.ttbox.domain.dao.AbstractHibernateDAOImpl;
import eu.ttbox.domain.model.user.Role;

@Repository("roleDAO")
public class RoleDAOImpl extends AbstractHibernateDAOImpl<Role, String>
		implements RoleDAO {

	@Override
	protected Order getOrderDefault() {
		return Order.asc("id");
	}
	
	
}
