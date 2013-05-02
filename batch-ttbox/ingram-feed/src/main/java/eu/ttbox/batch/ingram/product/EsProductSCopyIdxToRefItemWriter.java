package eu.ttbox.batch.ingram.product;

import java.util.List;

import javax.annotation.PostConstruct;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.action.bulk.BulkRequestBuilder;
import org.elasticsearch.client.action.index.IndexRequestBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import eu.ttbox.batch.ingram.dao.converter.ProductConverter;

@Service
public class EsProductSCopyIdxToRefItemWriter implements ItemWriter<String> {

	private static final Logger LOG = LoggerFactory.getLogger(EsProductSCopyIdxToRefItemWriter.class);

	@Autowired
	ProductConverter productConverter;

	@Autowired
	Client esClient;

	@Value("${es.icecat.indexName}")
	String indexName;

	@Value("${es.icecat.indexType}")
	String indexType;

	@Value("${es.icecat.all.indexName}")
	String allIndexName;

	@Value("${es.icecat.all.indexType}")
	String allIndexType;

	long timeoutMillis = 1000;

	private ActionListener<BulkResponse> listener = new ActionListener<BulkResponse>() {

		@Override
		public void onResponse(BulkResponse response) {
			LOG.debug("Index succefully of {} items in {} ms.", response.items().length, response.getTookInMillis());
		}

		@Override
		public void onFailure(Throwable e) {
			LOG.error("Error in indexing : " + e.getMessage(), e);
		}
	};

	@PostConstruct
	public void afterPropertiesSet() throws Exception {
		Assert.hasText(indexName, "indexName is mandatory");
		Assert.hasText(indexType, "indexType is mandatory");
		Assert.hasText(allIndexName, "allIndexName is mandatory");
	}

	@Override
	public void write(List<? extends String> items) {
		BulkRequestBuilder batch = esClient.prepareBulk();
		for (String icecatProductId : items) {
			boolean toCopy = true;
			// TODO GetResponse productExist = esClient.prepareGet(indexName, indexType, icecatProductId).execute().actionGet(timeoutMillis);
			// toCopy = !productExist.exists();
			if (toCopy) {
				GetResponse productCopy = esClient.prepareGet(allIndexName, allIndexType, icecatProductId).execute().actionGet(timeoutMillis);
				if (productCopy.exists()) {
					IndexRequestBuilder indexRequest = esClient.prepareIndex(indexName, indexType, icecatProductId);
					indexRequest.setSource(productCopy.source());
					batch.add(indexRequest);
				}
			}
		}
		// Send to Index
		if (batch.numberOfActions() > 0) {
			batch.execute(listener);
		}
	}

}
