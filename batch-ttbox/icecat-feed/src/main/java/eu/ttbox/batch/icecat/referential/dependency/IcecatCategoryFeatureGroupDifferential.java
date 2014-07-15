package eu.ttbox.batch.icecat.referential.dependency;

import biz.icecat.referential.v1.CategoryFeatureGroup;
import biz.icecat.referential.v1.FeatureGroup;
import eu.ttbox.icecat.model.referential.IcecatCategory;
import eu.ttbox.icecat.model.referential.IcecatCategoryFeatureGroup;
import eu.ttbox.icecat.model.referential.IcecatFeatureGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("icecatCategoryFeatureGroupDifferential")
public class IcecatCategoryFeatureGroupDifferential extends AbstractDependencyDifferential<IcecatCategory, IcecatCategoryFeatureGroup, CategoryFeatureGroup> {

	@Autowired
	@Qualifier("icecatVocabularyDifferential")
	IIcecatVocabularyDifferential icecatVocabularyDifferential;

	@Override
	public Integer getFeedElementId(CategoryFeatureGroup elt) {
		return elt.getID();
	}

	@Override
	public boolean isValidEntity(IcecatCategoryFeatureGroup entity) {
		if (entity.getFeatureGroup() == null) {
			return false;
		}
		if (entity.getCategory() == null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isValidFeed(CategoryFeatureGroup elt) {
		return true;
	}

	@Override
	public IcecatCategoryFeatureGroup createEntity(IcecatCategory master, CategoryFeatureGroup elt) {
		Integer cfgId = elt.getID();
		IcecatCategoryFeatureGroup icecatCategoryFeatureGroup = new IcecatCategoryFeatureGroup();
		icecatCategoryFeatureGroup.setId(cfgId);
		icecatCategoryFeatureGroup.setCategory(master);
		return icecatCategoryFeatureGroup;
	}

	@Override
	public IcecatCategoryFeatureGroup updateEntity(IcecatCategory master, CategoryFeatureGroup elt, IcecatCategoryFeatureGroup entity) {

		// Values
		entity.setDisplayOrder(elt.getNo());

		// Manage FeatureGroup Dependency
		List<FeatureGroup> featureGroups = elt.getFeatureGroups();
		FeatureGroup selectFeatureGroup = null;
		if (featureGroups != null && featureGroups.size() > 0) {
			selectFeatureGroup = featureGroups.get(0);
			if (featureGroups.size() > 1) {
				log.warn("Ignore FeatureGroups > 1 for {}", entity);
			}
		}
		if (selectFeatureGroup != null && selectFeatureGroup.getID() != null) {
			IcecatFeatureGroup icecatFeatureGroup = doImportReferentialFeatureGroup(selectFeatureGroup);
			entity.setFeatureGroup(icecatFeatureGroup);
		} else {
			entity.setFeatureGroup(null);
		}

		return entity;
	}

	public IcecatFeatureGroup doImportReferentialFeatureGroup(FeatureGroup featureGroup) {
		Integer sid = null;
		Integer featureGroupId = featureGroup.getID();

		IcecatFeatureGroup icecatFeature = (IcecatFeatureGroup) getIcecatDAO().getById(featureGroupId, IcecatFeatureGroup.class);
		if (icecatFeature == null) {
			icecatFeature = new IcecatFeatureGroup();
			icecatFeature.setId(featureGroupId);
			sid = getIcecatDAO().getSidSequenceNextVal();
			icecatFeature.setSid(sid);
		} else {
			sid = icecatFeature.getSid();
		}
		// Save
		getIcecatDAO().saveObject(icecatFeature);
		getIcecatDAO().flush();

		// Names
		this.icecatVocabularyDifferential.importVocabulary(sid, featureGroup.getNames());
		getIcecatDAO().flush();

		return icecatFeature;
	}

	// @Override
	// protected void persitToDelete(List<IcecatCategoryFeatureGroup> entities, List<IcecatCategoryFeatureGroup> toDelete) {
	// if (!toDelete.isEmpty()) {
	// IcecatDAO icecatDAO = getIcecatDAO();
	// for (IcecatCategoryFeatureGroup del : toDelete) {
	// log.info("Ask Delete {} ", del);
	// // Delete Dependencies
	// // // IcecatCategoryFeature
	// // if ( del.getFeatureGroup()!=null) {
	// // icecatDAO.deleteAll(del.getFeatureGroup());
	// // }
	// //Delete Entity
	// icecatDAO.deleteAll(del);
	// icecatDAO.flush();
	// }
	// // Delete Entity from Collection
	// // log.debug("toDelete List {} entities", toDelete.size());
	// // entities.removeAll(toDelete);
	// }
	// }

}
