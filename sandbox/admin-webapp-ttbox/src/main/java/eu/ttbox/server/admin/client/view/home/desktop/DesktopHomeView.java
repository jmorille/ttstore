package eu.ttbox.server.admin.client.view.home.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import eu.ttbox.server.admin.client.view.home.HomeView;

public class DesktopHomeView extends Composite implements HomeView, HasText {

	private static DesktopHomeViewUiBinder uiBinder = GWT.create(DesktopHomeViewUiBinder.class);

	interface DesktopHomeViewUiBinder extends UiBinder<Widget, DesktopHomeView> {
	}

	 
	private Presenter presenter;

	public DesktopHomeView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField TextBox nameField;

	@UiField Button button;
	 
	@UiField Button gotoButton;
	
	public DesktopHomeView(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		button.setText(firstName);
	}

	@UiHandler("button")
	void onClick(ClickEvent e) {
		Window.alert("Hello! " + nameField.getValue());
	}

	@UiHandler("gotoButton")
	void onGotoClick(ClickEvent e) {
		presenter.gotoSalespoints();
	}

	
	public void setText(String text) {
		button.setText(text);
	}

	public String getText() {
		return button.getText();
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;

	}

}
