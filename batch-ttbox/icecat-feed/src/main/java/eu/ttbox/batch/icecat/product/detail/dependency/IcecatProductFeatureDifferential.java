package eu.ttbox.batch.icecat.product.detail.dependency;

import biz.icecat.referential.v1.ProductFeature;
import eu.ttbox.batch.icecat.referential.dependency.AbstractDependencyDifferential;
import eu.ttbox.icecat.model.product.IcecatProduct;
import eu.ttbox.icecat.model.product.IcecatProductFeature;
import eu.ttbox.icecat.model.referential.IcecatCategoryFeature;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Service("icecatProductFeatureDifferential")
public class IcecatProductFeatureDifferential extends AbstractDependencyDifferential<IcecatProduct, IcecatProductFeature, ProductFeature> {

	@Override
	public Integer getFeedElementId(ProductFeature elt) {
		return elt.getID();
	}

	@Override
	public boolean isValidEntity(IcecatProductFeature entity) {
		if (entity.getCategoryFeature() == null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isValidFeed(ProductFeature elt) {
		return true;
	}

	@Override
	public IcecatProductFeature createEntity(IcecatProduct iceProduct, ProductFeature elt) {
		Integer entityId = elt.getID();
		IcecatProductFeature entity = new IcecatProductFeature();
		entity.setId(entityId);
		entity.setProduct(iceProduct);
		entity.setUpdated(iceProduct.getUpdated());
		return entity;
	}

	@Override
	public IcecatProductFeature updateEntity(IcecatProduct product, ProductFeature elt, IcecatProductFeature entity) {
		// Update
		// entity.setUpdated(iceProduct.getUpdated());

		entity.setPresentationValue(StringUtils.trimToNull(elt.getPresentationValue()));
		entity.setValue(StringUtils.trimToNull(elt.getValue()));

		// Value
		// TODO entity.setNo(ref.getNo());
		// TODO ref.isLocalized();

		// Category Feature
		Integer catFeatureId = elt.getCategoryFeatureID();
		if (catFeatureId != null) {
			boolean needUpdate = entity.getCategoryFeature() == null;
			if (!needUpdate) {
				needUpdate = !catFeatureId.equals(entity.getCategoryFeature().getId());
			}
			if (needUpdate) {
				IcecatCategoryFeature catFeature = (IcecatCategoryFeature) getIcecatDAO().getById(catFeatureId, IcecatCategoryFeature.class);
				entity.setCategoryFeature(catFeature);
			}
		} else {
			entity.setCategoryFeature(null);
		}

		// // Category Feature Group
		// Integer catFeatureGroupId = elt.getCategoryFeature_ID();
		// if (catFeatureGroupId != null) {
		// boolean needUpdate = entity.getCategoryFeatureGroup() == null;
		// if (!needUpdate) {
		// needUpdate = !catFeatureGroupId.equals(entity
		// .getCategoryFeatureGroup().getId());
		// }
		// } else {
		// entity.setCategoryFeatureGroup(null);
		// }

		return entity;
	}

}
