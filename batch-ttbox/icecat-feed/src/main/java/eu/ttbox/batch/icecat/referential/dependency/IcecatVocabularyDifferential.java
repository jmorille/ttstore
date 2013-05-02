package eu.ttbox.batch.icecat.referential.dependency;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.icecat.referential.v1.Name;
import biz.icecat.referential.v1.Names;
import eu.ttbox.icecat.model.referential.IcecatLanguageEnum;
import eu.ttbox.icecat.model.referential.IcecatVocabulary;

@Service("icecatVocabularyDifferential")
@Transactional(propagation = Propagation.REQUIRED)
public class IcecatVocabularyDifferential extends AbstractLangDependencyDifferential<Integer, IcecatVocabulary, Name> implements IIcecatVocabularyDifferential {

	@Override
	public void afterPropertiesSet() throws Exception {
		setLogValidatedMessgage(false);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void importVocabulary(Integer sid, Names name) {
		List<Name> names = null;
		if (name != null) {
			names = name.getNames();
		}
		importVocabulary(sid, names);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void importVocabulary(Integer sid, List<Name> names) {
		if (names == null || names.isEmpty()) {
			return;
		}

		List<IcecatVocabulary> entities = getIcecatDAO().getIcecatVocabularyBySid(sid);
		if (entities == null) {
			entities = new ArrayList<IcecatVocabulary>();
		}
		this.doImportDependencies(sid, entities, names);
	}

	@Override
	public Integer getFeedElementId(Name elt) {
		return elt.getID();
	}

	@Override
	public Integer getFeedLangId(Name elt) {
		return elt.getLangid();
	}

	@Override
	public boolean isValidEntity(IcecatVocabulary entity) {
		if (entity.getLangid() == null) {
			return false;
		}

		if (StringUtils.isEmpty(entity.getValue())) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isValidFeed(Name elt) {
		String valueContent = getNameValue(elt);
		if (StringUtils.isBlank(valueContent)) {
			return false;
		}
		return true;
	}

	public String getNameValue(Name elt) {
		String valueContent = elt.getAttributeValue();
		// grrrr, Parfois en body des tags et parfois dans l'attribut value.
		if (StringUtils.isEmpty(valueContent)) {
			valueContent = elt.getValue();
		}
		return valueContent;
	}

	@Override
	public IcecatVocabulary createEntity(Integer master, Name elt) {
		IcecatVocabulary vocEntity = new IcecatVocabulary();
		vocEntity.setId(getFeedElementId(elt));
		vocEntity.setLangid(IcecatLanguageEnum.getByLangId(getFeedLangId(elt)));
		vocEntity.setSid(master);
		return vocEntity;
	}

	@Override
	public IcecatVocabulary updateEntity(Integer master, Name elt, IcecatVocabulary entity) {
		String valueContent = getNameValue(elt);
		// Validate
		valueContent = StringUtils.trimToNull(valueContent);
		entity.setValue(valueContent);
		return entity;
	}

}
