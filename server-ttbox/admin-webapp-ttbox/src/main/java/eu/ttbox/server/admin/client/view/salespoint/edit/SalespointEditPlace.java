package eu.ttbox.server.admin.client.view.salespoint.edit;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class SalespointEditPlace extends Place  {

	Integer id;
	
 	public SalespointEditPlace(Integer id) {
		super();
		this.id = id;
	}

 	@Prefix("salespoint")
    public static final class Tokenizer implements PlaceTokenizer<SalespointEditPlace> {
		
        public SalespointEditPlace getPlace(String token) {
        	Integer id = Integer.valueOf(token);
            return new SalespointEditPlace(id);
        }

        public String getToken(SalespointEditPlace place) {
            return place.id==null?"":place.id.toString();
        }
    }

	public Integer getId() {
    	return id;
    }
 
 	
 	
}
