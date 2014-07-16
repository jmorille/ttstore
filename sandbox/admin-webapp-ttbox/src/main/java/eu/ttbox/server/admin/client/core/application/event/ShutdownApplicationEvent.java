package eu.ttbox.server.admin.client.core.application.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;


public class ShutdownApplicationEvent extends GwtEvent<ShutdownApplicationEvent.Handler> {

	public interface Handler extends EventHandler {

		void onShutdown(ShutdownApplicationEvent event);

	}

	public static final Type<ShutdownApplicationEvent.Handler> TYPE = new Type<ShutdownApplicationEvent.Handler>();

	@Override
	public final Type<ShutdownApplicationEvent.Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ShutdownApplicationEvent.Handler handler) {
		handler.onShutdown(this);
	}
}
