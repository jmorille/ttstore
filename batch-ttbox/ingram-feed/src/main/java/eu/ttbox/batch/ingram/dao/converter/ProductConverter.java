package eu.ttbox.batch.ingram.dao.converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.ttbox.batch.ingram.dao.TTBoxDAO;
import eu.ttbox.model.product.CatalogSrcEnum;
import eu.ttbox.model.product.Product;

@Service
public class ProductConverter implements DisposableBean {

	private static Logger log = LoggerFactory.getLogger(ProductConverter.class);

	@Autowired
	TTBoxDAO ttboxDAO;

	private Map<String, MappingCount> pbMapping = new HashMap<String, MappingCount>();

	private Map<String, Integer> cache;

	@Override
	public void destroy() throws Exception {
		printLogWarning();
		pbMapping.clear();
		if (cache != null) {
			cache.clear();
			cache = null;
		}
	}

	public void printLogWarning() {
		// if (log.isWarnEnabled()) {
		// List<MappingCount> mappings = new ArrayList<MappingCount>(pbMapping.values());
		// Collections.sort(mappings);
		// for (MappingCount mapping : mappings) {
		// log.warn("No Mapping Product for {}", mapping);
		// }
		// }
	}

	private Product convertIngramIdToProduct(String supplierProductId) {
		Product product = ttboxDAO.getProductByIngramId(supplierProductId);
		return product;
	}

	private Map<String, Integer> getCache() {
		if (cache == null) {
			cache = new HashMap<String, Integer>();
			long begin = System.currentTimeMillis();
			List<Object[]> mappingIds = ttboxDAO.getProductIdWithIngramId();
			for (Object[] mappingId : mappingIds) {
				Integer productId = (Integer) mappingId[0];
				String ingramProductId = (String) mappingId[1];
				cache.put(ingramProductId, productId);
			}
			long end = System.currentTimeMillis();
			log.info("Init cache {} products mappings in {} ms", mappingIds.size(), (end - begin));
		}
		return cache;
	}

	public Product getProductByIngramId(String supplierId) {
		Product product = null;
		// Read in local Cache
		Integer productId = getCache().get(supplierId);
		// Manage the not cached value
		if (productId == null) {
			// if (!pbMapping.containsKey(supplierId)) {
			// // Read in Database
			// product = convertTechIdToProduct(supplierId);
			// if (product != null) {
			// productId = product.getId();
			// getCache().put(supplierId, productId);
			// }
			// }
		} else {
			product = getProduct(productId);
		}
		// Manage Pb Mapping
		if (product == null) {
			MappingCount count = pbMapping.get(supplierId);
			if (count != null) {
				count.addCount();
			} else {
				MappingCount mapCount = new MappingCount(1).addAttribute(CatalogSrcEnum.INGRAM.name(), supplierId);
				pbMapping.put(supplierId, mapCount);
			}
		}
		return product;
	}

	public Integer getProductIdByIngramId(String supplierId) {
		// Read in local Cache
		Integer productId = getCache().get(supplierId);

		return productId;
	}

	public Product getProduct(Integer productId) {
		return ttboxDAO.load(Product.class, productId);
	}

}
