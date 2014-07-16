package eu.ttbox.server.admin.client.core.activity;

import javax.inject.Provider;

import com.google.gwt.place.shared.Place;

import eu.ttbox.server.admin.client.core.common.Initializer;

public class DirectPlaceActivityMapping<A extends PlaceActivity<P>, P extends Place>  implements PlaceActivityMapping<A,P>  {


	private final Provider<A> provider;

	public DirectPlaceActivityMapping(Provider<A> provider) {
		this.provider = provider;
	}
	
	@Override
	public ActivityProxy<A> getActivity(final P place) {
		
		Initializer<A> initializer = new Initializer<A>() {

			@Override
			public void initialize(A activity) {
				activity.setPlace(place);

			}
		};
		
		DirectActivityProxy<A> async = new DirectActivityProxy<A>(provider, initializer);
		return async;
	}
	
}
