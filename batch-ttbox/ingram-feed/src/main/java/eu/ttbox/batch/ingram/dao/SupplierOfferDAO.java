package eu.ttbox.batch.ingram.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import eu.ttbox.batch.ingram.dao.converter.ProductBrandAssosiater;
import eu.ttbox.batch.ingram.dao.converter.ProductCategoryAssosiater;
import eu.ttbox.batch.ingram.price.diff.PriceDifferentialItem;
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

@Service
public class SupplierOfferDAO // extends HibernateDaoSupport
{

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	ProductCategoryAssosiater productCategoryAssosiater;

	@Autowired
	ProductBrandAssosiater productBrandAssosiater;

	SessionFactory sessionFactory;

	@Autowired
	TTBoxDAO ttboxDAO;

	@Autowired
	public SupplierOfferDAO(@Qualifier("ttboxSessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		// super.setSessionFactory(sessionFactory);
	}

	// @Transactional(propagation=Propagation.REQUIRES_NEW)
	public void saveOfferUpdates(SupplierFeedStatus status, List<SupplierPrice> updates) {
		Session session = sessionFactory.getCurrentSession();
		for (SupplierPrice entity : updates) {
			session.saveOrUpdate(entity);
		}
		// Status
		status.setCountUpdate(updates.size());
		session.saveOrUpdate(status);
	}

	@SuppressWarnings("unused")
	// @Transactional(propagation=Propagation.REQUIRES_NEW)
	public void saveOfferCreates(SupplierFeedStatus status, List<PriceDifferentialItem<SupplierPrice, FieldSet>> creates) {
		StopWatch stopWatch = new StopWatch("Insert " + creates.size() + " SupplierPrice for " + status.getSupplier() + "=" + status.getSupplierId());

		Session session = sessionFactory.getCurrentSession();
		// --- Insert Supplier Price ---
		// -----------------------------
		if (!creates.isEmpty()) {
			stopWatch.start("Insert SupplierPrice");
			for (PriceDifferentialItem<SupplierPrice, FieldSet> entity : creates) {
				session.saveOrUpdate(entity.getMasterItem());
			}
			stopWatch.stop();
		}
		log.info(stopWatch.prettyPrint());

		// --- Create Offer ---
		// --------------------
		if (false) {
			Query query = session.createQuery("from SalespointSupplier ss where ss.supplier=? and ss.supplierId=?");
			query.setParameter(0, status.getSupplier()).setParameter(1, status.getSupplierId());
			List<SalespointSupplier> suppliers = query.list();
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
					// if (sbrand != null && scat != null) {
					Offer offer = new Offer();
					offer.setSalespoint(salespoint);
					offer.setSupplierPrice(supplierPrice);
					offer.setProduct(product);
					offer.setBrand(sbrand);
					offer.setCategory(scat);
					// TODO offer.setPricing(pricing);
					// Register Offer
					offers.add(offer);
					// }
				}
				for (Offer offer : offers) {
					session.saveOrUpdate(offer);
				}
				stopWatch.stop();
				log.info(stopWatch.prettyPrint());
			}

		}

		// Status
		stopWatch.start("Update SupplierFeedStatus");
		status.setCountCreate(creates.size());
		session.saveOrUpdate(status);
		stopWatch.stop();
		// Print Log
		log.info(stopWatch.prettyPrint());
	}

	private Map<Integer, Integer> getSalespointBrandCache(Salespoint salespoint) {
		long begin = System.currentTimeMillis();
		Map<Integer, Integer> mappingIds = new HashMap<Integer, Integer>();
		Session session = sessionFactory.getCurrentSession();
		List<Integer[]> sbrands = session.createQuery("select sb.brand.id, sb.id from SalespointBrand sb where sb.salespoint=?").setParameter(0, salespoint)
				.list();

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
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select sb.category.id, sb.id from SalespointCategory sb where sb.salespoint=?").setParameter(0, salespoint);
		List<Integer[]> sbrands = query.list();
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
		Session session = sessionFactory.getCurrentSession();
		// Check Cache
		if (sCatId != null) {
			return (SalespointCategory) session.load(SalespointCategory.class, sCatId);
		}
		// Search
		SalespointCategory result = null;
		// List<SalespointCategory> sCategories = getHibernateTemplate().find("from SalespointCategory sb where sb.salespoint=? and sb.category.id=?",
		// salespoint,
		// categoryId);
		// if (sCategories.size() == 1) {
		// result = sCategories.get(0);
		// } else if (sCategories.size() > 1) {
		// log.warn("More than one result for SalespointCategory with salespoint = {} and categoryId = {}", salespoint, categoryId);
		// } else {
		// Create
		if (result == null) {
			result = new SalespointCategory();
			result.setSalespoint(salespoint);
			Category category = (Category) session.load(Category.class, categoryId);
			result.setCategory(category);
			session.save(result);
			session.flush();
		}
		// Add To Cache
		cacheCategoryIdKey.put(categoryId, result.getId());
		// }
		return result;
	}

	private SalespointBrand getSalespointBrand(Map<Integer, Integer> cacheBrandidKey, Salespoint salespoint, Integer brandId) {
		Integer sBrandId = cacheBrandidKey.get(brandId);
		// Check Cache
		Session session = sessionFactory.getCurrentSession();
		if (sBrandId != null) {
			return (SalespointBrand) session.load(SalespointBrand.class, sBrandId);
		}
		// Search
		SalespointBrand result = null;
		// List<SalespointBrand> sbrands = getHibernateTemplate().find("from SalespointBrand sb where sb.salespoint=? and sb.brand.id=?", salespoint, brandId);
		// if (sbrands.size() == 1) {
		// result = sbrands.get(0);
		// } else if (sbrands.size() > 1) {
		// log.warn("More than one result for SalespointBrand with salespoint = {} and brandId = {}", salespoint, brandId);
		// } else {
		// Create
		if (result == null) {
			result = new SalespointBrand();
			result.setSalespoint(salespoint);
			Brand brand = (Brand) session.load(Brand.class, brandId);
			result.setBrand(brand);
			session.save(result);
			session.flush();
		}
		// Add To Cache
		cacheBrandidKey.put(brandId, result.getId());
		// }
		return result;
	}

	// @Transactional(propagation=Propagation.REQUIRES_NEW)
	public void saveOfferDeletes(SupplierFeedStatus status, List<SupplierPrice> deletes) {
		Session session = sessionFactory.getCurrentSession();
		for (SupplierPrice delete : deletes) {
			session.delete(delete);
		}
		// Status
		status.setCountDelete(deletes.size());
		session.saveOrUpdate(status);
	}

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
}
