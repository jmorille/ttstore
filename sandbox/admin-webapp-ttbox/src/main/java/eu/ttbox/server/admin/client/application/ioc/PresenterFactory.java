package eu.ttbox.server.admin.client.application.ioc;

import com.google.gwt.inject.client.AsyncProvider;

import eu.ttbox.server.admin.client.view.home.HomePresenter;
import eu.ttbox.server.admin.client.view.salespoint.edit.SalespointEditPresenter;
import eu.ttbox.server.admin.client.view.salespoint.list.SalespointListPresenter;

public interface PresenterFactory {

	AsyncProvider<HomePresenter> getHomePresenter();
	
	AsyncProvider<SalespointListPresenter> getSalespointListPresenter();

	AsyncProvider<SalespointEditPresenter> getSalespointEditPresenter();
	 
}
