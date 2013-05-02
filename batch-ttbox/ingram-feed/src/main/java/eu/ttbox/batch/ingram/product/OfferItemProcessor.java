package eu.ttbox.batch.ingram.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Service;

import eu.ttbox.batch.ingram.price.diff.PriceDifferentialItem;
import eu.ttbox.model.catalog.Offer;
import eu.ttbox.model.salespoint.Salespoint;
import eu.ttbox.model.supplier.SupplierPrice;

@Service
public class OfferItemProcessor implements ItemProcessor<PriceDifferentialItem<SupplierPrice, FieldSet>, Offer> {

	private static final Logger LOG = LoggerFactory.getLogger(OfferItemProcessor.class);

	@Override
	public Offer process(PriceDifferentialItem<SupplierPrice, FieldSet> item) throws Exception {
		Offer offer = null;
		SupplierPrice supplierPrice = item.getMasterItem();
		boolean isSame = true;
		if (item.isStatusCreate()) {
			Salespoint salespoint = item.parent.getSalespoint();
			// Create Offer
			offer = new Offer();
			offer.setSupplierPrice(supplierPrice);
			offer.setProduct(supplierPrice.getProduct());
			offer.setSalespoint(salespoint);
			// Status
			isSame = false;
		}
		// Update Promo
		if (!isSame) {
			// Validate
			boolean isValid = isValid(offer);
			// Result
			if (isValid) {
				if (item.isStatusCreate()) {
					item.parent.offers.addCreate(offer);
				}
				return offer;
			}
		}
		return null;

	}

	private boolean isValid(Offer offer) {
		boolean isValid = true;
		if (isValid) {
			isValid = offer.getSalespoint() != null;
		}
		if (isValid) {
			isValid = offer.getProduct() != null;
		}
		if (isValid) {
			isValid = offer.getSupplierPrice() != null;
		}
		return isValid;
	}

}
