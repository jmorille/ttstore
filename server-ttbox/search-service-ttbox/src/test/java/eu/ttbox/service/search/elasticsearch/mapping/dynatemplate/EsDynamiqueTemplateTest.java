package eu.ttbox.service.search.elasticsearch.mapping.dynatemplate;

import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse.AnalyzeToken;
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
import eu.ttbox.service.search.elasticsearch.helper.ElasticInitializerDatas;
import eu.ttbox.service.search.elasticsearch.helper.ElasticInitializerMapping;
import eu.ttbox.service.search.elasticsearch.helper.ElasticInitializerMappings;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class EsDynamiqueTemplateTest {

	private static Logger log = LoggerFactory.getLogger(EsDynamiqueTemplateTest.class);

	@Autowired
	Client client;

	@Rule
	@Autowired
	public ElasticInitializer elasticInitializer;

 

	/**
	 * TODO Howto search in All
	 */
	@Test
	@ElasticInitializerSetting(indexName = "dynatemp", indexType = "france", pathSetting = "settings.json", deleteBefore = true, deleteAfter = false)
 	@ElasticInitializerMapping(pathMapping = "mappings.json", indexType = "france")  
	@ElasticInitializerData(indexType = "france", fileDatas = { "person-fr-f.json", "person-fr-m.json", "person-fr-u.json" }, pathRoot = "/data/firstname")  
	public void searchTest() throws Exception {
		// Create Index
		String indexName = "dynatemp";

		// Do a seach
		for (String search : new String[] { "guilaume", "guyaume", "Gerôme", "Jérôme", "Ophélie", "sophie" }) {
			QueryBuilder queryBuilder = QueryBuilders.termQuery("_all", search);
			SearchResponse response = client.prepareSearch(indexName).setQuery(queryBuilder).addSort(SortBuilders.scoreSort()).execute().actionGet();
			log.info("Elastic search {} : {} items in {}", new Object[] { search, response.getHits().totalHits(), response.took() });
			int i = 0;
			for (SearchHit hit : response.getHits()) {
				log.info("Phonetic {} : {} =  {}", new Object[] { ++i, search, hit.sourceAsString() });
			}
		}
		// Destroy index
		// deleteIndex(indexName);
	}

}
