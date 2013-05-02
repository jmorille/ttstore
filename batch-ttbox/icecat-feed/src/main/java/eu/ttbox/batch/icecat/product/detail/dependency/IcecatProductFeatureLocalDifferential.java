package eu.ttbox.batch.icecat.product.detail.dependency;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import biz.icecat.referential.v1.ProductFeature;
import eu.ttbox.batch.icecat.referential.dependency.AbstractDependencyDifferential;
import eu.ttbox.icecat.model.product.IcecatProduct;
import eu.ttbox.icecat.model.product.IcecatProductFeatureLocal;
import eu.ttbox.icecat.model.referential.IcecatCategoryFeature;

@Service("icecatProductFeatureLocalDifferential")
public class IcecatProductFeatureLocalDifferential extends AbstractDependencyDifferential<IcecatProduct, IcecatProductFeatureLocal, ProductFeature> {

	@Override
	public Integer getFeedElementId(ProductFeature elt) {
		return elt.getID();
	}

	@Override
	public boolean isValidEntity(IcecatProductFeatureLocal entity) {
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
	public IcecatProductFeatureLocal createEntity(IcecatProduct iceProduct, ProductFeature elt) {
		Integer entityId = elt.getID();
		IcecatProductFeatureLocal entity = new IcecatProductFeatureLocal();
		entity.setId(entityId);
		entity.setProduct(iceProduct);
		entity.setUpdated(iceProduct.getUpdated());
		return entity;
	}

	@Override
	public IcecatProductFeatureLocal updateEntity(IcecatProduct product, ProductFeature elt, IcecatProductFeatureLocal entity) {

		// Value
		entity.setValue(StringUtils.trimToNull(elt.getValue()));

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

		return entity;
	}

}
