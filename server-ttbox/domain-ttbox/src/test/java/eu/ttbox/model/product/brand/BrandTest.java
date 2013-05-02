package eu.ttbox.model.product.brand;

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

import eu.ttbox.model.product.CatalogSrcEnum;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class BrandTest {

	Logger log = LoggerFactory.getLogger(getClass());

	@PersistenceContext
	EntityManager entityManager;


	@Test
	@Transactional
	public void testInsert() {
		for (int i = 0; i < 10; i++) {
			Brand brand = new Brand();
			brand.setName("testName" + i);
			brand.addSrc(CatalogSrcEnum.TECHDATA, "18787"+i, "toto");
			entityManager.persist(brand);
			entityManager.flush();
			// Check valid id
			Assert.assertNotNull(brand.getId());
			log.info("Insert {} : {}",i,brand);
		} 

	}
}
