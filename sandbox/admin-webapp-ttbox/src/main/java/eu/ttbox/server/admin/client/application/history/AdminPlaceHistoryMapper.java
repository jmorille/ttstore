package eu.ttbox.server.admin.client.application.history;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

import eu.ttbox.server.admin.client.view.home.HomePlace;
import eu.ttbox.server.admin.client.view.salespoint.edit.SalespointEditPlace;
import eu.ttbox.server.admin.client.view.salespoint.list.SalespointListPlace;

/**
 * Cette interface permet de mapper une place à son token et inversement.
 * 
 * L'implémentation est générée par GWT.
 * 
 */
@WithTokenizers({ //
HomePlace.Tokenizer.class,//
SalespointListPlace.Tokenizer.class, //
SalespointEditPlace.Tokenizer.class //
})
public interface AdminPlaceHistoryMapper extends PlaceHistoryMapper {

}
