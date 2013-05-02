package eu.ttbox.web.admin.user;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import eu.ttbox.model.user.Role;


//@Controller
@ManagedBean 
@RequestScoped
@Transactional
public class RoleManagedBean {

	static Logger LOG = LoggerFactory.getLogger(RoleManagedBean.class);
 
	//@PersistenceUnit
	//EntityManagerFactory emf;
	//emf.createEntityManager();
	
	@Resource(name="#{entityManagerFactory}")
	//@ManagedProperty("#{entityManagerFactory}")
	public EntityManager entityManager;
 
 
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	private Role currentRole;

	/** Data link to table **/
	private DataModel<Role> roles;

	/**
	 * Initialize data for screen
	 */
	public RoleManagedBean() {
		super();
		currentRole = new Role();
		currentRole.setId(Long.valueOf(1));
		currentRole.setDescription("MyName");
	}

	@PostConstruct
	public void init() {
		LOG.info("Init()");
		LOG.info("EntityManager = " + entityManager);
		reload();
	}

	public void reload() {
		LOG.info("roleEJB.getAll()");
  		Query query = entityManager.createNamedQuery("Role.findAll");
		List<Role> result = (List<Role>) query.getResultList();
  		this.roles = new ListDataModel<Role>(result);
	}

	public String addRole() {
		LOG.info("Save entity : " + currentRole);
		entityManager.persist(currentRole);
		// roleEJB.save(currentRole);
		reload();
		currentRole = new Role();
		return "";
	}

	// Coder ici la suppression

	/** GETTER AND SETTER **/

	public Role getCurrentRole() {
		return currentRole;
	}

	public void setCurrentRole(Role currentRole) {
		this.currentRole = currentRole;
	}

	public DataModel<Role> getRoles() {
		return roles;
	}

	public void setRoles(DataModel<Role> roles) {
		this.roles = roles;
	}

}
