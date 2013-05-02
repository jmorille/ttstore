package eu.ttbox.batch.icecat.product.detail.dependency;

import org.springframework.stereotype.Service;

import biz.icecat.referential.v1.ProductGallery.ProductPicture;
import eu.ttbox.batch.icecat.referential.dependency.AbstractDependencyDifferential;
import eu.ttbox.icecat.model.product.IcecatProduct;
import eu.ttbox.icecat.model.product.IcecatProductGallery;

@Service("icecatProductGalleryDifferential")
public class IcecatProductGalleryDifferential extends AbstractDependencyDifferential<IcecatProduct, IcecatProductGallery, ProductPicture> {

	@Override
	public Integer getFeedElementId(ProductPicture elt) {
		return elt.getProductPictureID();
	}

	@Override
	public boolean isValidEntity(IcecatProductGallery entity) {
		return true;
	}

	@Override
	public boolean isValidFeed(ProductPicture elt) {
		return true;
	}

	@Override
	public IcecatProductGallery createEntity(IcecatProduct iceProduct, ProductPicture elt) {
		Integer entityId = elt.getProductPictureID();
		IcecatProductGallery entity = new IcecatProductGallery();
		entity.setId(entityId);
		entity.setProduct(iceProduct);
		entity.setUpdated(iceProduct.getUpdated());
		return entity;
	}

	@Override
	public IcecatProductGallery updateEntity(IcecatProduct iceProduct, ProductPicture elt, IcecatProductGallery entity) {

		// Value
		entity.setLink(elt.getPic());
		entity.setHeight(elt.getPicHeight());
		entity.setWidth(elt.getPicWidth());
		entity.setSize(elt.getSize());
		entity.setThumbLink(elt.getThumbPic());
		// NOT IN XSD entity.setQuality(elt.get)
		return entity;
	}

}
