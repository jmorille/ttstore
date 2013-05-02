package fr.generali.sample.solr.indexor;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("solrItemWriter")
public class SolrItemWriter implements ItemWriter<SolrInputDocument> {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("solrServer")
    SolrServer solrServer;

static int count = 0; 

    @Override
    public void write(List< ? extends SolrInputDocument> items) throws Exception {
        @SuppressWarnings("unchecked")
        List<SolrInputDocument> docs= ( List<SolrInputDocument>)items;
        // Add to SOlr 
        solrServer.add(docs);
        solrServer.commit(false, false); 
        int docSize = docs.size();
        count+=docSize;
        log.info("solrServer add {} docs / {} current totals", docs.size(), count);
    }
 

}
