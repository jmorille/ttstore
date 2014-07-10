package eu.ttbox.service.search.elasticsearch.index.template;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

import java.util.Arrays;

import junit.framework.Assert;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import eu.ttbox.service.search.elasticsearch.helper.ElasticInitializer;
import static org.elasticsearch.index.query.QueryBuilders.*;
//import static org.hamcrest.MatcherAssert.*;
//import static org.hamcrest.Matchers.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class EsFirstnameTemplateTest {

	private static Logger logger = LoggerFactory.getLogger(EsFirstnameTemplateTest.class);

	@Autowired
	Client client;

	@Rule
	@Autowired
	public ElasticInitializer elasticInitializer;

	@Test public void simpleIndexTemplateTests() throws Exception {
        clean();
        String indexName  ="test_index";
        String indexType = "france";
        
        XContentBuilder mappingBuilder = XContentFactory.jsonBuilder().startObject().startObject(indexType).startObject("properties")
                .startObject("country").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
                .startObject("sexe").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()
                .endObject().endObject().endObject();
        
          client.admin().indices().preparePutTemplate("template_1")
                .setTemplate("te*")
                .setOrder(0)
                .addMapping(indexType, mappingBuilder)
                .execute().actionGet();

        
        // index something into test_index, will match on both templates
        client.prepareIndex(indexName, "france", "1").setSource("name", "Sophie", "country", "FR", "sexe", "F").setRefresh(true).execute().actionGet();

//        client.admin().cluster().prepareHealth().setWaitForGreenStatus().execute().actionGet();

        SearchResponse searchResponse = client.prepareSearch(indexName)
                .setQuery(termQuery("country", "FR"))
                .addField("country").addField("sexe")
                .execute().actionGet();
        if (searchResponse.getFailedShards() > 0) {
            logger.warn("failed search " + Arrays.toString(searchResponse.getShardFailures()));
        }
//        clean()	;
    }

    private void clean() {
        try {
            client.admin().indices().prepareDelete("test_index").execute().actionGet();
        } catch (Exception e) {
            // ignore
        }
        try {
            client.admin().indices().prepareDelete("text_index").execute().actionGet();
        } catch (Exception e) {
            // ignore
        }
        try {
            client.admin().indices().prepareDeleteTemplate("template_1").execute().actionGet();
        } catch (Exception e) {
            // ignore
        }
        try {
            client.admin().indices().prepareDeleteTemplate("template_2").execute().actionGet();
        } catch (Exception e) {
            // ignore
        }  
        
        
    }
	
}
