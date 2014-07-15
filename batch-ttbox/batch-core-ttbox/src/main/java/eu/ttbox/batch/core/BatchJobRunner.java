package eu.ttbox.batch.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.converter.DefaultJobParametersConverter;
import org.springframework.batch.core.converter.JobParametersConverter;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.ExitCodeMapper;
import org.springframework.batch.core.launch.support.JvmSystemExiter;
import org.springframework.batch.core.launch.support.SimpleJvmExitCodeMapper;
import org.springframework.batch.core.launch.support.SystemExiter;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.Set;
 
public class BatchJobRunner {

	protected static final Logger logger = LoggerFactory.getLogger(BatchJobRunner.class);

	private ExitCodeMapper exitCodeMapper = new SimpleJvmExitCodeMapper();

	private JobLauncher launcher;

	private JobLocator jobLocator;

	// Package private for unit test
	private static SystemExiter systemExiter = new JvmSystemExiter();

	private static String message = "";

	private JobParametersConverter jobParametersConverter = new DefaultJobParametersConverter();

	private JobExplorer jobExplorer;

	private JobRepository jobRepository;

	/**
	 * Injection setter for the {@link JobLauncher}.
	 * 
	 * @param launcher
	 *            the launcher to set
	 */
	public void setLauncher(JobLauncher launcher) {
		this.launcher = launcher;
	}

	/**
	 * @param jobRepository
	 *            the jobRepository to set
	 */
	public void setJobRepository(JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}

	/**
	 * Injection setter for {@link JobExplorer}.
	 * 
	 * @param jobExplorer
	 *            the {@link JobExplorer} to set
	 */
	public void setJobExplorer(JobExplorer jobExplorer) {
		this.jobExplorer = jobExplorer;
	}

	/**
	 * Injection setter for the {@link ExitCodeMapper}.
	 * 
	 * @param exitCodeMapper
	 *            the exitCodeMapper to set
	 */
	public void setExitCodeMapper(ExitCodeMapper exitCodeMapper) {
		this.exitCodeMapper = exitCodeMapper;
	}

	/**
	 * Static setter for the {@link SystemExiter} so it can be adjusted before
	 * dependency injection. Typically overridden by
	 * {@link #setSystemExiter(SystemExiter)}.
	 * 
	 * @param systemExitor
	 */
	public static void presetSystemExiter(SystemExiter systemExiter) {
		BatchJobRunner.systemExiter = systemExiter;
	}

	/**
	 * Retrieve the error message set by an instance of {@link BatchJobRunner}
	 * as it exits. Empty if the last job launched was successful.
	 * 
	 * @return the error message
	 */
	public static String getErrorMessage() {
		return message;
	}

	/**
	 * Injection setter for the {@link SystemExiter}.
	 * 
	 * @param systemExitor
	 */
	public void setSystemExiter(SystemExiter systemExiter) {
		BatchJobRunner.systemExiter = systemExiter;
	}

	/**
	 * Injection setter for {@link JobParametersConverter}.
	 * 
	 * @param jobParametersConverter
	 */
	public void setJobParametersConverter(
			JobParametersConverter jobParametersConverter) {
		this.jobParametersConverter = jobParametersConverter;
	}

	/**
	 * Delegate to the exiter to (possibly) exit the VM gracefully.
	 * 
	 * @param status
	 */
	public void exit(int status) {
		systemExiter.exit(status);
	}

	/**
	 * {@link JobLocator} to find a job to run.
	 * 
	 * @param jobLocator
	 *            a {@link JobLocator}
	 */
	public void setJobLocator(JobLocator jobLocator) {
		this.jobLocator = jobLocator;
	}

	/*
	 * Start a job by obtaining a combined classpath using the job launcher and
	 * job paths. If a JobLocator has been set, then use it to obtain an actual
	 * job, if not ask the context for it.
	 */
	public int start(String[] jobPath, String[] jobIdentifiers,
			String[] parameters, Set<String> opts) {
		int lastJobExeCode = exitCodeMapper.intValue(ExitStatus.UNKNOWN.getExitCode());
		int jobCounts = jobIdentifiers.length;
		logger.info("Starting for {} jobs {}", jobCounts, jobIdentifiers);
		logger.info(" jobPath {}", new Object[] { jobPath });
		ConfigurableApplicationContext context = null;

		try {
			context = new ClassPathXmlApplicationContext(jobPath);
			context.getAutowireCapableBeanFactory().autowireBeanProperties(
					this, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, false);
 
			int jobIdx = 0;
			StopWatch stopWatch = new StopWatch();
			for (String jobIdentifier : jobIdentifiers) {
				jobIdx++;
				stopWatch.start(String.format("%s / %s : %s", jobIdx, jobCounts, jobIdentifier ));
				String jobName = jobIdentifier;
				logger.info("Starting job {} / {} : {}", new Object[] {jobIdx, jobCounts, jobIdentifier} );
				JobParameters jobParameters = jobParametersConverter
						.getJobParameters(StringUtils
								.splitArrayElementsIntoProperties(parameters,
										"="));
				Assert.isTrue(
						parameters == null || parameters.length == 0
								|| !jobParameters.isEmpty(),
						"Invalid JobParameters "
								+ Arrays.asList(parameters)
								+ ". If parameters are provided they should be in the form name=value (no whitespace).");

		 
 

				Job job;
				if (jobLocator != null) {
					job = jobLocator.getJob(jobName);
				} else {
					job = (Job) context.getBean(jobName);
				} 
 
				JobExecution jobExecution = launcher.run(job, jobParameters);
				int jobExeCode =  exitCodeMapper.intValue(jobExecution.getExitStatus()
						.getExitCode());
				logger.info("Job {} end with code : {}", jobName, jobExeCode );
				Date startTime = jobExecution.getStartTime();
				Date endTime = jobExecution.getEndTime();
				logger.info("Job Start at {} and end at {}",startTime, endTime);

				if (jobExeCode == exitCodeMapper.intValue(ExitStatus.FAILED.getExitCode())) {
					return jobExeCode;
				}
				lastJobExeCode = jobExeCode;
				stopWatch.stop();
				logger.info(stopWatch.prettyPrint());
			}
			return lastJobExeCode;

		} catch (Throwable e) {
			String message = "Job Terminated in error: " + e.getMessage();
			logger.error(message, e);
			BatchJobRunner.message = message;
			return exitCodeMapper.intValue(ExitStatus.FAILED.getExitCode());
		} finally {
			if (context != null) {
				context.close();
			}
		}
	}

	 
	 
}
