package eu.ttbox.server.admin.client.core.activity;

import com.google.gwt.place.shared.Place;

public interface PlaceActivityMapping<A extends PlaceActivity<P>, P extends Place> {

	ActivityProxy<A> getActivity(P place);

}
