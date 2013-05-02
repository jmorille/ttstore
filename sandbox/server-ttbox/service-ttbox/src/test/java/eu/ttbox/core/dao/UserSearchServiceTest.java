package eu.ttbox.core.dao;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ModelMap;

import eu.ttbox.model.IBoxPersistantModelObject;
import eu.ttbox.model.user.User;
import eu.ttbox.model.user.User_;
import eu.ttbox.service.bean.SearchCriteria;
import eu.ttbox.service.user.AbstractDbServiceTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class UserSearchServiceTest extends AbstractDbServiceTest {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
    @Qualifier("userSearchService")
	UserSearchService userSearchService;

	private void checkSearchResult(ModelMap result) {
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.get(ISearchService.RESULT_FIRST_KEY));
		// Assert.assertNotNull(result.get(ISearchService.RESULT_MAX_KEY));
		Assert.assertNotNull(result.get(ISearchService.RESULT_COUNT_KEY));
		Assert.assertNotNull(result.get(ISearchService.RESULT_LIST_KEY));
	}

	@Test
	public void testList() {
		SearchCriteria searchCriteria = null;
		Integer first = null;
		Integer max = null;
		String[] order = null;
		Boolean[] desc = null;
		ModelMap result = userSearchService.list(searchCriteria, first, max, order, desc);
		// Check Result
		checkSearchResult(result);
	}

	private ISearchService getSearchService() {
		return userSearchService;
	}

	private void checkOrderById(List<IBoxPersistantModelObject> entities, boolean desc) {
		Long lastId = desc ? Long.MAX_VALUE : Long.MIN_VALUE;

		for (IBoxPersistantModelObject entity : entities) {
			Long currentId = entity.getId();
			int compare = currentId.compareTo(lastId);
			log.info(" * id : {} ==> compare {}", currentId, compare);
			if (desc) {
				Assert.assertTrue(compare <= 0);
			} else {
				Assert.assertTrue(compare >= 0);
			}
			lastId = currentId;
		}

	}

	@Test
	@SuppressWarnings("unchecked")
	public void testListWithOrderByIdAscWithNoDesc() {
		SearchCriteria searchCriteria = null;
		Integer first = null;
		Integer max = null;
		String[] order = new String[] { User_.id.getName() };
		Boolean[] desc = null;
		ModelMap result = userSearchService.list(searchCriteria, first, max, order, desc);
		// Check Result
		checkSearchResult(result);
		// Check Order Asc
		List<IBoxPersistantModelObject> entities = (List<IBoxPersistantModelObject>) result
				.get(ISearchService.RESULT_LIST_KEY);
		checkOrderById(entities, false);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testListWithOrderByIdAsc() {
		SearchCriteria searchCriteria = null;
		Integer first = null;
		Integer max = null;
		String[] order = new String[] { User_.id.getName() };
		Boolean[] desc = new Boolean[] { Boolean.FALSE };
		ModelMap result = userSearchService.list(searchCriteria, first, max, order, desc);
		// Check Result
		checkSearchResult(result);
		// Check Order Asc
		log.info("Test search by id Asc");
		List<IBoxPersistantModelObject> entities = (List<IBoxPersistantModelObject>) result
				.get(ISearchService.RESULT_LIST_KEY);
		checkOrderById(entities, false);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testListWithOrderByIdAscNullAndDesc() {
		SearchCriteria searchCriteria = null;
		Integer first = null;
		Integer max = null;
		String[] order = new String[] { User_.id.getName(), User_.firstName.getName() };
		Boolean[] desc = new Boolean[] { null, Boolean.TRUE };
		ModelMap result = userSearchService.list(searchCriteria, first, max, order, desc);
		// Check Result
		checkSearchResult(result);
		// Check Order Asc
		log.info("Test search by id Asc");
		List<IBoxPersistantModelObject> entities = (List<IBoxPersistantModelObject>) result
				.get(ISearchService.RESULT_LIST_KEY);
		checkOrderById(entities, false);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testListWithOrderByIdDesc() {
		SearchCriteria searchCriteria = null;
		Integer first = null;
		Integer max = null;
		String[] order = new String[] { User_.id.getName() };
		Boolean[] desc = new Boolean[] { Boolean.TRUE };
		ModelMap result = userSearchService.list(searchCriteria, first, max, order, desc);
		// Check Result
		checkSearchResult(result);
		// Check Order Asc
		log.info("Test search by id Desc");
		List<IBoxPersistantModelObject> entities = (List<IBoxPersistantModelObject>) result
				.get(ISearchService.RESULT_LIST_KEY);
		checkOrderById(entities, true);
	}

	@Test
	public void testListWithOrder() {
		SearchCriteria searchCriteria = null;
		Integer first = null;
		Integer max = null;
		String[] order = new String[] { User_.lastName.getName() };
		Boolean[] desc = null;
		ModelMap result = userSearchService.list(searchCriteria, first, max, order, desc);
		// Check Result
		checkSearchResult(result);
		// Check Order
		List<User> users = (List<User>) result.get(ISearchService.RESULT_LIST_KEY);
		String lastLastName = "";
		for (User user : users) {
			String currentLastName = user.getLastName();
			int compare = currentLastName.compareTo(lastLastName);
			Assert.assertTrue(compare >= 0);
			lastLastName = currentLastName;
		}
	}

}
