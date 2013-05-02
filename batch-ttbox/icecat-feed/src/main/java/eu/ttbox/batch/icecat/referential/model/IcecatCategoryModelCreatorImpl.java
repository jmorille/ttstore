package eu.ttbox.batch.icecat.referential.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import biz.icecat.referential.v1.Category;
import biz.icecat.referential.v1.Keywords;
import biz.icecat.referential.v1.Name;
import biz.icecat.referential.v1.Names;
import biz.icecat.referential.v1.ParentCategory;
import eu.ttbox.batch.icecat.referential.dependency.IDependencyDifferential;
import eu.ttbox.icecat.model.referential.IcecatCategory;
import eu.ttbox.icecat.model.referential.IcecatCategoryKeywords;

@Transactional
@Service("icecatCategoryModelCreator")
public class IcecatCategoryModelCreatorImpl extends AbstractIcecatModelCreatorImpl<IcecatCategory, Category> {

	@Resource(name = "icecatCategoryKeywordsDifferential")
	IDependencyDifferential<IcecatCategory, IcecatCategoryKeywords, Keywords> categoryKeywordsDifferential;

	@Override
	public Integer getFeedElementId(Category elt) {
		return elt.getID();
	}

	@Override
	public IcecatCategory createEntity(Category elt) {
		Integer entityId = elt.getID();
		Integer sid = getIcecatDAO().getSidSequenceNextVal();
		Integer tid = getIcecatDAO().getTidSequenceNextVal();
		IcecatCategory entity = new IcecatCategory();
		entity.setId(entityId);
		entity.setSid(sid);
		entity.setTid(tid);
		return entity;
	}

	@Override
	public IcecatCategory updateEntity(Category elt, IcecatCategory entity) {
		Integer sid = entity.getSid();
		Integer tid = entity.getTid();

		// Values
		entity.setUnspcCategory(StringUtils.trimToNull(elt.getAttributeUNCATID()));
		if (elt.isSetSearchable() && elt.isSearchable()) {
			entity.setSearchable(Integer.valueOf(1));
		} else {
			entity.setSearchable(Integer.valueOf(0));
		}
		entity.setLowDefPicture(StringUtils.trimToNull(elt.getLowPic()));
		entity.setThumbPicture(StringUtils.trimToNull(elt.getThumbPic()));

		// Parent Category
		ParentCategory parentCategory = elt.getParentCategory();
		IcecatCategory parentEntity = null;
		if (parentCategory != null) {
			Integer parentId = parentCategory.getID();
			Integer parentSid = null;
			Integer parentTid = null;
			parentEntity = (IcecatCategory) getIcecatDAO().getById(parentId, IcecatCategory.class);
			if (parentEntity == null) {
				parentEntity = new IcecatCategory();
				parentEntity.setId(parentId);
				parentSid = getIcecatDAO().getSidSequenceNextVal();
				parentTid = getIcecatDAO().getTidSequenceNextVal();
				parentEntity.setSid(parentSid);
				parentEntity.setTid(parentTid);
				parentEntity.setUpdated(new Date());
			} else {
				parentSid = parentEntity.getSid();
				parentTid = parentEntity.getTid();
			}
			// Default Values
			parentEntity.setSearchable(Integer.valueOf(0));
			// Save
			getIcecatDAO().saveObject(parentEntity);
			// Parent Names
			if (parentCategory.getNames() != null) {
				List<Name> parentNames = null;
				for (Names names : parentCategory.getNames()) {
					if (parentNames == null) {
						parentNames = names.getNames();
					} else {
						parentNames.addAll(names.getNames());
					}
				}
				this.icecatVocabularyDifferential.importVocabulary(parentSid, parentNames);
			}
		}

		entity.setParent(parentEntity);

		// entity.setUpdated(new Date());

		// Save Entity
		getIcecatDAO().saveObject(entity);

		// Names
		this.icecatVocabularyDifferential.importVocabulary(sid, elt.getNames());

		// Description
		this.icecatTexDifferential.importDescription(tid, elt.getDescriptions());

		// Keywords
		importKeywords(entity, elt.getKeywords());

		return entity;
	}

	private void importKeywords(IcecatCategory category, List<Keywords> keywords) {
		List<IcecatCategoryKeywords> entities = category.getKeywords();
		if (entities == null) {
			entities = new ArrayList<IcecatCategoryKeywords>();
			category.setKeywords(entities);
		}
		this.categoryKeywordsDifferential.doImportDependencies(category, entities, keywords);
	}

}
