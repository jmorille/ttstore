package eu.ttbox.core.listener;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.ttbox.model.IBoxUUID;
import eu.ttbox.model.catalog.Offer;
import eu.ttbox.model.catalog.OfferStock;
import eu.ttbox.model.pricing.Pricing;
import eu.ttbox.model.product.Product;
import eu.ttbox.model.salespoint.Holding;
import eu.ttbox.model.salespoint.Salespoint;

public class BoxUUIDInterceptorTest {

	Logger log = LoggerFactory.getLogger(getClass());

	private BoxUUIDInterceptor service;

	@Before
	public void setUp() throws Exception {
		service = new BoxUUIDInterceptor();
	}

	@Test
	public void testOnPostPersist() {
		IBoxUUID[] entities = new IBoxUUID[] { new Holding(), new Salespoint(),
				new Pricing(), new Product(), new Offer(), new OfferStock() };
		for (IBoxUUID entity : entities) {
			checkKeepUID(checkGenerateNewUUID(entity));
		}
	}

	private IBoxUUID checkGenerateNewUUID(IBoxUUID entity) {
		service.onPostPersist(entity);
		String uuid = entity.getUid();
		Assert.assertNotNull(uuid);
		log.info("Generated UUID {} for class {}", uuid, entity.getClass());
		return entity;
	}

	private IBoxUUID checkKeepUID(IBoxUUID entity) {
		String uuidOri = entity.getUid();
		Assert.assertNotNull(uuidOri);
		service.onPostPersist(entity);
		String uuid = entity.getUid();
		Assert.assertNotNull(uuid);
		Assert.assertEquals(uuidOri, uuid);
		return entity;
	}

}
