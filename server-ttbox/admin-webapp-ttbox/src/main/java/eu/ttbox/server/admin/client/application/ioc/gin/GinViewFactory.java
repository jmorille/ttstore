package eu.ttbox.server.admin.client.application.ioc.gin;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

import eu.ttbox.server.admin.client.application.ioc.ViewFactory;

@GinModules({ 
	ViewModule.class
})
public interface GinViewFactory extends Ginjector, ViewFactory {

}
