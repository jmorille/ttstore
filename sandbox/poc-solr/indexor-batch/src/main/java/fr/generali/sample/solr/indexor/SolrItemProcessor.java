package fr.generali.sample.solr.indexor;

import java.util.Map;

import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class SolrItemProcessor implements ItemProcessor<Map<String, Object>, SolrInputDocument>{

    Logger log = LoggerFactory.getLogger(getClass());
    
    @Override
    public SolrInputDocument process(Map<String, Object> item) throws Exception {
        SolrInputDocument doc = new SolrInputDocument();
        for (String key : item.keySet()) {
            Object value = item.get(key);
            doc.addField(key, value);
          //  log.info("Line key={} ===> value={}", key, value);
        }
        return doc;
    }

}
