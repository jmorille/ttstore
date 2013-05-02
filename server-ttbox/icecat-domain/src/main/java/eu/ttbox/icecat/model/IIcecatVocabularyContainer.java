package eu.ttbox.icecat.model;

import java.util.Map;

import eu.ttbox.icecat.model.referential.IcecatLanguageEnum;
import eu.ttbox.icecat.model.referential.IcecatVocabulary;

public interface IIcecatVocabularyContainer {

	public Map<IcecatLanguageEnum, IcecatVocabulary> getNames();

	public void setNames(Map<IcecatLanguageEnum, IcecatVocabulary> names);

}
