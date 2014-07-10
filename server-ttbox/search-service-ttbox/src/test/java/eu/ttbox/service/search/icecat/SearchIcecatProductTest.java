package eu.ttbox.service.search.icecat;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import eu.ttbox.service.search.elasticsearch.helper.ElasticInitializer;
import eu.ttbox.service.search.elasticsearch.helper.ElasticInitializerData;
import eu.ttbox.service.search.elasticsearch.helper.ElasticInitializerSetting;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class SearchIcecatProductTest {

	private static Logger log = LoggerFactory.getLogger(SearchIcecatProductTest.class);

	@Autowired
	Client esClient;

	@Rule
	@Autowired
	public ElasticInitializer elasticInitializer;
	
	
	
	
	@Test
	@ElasticInitializerSetting(indexName = "icecat-test", indexType = "test",  deleteAfter=false)
 	@ElasticInitializerData(fileDatas = { "eans-search.json" }, pathRoot = "/data/icecat")	
	public void searchByean() {
		String indexName="icecat-test";
		String indexType="test";
		String ean = "2000006035103"; 
 		// Search
		TermQueryBuilder query = QueryBuilders.termQuery("eans", ean);
		SearchResponse response = esClient.prepareSearch(indexName).setQuery(query).setSize(1).execute().actionGet();
		SearchHits hits = response.getHits();
		// Check result
		Assert.assertEquals(1, hits.totalHits() );
		if (hits.totalHits() == 1) {
			SearchHit hit = hits.hits()[0];
			log.info("Search ean {} and result {}", ean, hit.sourceAsString());
		}  
	}
}
