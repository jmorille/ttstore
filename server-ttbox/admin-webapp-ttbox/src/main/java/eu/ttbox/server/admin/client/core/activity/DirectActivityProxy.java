package eu.ttbox.server.admin.client.core.activity;

import javax.inject.Provider;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import eu.ttbox.server.admin.client.core.common.Initializer;

public class DirectActivityProxy<A extends Activity> extends ActivityProxy<A>  {

private Provider<A> provider;
	
	private Initializer<A> initalizer;

	public DirectActivityProxy(Provider<A> provider, Initializer<A> initalizer) {
		this.provider = provider;;
		this.initalizer = initalizer;
	}
	
	@Override
	public final void onCancel() {
		if (activity != null) {
			activity.onCancel();
		}
	}

	@Override
	public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
		activity = provider.get();
		initalizer.initialize(activity);
		activity.start(panel, eventBus);
	}

}
