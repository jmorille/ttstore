package eu.ttbox.batch.icecat.referential.model;

public interface IIcecatModelCreator<REF, FEED> {

	REF doImport(FEED referential);
}
