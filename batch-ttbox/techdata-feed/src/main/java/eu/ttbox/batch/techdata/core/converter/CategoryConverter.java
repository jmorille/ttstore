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
import eu.ttbox.model.product.category.Category;

@Service
public class CategoryConverter implements DisposableBean {

	private static Logger log = LoggerFactory.getLogger(CategoryConverter.class);

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
		if (log.isWarnEnabled()) {
			List<MappingCount> mappings = new ArrayList<MappingCount>(pbMapping.values());
			Collections.sort(mappings);
			for (MappingCount mapping : mappings) {
				log.warn("No Mapping Category for {}", mapping);
			}
		}
	}

	private Category createCategory(String techId, String name) {
		Category category = null;
		if (name != null) {
			category = new Category();
			category.setName(name);
			category.addSrc(CatalogSrcEnum.TECHDATA, techId, null);
			ttboxDAO.saveOrUpdate(category);
			ttboxDAO.flush();
		}
		return category;
	}

	private Category convertTechIdToCategory(String techId, String name) {
		Category category = ttboxDAO.getCategoryByTechId(techId);
		if (category == null) {
			// Resolved by names
			category = ttboxDAO.getCategoryByName(name);
		}
		if (category == null) {
			category = createCategory(techId, name);
		}
		return category;
	}

	private Map<String, Integer> getCache() {
		if (cache==null) {
			long begin = System.currentTimeMillis();
			cache = ttboxDAO.getCategoryIdWithTechId();
			long end = System.currentTimeMillis();
			log.info("Init cache {} categories mappings in {} ms", cache.size(), (end-begin));
		}
		return cache;
	}
	
	public Category getCategoryByTechId(String supplierId, String name) {
		Category entity = null;
		// Read in local Cache
		Integer entityId = getCache().get(supplierId);
		// Manage the not cached value
		if (entityId == null) {
			if (!pbMapping.containsKey(supplierId)) {
				// Read in Database
				entity = convertTechIdToCategory(supplierId, name);
				if (entity != null) {
					entityId = entity.getId();
					 getCache().put(supplierId, entityId);
				}
			}
		} else {
			entity = ttboxDAO.load(Category.class, entityId); 
		}
		// Manage Pb Mapping
		if (entity == null) {
			MappingCount count = pbMapping.get(supplierId);
			if (count != null) {
				count.addCount();
			} else {
				MappingCount mapCount = new MappingCount(1).addAttribute(CatalogSrcEnum.TECHDATA.name(), supplierId);
				if (name != null) {
					mapCount.addAttribute("TECHDATA-name", name);
				}
				pbMapping.put(supplierId, mapCount);
			}
		}
		return entity;
	}

}
