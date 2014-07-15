package eu.ttbox.batch.core.elasticsearch.writer;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class ElasticsearchItemWriter implements ItemWriter<ElasticPersonItem> {

	private static Logger log = LoggerFactory.getLogger(ElasticsearchItemWriter.class);

	Client client;

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public void write(List<? extends ElasticPersonItem> items) throws Exception {

		// Prepare Index
		BulkRequestBuilder bulkRequest = client.prepareBulk();
		for (ElasticPersonItem item : items) {
			bulkRequest.add(client.prepareIndex(item.getIndex(), item.getType(), item.getId()).setSource(
					item.getContent()));

		}
		// Send Bulk
		BulkResponse bulkResponse = bulkRequest.execute().actionGet();
		if (bulkResponse.hasFailures()) {
			// process failures by iterating through each bulk response item
			log.error(bulkResponse.buildFailureMessage());
		} else {
			log.info("Elasticsearch Index succefully in {} ms.", bulkResponse.getTookInMillis());
		}
	}

}
