package eu.ttbox.batch.icecat.referential.dependency;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import biz.icecat.referential.v1.Feature;
import eu.ttbox.icecat.model.referential.IcecatCategory;
import eu.ttbox.icecat.model.referential.IcecatCategoryFeature;
import eu.ttbox.icecat.model.referential.IcecatCategoryFeatureGroup;
import eu.ttbox.icecat.model.referential.IcecatFeature;

@Transactional
@Service("icecatCategoryFeatureDifferential")
public class IcecatCategoryFeatureDifferential extends AbstractDependencyDifferential<IcecatCategory, IcecatCategoryFeature, Feature> {

	@Override
	public Integer getFeedElementId(Feature elt) {
		return elt.getCategoryFeatureID();
	}

	@Override
	public boolean isValidEntity(IcecatCategoryFeature entity) {
		return true;
	}

	@Override
	public boolean isValidFeed(Feature elt) {
		return true;
	}

	@Override
	public IcecatCategoryFeature createEntity(IcecatCategory master, Feature elt) {
		IcecatCategoryFeature entity = new IcecatCategoryFeature();
		entity.setId(getFeedElementId(elt));
		entity.setCategory(master);

		return entity;
	}

	@Override
	public IcecatCategoryFeature updateEntity(IcecatCategory master, Feature elt, IcecatCategoryFeature entity) {

		// Values
		Integer displayOrder = elt.getNo();
		if (displayOrder == null) {
			displayOrder = Integer.valueOf(0);
		}
		entity.setDisplayOrder(displayOrder);
		if (elt.isSearchable()) {
			entity.setSearchable(1);
		} else {
			entity.setSearchable(0);
		}
		entity.setMandatory(0);
		// entity.setUpdated( new Date());

		//

		// CategoryFeatureGroup Dependencies

		if (elt.getID() != null) {
			Integer featureId = elt.getCategoryFeatureGroupID();
			boolean needUpdate = entity.getCategoryFeatureGroup() == null;
			if (!needUpdate) {
				needUpdate = !featureId.equals(entity.getCategoryFeatureGroup().getId());
			}
			if (needUpdate) {
				IcecatCategoryFeatureGroup feature = (IcecatCategoryFeatureGroup) getIcecatDAO().getById(featureId, IcecatCategoryFeatureGroup.class);
				if (feature == null) {
					log.warn("No feature Id {} => Need to Create", featureId);
					// FIXME Nor return null
					return null;
				}
				entity.setCategoryFeatureGroup(feature);
			}
		} else {
			entity.setCategoryFeatureGroup(null);
		}

		// Feature Dependencies
		if (elt.getID() != null) {
			Integer featureId = elt.getID();
			boolean needUpdate = entity.getFeature() == null;
			if (!needUpdate) {
				needUpdate = !featureId.equals(entity.getFeature().getId());
			}
			if (needUpdate) {
				IcecatFeature feature = (IcecatFeature) getIcecatDAO().getById(featureId, IcecatFeature.class);
				if (feature == null) {
					log.warn("************** No feature Id {} => Need to Create", featureId);
				}
				entity.setFeature(feature);
			}
		} else {
			entity.setFeature(null);
		}

		// TODO Auto-generated method stub
		return entity;
	}

	// @Override
	// protected void persitToDelete(List<IcecatCategoryFeature> entities, List<IcecatCategoryFeature> toDelete) {
	// if (!toDelete.isEmpty()) {
	// for (IcecatCategoryFeature del : toDelete) {
	// log.info("Ask Delete {} ", del);
	// }
	// }
	// super.persitToDelete(entities, toDelete);
	// }
	
	protected boolean isValid(IcecatCategoryFeature entity) {
		boolean isValid = true;
		if (isValid) {
			isValid = entity.getFeature()!=null;
		}
		if (isValid) {
			isValid = entity.getCategory()!=null;
		}
		if (isValid) {
			isValid = entity.getCategoryFeatureGroup()!=null;
		}
		return isValid;
	}
}
