package eu.ttbox.server.admin.server.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.web.bindery.requestfactory.shared.Locator;

import eu.ttbox.model.salespoint.Salespoint;
import eu.ttbox.server.admin.server.locator.SpringServiceLocator;
import eu.ttbox.server.admin.server.model.SearchResult;

@Service
@Transactional
public class SalespointService extends Locator<Salespoint, Integer> {

	private static final Logger LOG = LoggerFactory.getLogger(SalespointService.class);

	@PersistenceContext
	EntityManager entityManager;

	public SearchResult searchSalespoints(int start, int pageSize) {
		LOG.info("SERVER Search searchSalespoints, start={} / pageSize={}", start, pageSize);
		// Count
		Query queryCount = entityManager.createQuery("select count(*) from Salespoint", Long.class);
		Long count = (Long) queryCount.getSingleResult();

		// List
		Query query = entityManager.createQuery("from Salespoint order by name", Salespoint.class);
		query.setMaxResults(pageSize).setFirstResult(start);
		List<Salespoint> entities = query.getResultList();
		SearchResult result = new SearchResult(entities, count.intValue(), start, pageSize);
		LOG.info("Search result {}", result);
		return result;
	}

	public Salespoint getSalespointById(Integer id) {
		LOG.info("Server Search Salaspoint Id={}", id);
		Salespoint result = entityManager.find(Salespoint.class, id);
		return result;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void saveSalespoint(Salespoint salespoint) {
		entityManager.merge(salespoint);
	}

	@Override
	public Salespoint create(Class<? extends Salespoint> clazz) {
		Salespoint entity = new Salespoint();
		return entity;
	}

	private void init() {
		EntityManagerFactory entityManagerFactory = SpringServiceLocator.applicationContext.getBean(EntityManagerFactory.class);
		entityManager = entityManagerFactory.createEntityManager();
	}

	@Override
	public Salespoint find(Class<? extends Salespoint> clazz, Integer id) {
		if (id == null) {
			return null;
		}
		LOG.info("find Salespoint with id {} and entityManager {}", id, entityManager);
		if (entityManager == null) {
			init();
			LOG.error("find Salespoint with id {} and entityManager {}", id, entityManager);
		}
		Salespoint entity = entityManager.find(Salespoint.class, id);
		return entity;
	}

	@Override
	public Class<Salespoint> getDomainType() {
		return Salespoint.class;
	}

	@Override
	public Integer getId(Salespoint domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<Integer> getIdType() {
		return Integer.class;
	}

	@Override
	public Object getVersion(Salespoint domainObject) {
		return domainObject.getVersion();
	}

}
