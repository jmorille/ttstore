package eu.ttbox.model.ui.store;


import java.io.Serializable;

public enum WebSiteStatus implements Serializable {

    OFF(false), ON(true);

    private final boolean actif;
 
    private WebSiteStatus(boolean actif) {
        this.actif = actif;
    }

    public boolean isActif() {
        return actif;
    }
}
