package eu.ttbox.icecat.model;

import java.util.Map;

import eu.ttbox.icecat.model.referential.IcecatLanguageEnum;
import eu.ttbox.icecat.model.referential.IcecatTex;

public interface IIcecatTexContainer  {

	public Map<IcecatLanguageEnum, IcecatTex> getDescriptions();

	public void setDescriptions(Map<IcecatLanguageEnum, IcecatTex> descriptions);

	
}
