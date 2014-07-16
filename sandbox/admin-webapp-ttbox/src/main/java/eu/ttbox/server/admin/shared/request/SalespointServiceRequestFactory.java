package eu.ttbox.server.admin.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;

import eu.ttbox.server.admin.server.locator.SpringServiceLocator;
import eu.ttbox.server.admin.server.service.SalespointService;
import eu.ttbox.server.admin.shared.proxy.SalespointProxy;
import eu.ttbox.server.admin.shared.proxy.SearchResultProxy;

public interface SalespointServiceRequestFactory extends RequestFactory {

 
	public SalespointServiceRequest context();
	
	@Service(value = SalespointService.class, locator=SpringServiceLocator.class) 
	public interface SalespointServiceRequest extends RequestContext {

		Request<SearchResultProxy> searchSalespoints(int start, int pageSize);

		Request<SalespointProxy> getSalespointById(Integer id);
		
		Request<Void> saveSalespoint(SalespointProxy salespoint);
		
	 
	}
	
}
