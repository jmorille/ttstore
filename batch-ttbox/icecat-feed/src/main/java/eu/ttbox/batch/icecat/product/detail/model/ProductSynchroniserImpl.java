package eu.ttbox.batch.icecat.product.detail.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.common.base.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import biz.icecat.files.v1.EANUPCS;
import biz.icecat.files.v1.IcecatFile;
import biz.icecat.referential.v1.Product;
import eu.ttbox.batch.icecat.dao.IcecatDAO;
import eu.ttbox.batch.icecat.product.detail.ProductImporter;
import eu.ttbox.icecat.model.product.IcecatProduct;
import eu.ttbox.icecat.model.product.IcecatProductLine;
import eu.ttbox.icecat.model.referential.IcecatBrand;
import eu.ttbox.icecat.model.referential.IcecatCategory;

@Service("icecatProductSynchroniser")
public class ProductSynchroniserImpl implements ProductSynchroniser {

	protected Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("icecatDAO")
	IcecatDAO icecatDAO;

	public IcecatDAO getIcecatDAO() {
		return icecatDAO;
	}

	public void setIcecatDAO(IcecatDAO icecatDAO) {
		this.icecatDAO = icecatDAO;
	}

	public IcecatProduct updateIcecatProductFromFile(IcecatProduct entity, IcecatFile productFile) {
		boolean isUpdated = false;
		if (entity == null) {
			entity = new IcecatProduct();
			entity.setId(productFile.getProductID());
			entity.setUserId(ProductImporter.DEFAULT_USER_ID);
			isUpdated = true;
		}
		// Compare version
		Date icecatFileUpdatedDate = ProductHelper.getUpdatedDate(productFile);
		if (!Objects.equal(entity.getPathVersionDate(), icecatFileUpdatedDate)) {
			entity.setPathVersionDate(icecatFileUpdatedDate);
			isUpdated = true;
		}

		// Default Values
		if (entity.getCode() == null) {
			entity.setCode(Integer.valueOf(0));
			isUpdated = true;
		}
		if (entity.getDateAdded() == null) {
			entity.setDateAdded(new Date());
			isUpdated = true;
		}

		// Hight Picture
		if (!Objects.equal(entity.getHighDefPicture(), productFile.getHighPic())) {
			entity.setHighDefPicture(productFile.getHighPic());
			isUpdated = true;
		}
		if (!Objects.equal(entity.getHighDefPictureSize(), productFile.getHighPicSize())) {
			entity.setHighDefPictureSize(productFile.getHighPicSize());
			isUpdated = true;
		}
		if (!Objects.equal(entity.getHighDefPictureWidth(), productFile.getHighPicWidth())) {
			entity.setHighDefPictureWidth(productFile.getHighPicWidth());
			isUpdated = true;
		}
		if (!Objects.equal(entity.getHighDefPictureHeight(), productFile.getHighPicHeight())) {
			entity.setHighDefPictureHeight(productFile.getHighPicHeight());
			isUpdated = true;
			log.debug("/!\\/!\\/!\\ Update getHighDefPictureHeight /!\\/!\\/!\\ : {} ", entity);

		}

		// Quality
		if (!Objects.equal(entity.getEditor(), productFile.getQuality())) {
			entity.setEditor(productFile.getQuality());
			log.debug("/!\\/!\\/!\\ Update getEditor /!\\/!\\/!\\ : {} ", entity);
			isUpdated = true;
		}
		if (!Objects.equal(entity.isOnMarket(), productFile.isOnMarket())) {
			entity.setOnMarket(productFile.isOnMarket());
			isUpdated = true;
			log.debug("/!\\/!\\/!\\ Update isOnMarket /!\\/!\\/!\\ : {} ", entity);
		}

		//
		if (!Objects.equal(entity.getPartNumber(), productFile.getProdID())) {
			entity.setPartNumber(productFile.getProdID());
			isUpdated = true;
			log.debug("/!\\/!\\/!\\ Update getPartNumber /!\\/!\\/!\\ : {} ", entity);
		}

		if (!Objects.equal(entity.getName(), productFile.getModelName())) {
			entity.setName(productFile.getModelName());
			isUpdated = true;
			log.debug("/!\\/!\\/!\\ Update getModelName /!\\/!\\/!\\ : {} ", entity);
		}

		if (!Objects.equal(entity.getPath(), productFile.getPath())) {
			entity.setPath(productFile.getPath());
			isUpdated = true;
			log.debug("/!\\/!\\/!\\ Update getPath /!\\/!\\/!\\ : {} ", entity);
		}

		if (productFile.isSetMProdIDs()) {
			List<String> partNumbers = new ArrayList<String>(productFile.getMProdIDs().size());
			for (String partNumb : productFile.getMProdIDs()) {
				partNumbers.add(partNumb);
				entity.addAlternativeMFPN(partNumb);
			}
			// TODO Delete partNumbers

		}

		if (productFile.isSetEANUPCS() && productFile.getEANUPCS().isSetEANUPCS()) {
			List<String> eanCodes = new ArrayList<String>(productFile.getEANUPCS().getEANUPCS().size());
			for (EANUPCS.EANUPC ean : productFile.getEANUPCS().getEANUPCS()) {
				String eanCode = ean.getValue();
				if (eanCode != null) {
					eanCodes.add(eanCode);
					if (!entity.containEans(eanCode)) {
						entity.addEans(eanCode);
						log.debug("/!\\/!\\/!\\ Update EANUPCS /!\\/!\\/!\\ : {} ", eanCode);
						isUpdated = true;
					}
				}
			}
			// TODO Remmove Not Present
			// eanCodes.retainAll(entity.getEans());
			// log.debug("/!\\/!\\/!\\ Remove EANUPCS /!\\/!\\/!\\ : {} ", entity);
		}

		// productFile.getCountryMarkets();
		// log.warn("Need to map {} : {}", "getCountryMarkets",
		// productFile.getCountryMarkets());
		//
		// productFile.getDistributors();
		// log.warn("Need to map {} : {}", "getDistributors",
		// productFile.getDistributors());
		//
		// productFile.getProductView();
		// log.warn("Need to map {} : {}", "getProductView",
		// productFile.getProductView());

		// Dependency
		// Brand
		if (updateSupplier(entity, productFile.getSupplierId()) != null) {
			log.debug("/!\\/!\\/!\\ Update Brand /!\\/!\\/!\\ : {} ", entity);
			isUpdated = true;
		}

		// Category
		if (updateCategory(entity, productFile.getCatid()) != null) {
			log.debug("/!\\/!\\/!\\ Update Product /!\\/!\\/!\\ Category : {} ", entity);
			isUpdated = true;
		}
		// this.icecatDAO.flushAndClear();
		if (isUpdated) {
			log.debug("/!\\/!\\/!\\ Update Product /!\\/!\\/!\\ : {} ", entity);
			return entity;
		} else {
			return null;
		}
	}

