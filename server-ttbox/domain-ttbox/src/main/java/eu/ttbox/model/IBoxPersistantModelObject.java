package eu.ttbox.model;

import java.io.Serializable;


public interface IBoxPersistantModelObject  extends Serializable {

 
	/**
	 * Get the Domain Object Identifier.
	 * @return The Identifier.
	 */
 
	Integer getId();
	 
	Integer getVersion() ;

}
