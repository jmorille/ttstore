package eu.ttbox.batch.ingram.product;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.google.common.base.Objects;
import com.google.common.base.Strings;

import eu.ttbox.batch.ingram.dao.converter.ProductConverter;
import eu.ttbox.batch.ingram.price.CrcCodeEnum;
import eu.ttbox.batch.ingram.price.PriceFieldEnum;
import eu.ttbox.batch.ingram.price.diff.PriceDifferentialItem;
import eu.ttbox.model.product.CatalogSrcEnum;
import eu.ttbox.model.product.Product;
import eu.ttbox.model.supplier.SupplierPrice;

@Service
public class ProductItemProcessor implements ItemProcessor<PriceDifferentialItem<SupplierPrice, FieldSet>, Product>, InitializingBean {

	private static final Logger LOG = LoggerFactory.getLogger(ProductItemProcessor.class);

	@Autowired
	ProductConverter productConverter;

	@Autowired
	Client esClient;

	@Autowired
	EsProductSCopyIdxToRefItemWriter esProductSynchroItemWriter;

	@Value("${es.icecat.all.indexName}")
	String allIndexName;

	long timeoutMillis = 1000;

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.hasText(allIndexName, "allIndexName is mandatory");
	}

	@Override
	public Product process(PriceDifferentialItem<SupplierPrice, FieldSet> item) throws Exception {
		CatalogSrcEnum catalog = CatalogSrcEnum.INGRAM;
		ProductEntityDifferentialVO status = item.parent.products;
		String supplierId = item.getSupplierId();
		// init variable
		SupplierPrice supplierPrice = item.getMasterItem();
		Product product = null;
		if (supplierPrice != null) {
			product = supplierPrice.getProduct();
		}
		FieldSet fs = item.getJoinItem();
		// Create Product
		boolean isCreate = false;
		boolean isSame = true;
		// Product Status
		CrcCodeEnum productType = CrcCodeEnum.readCrcCode(fs);
		// if (productType == null) {
		// LOG.error("Not manage CrcCode {} : {} ", PriceFieldEnum.CrcCode.readString(fs), fs);
		// return null;
		// }
		if (productType != null) {
			// Manage Ignore
			if (!productType.isSellable()) {
				status.addIgnoreNotSellable();
				return null;
			}
			if (productType.isLicence()) {
				status.addIgnoreLicence();
				return null;
			}

		}
		// Search Existing Product
		String skuProductId = PriceFieldEnum.Sku.readString(fs);
		if (product == null) {
			// Search product
			product = productConverter.getProductByIngramId(skuProductId);
			// Keep As cache for later
			Integer productId = productConverter.getProductIdByIngramId(skuProductId);
			item.setProductId(productId);
		}
		// Create Product
		if (product == null) {
			product = new Product();
			product.addSrc(catalog, supplierId, "Ingram feed");
			// Manage Status
			isCreate = true;
		}

		// Update Product Datas
		isSame = isUpdateProductDescription(isSame, product, fs);
		isSame = isUpdateProductIdentier(isSame, product, fs);
		isSame = isUpdatePublicPrice(isSame, product, fs);
		isSame = isUpdateProductCrc(isSame, product, fs, productType);
		// Icecat Link
		isSame = isUpdateProductIcecat(isSame, product, fs);

		// Result
		if (isCreate) {
			item.parent.products.addCreate(product);
		} else if (!isSame) {
			item.parent.products.addUpdate(product);
		}
		return product;
	}

	private boolean isUpdateProductCrc(boolean isSam, Product product, FieldSet fs, CrcCodeEnum productType) {
		boolean isSame = isSam;
		if (productType == null) {
			return isSame;
		}
		// Manage Kit
		boolean isKit = productType.isKit();
		if (product.isKit() == isKit) {
			product.setKit(isKit);
			isSame = false;
		}
		return isSame;
	}

	private boolean isUpdateProductIcecat(boolean isSam, Product product, FieldSet fs) {
		boolean isSame = isSam;
		String icecatId = product.getExtIcecatId();
		if (Strings.isNullOrEmpty(icecatId)) {
			// Icecat Link
			String ean = product.getEan();
			if (!Strings.isNullOrEmpty(ean)) {
				TermQueryBuilder query = QueryBuilders.termQuery("eans", ean);
				SearchResponse response = esClient.prepareSearch(allIndexName).setQuery(query).setSize(1).execute().actionGet(timeoutMillis);
				SearchHits hits = response.hits();
				if (hits.totalHits() == 1) {
					SearchHit hit = hits.hits()[0];
					icecatId = hit.getId();
					product.addSrc(CatalogSrcEnum.ICECAT, icecatId, "BY_EAN");
					isSame = false;

				} else if (hits.totalHits() > 1) {
					LOG.warn("Could not associate ean {} and found {} potential Icecat Product", ean, hits.totalHits());
				} else {
					LOG.debug("Could not associate ean {} to Icecat from {}", ean, product);
				}
			}
		}
		// Synchro Index
		if (!isSame && !Strings.isNullOrEmpty(icecatId)) {
			ArrayList<String> request = new ArrayList<String>(1);
			request.add(icecatId);
			esProductSynchroItemWriter.write(request);
		}
		return isSame;
	}

	private boolean isUpdateProductIdentier(boolean isSam, Product product, FieldSet fs) {
		boolean isSame = isSam;
		String ean = PriceFieldEnum.Ean.readString(fs);
		String partNumber = PriceFieldEnum.PartNumber.readString(fs);
		if (!Objects.equal(ean, product.getEan())) {
			if (ean != null && ean.length() == 13) {
				product.setEan(ean);
				isSame = false;
			} else if (product.getEan() != null) {
				product.setEan(null);
				isSame = false;
			}
		}
		if (!Objects.equal(partNumber, product.getPartNumber())) {
			product.setPartNumber(partNumber);
			isSame = false;
		}

		return isSame;
	}

	private boolean isUpdateProductDescription(boolean isSam, Product product, FieldSet fs) {
		boolean isSame = isSam;
		String name = PriceFieldEnum.ProductDesc1.readString(fs);
		if (!Objects.equal(name, product.getName())) {
			product.setName(name);
			isSame = false;
		}
		String desc = PriceFieldEnum.ProductDesc2.readString(fs);
		if (!Objects.equal(desc, product.getDescription())) {
			product.setDescription(desc);
			isSame = false;
		}
		return isSame;
	}

	private boolean isUpdatePublicPrice(boolean isSam, Product product, FieldSet fs) {
		boolean isSame = isSam;
		// Price Public
		BigDecimal pricePub = PriceFieldEnum.PublicPrice.readBigDecimal(fs);
		if (!isEquals(pricePub, product.getPriceHT())) {
			product.setPriceHT(pricePub);
			isSame = false;
		}
		return isSame;

	}

	private boolean isEquals(BigDecimal feedVal, BigDecimal refVal) {
		boolean isSame = (feedVal == null ? refVal == null : feedVal.compareTo((refVal == null ? BigDecimal.ZERO : refVal)) == 0);
		return isSame;
	}

}
