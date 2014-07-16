package eu.ttbox.server.admin.client.view.salespoint.edit;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.google.web.bindery.requestfactory.shared.RequestContext;

import eu.ttbox.server.admin.client.view.salespoint.edit.desktop.SalespointEditor;
import eu.ttbox.server.admin.shared.proxy.SalespointProxy;

public interface SalespointEditView extends IsWidget {

	public interface Presenter {
		
		void goToPlaceList() ;
		
		SalespointProxy getEntityAsCache();

		void saveEntity();
	
	}

	void setPresenter(Presenter presenter);

//	void edit(SalespointProxy entity,  RequestContext context);
	
	RequestFactoryEditorDriver<SalespointProxy, SalespointEditor> getEditorDriver();
}
