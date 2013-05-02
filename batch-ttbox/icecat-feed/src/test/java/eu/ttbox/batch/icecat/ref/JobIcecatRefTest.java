package eu.ttbox.batch.icecat.ref;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml",
// "/jobs/languageList.xml" //
// "/jobs/measureList.xml" //
// "/jobs/supplierList.xml" //
// "/jobs/categoryList.xml" //
// "/jobs/featureList.xml" //
// "/jobs/categoryFeaturesList.xml" //
// "/jobs/supplierProductFamiliesList.xml" //
// "/jobs/featureValuesVocabularyList.xml" //
		"/jobs/relationsList.xml" //
// "/jobs/distributorList.xml" //
// "/jobs/supplierMapping.xml" //
// "/jobs/campaignsList.xml" //
})
public class JobIcecatRefTest {

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job job;

	@Test
	public void icecatFileRefReader() throws Exception {
		System.out.println("Started Job " + job.getName());
		JobExecution jobExecution = jobLauncher.run(job, new JobParameters());
		// JobExecution jobExecution = jobLauncherTestUtils.launchJob();
		Assert.assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode());
	}

}
