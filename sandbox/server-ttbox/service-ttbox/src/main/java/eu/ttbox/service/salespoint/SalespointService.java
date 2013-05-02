package eu.ttbox.service.salespoint;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.granite.messaging.amf.io.util.externalizer.annotation.IgnoredProperty;
import org.granite.messaging.service.annotations.RemoteDestination;
import org.granite.tide.data.DataEnabled;
import org.granite.tide.data.DataEnabled.PublishMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import eu.ttbox.core.listener.ObserveAllPublishAll;
import eu.ttbox.model.salespoint.Salespoint;

@Service("salespointService")
@Transactional
@RemoteDestination(id = "salespointService", source = "salespointService", channel = "graniteamf", securityRoles = { "ROLE_ADMIN" })
@DataEnabled(topic = "dataTopic", params = ObserveAllPublishAll.class, publish = PublishMode.ON_SUCCESS)
public class SalespointService {

	@IgnoredProperty
	Logger log = LoggerFactory.getLogger(getClass());

	@IgnoredProperty
	@PersistenceContext
	EntityManager entityManager;

	@Transactional(propagation = Propagation.REQUIRED)
	public void persist(Salespoint entity) {
		entityManager.persist(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void merge(Salespoint entity) {
		entityManager.merge(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(Salespoint entity) {
		Salespoint entityLocal = entityManager.merge(entity);
		entityManager.remove(entityLocal);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Salespoint getById(Long id) {
		Salespoint entityLocal = entityManager.find(Salespoint.class, id);
		return entityLocal;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Salespoint> getAll() {
		Query q = entityManager.createQuery("Select e from Salespoint e order by e.name");
		return q.getResultList();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Salespoint> getByHoldingId(Long holdingId) {
		Query q = entityManager.createQuery("select s from Salespoint s where s.holding.id=:holdingId");
		q.setParameter("holdingId", holdingId);
		@SuppressWarnings("unchecked")
		List<Salespoint> entities = q.getResultList();
		return entities;
	}

}
