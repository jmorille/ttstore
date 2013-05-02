package eu.ttbox.service.catalog;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.granite.messaging.amf.io.util.externalizer.annotation.IgnoredProperty;
import org.granite.messaging.service.annotations.RemoteDestination;
import org.granite.tide.annotations.TideEnabled;
import org.granite.tide.data.DataEnabled;
import org.granite.tide.data.DataEnabled.PublishMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import eu.ttbox.core.listener.ObserveAllPublishAll;
import eu.ttbox.model.catalog.Catalog;

@Service("catalogService")
@Transactional
@TideEnabled
@RemoteDestination(id = "catalogService", source = "catalogService",   channel = "graniteamf")
@DataEnabled(topic = "dataTopic", params = ObserveAllPublishAll.class, publish = PublishMode.ON_SUCCESS)
public class CatalogService {

	@IgnoredProperty
	Logger log = LoggerFactory.getLogger(getClass());

	@IgnoredProperty
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
