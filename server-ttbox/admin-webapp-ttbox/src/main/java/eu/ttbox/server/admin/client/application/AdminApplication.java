package eu.ttbox.server.admin.client.application;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.FilteredActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.user.client.ui.HasWidgets.ForIsWidget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

import eu.ttbox.server.admin.client.application.activity.AppActivityMapper;
import eu.ttbox.server.admin.client.application.activity.AppPlaceFilter;
import eu.ttbox.server.admin.client.application.ioc.ClientFactory;
import eu.ttbox.server.admin.client.application.ioc.PresenterFactory;
import eu.ttbox.server.admin.client.application.ioc.ViewFactory;
import eu.ttbox.server.admin.client.view.home.HomePlace;

public class AdminApplication {

	private static final Logger LOG = Logger.getLogger(AdminApplication.class.getName());
	
	private final ClientFactory clientFactory;
	
	private final PresenterFactory presenterFactory;
	
	private final ViewFactory viewFactory;
	
	// Other
//	private final PlaceHistoryHandler placeHistoryHandler;
	
	private final EventBus eventBus;

	private final PlaceController placeController;
	
	private ActivityManager activityManager;
	
 
	@Inject
	public AdminApplication(ClientFactory clientFactory, PresenterFactory presenterFactory,  ViewFactory viewFactory) {

		this.clientFactory = clientFactory;
		this.presenterFactory = presenterFactory;
		this.viewFactory = viewFactory;
		//
		this.eventBus = clientFactory.getEventBus();
		this.placeController = clientFactory.getPlaceController(); 
		//View 
//		this.placeHistoryHandler = placeHistoryHandler;
	}
 
	
	public void startup(ForIsWidget rootPanel ) {
		LOG.info("AdminApplication startup");
		// On ajoute le shell au rootPanl
		showUI(rootPanel); 
	}

	
	private void showUI(ForIsWidget rootPanel) {
		// 0 - Suprression de l'écran de bootstrap
		rootPanel.clear();
		// 1 - On ajoute le shell au rootPanel
		rootPanel.add(viewFactory.getShell());
		// 2 - on initialise le framework Activities
		initActivityManager();

		// 3 - On initialise le framework Places pour gérer la navigation et
		// l'historique 
		HomePlace initialPlace = new HomePlace();
		goTo(initialPlace);
	}
	
	
	
	public void shutdown() {
		LOG.info("AdminApplication shutdown");
		activityManager.setDisplay(null);
	}
	
	
	private ActivityManager initActivityManager() {
		if (activityManager == null) {

			// PresenterFactory presenterFactory = new
			// PresenterFactoryImpl(viewFactory, placeController, dispatcher);

			AppActivityMapper activityMapper = new AppActivityMapper(presenterFactory, clientFactory.getFailureHandler());
			// Le mapper suivtant est filtrÃ© avec le placeFilter qui vient aprÃšs
			AppPlaceFilter placeFilter = new AppPlaceFilter();
			FilteredActivityMapper mapper = new FilteredActivityMapper(placeFilter, activityMapper);

			activityManager = new ActivityManager(mapper, eventBus);
			activityManager.setDisplay(viewFactory.getShell());
		}
		return activityManager;
	}
	
	private void goTo(Place defaultPlace) {
		PlaceHistoryMapper historyMapper = GWT.create(PlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(placeController, eventBus, defaultPlace);
		historyHandler.handleCurrentHistory();
	}
	
}
