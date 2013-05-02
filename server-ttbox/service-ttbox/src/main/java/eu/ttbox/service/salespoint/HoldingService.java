package eu.ttbox.service.salespoint;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import eu.ttbox.model.salespoint.Holding;

@Service("holdingService")
@Transactional 
public class HoldingService {

	
	Logger log = LoggerFactory.getLogger(getClass());

	
	@PersistenceContext
	EntityManager entityManager;

	@Transactional(propagation = Propagation.REQUIRED)
	public void persist(Holding entity) {
		entityManager.persist(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void merge(Holding entity) {
		entityManager.merge(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(Holding entity) {
		Holding entityLocal = entityManager.merge(entity);
		entityManager.remove(entityLocal);
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Holding> getAll() {
		Query q = entityManager.createQuery("Select e from Holding e order by e.name");
		return q.getResultList();
	}

	
	@Transactional(propagation = Propagation.REQUIRED)
	public Holding getById(Long id) {
		Holding entityLocal = entityManager.find(Holding.class, id);
		return entityLocal;
	}
}
