package eu.ttbox.batch.icecat.product.detail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.icecat.files.v1.CountryMarkets;
import biz.icecat.files.v1.EANUPCS;
import biz.icecat.files.v1.IcecatFile;
import biz.icecat.referential.v1.EANCode;
import biz.icecat.referential.v1.Product;
import biz.icecat.referential.v1.ProductBundled;
import biz.icecat.referential.v1.ProductDescription;
import biz.icecat.referential.v1.ProductFeature;
import biz.icecat.referential.v1.ProductGallery;
import biz.icecat.referential.v1.ProductGallery.ProductPicture;
import biz.icecat.referential.v1.ProductMultimediaObject;
import biz.icecat.referential.v1.ProductMultimediaObject.MultimediaObject;
import biz.icecat.referential.v1.ProductRelated;
import biz.icecat.referential.v1.SummaryDescription;
import eu.ttbox.batch.core.fs.ProxyCacheDownloadConnector;
import eu.ttbox.batch.icecat.dao.IcecatDAO;
import eu.ttbox.batch.icecat.product.detail.model.ProductHelper;
import eu.ttbox.batch.icecat.product.detail.model.ProductSynchroniser;
import eu.ttbox.batch.icecat.product.detail.parser.IcecatProductDetailFileUnmarshalliser;
import eu.ttbox.batch.icecat.referential.dependency.IDependencyDifferential;
import eu.ttbox.icecat.model.product.IcecatProduct;
import eu.ttbox.icecat.model.product.IcecatProductBundled;
import eu.ttbox.icecat.model.product.IcecatProductDescription;
import eu.ttbox.icecat.model.product.IcecatProductFeature;
import eu.ttbox.icecat.model.product.IcecatProductFeatureLocal;
import eu.ttbox.icecat.model.product.IcecatProductGallery;
import eu.ttbox.icecat.model.product.IcecatProductMultimediaObject;
import eu.ttbox.icecat.model.product.IcecatProductRelated;
import eu.ttbox.icecat.model.product.IcecatProductSummaryDescription;
import eu.ttbox.icecat.model.product.QualityEnum;

@Transactional
@Service("productImporter")
public class ProductImporterImpl implements ProductImporter {

	protected Logger log = LoggerFactory.getLogger(getClass());

	int bufferSize = 1024;

	@Autowired
	@Qualifier("icecatDAO")
	IcecatDAO icecatDAO;

	@Autowired
	@Qualifier("icecatProxyCacheHttpConnector")
	ProxyCacheDownloadConnector icecatHttpConnector;

	@Autowired
	IcecatProductDetailFileUnmarshalliser productUmarshalliser;

	@Autowired
	@Qualifier("icecatProductSynchroniser")
	ProductSynchroniser productSynchroniser;

	@Autowired
	@Qualifier("icecatProductFeatureDifferential")
	IDependencyDifferential<IcecatProduct, IcecatProductFeature, ProductFeature> productFeatureDifferential;

	@Autowired
	@Qualifier("icecatProductFeatureLocalDifferential")
	IDependencyDifferential<IcecatProduct, IcecatProductFeatureLocal, ProductFeature> productFeatureLocalDifferential;

	@Autowired
	@Qualifier("icecatProductRelatedDifferential")
	IDependencyDifferential<IcecatProduct, IcecatProductRelated, ProductRelated> productRelatedDifferential;

	@Autowired
	@Qualifier("icecatProductBundledDifferential")
	IDependencyDifferential<IcecatProduct, IcecatProductBundled, ProductBundled> productBundledDifferential;

	@Autowired
	@Qualifier("icecatProductDescriptionDifferential")
	IDependencyDifferential<IcecatProduct, IcecatProductDescription, ProductDescription> productDescriptionDifferential;

	@Autowired
	@Qualifier("icecatProductSummaryDescriptionDifferential")
	IDependencyDifferential<IcecatProduct, IcecatProductSummaryDescription, SummaryDescription> productSummaryDescriptionDifferential;

	@Autowired
	@Qualifier("icecatProductGalleryDifferential")
	IDependencyDifferential<IcecatProduct, IcecatProductGallery, ProductPicture> productGalleryDifferential;

	@Autowired
	@Qualifier("icecatProductMultimediaObjectDifferential")
	IDependencyDifferential<IcecatProduct, IcecatProductMultimediaObject, MultimediaObject> productMultimediaObjectDifferential;

	public IcecatDAO getIcecatDAO() {
		return icecatDAO;
	}

	public void setIcecatDAO(IcecatDAO icecatDAO) {
		this.icecatDAO = icecatDAO;
	}

