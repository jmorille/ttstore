package eu.ttbox.service.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import eu.ttbox.model.product.Product;
import eu.ttbox.model.user.GroupRole;

@Service("groupRoleService")
@Transactional 
public class GroupRoleService {

	
	Logger log = LoggerFactory.getLogger(getClass());

	
	@PersistenceContext
	EntityManager entityManager;

	@Transactional(propagation = Propagation.REQUIRED)
	public void persist(GroupRole entity) {
		entityManager.persist(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void merge(GroupRole entity) {
		entityManager.merge(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(GroupRole entity) {
		GroupRole entityLocal = entityManager.merge(entity);
		entityManager.remove(entityLocal);
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Product> getAll() {
		Query q = entityManager.createQuery("Select e from GroupRole e");
		return q.getResultList();
	}

}
