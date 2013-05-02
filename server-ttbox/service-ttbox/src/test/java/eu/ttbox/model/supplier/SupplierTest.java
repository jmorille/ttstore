package eu.ttbox.model.supplier;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class SupplierTest {

	@PersistenceContext
	EntityManager entityManager;

	@Test
	@Transactional
	public void testInsert() {
		for (int i = 0; i < 10; i++) {
			Supplier supplier = new Supplier();
			supplier.setName("testName" + i);
			entityManager.persist(supplier);
			entityManager.flush();
			// Check valid id
			Assert.assertNotNull(supplier.getId());
			System.out.println(supplier);
		}

	}

}
