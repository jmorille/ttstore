package eu.ttbox.batch.core.elasticsearch.writer;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.optimize.OptimizeRequestBuilder;
import org.elasticsearch.action.admin.indices.optimize.OptimizeResponse;
import org.elasticsearch.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class EsOptimizerTasklet implements Tasklet {

	private static final Logger LOG = LoggerFactory.getLogger(EsOptimizerTasklet.class);

	
	private Client esClient;
	
	private String indexName;
	
	
	public void setEsClient(Client esClient) {
		this.esClient = esClient;
	}


	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}


	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		OptimizeRequestBuilder builder =   esClient.admin().indices().prepareOptimize(indexName);
		builder.execute(new ActionListener<OptimizeResponse>() { 
			@Override
			public void onResponse(OptimizeResponse response) {
				LOG.info("Optimize {} / {} => Failure {}", new Object[] { response.getSuccessfulShards(), response.getTotalShards(), response.getFailedShards()});
 			}
			
			@Override
			public void onFailure(Throwable e) {
				LOG.error("Optimize Failure : "+ e.getMessage(), e);
			}
		});
		return RepeatStatus.FINISHED;
	}

}
