package eu.ttbox.server.admin.client.core.activity;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public abstract class ActivityProxy <A extends Activity> implements Activity {

	/**
	 * L'activitÃ© proxifiÃ©.
	 */
	protected A activity;
	
	@Override
	public String mayStop() {
		if (activity != null) {
			return activity.mayStop();
		}
		return null;
	}

	@Override
	public void onStop() {
		if (activity != null) {
			activity.onStop();
		}
	}
	
	public abstract void start(final AcceptsOneWidget panel, final EventBus eventBus);

}
