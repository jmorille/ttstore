package eu.ttbox.server.admin.client.application.ioc.gin;

import java.util.logging.Logger;

import javax.inject.Singleton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Window;
import com.google.inject.Provides;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

import eu.ttbox.server.admin.client.core.common.FailureHandler;
import eu.ttbox.server.admin.shared.request.SalespointServiceRequestFactory;
import eu.ttbox.server.admin.shared.request.SalespointServiceRequestFactory.SalespointServiceRequest;

public class ClientModule  extends AbstractGinModule  {

	private static final Logger LOG = Logger.getLogger(ClientModule.class.getName() );
	
	@Override
    protected void configure() {
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class); 
    }

	@Provides
	@Singleton
	PlaceController providePlaceController(EventBus eventBus) {
		final PlaceController placeController = new PlaceController(eventBus);
		return placeController;
	}

	@Provides
	@Singleton
	FailureHandler getFailureHandler() {
		return new FailureHandler() {
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(printStackTrace(caught));
			}

			private String printStackTrace(Throwable e) {

				String stackTrace = e.getClass() + ":" + e.getMessage() + "\n";

				for (StackTraceElement se : e.getStackTrace()) {
					stackTrace += "at " + se.getClassName() + "." + se.getMethodName() + "(" + se.getFileName() + ":" + se.getLineNumber()
							+ ")\n";
				}
				return stackTrace;

			}
		};
	}
	
	@Provides
	@Singleton
	public SalespointServiceRequestFactory createSalespointServiceRequestFactory(EventBus eventBus) {
		LOG.info("Create SalespointServiceRequestFactory = "  );
		SalespointServiceRequestFactory factory = GWT.create(SalespointServiceRequestFactory.class);
		factory.initialize(eventBus);
		return factory;
	}

	@Provides
	public SalespointServiceRequest createSalespointServiceRequest(SalespointServiceRequestFactory factory) {
		LOG.info("create SalespointServiceRequest with SalespointServiceRequestFactory = " +( factory!=null));
		return factory.context();
	}
}
