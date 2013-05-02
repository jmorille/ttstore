package eu.ttbox.batch.ingram.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import eu.ttbox.model.product.CatalogSrcEnum;
import eu.ttbox.model.product.Product;
import eu.ttbox.model.product.brand.Brand;
import eu.ttbox.model.product.category.Category;
import eu.ttbox.model.supplier.SupplierEnum;

@Repository("ttboxDAO")
// @Transactional
public class TTBoxDAO // extends HibernateDaoSupport
{

	SessionFactory sessionFactory;

	@Autowired
	public TTBoxDAO(@Qualifier("ttboxSessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		// super.setSessionFactory(sessionFactory);
	}

	public void flushAndClear() {
		Session session = sessionFactory.getCurrentSession();
		session.flush();
		session.clear();
	}

	public void flush() {
		Session session = sessionFactory.getCurrentSession();
		session.flush();
	}

	public <T> T get(Class<T> entityClass, Serializable id) {
		Session session = sessionFactory.getCurrentSession();
		return (T) session.get(entityClass, id);
	}

	public <T> T load(Class<T> entityClass, Serializable id) {
		Session session = sessionFactory.getCurrentSession();
		return (T) session.load(entityClass, id);
	}

	public <T> void deleteAll(@SuppressWarnings("rawtypes") Collection<T> entities) {
		Session session = sessionFactory.getCurrentSession();
		for (T entity : entities) {
			session.delete(entities);
		}
	}

	public <T> void saveOrUpdate(Object entity) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(entity);
	}

	public <T> void saveOrUpdateAll(@SuppressWarnings("rawtypes") Collection<T> entities) {
		Session session = sessionFactory.getCurrentSession();
		for (T entity : entities) {
			session.saveOrUpdate(entity);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getProductIdWithIngramId() {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select p.id, p.extIds.ingramId from Product p where p.extIds.ingramId is not null";
		Query query = session.createQuery(queryString);
		List<Object[]> result = query.list();
		return result;
	}

	public Product getProductByIngramId(String ingramProductId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("product.findByIngramProductId");
		query.setParameter(0, ingramProductId);
		List<Product> result = query.list();
		Product entity = null;
		if (result != null && !result.isEmpty()) {
			return result.get(0);
		}
		return entity;
	}

	public Category getCategoryBySupplierId(CatalogSrcEnum supplier, String techId) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select p from Category p inner join p.srcs src where src.source=? and src.sourceId=?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, supplier);
		query.setParameter(1, techId);
		@SuppressWarnings("unchecked")
		List<Category> result = query.list();
		Category entity = null;
		if (result != null && !result.isEmpty()) {
			return result.get(0);
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	public Brand getBrandBySupplierId(CatalogSrcEnum supplier, String supplierId) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select p from Brand p inner join p.srcs src where src.source=? and src.sourceId=?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, supplier);
		query.setParameter(1, supplierId);
		List<Brand> result = query.list();
		Brand entity = null;
		if (result != null && !result.isEmpty()) {
			return result.get(0);
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<String> getSupplierIds(SupplierEnum supplier) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select p.supplierId from SalespointSupplier p where p.supplier=?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, supplier);
		List<String> result = query.list();
		return result;
	}

	// -- Category Converter -- //
	@SuppressWarnings("unchecked")
	public Map<String, Integer> getCategoryIdWithSupplierId(CatalogSrcEnum supplier) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select p.id, src.sourceId from Category p inner join p.srcs src where src.source=?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, supplier);

		List<Object[]> mappingIds = query.list();
		Map<String, Integer> cache = new HashMap<String, Integer>(mappingIds.size());
		for (Object[] mappingId : mappingIds) {
			Integer categoryId = (Integer) mappingId[0];
			String supplierCategoryId = (String) mappingId[1];
			cache.put(supplierCategoryId, categoryId);
		}
		return cache;
	}

	@SuppressWarnings("unchecked")
	public Category getCategoryByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select p from Category p where p.name=?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, name);

		List<Category> result = query.list();
		Category entity = null;
		if (result != null && !result.isEmpty()) {
			return result.get(0);
		}
		return entity;
	}

	// -- Brand Converter -- //

	@SuppressWarnings("unchecked")
	public Map<String, Integer> getBrandIdWithSupplierId(CatalogSrcEnum supplier) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select p.id, src.sourceId from Brand p inner join p.srcs src where src.source=?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, supplier);

		List<Object[]> mappingIds = query.list();
		Map<String, Integer> cache = new HashMap<String, Integer>(mappingIds.size());
		for (Object[] mappingId : mappingIds) {
			Integer brandId = (Integer) mappingId[0];
			String techBrandId = (String) mappingId[1];
			cache.put(techBrandId, brandId);
		}
		return cache;
	}

	// -- ProductBrandAssosiater --//
	@SuppressWarnings("unchecked")
	public Map<Integer, Integer> getMapByProductIdAndBrandId() {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select p.id, p.brand.id from Product p";
		Query query = session.createQuery(queryString);

		List<Object[]> mappingIds = query.list();
		Map<Integer, Integer> cache = new HashMap<Integer, Integer>(mappingIds.size());
		for (Object[] mappingId : mappingIds) {
			Integer productId = (Integer) mappingId[0];
			Integer brandId = (Integer) mappingId[1];
			cache.put(productId, brandId);
		}
		return cache;
	}

	@SuppressWarnings("unchecked")
	public Brand getBrandByProductId(Integer productId) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select p.brand from Product p where p.id=?";
		Query query = session.createQuery(queryString);
		query.setInteger(0, productId);

		List<Brand> result = query.list();
		Brand entity = null;
		if (result.size() == 1) {
			entity = result.get(0);
		}
		return entity;
	}

	// -- ProductCategoryAssosiater --//

	@SuppressWarnings("unchecked")
	public Map<Integer, Integer> getMapByProductIdAndCategoryId() {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select p.id, p.category.id from Product p";
		Query query = session.createQuery(queryString);

		List<Object[]> mappingIds = query.list();
		Map<Integer, Integer> cache = new HashMap<Integer, Integer>(mappingIds.size());
		for (Object[] mappingId : mappingIds) {
			Integer productId = (Integer) mappingId[0];
			Integer categoryId = (Integer) mappingId[1];
			cache.put(productId, categoryId);
		}
		return cache;
	}

	@SuppressWarnings("unchecked")
	public Category getCategoryByProductId(Integer productId) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select p.category from Product p where p.id=?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, productId);

		List<Category> result = query.list();
		Category entity = null;
		if (result.size() == 1) {
			entity = result.get(0);
		}
		return entity;
	}

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

}
