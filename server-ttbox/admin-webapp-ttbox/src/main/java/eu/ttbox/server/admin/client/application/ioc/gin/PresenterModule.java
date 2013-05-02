package eu.ttbox.server.admin.client.application.ioc.gin;

import com.google.gwt.inject.client.AbstractGinModule;

import eu.ttbox.server.admin.client.view.home.HomePresenter;
import eu.ttbox.server.admin.client.view.salespoint.edit.SalespointEditPresenter;
import eu.ttbox.server.admin.client.view.salespoint.list.SalespointListPresenter;

public class PresenterModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(HomePresenter.class);
		bind(SalespointListPresenter.class);
		bind(SalespointEditPresenter.class); 
	}

}
