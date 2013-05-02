package eu.ttbox.icecat.model;

import eu.ttbox.icecat.model.referential.IcecatLanguageEnum;


public interface IIcecatLangModelObject extends IIcecatPersistantModelObject {

	IcecatLanguageEnum getLangid();

	//void setLangid(Integer langid);

	void setLangid(IcecatLanguageEnum langid);

}
