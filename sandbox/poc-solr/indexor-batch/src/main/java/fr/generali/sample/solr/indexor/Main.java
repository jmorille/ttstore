package fr.generali.sample.solr.indexor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StopWatch;

public class Main {

    static Logger log = LoggerFactory.getLogger(Main.class);
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/fr/generali/sample/solr/indexor/*.xml");
        JobLauncher jobLauncher = (JobLauncher)context.getBean("jobLauncher");
        Job mainJob = (Job)context.getBean("jdbcToSolrJob");
        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start("index RCE");
            jobLauncher.run(mainJob, new JobParameters());
            stopWatch.stop();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }  
        log.info(stopWatch.prettyPrint());
    }

}
