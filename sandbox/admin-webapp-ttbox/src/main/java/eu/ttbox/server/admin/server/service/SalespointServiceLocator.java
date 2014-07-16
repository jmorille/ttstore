package eu.ttbox.server.admin.server.service;

import com.google.web.bindery.requestfactory.shared.ServiceLocator;

public class SalespointServiceLocator implements ServiceLocator {

	private SalespointServiceMock instance = new SalespointServiceMock();
	
	@Override
	public Object getInstance(Class<?> clazz) { 
		System.err.println("@@@@@ class" + clazz);
		return instance;
	}

}
