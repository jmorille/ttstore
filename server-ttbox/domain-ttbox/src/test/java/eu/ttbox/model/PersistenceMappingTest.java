package eu.ttbox.model;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import eu.ttbox.model.salespoint.Salespoint;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class PersistenceMappingTest extends AbstractPersistenceMappingTest {

 	@PersistenceContext
	EntityManager entityManager;

 
	@Test
	public void testMappingPersonne() throws Exception {
		Query query = entityManager.createQuery("from Salespoint");
		List<Salespoint> result = query.getResultList();
		Assert.assertNotNull(result);
		Assert.assertEquals(0, result.size());
		// Check Table less than 32
		List<Map<String, String>> tables = printSqlTableName();
		checkTableNameSize(tables, 32);
		log.info("Number of table generated is {} tables", tables.size());
		
		// Check Column name less than 32
		List<Map<String, String>> columns =	printSqlTableNameColumn(null);
		checkTableColumnNameSize(columns, 32);
		
	}
 

}
