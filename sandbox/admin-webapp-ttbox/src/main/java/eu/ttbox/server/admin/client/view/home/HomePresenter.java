package eu.ttbox.server.admin.client.view.home;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

import eu.ttbox.server.admin.client.core.activity.PlaceActivity;
import eu.ttbox.server.admin.client.view.salespoint.list.SalespointListPlace;

public class HomePresenter extends PlaceActivity<HomePlace> implements HomeView.Presenter {

	private final HomeView view;

	private final PlaceController placeController;

	@Inject
	public HomePresenter(final HomeView display, final PlaceController placeController) {
		super();
		this.view = display; 
		this.view.setPresenter(this);
		this.placeController = placeController;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);

	}
	
	@Override
	public void gotoSalespoints() {
		placeController.goTo(new SalespointListPlace());
	}

}
