package eu.ttbox.server.admin.client.application.ioc.gin;

import javax.inject.Singleton;

import com.google.gwt.inject.client.AbstractGinModule;

import eu.ttbox.server.admin.client.view.home.HomeView;
import eu.ttbox.server.admin.client.view.home.desktop.DesktopHomeView;
import eu.ttbox.server.admin.client.view.salespoint.edit.SalespointEditView;
import eu.ttbox.server.admin.client.view.salespoint.edit.desktop.DesktopSalespointEditView;
import eu.ttbox.server.admin.client.view.salespoint.list.SalespointListView;
import eu.ttbox.server.admin.client.view.salespoint.list.desktop.DesktopSalespointListView;
import eu.ttbox.server.admin.client.view.shell.DesktopShell;
import eu.ttbox.server.admin.client.view.shell.Shell;

public class ViewModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(Shell.class).to(DesktopShell.class).in(Singleton.class);
		bind(HomeView.class, DesktopHomeView.class);
		bind(SalespointListView.class, DesktopSalespointListView.class);
		bind(SalespointEditView.class, DesktopSalespointEditView.class);
	}
	

	<T> void bind(Class<T> view, Class<? extends T> viewImpl) {
		bind(view).to(viewImpl).in(Singleton.class);
	}

}
