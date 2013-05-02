package eu.ttbox.model.catalog;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.ttbox.model.pricing.Pricing;
import eu.ttbox.model.pricing.PricingTypeEnum;

public class PricingTest {

	Logger log = LoggerFactory.getLogger(getClass());

	BigDecimal supplierPriceHT = new BigDecimal("123.33");
	BigDecimal publicPriceHT = new BigDecimal("150.33");

	private Pricing createPricing(BigDecimal marginCoeff) {
		Pricing pricing = new Pricing();
		// pricing.setControlSellLoss(false);
		if (marginCoeff != null) {
			pricing.setMarginCoeff(marginCoeff);
		}
		return pricing;
	}

	@Test
	public void testGetPriceHT() {
		Pricing pricing = createPricing(new BigDecimal("0.122345768"));
		validattePricing(pricing);
	}

	public void testGetPriceHTForNotSellPolicy() {
		Pricing pricing = createPricing(new BigDecimal("0.122345768"));
		pricing.setType(PricingTypeEnum.NOT_SELL);
		BigDecimal priceHT = pricing.getPriceHT(supplierPriceHT, publicPriceHT);
		Assert.assertNull("No price to be set for NOT_SELL policy", priceHT);
	}

	//@Test
	public void getPriceHTForPublicPricePolicy() {
		// Check Normal Public Price
		Pricing pricing = createPricing(new BigDecimal("0.122345768"));
		pricing.setType(PricingTypeEnum.PUBLIC_PRICE);
		BigDecimal priceHT = pricing.getPriceHT(supplierPriceHT, publicPriceHT);
		Assert.assertNotNull(priceHT);
		Assert.assertEquals(publicPriceHT, priceHT);
		// Check Null Public Price
		priceHT = pricing.getPriceHT(supplierPriceHT, null);
		Assert.assertNull("No price to be set for PUBLIC_PRICE policy with no public price", priceHT);
		// Check LostSell Public Price
		priceHT = pricing.getPriceHT(BigDecimal.ZERO, publicPriceHT);
		// TODO
		// Assert.assertNull("No price to be set for PUBLIC_PRICE policy with LostSell",
		// priceHT);
	}

	private void validattePricing(Pricing pricing) {

		BigDecimal price = pricing.getPriceHT(supplierPriceHT, publicPriceHT);
		log.info("Sell Price {} from supplier {} with pricing {}", new Object[] { price, supplierPriceHT, pricing });
		Assert.assertNotNull(price);
	}
}
