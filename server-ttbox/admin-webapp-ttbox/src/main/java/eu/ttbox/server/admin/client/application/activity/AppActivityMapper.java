package eu.ttbox.server.admin.client.application.activity;

import eu.ttbox.server.admin.client.application.ioc.PresenterFactory;
import eu.ttbox.server.admin.client.core.activity.PlaceActivityMapper;
import eu.ttbox.server.admin.client.core.common.FailureHandler;
import eu.ttbox.server.admin.client.view.home.HomePlace;
import eu.ttbox.server.admin.client.view.salespoint.edit.SalespointEditPlace;
import eu.ttbox.server.admin.client.view.salespoint.list.SalespointListPlace;

public class AppActivityMapper extends PlaceActivityMapper {

	public AppActivityMapper(final PresenterFactory factory, FailureHandler failureHandler) {

		super(failureHandler);

		register(HomePlace.class, factory.getHomePresenter());
		register(SalespointListPlace.class, factory.getSalespointListPresenter());
		register(SalespointEditPlace.class, factory.getSalespointEditPresenter());
	}
	
	
}