	// @Transactional(propagation = Propagation.SUPPORTS)
	public IcecatProduct updateIcecatProductWithAllDependencies(IcecatProduct entity, Product productFile) {
		if (entity.getId() == null) {
			entity.setId(productFile.getID());
		}

		// Default Values
		if (entity.getUserId() == null) {
			entity.setUserId(ProductImporter.DEFAULT_USER_ID);
		}
		if (entity.getDateAdded() == null) {
			entity.setDateAdded(new Date());
		}

		// Values Picture
		// Hight Picture
		entity.setHighDefPicture(StringUtils.trimToNull(productFile.getHighPic()));
		entity.setHighDefPictureSize(productFile.getHighPicSize());
		entity.setHighDefPictureWidth(productFile.getHighPicWidth());
		entity.setHighDefPictureHeight(productFile.getHighPicHeight());

		// Low Picture
		entity.setLowDefPicture(StringUtils.trimToNull(productFile.getLowPic()));
		entity.setLowDefPictureSize(productFile.getLowPicSize());
		entity.setLowDefPictureWidth(productFile.getLowPicWidth());
		entity.setLowDefPictureHeight(productFile.getLowPicHeight());

		// Thumb Picture
		entity.setThumbPicture(StringUtils.trimToNull(productFile.getThumbPic()));
		entity.setThumbPictureSize(productFile.getThumbPicSize());

		// Quality
		entity.setEditor(StringUtils.trimToNull(productFile.getQuality()));

		// Values
		entity.setName(StringUtils.trimToNull(productFile.getName()));
		entity.setPartNumber(StringUtils.trimToNull(productFile.getProdId()));
		if (productFile.getCode() != null) {
			entity.setCode(productFile.getCode());
		} else {
			entity.setCode(Integer.valueOf(0));
		}
		if (productFile.getScore() != null) {
			entity.setScore(productFile.getScore());
		} else {
			entity.setScore(Integer.valueOf(0));
		}

		// TODO entity.setDescriptions(referential.getProductsDescription());
		log.warn("Need to map {} : {}", "getProductsDescription", productFile.getProductsDescription());

		// Dependency
		// Brand
		Integer brandId = productFile.getSupplier() == null ? null : productFile.getSupplier().getID();
		updateSupplier(entity, brandId);

		// Category
		Integer catId = productFile.getCategory() == null ? null : productFile.getCategory().getID();
		updateCategory(entity, catId);

		// Product Familly
		Integer famillyId = productFile.getProductFamily() == null ? null : productFile.getProductFamily().getID();
		updateProductFamily(entity, famillyId);
		return entity;
	}

