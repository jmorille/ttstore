package eu.ttbox.service.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.granite.messaging.amf.io.util.externalizer.annotation.IgnoredProperty;
import org.granite.messaging.service.annotations.RemoteDestination;
import org.granite.tide.annotations.TideEnabled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import eu.ttbox.model.product.Product;
import eu.ttbox.model.user.Role;

@Service("roleService")
@Transactional
@TideEnabled
@RemoteDestination(id = "roleService", source = "roleService",  channel = "graniteamf", securityRoles={"ROLE_ADMIN"})
public class RoleService {

	@IgnoredProperty
	Logger log = LoggerFactory.getLogger(getClass());

	@IgnoredProperty
	@PersistenceContext
	EntityManager entityManager;

	@Transactional(propagation = Propagation.REQUIRED) 
	public void persist(Role entity) {
		entityManager.persist(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void merge(Role entity) {
		entityManager.merge(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(Role entity) {
		Role entityLocal = entityManager.merge(entity);
		entityManager.remove(entityLocal);
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Product> getAll() {
		Query q = entityManager.createQuery("Select e from Role e");
		return q.getResultList();
	}

}
