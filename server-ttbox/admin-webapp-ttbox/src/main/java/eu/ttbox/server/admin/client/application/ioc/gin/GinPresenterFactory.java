package eu.ttbox.server.admin.client.application.ioc.gin;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

import eu.ttbox.server.admin.client.application.ioc.PresenterFactory;

@GinModules({ 
	PresenterModule.class
})
public interface GinPresenterFactory extends Ginjector, PresenterFactory {

}
