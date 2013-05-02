package eu.ttbox.batch.icecat.product.detail.dependency;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import biz.icecat.referential.v1.Product;
import biz.icecat.referential.v1.ProductBundled;
import eu.ttbox.batch.icecat.product.detail.ProductImporter;
import eu.ttbox.batch.icecat.product.detail.model.ProductSynchroniser;
import eu.ttbox.batch.icecat.referential.dependency.AbstractDependencyDifferential;
import eu.ttbox.icecat.model.product.IcecatProduct;
import eu.ttbox.icecat.model.product.IcecatProductBundled;
import eu.ttbox.icecat.model.product.QualityEnum;

@Service("icecatProductBundledDifferential")
public class IcecatProductBundledDifferentialImpl extends AbstractDependencyDifferential<IcecatProduct, IcecatProductBundled, ProductBundled> {

	@Resource(name = "icecatProductSynchroniser")
	ProductSynchroniser productSynchroniser;

	@Value(value = "${icecat.product.bundled.createLightProduct}")
	boolean createLightProduct = true;

	@Override
	public void afterPropertiesSet() throws Exception {
		setLogValidatedMessgage(false);
	}

	@Override
	public Integer getFeedElementId(ProductBundled elt) {
		return elt.getID();
	}

	@Override
	public boolean isValidEntity(IcecatProductBundled entity) {
		if (entity.getProductBundled() == null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isValidFeed(ProductBundled elt) {

		if (!elt.isSetProducts()) {
			return false;
		}
		return true;
	}

	@Override
	protected Map<Integer, ProductBundled> getDedupElementById(List<ProductBundled> elements, IcecatProduct master) {
		Map<Integer, ProductBundled> eltsById = new HashMap<Integer, ProductBundled>();
		Map<Integer, ProductBundled> eltsBySubProductId = new HashMap<Integer, ProductBundled>();
		for (ProductBundled elt : elements) {
			Integer feedId = getFeedElementId(elt);
			if (feedId != null) {
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
					log.warn("No sub Product for Product Bundled ID {}", feedId);
				}
			}
		}
		return eltsById;
	}

	@Override
	public IcecatProductBundled createEntity(IcecatProduct product, ProductBundled elt) {
		Integer productBundledId = elt.getID();
		IcecatProductBundled iceRelated = new IcecatProductBundled();
		iceRelated.setId(productBundledId);
		iceRelated.setProduct(product);
		return iceRelated;
	}

	@Override
	public IcecatProductBundled updateEntity(IcecatProduct product, ProductBundled elt, IcecatProductBundled entity) {

		// Product Dependency
		List<Product> subProducts = elt.getProducts();
		for (Product subProduct : subProducts) {

			// if (subProducts.size() == 1) {
			// subProduct = subProducts.get(0);
			// } else if (subProducts.size() > 1) {
			// log.warn("Ignore some related " + subProducts.size() +
			// " products");
			// }
			if (subProduct != null) {
				Integer subProductId = subProduct.getID();
				if (subProductId != null) {
					boolean needUpdate = entity.getProductBundled() == null;
					if (!needUpdate) {
						needUpdate = !subProductId.equals(entity.getProductBundled().getId());
					}

					if (needUpdate) {
						IcecatProduct subIceProd = (IcecatProduct) getIcecatDAO().getById(subProductId, IcecatProduct.class);
						if (subIceProd == null) {
							// Not existing Sub Product , Need to Create It
							subIceProd = createLightProduct(product, entity, subProduct);

						}
						entity.setProductBundled(subIceProd);
					}
				} else {
					entity.setProductBundled(null);
				}
			}
		}
		return entity;
	}

	public IcecatProduct createLightProduct(IcecatProduct product, IcecatProductBundled entity, Product subProduct) {
		IcecatProduct subIceProd = null;
		if (createLightProduct) {
			Integer subProductId = subProduct.getID();
			subIceProd = new IcecatProduct();
			subIceProd.setId(subProductId);
			productSynchroniser.updateIcecatProductWithAllDependencies(subIceProd, subProduct);
			// Surcharge
			subIceProd.setEditor(QualityEnum.LIGHT.name());
			subIceProd.setUpdated(ProductImporter.ZERO_VERSION_DATE);

			if (subIceProd.getCategory() == null) {
				subIceProd.setCategory(product.getCategory());
			}
			if (this.productSynchroniser.isIcecatProductValid(subIceProd)) {
				getIcecatDAO().saveObject(subIceProd);
				getIcecatDAO().flush();
			} else {
				subIceProd = null;
				log.warn("Bundled exlusion No Mapping for Product Id {}", subProductId);
			}
		}
		return subIceProd;

	}
}
