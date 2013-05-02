package eu.ttbox.model.salespoint;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class SalespointTest {

	

	Logger log = LoggerFactory.getLogger(getClass());
	
	@PersistenceContext
	EntityManager entityManager;

	@Test
	@Transactional
	public void testInsert() {
		for (int i = 0; i < 10; i++) {
			Salespoint salespoint = new Salespoint();
			salespoint.setName("testName" + i);
			entityManager.persist(salespoint);
			entityManager.flush();
			// Check valid id
			Assert.assertNotNull(salespoint.getId());
			log.info("Insert {} : {}",i,salespoint);
		} 

	}
	
}
