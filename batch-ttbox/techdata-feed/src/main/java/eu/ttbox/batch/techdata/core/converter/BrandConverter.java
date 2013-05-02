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
import eu.ttbox.model.product.CatalogSrcEnum;
import eu.ttbox.model.product.brand.Brand;

@Service 
public class BrandConverter implements DisposableBean {

	private static Logger log = LoggerFactory.getLogger(BrandConverter.class);

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
			cache = null;
		}
	}

	public void printLogWarning() {
		if (log.isWarnEnabled()) {
			List<MappingCount> mappings = new ArrayList<MappingCount>(pbMapping.values());
			Collections.sort(mappings);
			for (MappingCount mapping : mappings) {
				log.warn("No Mapping Brand for {}", mapping);
			}
		}
	}

	private Brand createBrand(String techId, String name) {
		Brand brand = null;
		if (name != null) {
			brand = new Brand();
			brand.setName(name);
			brand.addSrc(CatalogSrcEnum.TECHDATA, techId, null);
			ttboxDAO.saveOrUpdate(brand);
			ttboxDAO.flush();
		}
		return brand;
	}
	
	
	private Brand convertTechIdToBrand(String techId) {
		Brand entity = ttboxDAO.getBrandByTechId(techId);
		if (entity==null) {
			entity = createBrand(techId, techId.toLowerCase());
		}
		return entity;
	}
	

	private Map<String, Integer> getCache() {
		if (cache==null) {
			long begin = System.currentTimeMillis();
			cache =  ttboxDAO.getBrandIdWithTechId();
			long end = System.currentTimeMillis();
			log.info("Init cache {} brands mappings in {} ms", cache.size(), (end-begin));
		}
		return cache;
	}
	

	public Brand getBrandByTechId(String supplierId) {
		log.debug("Search Brand for {}", supplierId);
		Brand entity = null;
		// Read in local Cache
		Integer entityId = getCache().get(supplierId);
		// Manage the not cached value
		if (entityId == null) {
			if (!pbMapping.containsKey(supplierId)) {
				// Read in Database
				entity = convertTechIdToBrand(supplierId);
				if (entity != null) {
					entityId = entity.getId();
					getCache().put(supplierId, entityId);
				}
			}
		} else {
			entity = ttboxDAO.load(Brand.class, entityId); 
		}
		// Manage Pb Mapping
		if (entity == null) {
			MappingCount count = pbMapping.get(supplierId);
			if (count != null) {
				count.addCount();
			} else {
				MappingCount mapCount = new MappingCount(1).addAttribute(CatalogSrcEnum.TECHDATA.name(), supplierId);
				pbMapping.put(supplierId, mapCount);
			}
		}
		return entity;
	}

}
