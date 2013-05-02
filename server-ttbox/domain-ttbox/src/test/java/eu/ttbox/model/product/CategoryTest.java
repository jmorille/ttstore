package eu.ttbox.model.product;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import eu.ttbox.model.product.category.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class CategoryTest {

	Logger log = LoggerFactory.getLogger(getClass());
	
	@PersistenceContext
	EntityManager entityManager;

	@Test
	@Transactional
	public void testInsert() {
		for (int i = 0; i < 10; i++) {
			Category category = new Category();
			category.setName("testName" + i);
			entityManager.persist(category);
			entityManager.flush();
			// Check valid id
			Assert.assertNotNull(category.getId());
			System.out.println(category);
		}
	}

	
	@Test
	@Transactional
	public void testInsertWithMapping() {
		for (int i = 0; i < 3; i++) {
			Category category = new Category();
			category.setName("testName" + i);
			category.addSrc(CatalogSrcEnum.ICECAT, "100"+i, null);
			category.addSrc(CatalogSrcEnum.TECHDATA, "200"+i, null);
			entityManager.persist(category);
			entityManager.flush();
			// Check valid id
			Assert.assertNotNull(category.getId());
			System.out.println(category);
			// Test Find Mapping
			Category findCat = entityManager.find(Category.class, category.getId());
			Assert.assertNotNull(findCat);
			Assert.assertEquals(category.getId(), findCat.getId());
			Assert.assertEquals(2, findCat.getSrcs().size());
			// Test Remove Mapping
		}
		// Serach Category by Src_Id
		log.info("Test search by Mapping");
		TypedQuery<Category> query = entityManager.createQuery("select c from Category c inner join c.srcs src where src.source=:source", Category.class);
		query.setParameter("source", CatalogSrcEnum.ICECAT);
		List<Category> searchResult =  query.getResultList();
		Assert.assertEquals(3, searchResult.size());
		
	}
}
