package eu.ttbox.batch.techdata.core.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.ttbox.batch.techdata.dao.TTBoxDAO;
import eu.ttbox.model.product.category.Category;

@Service 
//@Deprecated
public class ProductCategoryAssosiater implements DisposableBean {

	private static Logger log = LoggerFactory.getLogger(ProductCategoryAssosiater.class);

	@Autowired
	TTBoxDAO ttboxDAO;

	private Map<String, MappingCount> pbMapping = new HashMap<String, MappingCount>();

	private Map<Integer, Integer> cache;

	@Override
	public void destroy() throws Exception {
		printLogWarning();
		pbMapping.clear();
		if (cache!=null) {
			cache.clear();
			cache = null;
		}
	}

	public void printLogWarning() {
		if (log.isWarnEnabled()) {
			List<MappingCount> mappings = new ArrayList<MappingCount>(pbMapping.values());
			Collections.sort(mappings);
			for (MappingCount mapping : mappings) {
				log.warn("No Mapping Product Brand for {}", mapping);
			}
		}
	} 
	
	
	private Category convertProductIdToCategory(Integer productId) {
		Category entity = ttboxDAO.getCategoryByProductId(productId); 
		return entity;
	}
	

	private Map<Integer, Integer> getCache() {
		if (cache==null) {
			long begin = System.currentTimeMillis();
			cache = ttboxDAO.getMapByProductIdAndCategoryId();
			long end = System.currentTimeMillis();
			log.info("Init cache {} Product brands mappings in {} ms", cache.size(), (end-begin));
		}
		return cache;
	}
	

	public Category getCategoryByProductId(Integer productId) {
		log.debug("Search Category for {}", productId);
		Category entity = null;
		// Read in local Cache
		Integer entityId = getCache().get(productId);
		// Manage the not cached value
		if (entityId == null) {
			if (!pbMapping.containsKey(productId)) {
				// Read in Database
				entity = convertProductIdToCategory(productId);
				if (entity != null) {
					entityId = entity.getId();
					getCache().put(productId, entityId);
				}
			}
		} else {
			entity = ttboxDAO.load(Category.class, entityId); 
		}
		// Manage Pb Mapping
		if (entity == null) {
			MappingCount count = pbMapping.get(productId);
			if (count != null) {
				count.addCount();
			} else {
//				MappingCount mapCount = new MappingCount(1).addAttribute(CatalogSrcEnum.TECHDATA.name(), productId);
//				pbMapping.put(productId, mapCount);
			}
		}
		return entity;
	}
	
	
	public Integer getCategoryIdByProductId(Integer productId) {
		log.debug("Search Category for {}", productId);
 
		// Read in local Cache
		Integer entityId = getCache().get(productId);
		// Manage the not cached value
		if (entityId == null) {
			if (!pbMapping.containsKey(productId)) {
				// Read in Database
				Category entity = convertProductIdToCategory(productId);
				if (entity != null) {
					entityId = entity.getId();
					getCache().put(productId, entityId);
				}
			}
		} 
		// Manage Pb Mapping
		if (entityId == null) {
			MappingCount count = pbMapping.get(productId);
			if (count != null) {
				count.addCount();
			} else {
//				MappingCount mapCount = new MappingCount(1).addAttribute(CatalogSrcEnum.TECHDATA.name(), productId);
//				pbMapping.put(productId, mapCount);
			}
		}
		return entityId;
	}

}
