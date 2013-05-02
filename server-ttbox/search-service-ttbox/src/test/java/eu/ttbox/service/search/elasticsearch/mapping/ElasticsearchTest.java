package eu.ttbox.service.search.elasticsearch.mapping;

import junit.framework.Assert;

import org.elasticsearch.action.count.CountResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
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
public class ElasticsearchTest {

	private static Logger log = LoggerFactory.getLogger(ElasticsearchTest.class);

	@Autowired
	Client elasticClient; 

	@Rule
	@Autowired
	public ElasticInitializer elasticInitializer;

	@Test
	@ElasticInitializerSetting(indexName = "person", indexType = "japon", pathSetting = "settings.json", deleteBefore=true, deleteAfter=false)
	@ElasticInitializerMappings({ 
			@ElasticInitializerMapping(pathMapping = "mappings.json", indexType = "japon"),
			@ElasticInitializerMapping(pathMapping = "mappings.json", indexType = "france") })
	@ElasticInitializerDatas({
			@ElasticInitializerData(indexType = "japon", fileDatas = { "person-jp-f.json", "person-jp-m.json", "person-jp-u.json" }, pathRoot = "/data/firstname"),
			@ElasticInitializerData(indexType = "france", fileDatas = { "person-fr-f.json", "person-fr-m.json", "person-fr-u.json" }, pathRoot = "/data/firstname")
		})
	public void indexatoinTest() throws Exception {
		// Create Index
		String indexName = "person";

		// Do a seach
		QueryBuilder queryBuilderF = QueryBuilders.termQuery("sexe", "F");
		CountResponse countResponseFemale = elasticClient.prepareCount(indexName).setQuery(queryBuilderF).setTypes("japon").execute().actionGet();
		log.info("Elasticsearch Count Female response in {} items.", countResponseFemale.count());
		Assert.assertEquals(351, countResponseFemale.count());

		QueryBuilder queryBuilderM = QueryBuilders.termQuery("sexe", "M");
		CountResponse countResponseMale = elasticClient.prepareCount(indexName).setQuery(queryBuilderM).setTypes("japon").execute().actionGet();
		log.info("Elasticsearch Count Male response in {} items.", countResponseMale.count());
		Assert.assertEquals(393, countResponseMale.count());

		// Destroy index
		// deleteIndex(indexName);

	}

}
