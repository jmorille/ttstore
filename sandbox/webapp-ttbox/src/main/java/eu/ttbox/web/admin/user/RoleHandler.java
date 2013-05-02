package eu.ttbox.web.admin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import eu.ttbox.domain.dao.user.RoleDAO;
import eu.ttbox.domain.model.user.Role;

@Controller("roleHandler")
public class RoleHandler {

	@Autowired
	@Qualifier("roleDAO")
	RoleDAO roleDAO;

	private Role role = new Role();

	private List<Role> roles = null;

	public String reinit() {
		role = new Role();

		return null;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Role> getRoles() {
		if (roles == null) {
			roles = roleDAO.findAll();
		}
		return roles;
	}

	public void save() {
		if (role != null) {
			this.roleDAO.persist(role);
		}
	}

	public void remove() {
		if (role != null) {
			this.roleDAO.remove(role);
		}
	}

}
