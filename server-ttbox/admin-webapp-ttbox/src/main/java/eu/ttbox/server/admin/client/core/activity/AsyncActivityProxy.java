package eu.ttbox.server.admin.client.core.activity;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.AsyncProvider;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import eu.ttbox.server.admin.client.core.common.FailureHandler;
import eu.ttbox.server.admin.client.core.common.Initializer;

public final class AsyncActivityProxy<A extends Activity> extends ActivityProxy<A>  {


	/**
	 * Un provider de l'activité A.
	 */
	private final AsyncProvider<A> provider;
	
	private final FailureHandler failureHandler;

	protected boolean isCancelled;
	
	private Initializer<A> initalizer;
	
	
	public AsyncActivityProxy(AsyncProvider<A> provider, Initializer<A> initalizer, FailureHandler failureHandler) {
		this.provider = provider;
		this.failureHandler = failureHandler;
		this.initalizer = initalizer;
	}
	
	@Override
	public final void onCancel() {
		isCancelled = true;
		if (activity != null) {
			activity.onCancel();
		}
	}

	@Override
	public void start(final AcceptsOneWidget panel, final EventBus eventBus) {

		provider.get(new AsyncCallback<A>() {

			@Override
			public void onSuccess(A result) {
				activity = result;
				
				// Il se peut qu'on ait annulé cette activité pendant le téléchargement. 
				// Il faut donc s'assurer que l'activité n'a pas été annulée avant son lancement.
				if (!isCancelled) {
					initalizer.initialize(activity);
					activity.start(panel, eventBus);
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				failureHandler.onFailure(caught);
			}
		});
	}
}
