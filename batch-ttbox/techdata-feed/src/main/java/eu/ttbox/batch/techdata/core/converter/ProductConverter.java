package eu.ttbox.batch.techdata.core.converter;

import eu.ttbox.batch.techdata.dao.TTBoxDAO;
import eu.ttbox.model.product.CatalogSrcEnum;
import eu.ttbox.model.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		if (cache!=null) {
			cache.clear();
			cache=null;
		}
	}

	public void printLogWarning() {
//		if (log.isWarnEnabled()) {
//			List<MappingCount> mappings = new ArrayList<MappingCount>(pbMapping.values());
//			Collections.sort(mappings);
//			for (MappingCount mapping : mappings) {
//				log.warn("No Mapping Product for {}", mapping);
//			}
//		}
	}

	private Product convertTechIdToProduct(String techProductId) {
		Product product = ttboxDAO.getProductByTechId(techProductId);
		return product;
	}

	private Map<String, Integer> getCache() {
		if (cache==null) {
			cache = new HashMap<String, Integer>();
			long begin = System.currentTimeMillis();
			List<Object[]> mappingIds = ttboxDAO.getProductIdWithTechId();
			for (Object[] mappingId : mappingIds) {
				Integer productId = (Integer)mappingId[0];
				String techProductId = (String)mappingId[1];
				cache.put(techProductId, productId);
			}
			long end = System.currentTimeMillis();
			log.info("Init cache {} products mappings in {} ms", mappingIds.size(), (end-begin));
		}
		return cache;
	}
	
	public Product getProductByTechId(String supplierId) {
		Product product = null; 
		// Read in local Cache
		Integer productId = getCache().get(supplierId);
		// Manage the not cached value
		if (productId == null) {
//			if (!pbMapping.containsKey(supplierId)) {
//				// Read in Database
//				product = convertTechIdToProduct(supplierId);
//				if (product != null) {
//					productId = product.getId();
//					getCache().put(supplierId, productId);
//				}
//			}
		} else {
			product = getProduct( productId); 
		}
		// Manage Pb Mapping
		if (product == null) {
			MappingCount count = pbMapping.get(supplierId);
			if (count != null) {
				count.addCount();
			} else {
				MappingCount mapCount =  new MappingCount(1).addAttribute(CatalogSrcEnum.TECHDATA.name(), supplierId); 
				pbMapping.put(supplierId,mapCount);
			}
		}
		return product;
	}
	
	public Integer getProductIdByTechId(String supplierId) {
		// Read in local Cache
		Integer productId = getCache().get(supplierId);
 
		return productId;
	}
	
	public Product getProduct(Integer productId) {
		return ttboxDAO.load(Product.class, productId);
	}

}
