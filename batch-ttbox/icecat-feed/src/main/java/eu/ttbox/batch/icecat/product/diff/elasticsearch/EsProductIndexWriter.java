package eu.ttbox.batch.icecat.product.diff.elasticsearch;

import biz.icecat.files.v1.IcecatFile;
import eu.ttbox.batch.core.reader.differential.DifferentialItem;
import eu.ttbox.batch.core.reader.differential.DifferentialItem.CUDStatus;
import eu.ttbox.icecat.model.IProductIndex;
import eu.ttbox.icecat.model.product.IcecatProduct;
import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.InitializingBean;

import java.io.StringWriter;
import java.util.List;

public class EsProductIndexWriter implements ItemWriter<DifferentialItem<IcecatProduct, IcecatFile>>, InitializingBean {

	private static final Logger LOG = LoggerFactory.getLogger(EsProductIndexWriter.class);

	private Client esClient;

	private String indexName;

	private String indexType;

	int bufferSize = 20480;

	private ObjectMapper mapper;

	@Override
	public void afterPropertiesSet() throws Exception {
		// Configure Serialisation
		ObjectMapper mapper = new ObjectMapper();
		mapper.getSerializationConfig().addMixInAnnotations(IcecatProduct.class, IProductIndex.class);
		this.mapper = mapper;
	}

	public void setEsClient(Client elasticClient) {
		this.esClient = elasticClient;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public void setIndexType(String indexType) {
		this.indexType = indexType;
	}

	@Override
	public void write(List<? extends DifferentialItem<IcecatProduct, IcecatFile>> items) throws Exception {
		if (items == null || items.isEmpty()) {
			return;
		}
		LOG.debug("Ask item writing {}", items.size());
		// Prepare Index
		BulkRequestBuilder builder = esClient.prepareBulk();

		// Do Serialisation
		for (DifferentialItem<IcecatProduct, IcecatFile> item : items) {
			try {
				IcecatProduct product = item.getMasterItem();
				if (product != null) {

					String productId = product.getId().toString();
					if (CUDStatus.DELETE == item.getStatus()) {
						DeleteRequestBuilder request = esClient.prepareDelete(indexName, indexType, productId);
						builder.add(request);
					} else {
						StringWriter sb = new StringWriter(bufferSize);
						mapper.writeValue(sb, product);
						IndexRequestBuilder request = esClient.prepareIndex(indexName, indexType);
						request.setId(productId);
						request.setSource(sb.getBuffer().toString());
						builder.add(request);
						// Check Buffer Size
						if (sb.getBuffer().capacity() > bufferSize) {
							LOG.warn("BufferSize is too Small of {}, need to increase to {}", bufferSize, sb.getBuffer().capacity());
						}

					}
				}
			} catch (Exception e) {
				LOG.error("Error Json write " + e.getMessage(), e);
				throw e;
			}
		}
		if (builder.numberOfActions() > 0)
			builder.execute(new ActionListener<BulkResponse>() {

				@Override
				public void onResponse(BulkResponse response) {

					LOG.debug("index {} requests success in {}", response.getItems().length, response.getTook());
				}

				@Override
				public void onFailure(Throwable ex) {
					LOG.error(ex.getMessage(), ex);
				}
			});
	}

}
