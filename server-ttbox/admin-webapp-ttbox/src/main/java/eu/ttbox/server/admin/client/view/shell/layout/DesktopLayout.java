package eu.ttbox.server.admin.client.view.shell.layout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

import eu.ttbox.server.admin.client.view.shell.nav.desktop.DesktopNavView;
 

public class DesktopLayout extends Composite implements  AcceptsOneWidget {

	private static DesktopLayoutUiBinder uiBinder = GWT.create(DesktopLayoutUiBinder.class);

	
	interface DesktopLayoutUiBinder extends UiBinder<Widget, DesktopLayout> {
	}
	
	@UiField
	SimplePanel nav;	
	
	@UiField
	SimplePanel center;

//	Presenter presenter;

	public DesktopLayout() {
		initWidget(uiBinder.createAndBindUi(this));
		nav.setWidget(new DesktopNavView());
	}

	
//	@Override
//	public void setPresenter(Presenter presenter) {
//		this.presenter = presenter;
//	}

	@Override
	public void setWidget(IsWidget w) {
		center.setWidget(w);
	}
	
	public AcceptsOneWidget getNav() {
		return nav;
	}

}
