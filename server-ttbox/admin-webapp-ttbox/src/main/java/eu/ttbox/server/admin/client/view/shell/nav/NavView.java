package eu.ttbox.server.admin.client.view.shell.nav;

import com.google.gwt.user.client.ui.IsWidget;

public interface NavView extends IsWidget {

	public void setPresenter(Presenter presenter);
 
	public interface Presenter {

		void onMenuSelect(Integer selectedItem);
		 
	}

	public void setSelectMenu(int i);
	
	
}
