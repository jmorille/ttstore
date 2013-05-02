package es.icecat;

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
public class IcecatMappingEsTest {

	private static Logger log = LoggerFactory.getLogger(IcecatMappingEsTest.class);

	@Autowired
	Client client;

	@Rule
	@Autowired
	public ElasticInitializer elasticInitializer;

	@Test
	@ElasticInitializerSetting(indexName = "icecat", indexType = "full",pathSetting = "settings.json",  deleteAfter=false)
	@ElasticInitializerMapping(pathMapping = "mappings.json")
	@ElasticInitializerData(fileDatas = { "person-jp-u.json" }, pathRoot = "/data/firstname")
	public void showAnalysis() {
		for (String searchString : new String[] { "Wifi", "WiFi", "Wi-Fi","wi-fi-4000" ,"I-9100", "I9100", "Galaxy S2", "Galaxy SII", "j2se"}) {
			AnalyzeResponse response = client.admin().indices().prepareAnalyze("icecat", searchString).setAnalyzer("word_analyzer").execute().actionGet();
			int tokenSize = response.getTokens().size();
			int i = 0;
			for (AnalyzeToken token : response.getTokens()) {
				log.info("token {} / {} : {} = {}", new Object[] {++i, tokenSize, searchString, token.getTerm() });
			}
		}
	}

  

}