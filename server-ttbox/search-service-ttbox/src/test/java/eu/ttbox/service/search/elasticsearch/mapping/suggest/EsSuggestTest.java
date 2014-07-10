package eu.ttbox.service.search.elasticsearch.mapping.suggest;

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
public class EsSuggestTest {

	private static Logger log = LoggerFactory.getLogger(EsSuggestTest.class);

	@Autowired
	Client client;

	@Rule
	@Autowired
	public ElasticInitializer elasticInitializer;

	@Test
	@ElasticInitializerSetting(indexName = "suggest", indexType = "france", pathSetting = "settings.json")
	@ElasticInitializerMapping(pathMapping = "mappings.json")
	@ElasticInitializerData(fileDatas = { "person-jp-u.json" }, pathRoot = "/data/firstname")
	public void showAnalysis() {
		for (String searchString : new String[] { "Sophie", "Jérôme" }) {
			AnalyzeResponse response = client.admin().indices().prepareAnalyze("suggest", searchString).setAnalyzer("suggest_analyzer").execute().actionGet();
			int tokenSize = response.getTokens().size();
			int i = 0;
			for (AnalyzeToken token : response.getTokens()) {
				log.info("token {} / {} : {} = {}", new Object[] {++i, tokenSize, searchString, token.getTerm() });
			}

		}
	}

	/**
	 * @See http://stackoverflow.com/questions/120180/how-to-do-query-auto-
	 *      completion-suggestions-in-lucene
	 * @throws Exception
	 */
	@Test
	@ElasticInitializerSetting(indexName = "suggest", indexType = "france", pathSetting = "settings.json", deleteBefore = true, deleteAfter = false)
	@ElasticInitializerMappings({ @ElasticInitializerMapping(pathMapping = "mappings.json", indexType = "japon"),
			@ElasticInitializerMapping(pathMapping = "mappings.json", indexType = "france") })
	@ElasticInitializerDatas({
			@ElasticInitializerData(indexType = "japon", fileDatas = { "person-jp-f.json", "person-jp-m.json", "person-jp-u.json" }, pathRoot = "/data/firstname"),
			@ElasticInitializerData(indexType = "france", fileDatas = { "person-fr-f.json", "person-fr-m.json", "person-fr-u.json" }, pathRoot = "/data/firstname") })
	public void suggestTest() throws Exception {
		// Create Index
		String indexName = "suggest";

		// Do a seach
		for (String search : new String[] { "s", "so", "sop", "soph", "sophi", "sophie" }) {
			QueryBuilder queryBuilder = QueryBuilders.termQuery("name.suggest", search);
			SearchResponse response = client.prepareSearch(indexName).setQuery(queryBuilder).addSort(SortBuilders.scoreSort()).execute().actionGet();
			log.info("Elastic search {} : {} items in {}", new Object[] { search, response.getHits().totalHits(), response.getTook() });
			int i = 0;
			for (SearchHit hit : response.getHits()) {
				log.info("Suggest {} : {} =  {}", new Object[] { ++i, search, hit.sourceAsString() });
			}
		}
		// Do search girl
		for (String search : new String[]  {"r" }) {
			QueryBuilder sexeQuery = QueryBuilders.termQuery("sexe", "F");
			QueryBuilder startQueryBuilder = QueryBuilders.termQuery("name.suggest", search);
			QueryBuilder queryBuilder =  QueryBuilders.boolQuery().must(sexeQuery).must(startQueryBuilder);
			SearchResponse response = client.prepareSearch(indexName).setQuery(queryBuilder).addSort(SortBuilders.scoreSort()).setSize(500).execute().actionGet();
			log.info("Elastic search {} : {} items in {}", new Object[] { search, response.getHits().totalHits(), response.getTook() });
			int i = 0;
			for (SearchHit hit : response.getHits()) {
				log.info("Suggest {} : {} =  {}", new Object[] { ++i, search, hit.sourceAsString() });
			}
		}
		
		
		// Destroy index
		// deleteIndex(indexName);
	}

}
