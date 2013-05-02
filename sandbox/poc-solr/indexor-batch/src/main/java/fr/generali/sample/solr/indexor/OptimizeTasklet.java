package fr.generali.sample.solr.indexor;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class OptimizeTasklet implements Tasklet {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("solrServer")
	SolrServer solrServer;

	/**
	 * @param args
	 * @throws IOException
	 * @throws SolrServerException
	 */
	public static void main(String[] args) throws SolrServerException, IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:/fr/generali/sample/solr/indexor/*.xml");
		OptimizeTasklet service = (OptimizeTasklet) context.getBean("optimizeTasklet");
		service.optimize();

	}

	public void optimize() throws SolrServerException, IOException {
		solrServer.optimize(false, false);
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		solrServer.optimize(false, false);
		return RepeatStatus.FINISHED;
	}
}
