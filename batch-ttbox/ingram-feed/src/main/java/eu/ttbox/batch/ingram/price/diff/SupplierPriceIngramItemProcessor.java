package eu.ttbox.batch.ingram.price.diff;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Objects;

import eu.ttbox.batch.ingram.price.CrcCodeEnum;
import eu.ttbox.batch.ingram.price.PriceFieldEnum;
import eu.ttbox.batch.ingram.price.list.PriceDifferentialVO;
import eu.ttbox.batch.ingram.product.OfferItemProcessor;
import eu.ttbox.batch.ingram.product.ProductItemProcessor;
import eu.ttbox.model.pricing.CurrencyEnum;
import eu.ttbox.model.pricing.Price;
import eu.ttbox.model.product.Product;
import eu.ttbox.model.supplier.SupplierEnum;
import eu.ttbox.model.supplier.SupplierPrice;
import eu.ttbox.model.supplier.SupplierPromoDetail;

@Service
public class SupplierPriceIngramItemProcessor implements
		ItemProcessor<PriceDifferentialItem<SupplierPrice, FieldSet>, PriceDifferentialItem<SupplierPrice, FieldSet>> {

	private static final Logger LOG = LoggerFactory.getLogger(SupplierPriceIngramItemProcessor.class);

	@Autowired
	ProductItemProcessor productItemProcessor;

	@Autowired
	OfferItemProcessor offerItemProcessor;

	@Override
	public PriceDifferentialItem<SupplierPrice, FieldSet> process(PriceDifferentialItem<SupplierPrice, FieldSet> item) throws Exception {
		PriceDifferentialVO status = item.parent;
		// Not need to manage delete
		if (item.isStatusDelete()) {
			return item;
		}
		// init variable
		SupplierPrice entity = item.getMasterItem();
		FieldSet fs = item.getJoinItem();
		// Manage Dependency
		Product product = productItemProcessor.process(item);
		if (product == null) {
			status.addIgnoreNoProduct();
			return null;
		}
		// Manage Creation
		boolean isSame = true;
		if (entity == null) {
			String ingramShopId = item.getSupplierId();
			// Search Product
			String skuProductId = PriceFieldEnum.Sku.readString(fs);
			// Create Price
			entity = new SupplierPrice();
			entity.setSupplier(SupplierEnum.INGRAM);
			entity.setSupplierId(ingramShopId);
			entity.setSupplierProductId(skuProductId);
			entity.setProduct(product);
			// Reset Item
			item.setMasterItem(entity);
			isSame = false;
		}
		// Update Price
		isSame = updateSupplierPrice(isSame, entity, fs);
		isSame = updatePromo(isSame, entity, fs);

		// Manage promoFreeProduct
		SupplierPromoDetail promoDetail = entity.getPromoDetail();
		if (promoDetail != null && promoDetail.getPromoFreeProductSku() != null) {
			if (promoDetail.getPromoFreeProduct() == null) {
				// TODO Search Product Sku
			}
		}
		// TODO IsInStock

		// Offers
		offerItemProcessor.process(item);

		// Result
		if (isSame) {
			status.addIgnoreNothingToDo();
			return null;
		} else {
			return item;
		}
	}

	private boolean updateSupplierPrice(boolean same, SupplierPrice entity, FieldSet fs) {
		boolean isSame = same;
		// Price
		BigDecimal feedSupplierPrice = PriceFieldEnum.SupplierPrice.readBigDecimal(fs);
		// String currencyString = PriceFieldEnum.Currency.readString(fs);
		// CurrencyEnum currency = CurrencyEnum.getMapping(currencyString);
		CurrencyEnum currency = CurrencyEnum.EUR;
		if (entity.getSupplierPrice() == null || !entity.getSupplierPrice().isSame(feedSupplierPrice, currency)) {
			Price price = new Price(feedSupplierPrice, currency);
			entity.setSupplierPrice(price);
			isSame = false;
		}
		return isSame;
	}

	private boolean updatePromo(boolean same, SupplierPrice entity, FieldSet fs) {
		boolean isSame = same;
		Date promoBegin = PriceFieldEnum.PromoDateBegin.readDate(fs);
		Date promoEnd = PriceFieldEnum.PromoDateEnd.readDate(fs);
		Integer promoMinQty = PriceFieldEnum.PromoMinQuantity.readInteger(fs);
		Integer promoFreeQty = PriceFieldEnum.PromoProductFreeQty.readInteger(fs);
		String promoFreeProductSku = PriceFieldEnum.PromoProductFreeQty.readString(fs);
		// Manage default value
		if (promoMinQty != null && promoMinQty.intValue() < 1) {
			promoMinQty = null;
		}
		if (promoFreeQty != null && promoFreeQty.intValue() < 1) {
			promoFreeQty = null;
			promoFreeProductSku = null;
		}
		// Create Promo
		boolean isPromo = false;
		SupplierPromoDetail promoDetail = entity.getPromoDetail();
		// Manage Embeded promo
		boolean isPromoDetail = (promoBegin != null) || (promoEnd != null) || (promoMinQty != null) || (promoFreeQty != null) || (promoFreeProductSku != null);
		if (isPromoDetail && promoDetail == null) {
			promoDetail = new SupplierPromoDetail();
			entity.setPromoDetail(promoDetail);
			isSame = false;
		} else if (!isPromoDetail && promoDetail != null) {
			promoDetail = null;
			entity.setPromoDetail(null);
			isSame = false;
		}
		// Promo Flag
		CrcCodeEnum productType = CrcCodeEnum.readCrcCode(fs);
		if (productType != null && productType.isPromo()) {
			isPromo = true;
		}
		// Promo Detail
		if (promoDetail != null) {
			if (!Objects.equal(promoBegin, promoDetail.getPromoBegin())) {
				promoDetail.setPromoBegin(promoBegin);
				isSame = false;
			}
			if (!Objects.equal(promoEnd, promoDetail.getPromoEnd())) {
				promoDetail.setPromoBegin(promoBegin);
				isSame = false;
			}
			if (!Objects.equal(promoMinQty, promoDetail.getPromoMinQuantity())) {
				promoDetail.setPromoMinQuantity(promoMinQty);
				isSame = false;
			}
			if (!Objects.equal(promoFreeQty, promoDetail.getPromoFreeQuantity())) {
				promoDetail.setPromoFreeQuantity(promoFreeQty);
				isSame = false;
			}
			if (!Objects.equal(promoFreeProductSku, promoDetail.getPromoFreeProductSku())) {
				promoDetail.setPromoFreeProductSku(promoFreeProductSku);
				promoDetail.setPromoFreeProduct(null);
				isSame = false;
			}
		}
		// Promo Flag
		if (isPromo != entity.isPromo()) {
			entity.setPromo(isPromo);
			isSame = false;
		}

		return isSame;
	}

}
