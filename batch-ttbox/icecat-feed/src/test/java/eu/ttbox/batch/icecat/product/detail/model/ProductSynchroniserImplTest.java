package eu.ttbox.batch.icecat.product.detail.model;

import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.Test;

import biz.icecat.files.v1.IcecatFile;
import eu.ttbox.batch.icecat.product.detail.ProductImporter;
import eu.ttbox.icecat.model.product.IcecatProduct;

public class ProductSynchroniserImplTest {

	@Test
	public void updateIcecatProductFromFile() {
		ProductSynchroniserImpl service = new ProductSynchroniserImpl();
		IcecatProduct entity = new IcecatProduct();
		IcecatFile productFile = new IcecatFile();
		// Default Product value
		entity.setUserId(ProductImporter.DEFAULT_USER_ID);
		entity.setCode(Integer.valueOf(0));
		entity.setDateAdded(new Date());

		// Do Tests
		productFile.setProductID(Integer.valueOf(10));
		entity.setId(productFile.getProductID());
		// Test img
		productFile.setHighPic("HicPic" + Math.random());
		productFile.setHighPicSize(Integer.valueOf((int) Math.random() * 1047));
		entity.setHighDefPicture(productFile.getHighPic());
		entity.setHighDefPictureSize(productFile.getHighPicSize());
		assertNull(service.updateIcecatProductFromFile(entity, productFile));
	}

}
