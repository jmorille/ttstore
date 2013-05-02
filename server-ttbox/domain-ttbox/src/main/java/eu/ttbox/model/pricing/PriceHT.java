package eu.ttbox.model.pricing;

import java.math.BigDecimal;

import eu.ttbox.model.supplier.SupplierPrice;

public class PriceHT {

	Long pricingVersion;

	Integer supplierPriceVersion; 

	Price supplierPriceHT;

	BigDecimal priceHT;

	public void setSupplierPrice(SupplierPrice supplierPrice) {
		this.supplierPriceHT = supplierPrice.getSupplierPrice(); 
		this.supplierPriceVersion = supplierPrice.getVersion();
	}

	public void setPricing(Pricing pricing) {
		BigDecimal publicPriceHT = null;
		priceHT = pricing.getPriceHT(supplierPriceHT.getAmount(), publicPriceHT);
	}

}
