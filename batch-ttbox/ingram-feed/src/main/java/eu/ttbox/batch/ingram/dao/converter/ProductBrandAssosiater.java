package eu.ttbox.batch.ingram.dao.converter;

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

import eu.ttbox.batch.ingram.dao.TTBoxDAO;
import eu.ttbox.model.product.brand.Brand;

@Service
// @Deprecated
public class ProductBrandAssosiater implements DisposableBean {

	private static Logger log = LoggerFactory.getLogger(ProductBrandAssosiater.class);

	@Autowired
	TTBoxDAO ttboxDAO;

	private Map<String, MappingCount> pbMapping = new HashMap<String, MappingCount>();

	private Map<Integer, Integer> cache;

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
		if (log.isWarnEnabled()) {
			List<MappingCount> mappings = new ArrayList<MappingCount>(pbMapping.values());
			Collections.sort(mappings);
			for (MappingCount mapping : mappings) {
				log.warn("No Mapping Product Brand for {}", mapping);
			}
		}
	}

	private Brand convertProductIdToBrand(Integer productId) {
		Brand entity = ttboxDAO.getBrandByProductId(productId);
		return entity;
	}

	private Map<Integer, Integer> getCache() {
		if (cache == null) {
			long begin = System.currentTimeMillis();
			cache = ttboxDAO.getMapByProductIdAndBrandId();
			long end = System.currentTimeMillis();
			log.info("Init cache {} Product brands mappings in {} ms", cache.size(), (end - begin));
		}
		return cache;
	}

	public Brand getBrandByProductId(Integer productId) {
		log.debug("Search Brand for {}", productId);
		Brand entity = null;
		// Read in local Cache
		Integer entityId = getCache().get(productId);
		// Manage the not cached value
		if (entityId == null) {
			if (!pbMapping.containsKey(productId)) {
				// Read in Database
				entity = convertProductIdToBrand(productId);
				if (entity != null) {
					entityId = entity.getId();
					getCache().put(productId, entityId);
				}
			}
		} else {
			entity = ttboxDAO.load(Brand.class, entityId);
		}
		// Manage Pb Mapping
		if (entity == null) {
			MappingCount count = pbMapping.get(productId);
			if (count != null) {
				count.addCount();
			} else {
				// MappingCount mapCount = new MappingCount(1).addAttribute(CatalogSrcEnum.TECHDATA.name(), productId);
				// pbMapping.put(productId, mapCount);
			}
		}
		return entity;
	}

	public Integer getBrandIdByProductId(Integer productId) {
		log.debug("Search Brand for {}", productId);
		// Read in local Cache
		Integer entityId = getCache().get(productId);
		// Manage the not cached value
		if (entityId == null) {
			if (!pbMapping.containsKey(productId)) {
				// Read in Database
				Brand entity = convertProductIdToBrand(productId);
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
				// MappingCount mapCount = new MappingCount(1).addAttribute(CatalogSrcEnum.TECHDATA.name(), productId);
				// pbMapping.put(productId, mapCount);
			}
		}
		return entityId;
	}

}
