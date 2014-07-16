package eu.ttbox.server.admin.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import eu.ttbox.server.admin.client.application.AdminApplication;
import eu.ttbox.server.admin.client.application.ioc.gin.GinApplicationInjector;

/**
 * @norme Le nom du EntryPoint doit être suffixé par "EntryPoint".
 */
public class GinAdminTTBoxEntryPoint implements EntryPoint  {
	
	private static final Logger LOG = Logger.getLogger("GinAdminTTBoxEntryPoint");

	public void onModuleLoad() {

		LOG.info("Démarrage de l'application en mode 'IOC GIN'");

		final GinApplicationInjector factory = GWT.create(GinApplicationInjector.class);
		AdminApplication application = new AdminApplication(factory, factory, factory);

		application.startup(RootLayoutPanel.get());
	}

}
