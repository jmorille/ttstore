package eu.ttbox.batch.ingram.price.list;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import eu.ttbox.batch.ingram.dao.SupplierOfferDAO;
import eu.ttbox.batch.ingram.dao.TTBoxDAO;
import eu.ttbox.batch.ingram.price.diff.PriceDifferentialItem;
import eu.ttbox.model.catalog.Offer;
import eu.ttbox.model.supplier.SupplierEnum;
import eu.ttbox.model.supplier.SupplierFeedStatus;
import eu.ttbox.model.supplier.SupplierPrice;

@Service("priceIngramItemWriter")
public class PriceItemWriter implements ItemWriter<PriceDifferentialVO> {

	@Autowired
	SupplierOfferDAO supplierOfferDAO;

	@Autowired
	TTBoxDAO ttboxDAO;

	private static final Logger log = LoggerFactory.getLogger(PriceItemWriter.class);

	@Override
	public void write(List<? extends PriceDifferentialVO> items) throws Exception {
		for (PriceDifferentialVO item : items) {
			StopWatch stopWatch = new StopWatch(item.getIngramId());
			// Deletes Offers, SupplierPrices
			writeDelete(item, stopWatch);
			// Products
			writeEntities(item.products, stopWatch, "products");
			// Supplier Price
			write(item, stopWatch);
			// Offer
			// TODO writeEntities(item.offers, stopWatch, "offers");
			log.info(stopWatch.prettyPrint());
		}

	}

	public void writeDelete(PriceDifferentialVO item, StopWatch stopWatch) {
		// Offers
		List<Offer> offers = item.offers.getDeletes();
		if (!offers.isEmpty()) {
			stopWatch.start("Delete Offers");
			ttboxDAO.deleteAll(offers);
			stopWatch.stop();
		}
		// Supplier Price
		List<SupplierPrice> supplierPrices = item.getDeletes();
		if (!supplierPrices.isEmpty()) {
			stopWatch.start("Delete Supplier Prices");
			ttboxDAO.deleteAll(supplierPrices);
			stopWatch.stop();
		}

	}

	public void write(PriceDifferentialVO item, StopWatch stopWatch) throws Exception {
		// Status
		SupplierFeedStatus status = new SupplierFeedStatus();
		status.setFileLastModified(item.getFileLastModified());
		status.setFileSizeInBytes(item.getFileSizeInBytes());
		status.setSupplier(SupplierEnum.INGRAM);
		status.setSupplierId(item.getIngramId());

		// Update
		List<SupplierPrice> updates = item.getUpdates();
		if (!updates.isEmpty()) {
			stopWatch.start(String.format("Update %d supplierPrices", updates.size()));
			supplierOfferDAO.saveOfferUpdates(status, updates);
			stopWatch.stop();
		}
		// Create
		List<PriceDifferentialItem<SupplierPrice, FieldSet>> creates = item.getCreates();
		if (!creates.isEmpty()) {
			stopWatch.start(String.format("Create %d supplierPrices", creates.size()));
			supplierOfferDAO.saveOfferCreates(status, creates);
			stopWatch.stop();
		}
	}

	public <T> void writeEntities(ListEntityDifferentialVO<T> products, StopWatch stopWatch, String entityName) throws Exception {

		if (!products.getUpdates().isEmpty()) {
			stopWatch.start(String.format("Update %d %s", products.getUpdates().size(), entityName));
			ttboxDAO.saveOrUpdateAll(products.getUpdates());
			stopWatch.stop();
		}
		if (!products.getCreates().isEmpty()) {
			stopWatch.start(String.format("Create %d %s", products.getCreates().size(), entityName));
			ttboxDAO.saveOrUpdateAll(products.getCreates());
			stopWatch.stop();
		}
		// if (!products.getDeletes().isEmpty()) {
		// stopWatch.start("Delete");
		// ttboxDAO.deleteAll(products.getDeletes());
		// stopWatch.stop();
		// }
		// if (log.isInfoEnabled()) {
		// log.info(stopWatch.prettyPrint());
		// }
	}

}
