package eu.ttbox.batch.icecat.referential.dependency;

import biz.icecat.referential.v1.Description;
import biz.icecat.referential.v1.Descriptions;
import eu.ttbox.icecat.model.referential.IcecatLanguageEnum;
import eu.ttbox.icecat.model.referential.IcecatTex;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("icecatTexDifferential")
@Transactional(propagation = Propagation.REQUIRED)
public class IcecatTexDifferential extends AbstractLangDependencyDifferential<Integer, IcecatTex, Description> implements IIcecatTexDifferential {

	@Override
	public void afterPropertiesSet() throws Exception {
		setLogValidatedMessgage(false);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void importDescription(Integer tid, Descriptions descs) {
		List<Description> descriptions = null;
		if (descs != null) {
			descriptions = descs.getDescriptions();
			importDescription(tid, descriptions);
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void importDescription(Integer tid, List<Description> descriptions) {
		if (descriptions == null || descriptions.isEmpty()) {
			return;
		}

		List<IcecatTex> entities = getIcecatDAO().getDescriptionByTid(tid);
		if (entities == null) {
			entities = new ArrayList<IcecatTex>();
		}
		doImportDependencies(tid, entities, descriptions);
	}

	@Override
	public Integer getFeedElementId(Description elt) {
		return elt.getID();
	}

	@Override
	public Integer getFeedLangId(Description elt) {
		return elt.getLangid();
	}

	@Override
	public boolean isValidEntity(IcecatTex entity) {
		if (entity.getLangid() == null) {
			return false;
		}
		if (StringUtils.isEmpty(entity.getValue())) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isValidFeed(Description elt) {
		String valueContent = getDescriptionValue(elt);
		if (StringUtils.isBlank(valueContent)) {
			return false;
		}
		return true;
	}

	public String getDescriptionValue(Description elt) {
		String valueContent = elt.getAttributeValue();
		// grrrr, Parfois en body des tags et parfois dans l'attribut value.
		if (StringUtils.isEmpty(valueContent)) {
			valueContent = elt.getContent();
		}
		return valueContent;
	}

	@Override
	public IcecatTex createEntity(Integer master, Description elt) {
		IcecatTex vocEntity = new IcecatTex();
		vocEntity.setId(getFeedElementId(elt));
		vocEntity.setLangid(IcecatLanguageEnum.getByLangId(elt.getLangid()));
		vocEntity.setTid(master);
		return vocEntity;
	}

	@Override
	public IcecatTex updateEntity(Integer master, Description elt, IcecatTex entity) {
		// Value
		String valueContent = getDescriptionValue(elt);

		// Validate
		// TODO PARSE TO HTML valueContent = left(valueContent, 9000,
		// "Description");
		valueContent = StringUtils.trimToNull(valueContent);
		if (valueContent != null && valueContent.length() > 4000) {
			valueContent = left(valueContent, 4000, "IcecatTex updateEntity");
		}
		entity.setValue(valueContent);
		return entity;
	}

}
