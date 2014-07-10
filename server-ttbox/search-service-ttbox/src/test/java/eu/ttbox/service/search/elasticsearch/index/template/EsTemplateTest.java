package eu.ttbox.service.search.elasticsearch.index.template;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortBuilders;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import eu.ttbox.service.search.elasticsearch.helper.ElasticInitializer;
import eu.ttbox.service.search.elasticsearch.helper.ElasticInitializerSetting;
import eu.ttbox.service.search.elasticsearch.helper.ElasticInitializerData;
import eu.ttbox.service.search.elasticsearch.helper.ElasticInitializerMapping;
import eu.ttbox.service.search.elasticsearch.helper.ElasticInitializerTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class EsTemplateTest {

	private static Logger log = LoggerFactory.getLogger(EsTemplateTest.class);

	@Autowired
	Client client;

	@Rule
	@Autowired
	public ElasticInitializer elasticInitializer;

	 
	@Test
	@ElasticInitializerSetting(indexName = "test_index", indexType = "france",  deleteBefore = true, deleteAfter = true)
	@ElasticInitializerMapping(pathMapping="mappings.json")
	@ElasticInitializerTemplate(templateName = "template_1", templatePattern = "tes*", pathSetting="templateSettings.json" 
	 , mapping =  @ElasticInitializerMapping(pathMapping = "templateMappings.json"  ) 
	)
	@ElasticInitializerData(  fileDatas = {  "person-fr-f.json" }, pathRoot = "/data/firstname")
	public void searchTest() throws Exception {
		// Create Index
		String indexName = "test_index"; 
		
		// Do a search
		for (String search : new String[] { "sophie", "ofélie", "lydia" }) {
			QueryBuilder queryBuilder = QueryBuilders.queryString(search).field("name.phonetic" );
			SearchResponse response = client.prepareSearch(indexName).setQuery(queryBuilder).addSort(SortBuilders.scoreSort()).execute().actionGet();
			log.info("Elastic search {} : {} items in {}", new Object[] { search, response.getHits().totalHits(), response.getTook() });
			int i = 0;
			for (SearchHit hit : response.getHits()) {
				log.info("Phonetic {} : {} =  {}", new Object[] { ++i, search, hit.sourceAsString() });
			}
		}
		// Destroy index
		// deleteIndex(indexName);
	}

	
	
	
}