	public int deleteProduct(IcecatFile productFile) {
		Integer productId = productFile.getProductID();
		// Delete Local File
		icecatHttpConnector.getWorkingLocalFolder().deleteLocalFile(productFile.getPath());

		return deleteProduct(productId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int deleteProduct(Integer productId) {
		int deleteCount = 0;
		IcecatProduct product = (IcecatProduct) getIcecatDAO().getById(productId, IcecatProduct.class);
		if (product != null) {
			// log.warn("TODO Delete QualityEnum.REMOVED product with Id {}",
			// productId);
			// getIcecatDAO().deleteAll(product.getFeatures());
			// getIcecatDAO().deleteAll(product.getGalleries());
			// getIcecatDAO().deleteAll(product.getMmo());
			// getIcecatDAO().deleteAll(product.getDescriptions().values());
			// Delete Bundled
			getIcecatDAO().deleteProductDependenciesByProductId(productId);
			getIcecatDAO().deleteAll(product);
		}
		return deleteCount;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void doImport(IcecatFile productFile) throws IOException {
		String qualityString = productFile.getQuality();
		QualityEnum qualityEnum = null;
		if (StringUtils.isNotBlank(qualityString)) {
			qualityEnum = QualityEnum.valueOf(qualityString);
		}
		if (qualityEnum == null || !QualityEnum.REMOVED.equals(qualityEnum)) {
			importProduct(productFile);
		} else {
			deleteProduct(productFile);
		}
	}

	@Override
	public Date getFeedVersionDate(IcecatFile productFile) {
		Date fileUpdatedDate = ProductHelper.getUpdatedDate(productFile);
		if (fileUpdatedDate == null) {
			throw new RuntimeException("Unable to parse date " + productFile.getUpdated());
		}
		return fileUpdatedDate;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int importProduct(IcecatFile productFile) throws IOException {
		int importCount = 0;
		// Remote Product Version
		Date fileUpdatedDate = getFeedVersionDate(productFile);

		// Check Product In Database
		Integer productId = productFile.getProductID();
		IcecatProduct productCurrentVersionDate = (IcecatProduct) getIcecatDAO().getById(productId, IcecatProduct.class);
		// Check Version
		boolean isUpdatedNeeded = false;
		if (productCurrentVersionDate == null) {
			isUpdatedNeeded = true;
		} else {
			Date currentProdVersion = productCurrentVersionDate.getUpdated();
			// Check Date
			if (currentProdVersion == null || currentProdVersion.before(fileUpdatedDate)) {
				isUpdatedNeeded = true;
			}
			//
			// log.debug(productFile.getProduct_ID() + " ==>  " +
			// lastUpdateString
			// + " ==> " + fileUpdatedDate);
		}

		// Download File
		if (isUpdatedNeeded) {
			String url = productFile.getPath();
			File resultDest = icecatHttpConnector.downloadFile(url, fileUpdatedDate);

			// Import Product Data
			Product product = productUmarshalliser.convertFileToProduct(resultDest);
			if (product == null) {
				log.warn("Ignore Product {}", productFile.getProductID());
			} else {
				Date productVersionDate = new Date();
				productVersionDate.setTime(resultDest.lastModified());
				importCount += doImportReferentialEntity(product, productFile, productVersionDate, productCurrentVersionDate);
			}

		}
		return importCount;
	}

	public int doImportReferentialEntity(Product referential, IcecatFile productFile, Date productVersionDate, IcecatProduct productEntity) {
		int importCount = 0;

		Integer entityId = referential.getID();
		IcecatProduct entity = productEntity;
		if (entity == null) {
			entity = new IcecatProduct();
			entity.setId(entityId);
			// Default Values
			entity.setUserId(ProductImporter.DEFAULT_USER_ID);
			entity.setDateAdded(new Date());
			importCount++;
		}

		entity.setUpdated(productVersionDate);

		// update product Data
		productSynchroniser.updateIcecatProductWithAllDependencies(entity, referential);

		// Default
		// TODO referential.getSummaryDescriptions();
		// TODO referential.getReleaseDate()
		// TODO referential.getRelevance();

		// TODO productFile.getProductView();
		// Distributors distributors = productFile.getDistributors();
		// if (distributors!=null && distributors.isSetDistributors() ) {
		// List<Distributor> distributorList = distributors.getDistributors();
		// for (Distributor distributor : distributorList) {
		// System.out.println("Dist : " + distributor.getID() + " / " +
		// distributor.getName());
		// // distributor.getID();
		// }
		// }
		// TODO
		if (productFile.getEANUPCS() != null && productFile.getEANUPCS().isSetEANUPCS()) {
			List<EANUPCS.EANUPC> eanUpcList = productFile.getEANUPCS().getEANUPCS();
			for (EANUPCS.EANUPC eanUpc : eanUpcList) {
				String eanUpcValue = eanUpc.getValue();
				entity.addEans(eanUpcValue);
			}
		}

		// Insert Product EANS
		List<EANCode> eans = referential.getEANCodes();
		for (EANCode ean : eans) {
			String eanVal = StringUtils.trimToNull(ean.getEAN());
			if (eanVal != null) {
				entity.addEans(eanVal);
			}
		}

		// On Market
		if (productFile.isSetOnMarket()) {
			entity.setOnMarket(productFile.isOnMarket());
		} else {
			entity.setOnMarket(false);
		}

		// CountryMarkets
		CountryMarkets countryMarket = productFile.getCountryMarkets();
		if (countryMarket != null) {
			List<CountryMarkets.CountryMarket> countryMarketList = countryMarket.getCountryMarkets();
			for (CountryMarkets.CountryMarket mark : countryMarketList) {
				String country = mark.getValue();
				if (country != null) {
					entity.addCountryMarket(country);
				}
			}
		}

		// --- Save Product
		// ----------------
		if (this.productSynchroniser.isIcecatProductValid(entity)) {
			getIcecatDAO().saveObject(entity);

			// Description
			doImportProductDescription(entity, referential.getProductDescriptions());

			doImportSummaryDescriptions(entity, referential.getSummaryDescriptions());

			// Insert Product Features (with added displayDescription)
			doImportProductFeatures(entity, referential.getProductFeatures());

			doImportProductFeatureLocals(entity, referential.getProductFeatures());

			// Product Multimedia Objects
			doImportProductMultimedia(entity, referential.getProductMultimediaObject());

			// Product Gallery
			doImportProductGallery(entity, referential.getProductGallery());

			// Insert Product Related
			List<ProductRelated> relateds = referential.getProductRelateds();
			doImportProductRelated(entity, relateds);

			// Insert Product Bundles
			List<ProductBundled> bundleds = referential.getProductBundleds();
			doImportProductBundled(entity, bundleds);

			// doImportCategoryFeatureGroups(entity,
			// referential.getCategoryFeatureGroups());

		} else {
			log.warn("Ignore not valid Product Product Id " + entityId);
		}

		return importCount;
	}

	private void doImportProductRelated(IcecatProduct product, List<ProductRelated> relateds) {
		List<IcecatProductRelated> entities = product.getRelateds();
		if (entities == null) {
			entities = new ArrayList<IcecatProductRelated>();
			product.setRelateds(entities);
		}
		this.productRelatedDifferential.doImportDependencies(product, entities, relateds);
	}

	private void doImportProductBundled(IcecatProduct product, List<ProductBundled> bundleds) {
		List<IcecatProductBundled> entities = product.getBundleds();
		if (entities == null) {
			entities = new ArrayList<IcecatProductBundled>();
			product.setBundleds(entities);
		}
		this.productBundledDifferential.doImportDependencies(product, entities, bundleds);

	}

	private void doImportProductFeatures(IcecatProduct product, List<ProductFeature> productFeatures) {
		List<IcecatProductFeature> entities = product.getFeatures();
		if (entities == null) {
			entities = new ArrayList<IcecatProductFeature>();
			product.setFeatures(entities);
		}
		this.productFeatureDifferential.doImportDependencies(product, entities, productFeatures);
	}

	private void doImportProductFeatureLocals(IcecatProduct entity, List<ProductFeature> productFeatures) {
		// TODO Auto-generated method stub
		// TODO this.productFeatureLocalDifferential.
	}

	private void doImportSummaryDescriptions(IcecatProduct product, List<SummaryDescription> summaryDescriptions) {
		List<IcecatProductSummaryDescription> entities = getIcecatDAO().getIcecatProductSummaryDescriptions(product);
		if (entities == null) {
			entities = new ArrayList<IcecatProductSummaryDescription>();
		}
		// TODO
		// this.productSummaryDescriptionDifferential.doImportDependencies(product,
		// entities, summaryDescriptions);
	}

	private void doImportProductDescription(IcecatProduct product, List<ProductDescription> productDescriptions) {

		List<IcecatProductDescription> entities = getIcecatDAO().getIcecatProductDescriptions(product);
		if (entities == null) {
			entities = new ArrayList<IcecatProductDescription>();
		}
		this.productDescriptionDifferential.doImportDependencies(product, entities, productDescriptions);
	}

	private void doImportProductGallery(IcecatProduct iceProduct, ProductGallery productGalleries) {
		List<IcecatProductGallery> entities = iceProduct.getGalleries();
		if (entities == null) {
			entities = new ArrayList<IcecatProductGallery>();
			iceProduct.setGalleries(entities);
		}
		// Prepare List Product Pictures
		List<ProductPicture> pictures = new ArrayList<ProductPicture>();

		List<ProductPicture> pcs = productGalleries.getProductPictures();
		if (!pcs.isEmpty()) {
			pictures.addAll(pcs);
		}

		this.productGalleryDifferential.doImportDependencies(iceProduct, entities, pictures);
	}

	private void doImportProductMultimedia(IcecatProduct iceProduct, ProductMultimediaObject productMultimediaObjects) {
		List<IcecatProductMultimediaObject> entities = iceProduct.getMmo();
		if (entities == null) {
			entities = new ArrayList<IcecatProductMultimediaObject>();
			iceProduct.setMmo(entities);
		}
		// Prepare List Product Pictures
		List<MultimediaObject> pictures = new ArrayList<MultimediaObject>();

		List<MultimediaObject> pcs = productMultimediaObjects.getMultimediaObjects();
		if (!pcs.isEmpty()) {
			pictures.addAll(pcs);
		}

		this.productMultimediaObjectDifferential.doImportDependencies(iceProduct, entities, pictures);
	}

}
