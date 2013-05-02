package eu.ttbox.batch.techdata.price;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Objects;

import eu.ttbox.batch.core.reader.differential.DifferentialItem;
import eu.ttbox.batch.techdata.core.converter.ProductConverter;
import eu.ttbox.batch.techdata.price.diff.PriceDifferentialItem;
import eu.ttbox.model.pricing.CurrencyEnum;
import eu.ttbox.model.pricing.Price;
import eu.ttbox.model.product.Product;
import eu.ttbox.model.supplier.SupplierEnum;
import eu.ttbox.model.supplier.SupplierPrice;
import eu.ttbox.model.supplier.SupplierPromoDetail;

@Service("priceTechdataItemProcessor")
public class PriceItemProcessor implements
		ItemProcessor<PriceDifferentialItem<SupplierPrice, FieldSet>, PriceDifferentialItem<SupplierPrice, FieldSet>> {

	private static final Logger log = LoggerFactory.getLogger(PriceItemProcessor.class);

	@Autowired
	ProductConverter productConverter;
	
	
	@Override
	public PriceDifferentialItem<SupplierPrice, FieldSet> process(PriceDifferentialItem<SupplierPrice, FieldSet> item)
			throws Exception {
		// Not need to manage delete
		if (item.isStatusDelete()) {
			return item;
		}
		// init variable
		SupplierPrice entity = item.getMasterItem();
		FieldSet fs = item.getJoinItem();
		boolean isSame = true;
		if (entity == null) {
			String techShopId = PriceFieldEnum.ShopId.readString(fs);
			// Search Product
			String techProductId = PriceFieldEnum.TechProductId.readString(fs);
			Product product = productConverter.getProductByTechId(techProductId);
			// Keep As cache for later
			Integer productId = productConverter.getProductIdByTechId(techProductId);
			item.setProductId(productId);
			if (product ==null) {
				return null;
			}
			// Create Price
			entity = new SupplierPrice();
			entity.setSupplier(SupplierEnum.TECHDATA);
			entity.setSupplierId(techShopId);
			entity.setSupplierProductId(techProductId); 
			entity.setProduct(product);
			// Reset Item
			item.setMasterItem(entity);
			isSame = false;
		}
		// Price 
		BigDecimal feedSupplierPrice = PriceFieldEnum.SupplierPrice.readBigDecimal(fs);
		String currencyString = PriceFieldEnum.Currency.readString(fs); 
		CurrencyEnum currency =  CurrencyEnum.getMapping(currencyString); 
		if (entity.getSupplierPrice()==null || !entity.getSupplierPrice().isSame(feedSupplierPrice, currency)) {
			Price price = new Price(feedSupplierPrice, currency);
			entity.setSupplierPrice(price);
			isSame = false;
		}
		// Surcharge
		BigDecimal feedSurcharge  = PriceFieldEnum.Surcharge.readBigDecimal(fs);
		if (!Objects.equal(feedSurcharge, entity.getSurcharge())) {
			entity.setSurcharge(feedSurcharge);
			isSame = false;
		}
		// Promo
		Date promoUntils = PriceFieldEnum.PromoUntilString.readDate(fs);
		SupplierPromoDetail promo = entity.getPromoDetail();
		if (!Objects.equal(promoUntils, promo.getPromoEnd())) {
			promo.setPromoEnd(promoUntils);
			isSame = false;
		}

		// Return
		if (isSame) {
			return null;
		} else {
			return item;
		}
	}
}
