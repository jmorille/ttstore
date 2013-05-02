package eu.ttbox.server.admin.client.core.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.place.shared.Place;

public abstract class PlaceActivity <P extends Place> extends AbstractActivity  {

	private P place;

	public void setPlace(P place) {
		this.place = place;
	}

	public P getPlace() {
		return place;
	}
	
}
