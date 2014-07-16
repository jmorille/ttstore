package eu.ttbox.server.admin.server.locator;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.google.web.bindery.requestfactory.shared.ServiceLocator;

@Component
public class SpringServiceLocator implements ServiceLocator, ApplicationContextAware {

	public static ApplicationContext applicationContext;
	
	@Override
	public Object getInstance(Class<?> clazz) {
//		ServletContext sc = RequestFactoryServlet.getThreadLocalServletContext();
//		ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
		return SpringServiceLocator.applicationContext.getBean(clazz);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringServiceLocator.applicationContext = applicationContext;
	}

}
