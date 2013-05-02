package eu.ttbox.batch.icecat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContextFactory {

	private static Logger log = LoggerFactory.getLogger(SpringContextFactory.class);

	private static String[] locations = new String[] { "/eu/ttbox/batch/icecat/applicationContext.xml" };

	private static AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext(locations);

	/**
	 * @see org.springframework.beans.factory.BeanFactory#getBean(String)
	 * @param appContextContextKey
	 *            The Application Context Key
	 * @param beanName
	 * @return The wanted bean Name.
	 */
	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

	public static void shutdown() {
		applicationContext.registerShutdownHook();
		log.info("Shutdown Spring context");
	}

	// public static Object getBean(SpringBeanEnum beanName) {
	// return applicationContext.getBean(beanName.getBeanName());
	// }

}
