package eu.ttbox.server.admin.client.view.salespoint.list;

import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.web.bindery.requestfactory.shared.Receiver;

import eu.ttbox.server.admin.client.core.activity.PlaceActivity;
import eu.ttbox.server.admin.client.view.salespoint.edit.SalespointEditPlace;
import eu.ttbox.server.admin.shared.proxy.SalespointProxy;
import eu.ttbox.server.admin.shared.proxy.SearchResultProxy;
import eu.ttbox.server.admin.shared.request.SalespointServiceRequestFactory.SalespointServiceRequest;

public class SalespointListPresenter extends PlaceActivity<SalespointListPlace> implements SalespointListView.Presenter {

	private static final Logger LOG = Logger.getLogger(SalespointListPresenter.class.getName() );
	
	private final SalespointListView view;

	@Inject
	PlaceController placeController;

	@Inject
	Provider<SalespointServiceRequest> salespointServiceRequest;
 

	@Inject
	public SalespointListPresenter(SalespointListView view) {
	    super();
	    this.view = view;
	    this.view.setPresenter(this);
    }

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);

	}

	@Override
	public void doSearchSalespoint(int start, int pageSize) {
		LOG.info("doSearchSalespoint("+start+ ", " + pageSize+ ")");
		salespointServiceRequest.get().searchSalespoints(start, pageSize).to(new Receiver<SearchResultProxy>() {
			@Override
			public void onSuccess(SearchResultProxy response) { 
				setDatas(response);
 			}
		}).fire(); 
	}
	
	public void setDatas(SearchResultProxy datas) {
		view.setResults(datas); 
	}
	
	public void goToEdit(SalespointProxy entity) {
		if (entity !=null) {
			placeController.goTo(new SalespointEditPlace(entity.getId()));
 		}
	}
	
}