	private IcecatProductLine updateProductFamily(IcecatProduct entity, Integer famillyId) {
		IcecatProductLine familly = entity.getLine();
		if (famillyId != null) {
			boolean needUpdate = entity.getLine() == null;
			if (!needUpdate) {
				needUpdate = !famillyId.equals(entity.getLine().getId());
			}
			if (needUpdate) {
				familly = (IcecatProductLine) getIcecatDAO().getById(famillyId, IcecatProductLine.class);
				if (familly == null) {
					log.warn("Product family id {} is missing", famillyId);
				}
			}
		} else {
			familly = null;
		}
		entity.setLine(familly);
		return familly;
	}

	private IcecatBrand updateSupplier(IcecatProduct entity, Integer brandId) {
		IcecatBrand brand = entity.getBrand();
		boolean needUpdate = false;
		if (brandId != null) {
			needUpdate = (brand == null);
			if (!needUpdate) {
				needUpdate = !brandId.equals(brand.getId());
			}
			if (needUpdate) {
				brand = (IcecatBrand) getIcecatDAO().getById(brandId, IcecatBrand.class);
			}
		} else {
			brand = null;
			needUpdate = true;
		}
		if (needUpdate) {
			entity.setBrand(brand);
		} else {
			return null;
		}
		return brand;
	}

	private IcecatCategory updateCategory(IcecatProduct entity, Integer catId) {
		IcecatCategory category = entity.getCategory();
		boolean needUpdate = false;
		// log.debug("Test update IcecatCategory for {}", catId );
		if (catId != null) {
			needUpdate = (category == null);
			if (!needUpdate) {
				needUpdate = !catId.equals(category.getId());
			}
			if (needUpdate) {
				category = (IcecatCategory) getIcecatDAO().getById(catId, IcecatCategory.class);
				if (category == null) {
					log.warn("Product Category id {} is missing");
				}
			}
		} else {
			category = null;
			needUpdate = true;
		}
		if (needUpdate) {
			entity.setCategory(category);
		} else {
			return null;
		}
		return category;
	}

	public boolean isIcecatProductValid(IcecatProduct product) {
		boolean isValid = true;
		log.debug("Begin Validate Product");
		if (isValid) {
			isValid = product.getCategory() != null;
			if (!isValid) {
				log.warn("Not Valid Null Category");
			}
		}
		if (isValid) {
			isValid = product.getBrand() != null;
			if (!isValid) {
				log.warn("Not Valid Null Brand");
			}
		}
		if (isValid) {
			isValid = product.getName() != null;
			if (!isValid) {
				log.warn("Not Valid Null Name");
			}
		}
		if (isValid) {
			isValid = product.getPartNumber() != null;
			if (!isValid) {
				log.warn("Not Valid Null PartNumber");
			}
		}
		log.debug("End   Validate Product");
		return isValid;
	}

}
