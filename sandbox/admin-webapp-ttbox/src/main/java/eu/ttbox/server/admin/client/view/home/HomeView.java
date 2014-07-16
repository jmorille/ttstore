package eu.ttbox.server.admin.client.view.home;

import com.google.gwt.user.client.ui.IsWidget;

public interface HomeView extends IsWidget {

	public interface Presenter {
		void gotoSalespoints();
	}
	
    /**
     * Injecte le presenter dans la vue.
     * 
     * Cet appel est généralement effectué dans le constructeur du presenter.
     */
    void setPresenter(Presenter presenter);

}
