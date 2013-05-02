package eu.ttbox.batch.icecat.product.diff;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import biz.icecat.files.v1.IcecatFile;
import eu.ttbox.batch.core.reader.differential.DifferentialItem;
import eu.ttbox.batch.core.reader.differential.DifferentialItem.CUDStatus;
import eu.ttbox.batch.icecat.product.detail.model.ProductSynchroniser;
import eu.ttbox.icecat.model.product.IcecatProduct;

@Service("icecatFileDiffProcessor")
public class IcecatFileDiffProcessor implements ItemProcessor<DifferentialItem<IcecatProduct, IcecatFile>, DifferentialItem<IcecatProduct, IcecatFile>> {

	private static Logger LOG = LoggerFactory.getLogger(IcecatFileDiffProcessor.class);

	@Autowired
	@Qualifier("icecatProductSynchroniser")
	ProductSynchroniser productSynchroniser;

	@Override
	public DifferentialItem<IcecatProduct, IcecatFile> process(DifferentialItem<IcecatProduct, IcecatFile> item) throws Exception {
		LOG.debug("Begin IcecatFileDiffProcessor");
		IcecatProduct product = item.getMasterItem();
		IcecatFile productFile = item.getJoinItem();

		if (item.isStatusDelete()) {
			return item;
		}

		boolean downloadProductFile = true;
		String productFileQuality = productFile.getQuality();
		if ("REMOVED".equals(productFileQuality)) {
			downloadProductFile = false;
			item.setStatus(CUDStatus.DELETE);
			item.setJoinItem(null);
			// log.warn("TO DELETE file {}", ifile.getPath());
			// TODO
			// icecatProxyCacheHttpConnector.deleteLocalFile(ifile.getPath());
			return item;
		} else if ("NOEDITOR".equals(productFileQuality)) {
			// Ignore for products for which we have no data-sheet
			downloadProductFile = false;
		}
		if (productFile.isOnMarket()) {
			// downloadProductFile = false;
		}

		// Manage File
		if (downloadProductFile) {
			IcecatProduct productResult = productSynchroniser.updateIcecatProductFromFile(product, productFile);
			if (productResult == null) {
				LOG.warn("Ignore not valid product {} (CUD status {})", productFile, item.getStatus());
				return null;
			} else {
				item.setMasterItem(productResult);
				item.setJoinItem(null);
				return item;
			}
		} else {
			LOG.warn("Ignore for not download product {} (CUD status {})", productFile, item.getStatus());
		}
		// Ignre result if not manage before
		LOG.debug("End   IcecatFileDiffProcessor");

		return null;

	}

}
