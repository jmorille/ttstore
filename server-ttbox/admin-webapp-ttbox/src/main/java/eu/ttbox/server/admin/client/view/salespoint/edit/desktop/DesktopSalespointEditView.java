package eu.ttbox.server.admin.client.view.salespoint.edit.desktop;

import java.util.Set;
import java.util.logging.Logger;

import javax.validation.ConstraintViolation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.RequestContext;

import eu.ttbox.server.admin.client.view.salespoint.edit.SalespointEditView;
import eu.ttbox.server.admin.shared.proxy.SalespointProxy;
import eu.ttbox.server.admin.shared.request.SalespointServiceRequestFactory.SalespointServiceRequest;

public class DesktopSalespointEditView extends Composite implements SalespointEditView {

	private static final Logger LOG = Logger.getLogger(DesktopSalespointEditView.class.getName());

	private static DesktopSalespointEditViewUiBinder uiBinder = GWT.create(DesktopSalespointEditViewUiBinder.class);

	interface DesktopSalespointEditViewUiBinder extends UiBinder<Widget, DesktopSalespointEditView> {
	}

	// Empty interface declaration, similar to UiBinder
	interface Driver extends RequestFactoryEditorDriver<SalespointProxy, SalespointEditor> {
	}

	@UiField
	Button buttonSave;

	@UiField
	Button buttonCancel;

	@UiField(provided = true)
	SalespointEditor editor = new SalespointEditor();

	private Presenter presenter;

	private Driver editorDriver;

	public DesktopSalespointEditView() {
		// editor = new SalespointEditor();
		initWidget(uiBinder.createAndBindUi(this));

		editorDriver = GWT.create(Driver.class);
		editorDriver.initialize(editor); 
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
	
	public RequestFactoryEditorDriver<SalespointProxy, SalespointEditor> getEditorDriver() {
		return editorDriver;
	}
 

	@UiHandler("buttonSave")
	void onSaveClick(ClickEvent e) {
		presenter.saveEntity();
	}

	@UiHandler("buttonCancel")
	void onCancelClick(ClickEvent e) {
		presenter.goToPlaceList();
	}

}
