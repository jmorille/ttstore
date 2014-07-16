package eu.ttbox.server.admin.shared.proxy;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;

import eu.ttbox.server.admin.server.model.SearchResult;

@ProxyFor(SearchResult.class)
public interface SearchResultProxy  extends ValueProxy {
	
	  List<SalespointProxy> getHits();

	  int getHitsCount();

	  int getStart();

	  int getPageSize();

}
