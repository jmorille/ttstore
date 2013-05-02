package eu.ttbox.server.admin.shared.proxy;

 

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;

import eu.ttbox.server.admin.server.model.Address;

@ProxyFor(Address.class)
public interface AddressProxy extends ValueProxy {


	 String getStreet();

	 void setStreet(String street);

	 String getCity();

	 void setCity(String city);

	 String getZipCode();

	 void setZipCode(String zipCode);
	
}
