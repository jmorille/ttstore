package eu.ttbox.service.chat;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

public class ChatService implements ServletContextAware {

	ServletContext servletContext;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;

	}

	public void getClients() {
		// Gravity gravity = GravityManager.getGravity(servletContext);
		// gravity.get
	}

}
