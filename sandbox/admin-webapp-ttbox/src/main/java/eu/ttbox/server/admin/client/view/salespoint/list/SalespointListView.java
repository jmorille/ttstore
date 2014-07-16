package eu.ttbox.server.admin.client.view.salespoint.list;

import com.google.gwt.user.client.ui.IsWidget;

import eu.ttbox.server.admin.shared.proxy.SalespointProxy;
import eu.ttbox.server.admin.shared.proxy.SearchResultProxy;

public interface SalespointListView extends IsWidget {

	public interface Presenter {

		void doSearchSalespoint(int start, int  pageSize);
		void goToEdit(SalespointProxy entity);
	}

	/**
	 * Injecte le presenter dans la vue.
	 * 
	 * Cet appel est généralement effectué dans le constructeur du presenter.
	 */
	void setPresenter(Presenter presenter);

	void setResults(SearchResultProxy datas);

}
