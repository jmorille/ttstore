package eu.ttbox.feed.techdata;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml", //
		"/jobs/techdata-tax.xml" //
})
public class TaxTechdataJobTest {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job job;

	@Test
	public void icecatFileIndexReader() throws Exception {
		System.out.println("Started Job " + job.getName());
		long begin = System.currentTimeMillis();
		JobExecution jobExecution = jobLauncher.run(job, new JobParameters());
		// JobExecution jobExecution = jobLauncherTestUtils.launchJob();
		long end = System.currentTimeMillis();
		log.info("Job end {} in {} ms.", jobExecution.getExitStatus(),
				(end - begin));
		Assert.assertEquals("COMPLETED", jobExecution.getExitStatus()
				.getExitCode());

	}
}
