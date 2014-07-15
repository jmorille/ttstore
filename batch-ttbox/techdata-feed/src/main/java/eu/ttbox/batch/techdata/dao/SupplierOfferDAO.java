package eu.ttbox.batch.techdata.dao;

import eu.ttbox.batch.techdata.core.converter.ProductBrandAssosiater;
import eu.ttbox.batch.techdata.core.converter.ProductCategoryAssosiater;
import eu.ttbox.batch.techdata.price.diff.PriceDifferentialItem;
import eu.ttbox.model.catalog.Offer;
import eu.ttbox.model.product.Product;
import eu.ttbox.model.product.brand.Brand;
import eu.ttbox.model.product.category.Category;
import eu.ttbox.model.salespoint.Salespoint;
import eu.ttbox.model.salespoint.SalespointBrand;
import eu.ttbox.model.salespoint.SalespointCategory;
import eu.ttbox.model.salespoint.SalespointSupplier;
import eu.ttbox.model.supplier.SupplierFeedStatus;
import eu.ttbox.model.supplier.SupplierPrice;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SupplierOfferDAO extends HibernateDaoSupport {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	ProductCategoryAssosiater productCategoryAssosiater;

	@Autowired
	ProductBrandAssosiater productBrandAssosiater;

	@Autowired
	public SupplierOfferDAO(@Qualifier("ttboxSessionFactory") SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	// @Transactional(propagation=Propagation.REQUIRES_NEW)
	public void saveOfferUpdates(SupplierFeedStatus status, List<SupplierPrice> updates) {
		Session session = getSession();
        for (SupplierPrice update : updates) {
            getHibernateTemplate().saveOrUpdate(update);
        }
		// Status
		status.setCountUpdate(updates.size());
		getHibernateTemplate().saveOrUpdate(status);
	}

	@SuppressWarnings("unused")
	// @Transactional(propagation=Propagation.REQUIRES_NEW)
	public void saveOfferCreates(SupplierFeedStatus status, List<PriceDifferentialItem<SupplierPrice, FieldSet>> creates) {
		StopWatch stopWatch = new StopWatch("Insert " + creates.size() + " SupplierPrice for " + status.getSupplier() + "=" + status.getSupplierId());

		// --- Insert Supplier Price ---
		// -----------------------------
		if (!creates.isEmpty()) {
			stopWatch.start("Insert SupplierPrice");
			final List<PriceDifferentialItem<SupplierPrice, FieldSet>> entities = creates;
			getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Object>() {
				public Object doInHibernate(Session session) throws HibernateException {
					// checkWriteOperationAllowed(session);
					for (PriceDifferentialItem<SupplierPrice, FieldSet> entity : entities) {
						session.saveOrUpdate(entity.getMasterItem());
					}
					return null;
				}
			});
			stopWatch.stop();
		}
		log.info(stopWatch.prettyPrint());
		
		
		// --- Create Offer ---
		// --------------------
		List<SalespointSupplier> suppliers = (List<SalespointSupplier>) getHibernateTemplate().find("from SalespointSupplier ss where ss.supplier=? and ss.supplierId=?",
				status.getSupplier(), status.getSupplierId());
		for (SalespointSupplier supplier : suppliers) {
			Salespoint salespoint = supplier.getSalespoint();
			stopWatch.start("Insert Offer for Salespoint id " + salespoint.toString());
			List<Offer> offers = new ArrayList<Offer>(creates.size());
			Map<Integer, Integer> brandCache = getSalespointBrandCache(salespoint);
			Map<Integer, Integer> cacheCategoryIdKey = getSalespointCategoryCache(salespoint);
			for (PriceDifferentialItem<SupplierPrice, FieldSet> itemDiff : creates) {
				SupplierPrice supplierPrice = itemDiff.getMasterItem();
				Product product = supplierPrice.getProduct();
				Integer productBrandId = productBrandAssosiater.getBrandIdByProductId(itemDiff.getProductId());
				Integer productCategoryId = productCategoryAssosiater.getCategoryIdByProductId(itemDiff.getProductId());
				SalespointBrand sbrand = getSalespointBrand(brandCache, salespoint, productBrandId);
				SalespointCategory scat = getSalespointCategory(cacheCategoryIdKey, salespoint, productCategoryId);
//				if (sbrand != null && scat != null) {
					Offer offer = new Offer();
					offer.setSalespoint(salespoint);
					offer.setSupplierPrice(supplierPrice);
					offer.setProduct(product);
					offer.setBrand(sbrand);
					offer.setCategory(scat);
					// TODO offer.setPricing(pricing);
					// Register Offer
					offers.add(offer);
//				}
			}
            for (Offer offer : offers)  {
                getHibernateTemplate().saveOrUpdate(offer);
            }
			stopWatch.stop();
			log.info(stopWatch.prettyPrint());
		}
		// Status
		stopWatch.start("Update SupplierFeedStatus");
		status.setCountCreate(creates.size());
		getHibernateTemplate().saveOrUpdate(status);
		stopWatch.stop();
		// Print Log
		log.info(stopWatch.prettyPrint());
	}

	private Map<Integer, Integer> getSalespointBrandCache(Salespoint salespoint) {
		long begin = System.currentTimeMillis();
		Map<Integer, Integer> mappingIds = new HashMap<Integer, Integer>();
		List<Integer[]> sbrands = (List<Integer[]> )getHibernateTemplate().find("select sb.brand.id, sb.id from SalespointBrand sb where sb.salespoint=?", salespoint);
		for (Integer[] sbrand : sbrands) {
			Integer brandId = sbrand[0];
			Integer entityId = sbrand[1];
			mappingIds.put(brandId, entityId);
		}
		long end = System.currentTimeMillis();
		log.info("Init cache {} Salespoint brands mappings in {} ms", mappingIds.size(), (end - begin));
		return mappingIds;
	}

	private Map<Integer, Integer> getSalespointCategoryCache(Salespoint salespoint) {
		long begin = System.currentTimeMillis();
		Map<Integer, Integer> mappingIds = new HashMap<Integer, Integer>();
		List<Integer[]> sbrands = (List<Integer[]> )getHibernateTemplate().find("select sb.category.id, sb.id from SalespointCategory sb where sb.salespoint=?", salespoint);
		for (Integer[] sbrand : sbrands) {
			Integer catId = sbrand[0];
			Integer entityId = sbrand[1];
			mappingIds.put(catId, entityId);
		}
		long end = System.currentTimeMillis();
		log.info("Init cache {} Salespoint categories mappings in {} ms", mappingIds.size(), (end - begin));
		return mappingIds;
	}

	private SalespointCategory getSalespointCategory(Map<Integer, Integer> cacheCategoryIdKey, Salespoint salespoint, Integer categoryId) {
		Integer sCatId = cacheCategoryIdKey.get(categoryId);
		// Check Cache
		if (sCatId != null) {
			return getHibernateTemplate().load(SalespointCategory.class, sCatId);
		}
		// Search
		SalespointCategory result = null;
//		List<SalespointCategory> sCategories = getHibernateTemplate().find("from SalespointCategory sb where sb.salespoint=? and sb.category.id=?", salespoint,
//				categoryId);
//		if (sCategories.size() == 1) {
//			result = sCategories.get(0);
//		} else if (sCategories.size() > 1) {
//			log.warn("More than one result for SalespointCategory with salespoint = {} and categoryId = {}", salespoint, categoryId);
//		} else {
			// Create
			if (result == null) {
				result = new SalespointCategory();
				result.setSalespoint(salespoint);
				Category category = getHibernateTemplate().load(Category.class, categoryId);
				result.setCategory(category);
				getHibernateTemplate().save(result);
				getHibernateTemplate().flush();
			}
			// Add To Cache
			cacheCategoryIdKey.put(categoryId, result.getId());
//		}
		return result;
	}

	private SalespointBrand getSalespointBrand(Map<Integer, Integer> cacheBrandidKey, Salespoint salespoint, Integer brandId) {
		Integer sBrandId = cacheBrandidKey.get(brandId);
		// Check Cache
		if (sBrandId != null) {
			return getHibernateTemplate().load(SalespointBrand.class, sBrandId);
		}
		// Search
		SalespointBrand result = null;
//		List<SalespointBrand> sbrands = getHibernateTemplate().find("from SalespointBrand sb where sb.salespoint=? and sb.brand.id=?", salespoint, brandId);
//		if (sbrands.size() == 1) {
//			result = sbrands.get(0);
//		} else if (sbrands.size() > 1) {
//			log.warn("More than one result for SalespointBrand with salespoint = {} and brandId = {}", salespoint, brandId);
//		} else {
			// Create
			if (result == null) {
				result = new SalespointBrand();
				result.setSalespoint(salespoint);
				Brand brand = getHibernateTemplate().load(Brand.class, brandId);
				result.setBrand(brand);
				getHibernateTemplate().save(result);
				getHibernateTemplate().flush();
			}
			// Add To Cache
			cacheBrandidKey.put(brandId, result.getId());
//		}
		return result;
	}

	// @Transactional(propagation=Propagation.REQUIRES_NEW)
	public void saveOfferDeletes(SupplierFeedStatus status, List<SupplierPrice> deletes) {
		getHibernateTemplate().deleteAll(deletes);
		// Status
		status.setCountDelete(deletes.size());
		getHibernateTemplate().saveOrUpdate(status);
	}

}
