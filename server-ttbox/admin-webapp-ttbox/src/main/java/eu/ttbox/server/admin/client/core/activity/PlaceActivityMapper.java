package eu.ttbox.server.admin.client.core.activity;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Provider;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.inject.client.AsyncProvider;
import com.google.gwt.place.shared.Place;

import eu.ttbox.server.admin.client.core.common.FailureHandler;
 

 

public class PlaceActivityMapper implements ActivityMapper {

	private final FailureHandler failureHandler;

	public PlaceActivityMapper(FailureHandler failureHandler) {
		this.failureHandler = failureHandler;
	}
	
    private final Map<Class<? extends Place>, PlaceActivityMapping<? extends PlaceActivity<?>, ? extends Place>> mappers = new HashMap<Class<? extends Place>, PlaceActivityMapping<? extends PlaceActivity<?>, ? extends Place>>();
	
	@Override
	public Activity getActivity(Place place) {
		return getPlaceActivity(place);
	}

	private <P extends Place, A extends PlaceActivity<P>> ActivityProxy<A> getPlaceActivity(P place) {

		@SuppressWarnings("unchecked")
		PlaceActivityMapping<A, P> mapper = (PlaceActivityMapping<A, P>) mappers.get(place.getClass());

		ActivityProxy<A> activity = mapper.getActivity(place);

		return activity;
	}

	protected <P extends Place, A extends PlaceActivity<P>> void register(Class<P> clazz,	PlaceActivityMapping<A, P> mapper) {
		mappers.put(clazz, mapper);
	}
	
	protected <P extends Place, A extends PlaceActivity<P>> void register(Class<P> clazz, AsyncProvider<A> provider) {
		register(clazz, new AsyncPlaceActivityMapping<A, P>(provider, failureHandler));
	}
	
	protected <P extends Place, A extends PlaceActivity<P>> void register(Class<P> clazz, AsyncProvider<A> provider, FailureHandler failureHandler) {
		register(clazz, new AsyncPlaceActivityMapping<A, P>(provider, failureHandler));
	}

	protected <P extends Place, A extends PlaceActivity<P>> void register(Class<P> clazz, Provider<A> provider) {
		register(clazz, new DirectPlaceActivityMapping<A, P>(provider));
	}
	
}
