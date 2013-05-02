package eu.ttbox.store.domain.model;

import java.io.Serializable;

/**
 * Base class for Model objects. Child objects should implement toString(),
 * equals() and hashCode();
 * 
 * <p>
 * Inspired by Matt Raible's Appfuse.
 * </p>
 * 
 * @author Jerome Morille
 */
public interface PersistantModelObject<ID extends Serializable> extends Serializable {

	
	/**
	 * Get the Domain Object Identifier.
	 * @return The Identifier.
	 */
 
	ID getId();
	 

}
