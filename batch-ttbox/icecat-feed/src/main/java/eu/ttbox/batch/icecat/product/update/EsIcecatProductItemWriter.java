package eu.ttbox.batch.icecat.product.update;

import eu.ttbox.icecat.model.IProductDetail;
import eu.ttbox.icecat.model.product.IcecatProduct;
import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.List;

@Service
public class EsIcecatProductItemWriter implements ItemWriter<IcecatProduct>, InitializingBean {

	private static Logger LOG = LoggerFactory.getLogger(EsIcecatProductItemWriter.class);

	@Autowired
	Client esClient;

	@Value("${es.icecat.indexName}")
	String indexName;

	@Value("${es.icecat.indexType}")
	String indexType;

	int bufferSize = 20480;

	// Local Initialisation
	private ObjectMapper mapper;

	private ActionListener<BulkResponse> bulkListener = new ActionListener<BulkResponse>() {

		@Override
		public void onResponse(BulkResponse response) {
			LOG.info("index {} requests success in {} : [{}]", new Object[] { response.getItems().length, response.getTook(), response.buildFailureMessage() });
		}

		@Override
		public void onFailure(Throwable ex) {
			LOG.error(ex.getMessage(), ex);
		}
	};

	@Override
	public void afterPropertiesSet() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.getSerializationConfig().addMixInAnnotations(IcecatProduct.class, IProductDetail.class);
		this.mapper = mapper;
	}

	@Override
	public void write(List<? extends IcecatProduct> items) throws Exception {
		BulkRequestBuilder bulk = esClient.prepareBulk();
		for (IcecatProduct entity : items) {
			// Create JSon
			StringWriter sb = new StringWriter(bufferSize);
			mapper.writeValue(sb, entity);
			// Check Buffer Size
			if (sb.getBuffer().capacity() > bufferSize) {
				LOG.warn("BufferSize is too Small of {}, need to increase to {}", bufferSize, sb.getBuffer().capacity());
			}
			// Prepare request
			String entityId = entity.getId().toString();
			IndexRequestBuilder request = esClient.prepareIndex(indexName, indexType, entityId);
			request.setSource(sb.getBuffer().toString());
			bulk.add(request);
		}
		if (bulk.numberOfActions() > 0) {
			bulk.execute(bulkListener);
		}
	}


}
