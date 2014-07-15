package eu.ttbox.batch.icecat.product.index;

import biz.icecat.files.v1.IcecatFile;
import eu.ttbox.batch.core.fs.ProxyCacheDownloadConnector;
import eu.ttbox.batch.icecat.dao.IcecatDAO;
import eu.ttbox.batch.icecat.product.detail.model.ProductHelper;
import eu.ttbox.batch.icecat.product.detail.model.ProductSynchroniser;
import eu.ttbox.icecat.model.product.IcecatProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.Date;

@Service
public class ProductListItemImporter {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("icecatDAO")
	IcecatDAO icecatDAO;

	@Autowired
	@Qualifier("icecatProductSynchroniser")
	ProductSynchroniser productSynchroniser;

	@Autowired
	ProxyCacheDownloadConnector icecatProxyCacheHttpConnector;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void doImport(IcecatFile productFile) throws IOException {
		String productFileQuality = productFile.getQuality();
		boolean downloadProductFile = true;

		if ("REMOVED".equals(productFileQuality)) {
			downloadProductFile = false;

			// log.warn("TO DELETE file {}", ifile.getPath());
			// TODO
			// icecatProxyCacheHttpConnector.deleteLocalFile(ifile.getPath());
		} else if ("NOEDITOR".equals(productFileQuality)) {
			// Ignore for products for which we have no data-sheet
			downloadProductFile = false;
		}
		if (productFile.isOnMarket()) {
			// downloadProductFile = false;
		}

		// Manage File
		if (downloadProductFile) {
			long begin = System.currentTimeMillis();
			Integer productId = productFile.getProductID();
			IcecatProduct product = (IcecatProduct) icecatDAO.getById(productId, IcecatProduct.class);
			boolean isCreated = false;
			if (product == null) {
				isCreated = true;
				product = new IcecatProduct();
				product.setId(productId);
			}
			// TODO Compare version
			Date declaredUpdated = ProductHelper.getUpdatedDate(productFile);

			// Update Values
			boolean isUpdated = false;

			if (productSynchroniser.updateIcecatProductFromFile(product, productFile) != null) {
				isUpdated = true;
			}
			// Persist Modif
			if (isUpdated) {
				isUpdated = productSynchroniser.isIcecatProductValid(product);
				if (!isUpdated) {
					icecatDAO.evict(product);
					log.warn("Ignore not valid product {} (isCreated={})", product, isCreated);
				}
			}
			if (isUpdated) {
				try {
					if (isCreated) {
						icecatDAO.insertObject(product);
						icecatDAO.flushAndClear();
						log.info("Create product index Id {} : {} \t in {} ms.",
								new Object[] { productId, productFile.getModelName(), (System.currentTimeMillis() - begin) });
					} else {
						icecatDAO.updateObject(product);
						icecatDAO.flushAndClear();
						log.info("Update product index Id {} : {} \t in {} ms.",
								new Object[] { productId, productFile.getModelName(), (System.currentTimeMillis() - begin) });
					}
				} catch (ConstraintViolationException e) {
					log.error("Error during persiting Product index Id {} : {}", productId, e.getMessage());
				} catch (DataIntegrityViolationException e) {
					log.error("Error during persiting Product index Id {} : {}", productId, e.getMessage());
				}
			}
		}
	}

	public void flush() {
		icecatDAO.flush();
	}

	public void flushAndClear() {
		icecatDAO.flushAndClear();
	}

	@Async
	public void downloadProductFile(String relatifPath, Date declaredUpdated) throws IOException {
		icecatProxyCacheHttpConnector.downloadFile(relatifPath, declaredUpdated);
	}

}
