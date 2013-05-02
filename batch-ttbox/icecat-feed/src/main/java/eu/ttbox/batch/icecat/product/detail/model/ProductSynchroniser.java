package eu.ttbox.batch.icecat.product.detail.model;

import biz.icecat.files.v1.IcecatFile;
import biz.icecat.referential.v1.Product;
import eu.ttbox.icecat.model.product.IcecatProduct;

public interface ProductSynchroniser {

	boolean isIcecatProductValid(IcecatProduct product);

	IcecatProduct updateIcecatProductFromFile(IcecatProduct entity, IcecatFile productFile);

	IcecatProduct updateIcecatProductWithAllDependencies(IcecatProduct entity, Product productFile);

}