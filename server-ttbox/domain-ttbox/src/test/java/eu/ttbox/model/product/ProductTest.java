package eu.ttbox.model.product;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class ProductTest {

	Logger log = LoggerFactory.getLogger(getClass());
	
	@PersistenceContext
	EntityManager entityManager;

	@Test
	@Transactional
	public void testFindNamedQuery() {
		String techProductId = "xxx";
		 Query query = entityManager.createNamedQuery("product.findByTechdataProductId");
		 query.setParameter(1, techProductId);
		 List<Product> products = query.getResultList();
	}

	@Test
	public void addSrc() {
		Product product = new Product();
		String icecatId = "20594";
		String cause = "BY_EAN"; 
		product.addSrc(CatalogSrcEnum.ICECAT, icecatId,cause);
		//
		Assert.assertEquals(icecatId,product.getSrc(CatalogSrcEnum.ICECAT).getSourceId() );
		Assert.assertEquals(cause,product.getSrc(CatalogSrcEnum.ICECAT).getCause());
		Assert.assertEquals(icecatId,product.getExtIcecatId());
	}
	
	 
}
