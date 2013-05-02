package eu.ttbox.icecat.model;

import java.io.Serializable;

public interface IIcecatPersistantModelObject extends Serializable
// PersistantModelObject<Long>
{

	/**
	 * Get the Domain Object Identifier.
	 * 
	 * @return The Identifier.
	 */

	Integer getId();

}
