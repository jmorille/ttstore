package eu.ttbox.server.admin.client.application.activity;

import com.google.gwt.activity.shared.FilteredActivityMapper;
import com.google.gwt.activity.shared.FilteredActivityMapper.Filter;
import com.google.gwt.place.shared.Place;

/**
 * AppPlaceFilter est utilsé dans un {@link FilteredActivityMapper}
 * conjointement avec le {@link AppActivityMapper}.
 * 
 * Les AppPlaceFilter est un contrôleur dans le sens où en fonction des données
 * d'une place, il peut retourner une autre place pour lancer une activité autre
 * que prévue initialement.
 * 
 * Typiquement, nous mettons ici les contrôles d'habilitation, si par exemple
 * l'utilisateur demande une place à laquelle il n'a pas droit d'accéder, le
 * filtre renvoie par exemple une place de substituion, par exemple l'cran
 * d'accueil.
 * 
 * @see{@link fr.generali.accueilclient.gwtpoc.client.application.ioc.factory.impl.DesktopApplicationFactory#getActivityManager()}
 * 
 * @author M. Abdennebi 20 févr. 2012
 */
public class AppPlaceFilter implements Filter {

	// Pour l'instant le filtre ne fait pas grand chose
	
	@Override
	public Place filter(Place place) {
		return place;
	}

}
