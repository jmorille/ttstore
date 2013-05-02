package eu.ttbox.server.admin.client.view.salespoint.edit;

import java.util.Set;
import java.util.logging.Logger;

import javax.validation.ConstraintViolation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.google.web.bindery.requestfactory.shared.Receiver;

import eu.ttbox.server.admin.client.core.activity.PlaceActivity;
import eu.ttbox.server.admin.client.view.salespoint.edit.desktop.SalespointEditor;
import eu.ttbox.server.admin.client.view.salespoint.list.SalespointListPlace;
import eu.ttbox.server.admin.shared.proxy.SalespointProxy;
import eu.ttbox.server.admin.shared.request.SalespointServiceRequestFactory.SalespointServiceRequest;

public class SalespointEditPresenter extends PlaceActivity<SalespointEditPlace> implements SalespointEditView.Presenter {

	private static final Logger LOG = Logger.getLogger(SalespointEditPresenter.class.getName());


	private final SalespointEditView view;

	@Inject
	PlaceController placeController;

	@Inject
	Provider<SalespointServiceRequest> salespointServiceRequest;
 
	private SalespointProxy entityAsCache;
	
	@Inject
	public SalespointEditPresenter(SalespointEditView view) {
	    super();
	    this.view = view;
	    this.view.setPresenter(this);
    }

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
		// Edit
		Integer id = getPlace().getId();
		loadEntity(id);
	}

	private void loadEntity(Integer id) {
		if (id==null) {
			return;
//			LOG.info("Create new Enmpty Entity");
//			SalespointProxy response = GWT.create(SalespointProxy.class);
//			editEntity(response);
		} else {
			LOG.info("Load Entity Id "+ id);
			salespointServiceRequest.get().getSalespointById(id).fire(new Receiver<SalespointProxy>() {

				@Override
				public void onSuccess(SalespointProxy response) {
					editEntity(response);
				}
			});
		}
	}

	public SalespointProxy getEntityAsCache() {
		return entityAsCache;
	}
	 
	public void saveEntity() {
		RequestFactoryEditorDriver<SalespointProxy, SalespointEditor> editorDriver = view.getEditorDriver();
	
		// Flush :  https://groups.google.com/forum/#!topic/google-web-toolkit/KW_4uK95qpE
		SalespointServiceRequest context = (SalespointServiceRequest) editorDriver.flush(); 
		
		// Client Side Validation : ??
//		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
//		Set<ConstraintViolation<SalespointProxy>> violations =  validator.validate(object, null);
		
		// Server Validation : http://stackoverflow.com/questions/4098579/how-to-use-the-gwt-editor-framework-for-validation
		if (editorDriver.hasErrors()) {
			// A sub-editor reported errors
			LOG.info("Editor has errors : " + editorDriver.getErrors().size());
			for (EditorError ee : editorDriver.getErrors()) {
//				GWT.log(ee.getMessage());
				LOG.info("Editor error : " + ee);
			}
			Window.alert("Drivers Error "+ editorDriver.getErrors().size() );
			return;
		}
		// SalespointProxy edited =
		// presenter.saveEntity(edited);
		//context.saveSalespoint();
		context.fire(new Receiver<Void>() {

			@Override
			public void onSuccess(Void response) {
				Window.alert("Success!");
				doSaveEntity();
//				presenter.goToPlaceList(); 
 			}
			@Override
			public void onConstraintViolation(Set<ConstraintViolation<?>> violations) {
				setViewConstraintViolations(violations);
			}
		});
	}
	
	private void setViewConstraintViolations(Set<ConstraintViolation<?>> violations) {
		RequestFactoryEditorDriver<SalespointProxy, SalespointEditor> editorDriver = view.getEditorDriver();
		editorDriver.setConstraintViolations(violations);
		// Print Msg
		StringBuilder sb = new StringBuilder();
		for (ConstraintViolation<?> e : violations) {
			sb.append(e.getPropertyPath()).append(": ").append(e.getMessage());
		}
		Window.alert(sb.toString());
	}
	
	private void doSaveEntity() {
		if (entityAsCache==null) {
			return;
		}
		salespointServiceRequest.get().saveSalespoint(entityAsCache).fire(new Receiver<Void>() {
 			@Override
			public void onSuccess(Void response) {
 				goToPlaceList();
 				entityAsCache = null;
 			}
		});
	}
	
	protected void editEntity(SalespointProxy entity) {
		LOG.info("Populate Entity Id "+ entity);
		this.entityAsCache = entity;
		view.getEditorDriver().edit(entity, salespointServiceRequest.get());
//		view.edit(entity, salespointServiceRequest.get());
		
	}

	public void goToPlaceList() {
		 placeController.goTo(new SalespointListPlace());
	}
	
}
