package eu.ttbox.server.admin.client.core.activity;

import com.google.gwt.inject.client.AsyncProvider;
import com.google.gwt.place.shared.Place;

import eu.ttbox.server.admin.client.core.common.FailureHandler;
import eu.ttbox.server.admin.client.core.common.Initializer;
 

public class AsyncPlaceActivityMapping<A extends PlaceActivity<P>, P extends Place> implements PlaceActivityMapping<A, P> {


	private final AsyncProvider<A> provider;

	private final FailureHandler failureHandler;

	public AsyncPlaceActivityMapping(AsyncProvider<A> provider, FailureHandler failureHandler) {
		this.provider = provider;
		this.failureHandler = failureHandler;
	}

	@Override
	public ActivityProxy<A> getActivity(final P place) {
		
		Initializer<A> initializer = new Initializer<A>() {

			@Override
			public void initialize(A activity) {
				activity.setPlace(place);

			}
		};
		
		AsyncActivityProxy<A> async = new AsyncActivityProxy<A>(provider, initializer, failureHandler);
		return async;
	}
	
}
