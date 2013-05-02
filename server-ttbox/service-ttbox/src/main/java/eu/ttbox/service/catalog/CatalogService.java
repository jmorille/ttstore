package eu.ttbox.service.catalog;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import eu.ttbox.model.catalog.Catalog;

@Service("catalogService")
@Transactional 
public class CatalogService {

	
	Logger log = LoggerFactory.getLogger(getClass());

	
	@PersistenceContext
	EntityManager entityManager;

	@Transactional(propagation = Propagation.REQUIRED)
	public void persist(Catalog entity) {
		entityManager.persist(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void merge(Catalog entity) {
		entityManager.merge(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(Catalog entity) {
		entityManager.remove(entity);
	}

	@Transactional(readOnly = true)
	public List<Catalog> getAll() {

		CriteriaQuery<Catalog> criteriaQuery = entityManager
				.getCriteriaBuilder().createQuery(Catalog.class);
		Root<Catalog> c = criteriaQuery.from(Catalog.class);
		criteriaQuery.select(c).distinct(true);
		c.fetch("offers", JoinType.LEFT);

		TypedQuery<Catalog> q = entityManager.createQuery(criteriaQuery);
		List<Catalog> result = q.getResultList();

		log.info("Return Catalogs list : {} ", result.size());
		return result;
	}

}
