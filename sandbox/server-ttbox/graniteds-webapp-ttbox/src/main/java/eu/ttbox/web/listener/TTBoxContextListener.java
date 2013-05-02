package eu.ttbox.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;

public class TTBoxContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
 	}

	/**
	 * @see http://logback.qos.ch/manual/jmxConfig.html#leak
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
	    lc.stop();
 	}

}
