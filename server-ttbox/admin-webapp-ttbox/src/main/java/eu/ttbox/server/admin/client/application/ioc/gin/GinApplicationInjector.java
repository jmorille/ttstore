package eu.ttbox.server.admin.client.application.ioc.gin;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

import eu.ttbox.server.admin.client.application.ioc.ClientFactory;
import eu.ttbox.server.admin.client.application.ioc.PresenterFactory;
import eu.ttbox.server.admin.client.application.ioc.ViewFactory;

@GinModules({ 
		ClientModule.class,
		PresenterModule.class,
		ViewModule.class  
})
public interface GinApplicationInjector extends Ginjector, ClientFactory, PresenterFactory,  ViewFactory  {

 

}
