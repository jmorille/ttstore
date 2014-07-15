package eu.ttbox.batch.icecat.referential.dependency;

import biz.icecat.referential.v1.Keywords;
import eu.ttbox.icecat.model.referential.IcecatCategory;
import eu.ttbox.icecat.model.referential.IcecatCategoryKeywords;
import eu.ttbox.icecat.model.referential.IcecatLanguageEnum;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("icecatCategoryKeywordsDifferential")
public class IcecatCategoryKeywordsDifferential extends AbstractLangDependencyDifferential<IcecatCategory, IcecatCategoryKeywords, Keywords> {

	@Override
	public Integer getFeedElementId(Keywords elt) {
		return elt.getID();
	}

	@Override
	public Integer getFeedLangId(Keywords elt) {
		return elt.getLangid();
	}

	@Override
	public boolean isValidEntity(IcecatCategoryKeywords entity) {
		if (entity.getLangid() == null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isValidFeed(Keywords elt) {
		return true;
	}

	@Override
	public IcecatCategoryKeywords createEntity(IcecatCategory master, Keywords elt) {
		IcecatCategoryKeywords entity = new IcecatCategoryKeywords();
		entity.setId(elt.getID());
		entity.setLangid(IcecatLanguageEnum.getByLangId(elt.getLangid()));
		entity.setCategory(master);
		return entity;
	}

	@Override
	public IcecatCategoryKeywords updateEntity(IcecatCategory master, Keywords elt, IcecatCategoryKeywords entity) {
		String value = StringUtils.trimToNull(elt.getValue());
		entity.setKeywords(value);
		return entity;
	}

}
