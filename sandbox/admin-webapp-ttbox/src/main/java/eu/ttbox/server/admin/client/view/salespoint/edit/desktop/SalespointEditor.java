package eu.ttbox.server.admin.client.view.salespoint.edit.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.ui.client.ValueBoxEditorDecorator;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.Widget;

import eu.ttbox.server.admin.shared.proxy.SalespointProxy;

public class SalespointEditor extends Composite implements Editor<SalespointProxy> {

	interface SalespointEditorUiBinder extends UiBinder<Widget, SalespointEditor> {
	}

	private static SalespointEditorUiBinder uiBinder = GWT.create(SalespointEditorUiBinder.class);

	public SalespointEditor() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	ValueBoxEditorDecorator<String> name;

	@UiField
	Focusable nameBox;

	public SalespointEditor(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
