package eu.ttbox.batch.icecat.referential.dependency;

import java.util.List;

import eu.ttbox.icecat.model.IIcecatPersistantModelObject;

public interface IDependencyDifferential<MASTER, REF extends IIcecatPersistantModelObject, FEED> {

	Integer getFeedElementId(FEED elt);

	REF createEntity(MASTER master, FEED elt);

	REF updateEntity(MASTER master, FEED elt, REF entity);

	boolean isValidEntity(REF entity);

	void doImportDependencies(MASTER master, List<REF> entities, List<FEED> elements);

	void doImportDependencies(MASTER master, List<REF> entities, List<FEED> elements, boolean create, boolean update, boolean delete);
}
