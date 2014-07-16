package eu.ttbox.server.admin.shared.proxy;

 

import javax.validation.constraints.Size;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

import eu.ttbox.model.salespoint.Salespoint;
import eu.ttbox.server.admin.server.service.SalespointService;
import eu.ttbox.server.admin.server.service.SalespointServiceMock;

@ProxyFor(value=Salespoint.class , locator=SalespointService.class) //
public interface SalespointProxy extends EntityProxy {

	Integer getId();

	Integer getVersion();

	@Size(min=2, max=20)
	String getName();
 
//	List<AddressProxy> getAddresses();
	
	void setName(String name);
	
	void setVersion(Integer version);
	
//	void setAddresses(List<AddressProxy> addresses);

} 