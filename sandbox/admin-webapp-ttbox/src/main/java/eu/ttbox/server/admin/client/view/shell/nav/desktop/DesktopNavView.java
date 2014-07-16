package eu.ttbox.server.admin.client.view.shell.nav.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

import eu.ttbox.server.admin.client.view.shell.nav.NavView;

public class DesktopNavView extends Composite implements  NavView {

	private static DesktopNavViewUiBinder uiBinder = GWT.create(DesktopNavViewUiBinder.class);

	interface DesktopNavViewUiBinder extends UiBinder<Widget, DesktopNavView> {
	}

	@UiField
	StackLayoutPanel menu;
	
	Presenter presenter;
	
	public DesktopNavView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("menu")
	public void onMenuSelect(SelectionEvent<Integer> event) {
//		Window.alert("Browse Event " + event.getSelectedItem());
		presenter.onMenuSelect(event.getSelectedItem()); 
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setSelectMenu(int i) {
	  menu.showWidget(i);
	}

	
	
}
