package eu.ttbox.batch.icecat.indexor;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.client.action.admin.indices.mapping.put.PutMappingRequestBuilder;
import org.elasticsearch.common.base.Strings;
import org.elasticsearch.common.io.Streams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.Resource;

import eu.ttbox.batch.icecat.product.index.IcecatIndexFileItemWriter;

public class EsIndexCreateTasklet implements Tasklet {

	private static Logger LOG = LoggerFactory.getLogger(IcecatIndexFileItemWriter.class);

	private Client esClient;

	private String indexName;

	private String indexType;

	private String encoding = "UTF-8";

	private Resource mappings;

	private Resource settings;

	public void setEsClient(Client elasticClient) {
		this.esClient = elasticClient;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public void setIndexType(String indexType) {
		this.indexType = indexType;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public void setMappings(Resource mappings) {
		this.mappings = mappings;
	}

	public void setSettings(Resource settings) {
		this.settings = settings;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		createSetting(indexName);
		createMapping(indexName, indexType);
		return RepeatStatus.FINISHED;
	}

	public void createSetting(String indexName) {
		LOG.debug("Create setting from {}", indexName);
		String settingContent = copyToStringFromClasspath(settings, encoding);
		// log.info(source);
		CreateIndexRequestBuilder createIndx = esClient.admin().indices().prepareCreate(indexName);
		if (!Strings.isNullOrEmpty(settingContent)) {
			createIndx.setSettings(settingContent);
		}
		CreateIndexResponse response = createIndx.execute().actionGet();
	}

	public void createMapping(String indexName, String indexType) {
		String mappingContent = copyToStringFromClasspath(mappings, encoding);
		if (mappingContent == null || mappingContent.length() < 1) {
			return;
		}
		// Create Request
		PutMappingRequestBuilder request = esClient.admin().indices().preparePutMapping(indexName);
		request.setSource(mappingContent).setType(indexType);

		PutMappingResponse response = request.execute().actionGet();
		LOG.debug("Create Mapping for {} / {} ", indexName, indexType);
	}

	public String copyToStringFromClasspath(Resource path, String encoding) {
		String fileEncoding = encoding;
		String result = null;
		if (Strings.isNullOrEmpty(fileEncoding)) {
			fileEncoding = "UTF-8";
		}
		if (path != null) {
			try {
				InputStream is = path.getInputStream();
				result = Streams.copyToString(new InputStreamReader(is, fileEncoding));
				is.close();
			} catch (Exception e) {
				throw new RuntimeException("Could not read " + path + "(" + fileEncoding + ")" + " : " + e.getMessage(), e);
			}
		}
		return result;
	}

}
