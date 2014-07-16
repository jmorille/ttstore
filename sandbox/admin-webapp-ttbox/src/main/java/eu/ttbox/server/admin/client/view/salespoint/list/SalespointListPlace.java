package eu.ttbox.server.admin.client.view.salespoint.list;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class SalespointListPlace extends Place {

	@Prefix("salespoints")
    public static final class Tokenizer implements PlaceTokenizer<SalespointListPlace> {
		
        public SalespointListPlace getPlace(String token) {
            return new SalespointListPlace();
        }

        public String getToken(SalespointListPlace place) {
            return "";
        }
    }
	
	
}
