package eu.ttbox.batch.icecat.product.detail.dependency;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import biz.icecat.referential.v1.Product;
import biz.icecat.referential.v1.ProductRelated;
import eu.ttbox.batch.icecat.product.detail.ProductImporter;
import eu.ttbox.batch.icecat.product.detail.model.ProductSynchroniser;
import eu.ttbox.batch.icecat.referential.dependency.AbstractDependencyDifferential;
import eu.ttbox.icecat.model.product.IcecatProduct;
import eu.ttbox.icecat.model.product.IcecatProductRelated;
import eu.ttbox.icecat.model.product.QualityEnum;
import eu.ttbox.icecat.model.referential.IcecatCategory;

/**
 * Note: If ProductRelated_ID = 0, it means, that this relation was generated dynamically, according to RelationsList.xml rules.
 * 
 * @author jmorille
 * 
 */
@Service("icecatProductRelatedDifferential")
public class IcecatProductRelatedDifferential extends AbstractDependencyDifferential<IcecatProduct, IcecatProductRelated, ProductRelated> {

	/**
	 * Note: If ProductRelated_ID = 0, it means, that this relation was generated dynamically, according to RelationsList.xml rules.
	 */
	private final static Integer AUTO_GEN_ID = Integer.valueOf(0);

	@Resource(name = "icecatProductSynchroniser")
	ProductSynchroniser productSynchroniser;

	@Value(value = "${icecat.product.related.createLightProduct}")
	boolean createLightProduct = true;

	@Override
	public void afterPropertiesSet() throws Exception {
		setLogValidatedMessgage(false);
	}

	@Override
	public Integer getFeedElementId(ProductRelated elt) {
		throw new RuntimeException("Not implemented");
	}

	public Integer getFeedElementId(ProductRelated elt, IcecatProduct product) {
		Integer prId = elt.getID();
		if (AUTO_GEN_ID.equals(prId)) {
			Integer productMaster = product.getId();
			for (Product prod : elt.getProducts()) {
				// FIXME Non Sens
				Integer relatedId = prod.getID();
				// Generated new Unique id with combine Ak
				final int prime = 31;
				int result = 1;
				result = prime * result + productMaster.intValue();
				result = prime * result + relatedId.intValue();
				return -result;
			}
		}
		return prId;
	}

	@Override
	public boolean isValidEntity(IcecatProductRelated entity) {
		if (entity.getProductRelated() == null) {
			return false;
		}
		if (entity.getProduct() == null) {
			return false;
		}
		if (entity.getCategory() == null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isValidFeed(ProductRelated elt) {
		if (!elt.isSetProducts()) {
			return false;
		}
		// else if (elt.getProduct().getID() == null) {
		// return false;
		// }
		return true;
	}

	@Override
	public IcecatProductRelated createEntity(IcecatProduct product, ProductRelated elt) {
		Integer entityId = getFeedElementId(elt, product);
		IcecatProductRelated entity = new IcecatProductRelated();
		entity.setId(entityId);
		entity.setProduct(product);
		// Updated
		entity.setUpdated(product.getUpdated());
		return entity;
	}

	@Override
	public IcecatProductRelated updateEntity(IcecatProduct product, ProductRelated elt, IcecatProductRelated entity) {

		// values
		entity.setReversed(elt.getReversed());
		entity.setPreferred(elt.getPreferred());

		// Category Dependency
		Integer catId = elt.getCategoryID();
		if (catId != null) {
			boolean needUpdate = entity.getCategory() == null;
			if (!needUpdate) {
				needUpdate = !catId.equals(entity.getCategory().getId());
			}
			if (needUpdate) {
				IcecatCategory cat = (IcecatCategory) getIcecatDAO().getById(catId, IcecatCategory.class);
				entity.setCategory(cat);
			}
		} else {
			entity.setCategory(null);
		}

		// Product Dependency
		if (elt.isSetProducts()) {
			for (Product subProduct : elt.getProducts()) {
				Integer subProductId = subProduct.getID();
				if (subProductId != null) {
					boolean needUpdate = entity.getProductRelated() == null;
					if (!needUpdate) {
						needUpdate = !subProductId.equals(entity.getProductRelated().getId());
					}
					if (needUpdate) {
						IcecatProduct subIceProd = (IcecatProduct) getIcecatDAO().getById(subProductId, IcecatProduct.class);
						if (subIceProd == null) {
							// Not existing Sub Product , Need to Create It
							subIceProd = createLightProduct(entity, subProduct);
						}
						entity.setProductRelated(subIceProd);
					}
				} else {
					entity.setProductRelated(null);
				}
			}

		}
		return entity;
	}

	public IcecatProduct createLightProduct(IcecatProductRelated entity, Product subProduct) {
		IcecatProduct subIceProd = null;
		if (createLightProduct) {
			Integer subProductId = subProduct.getID();
			subIceProd = new IcecatProduct();
			subIceProd.setId(subProductId);
			productSynchroniser.updateIcecatProductWithAllDependencies(subIceProd, subProduct);
			// Overlay
			subIceProd.setEditor(QualityEnum.LIGHT.name());
			subIceProd.setUpdated(ProductImporter.ZERO_VERSION_DATE);

			if (subIceProd.getCategory() == null) {
				subIceProd.setCategory(entity.getCategory());
			}
			if (this.productSynchroniser.isIcecatProductValid(subIceProd)) {
				getIcecatDAO().saveObject(subIceProd);
				getIcecatDAO().flush();
			} else {
				subIceProd = null;
				log.warn("Related exlusion No Mapping for Product Id " + subProductId);
			}
		}
		return subIceProd;
	}

	@Override
	protected Map<Integer, ProductRelated> getDedupElementById(List<ProductRelated> elements, IcecatProduct master) {
		Map<Integer, ProductRelated> eltsById = new HashMap<Integer, ProductRelated>();
		Map<Integer, ProductRelated> eltsBySubProductId = new HashMap<Integer, ProductRelated>();
		for (ProductRelated elt : elements) {
			Integer feedId = getFeedElementId(elt, master);
			if (elt.isSetProducts()) {
				for (Product prod : elt.getProducts()) {
					Integer subProductId = prod.getID();
					if (eltsById.containsKey(feedId)) {
						log.warn("Ignore Duplicated Id {}", feedId);
					} else {
						if (eltsBySubProductId.containsKey(subProductId)) {
							log.warn("Ignore Duplicated Lang Id {} for Id {}", subProductId, feedId);
						} else {
							eltsById.put(feedId, elt);
							eltsBySubProductId.put(subProductId, elt);
						}
					}
				}

			} else {
				// log.warn("No sub Product for Product Related ID " + feedId);
			}
		}
		return eltsById;
	}

}
