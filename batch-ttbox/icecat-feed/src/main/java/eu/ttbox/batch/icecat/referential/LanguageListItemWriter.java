package eu.ttbox.batch.icecat.referential;

import biz.icecat.referential.v1.LanguageList.Language;
import eu.ttbox.icecat.model.referential.IcecatLanguage;
import eu.ttbox.icecat.model.referential.IcecatLanguageEnum;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("languageListsIcecatItemWriter")
public class LanguageListItemWriter extends AbstractReferentialItemWriter<Language> implements ItemWriter<Language> {

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int doImport(Language referential) {
		int importCount = 0;
		Integer sid = null;
		Integer entityId = referential.getID();
		// Test For Enum Value
		IcecatLanguageEnum langEnum = IcecatLanguageEnum.getByLangId(entityId);
		if (langEnum == null) {
			log.warn("No Language Enum Id {} / {} / {}, need to create It", new Object[] { entityId, referential.getShortCode(), referential.getCode() });
		}
		// Get Database Entity
		IcecatLanguage entity = (IcecatLanguage) icecatDAO.getById(entityId, IcecatLanguage.class);
		if (entity == null) {
			entity = new IcecatLanguage();
			sid = icecatDAO.getSidSequenceNextVal();
			entity.setId(entityId);
			entity.setSid(sid);
			importCount++;
		} else {
			sid = entity.getSid();
		}
		entity.setShortCode(referential.getShortCode());
		entity.setCode(referential.getCode());
		// Names
		this.icecatVocabularyDifferential.importVocabulary(sid, referential.getNames());

		// Save Entity
		icecatDAO.saveObject(entity);

		return importCount;
	}

}
