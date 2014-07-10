package eu.ttbox.service.search.elasticsearch.index.remapping;

import junit.framework.Assert;

import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.count.CountResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.cluster.metadata.AliasAction;
import org.elasticsearch.cluster.metadata.AliasAction.Type;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
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
import eu.ttbox.service.search.elasticsearch.helper.ElasticInitializerAlias;
import eu.ttbox.service.search.elasticsearch.helper.ElasticInitializerClean;
import eu.ttbox.service.search.elasticsearch.helper.ElasticInitializerData;
import eu.ttbox.service.search.elasticsearch.helper.ElasticInitializerMapping;
import eu.ttbox.service.search.elasticsearch.helper.ElasticInitializerSetting;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class EsUpdateMappingTest {

	private static Logger log = LoggerFactory.getLogger(EsUpdateMappingTest.class);

	@Autowired
	Client client;

	@Rule
	@Autowired
	public ElasticInitializer elasticInitializer;

	/**
	 * TODO Howto search in All
	 */
	@Test
	@ElasticInitializerSetting(indexName = "idx0", indexType = "france", pathSetting = "settings.json", deleteBefore = true, deleteAfter = false,
	aliases=@ElasticInitializerAlias( "idxsearch"))
	@ElasticInitializerClean(indexName="idx1", deleteAfter=false)
	@ElasticInitializerMapping(pathMapping = "mappings.json")
	@ElasticInitializerData(fileDatas = { "person-fr-f.json", "person-fr-m.json", "person-fr-u.json" }, pathRoot = "/data/firstname")
	public void updateMappingTest() throws Exception {
		// Create Index
		String indexName = "idx0";
		String indexType = "france";
		String indexNameCopy = "idx1";
		long searchOriOnAlias = testSearchOnIndex("idxsearch");
		// Alias
//		client.admin().indices().prepareAliases().addAlias("idx0", "idxsearch").execute().actionGet();
		elasticInitializer.createSetting(indexNameCopy, elasticInitializer.copyToStringFromClasspath(this, "settings.json", "UTF-8"));
//		elasticInitializer.createMapping(indexNameCopy, indexType,  elasticInitializer.copyToStringFromClasspath(this, "mappings.json", "UTF-8"));
		
		// Update Mapping
        XContentBuilder mappingBuilder = XContentFactory.jsonBuilder().startObject().startObject(indexType).startObject("properties")
                .startObject("name").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
                .startObject("description").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
                .endObject().endObject().endObject();
		PutMappingResponse mappingResponse =  client.admin().indices().preparePutMapping(indexNameCopy).setType(indexType).setSource(mappingBuilder).setIgnoreConflicts(false).execute().actionGet();

		// Reindex
		for (String dataFile : new String[] {"person-fr-f.json", "person-fr-m.json", "person-fr-u.json"  }) {
			byte[] datas = elasticInitializer.copyToByteArrayFromClasspath(this, "/data/firstname/" +dataFile );
			elasticInitializer.loadDatas(indexNameCopy, indexType, datas);
		} 
		
		// Move alias
		//TODO
		//client.admin().indices().prepareAliases().addAliasAction(new AliasAction(Type.REMOVE, "idx0", "idxsearch")).addAlias("idx1", "idxsearch").execute().actionGet();
// CREATE DUPLICATED Alias		
		client.admin().indices().prepareAliases().addAlias("idx1", "idxsearch").removeAlias("idx0", "idxsearch").execute().actionGet();

		// Test search Alias
		long searchReindxOnAlias = testSearchOnIndex("idxsearch");
		Assert.assertEquals(searchOriOnAlias, searchReindxOnAlias);
		
	}
	
	private long testSearchOnIndex(String indexName) {
		QueryBuilder queryBuilder = QueryBuilders.queryString("F").field("sexe");
		CountResponse response =  client.prepareCount(indexName).setQuery(queryBuilder).execute().actionGet();
		return response.getCount();
	}
	
}
