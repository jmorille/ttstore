package eu.ttbox.server.admin.client.application.ioc;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

import eu.ttbox.server.admin.client.core.common.FailureHandler;

public interface ClientFactory {
	
	
	EventBus getEventBus();
	 
	PlaceController getPlaceController();

	FailureHandler getFailureHandler();
	
	
}
