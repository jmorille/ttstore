package eu.ttbox.batch.techdata.dao;

import eu.ttbox.model.product.CatalogSrcEnum;
import eu.ttbox.model.product.Product;
import eu.ttbox.model.product.brand.Brand;
import eu.ttbox.model.product.category.Category;
import eu.ttbox.model.supplier.SupplierEnum;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("ttboxDAO")
// @Transactional
public class TTBoxDAO extends HibernateDaoSupport {

	@Autowired
	public TTBoxDAO(@Qualifier("ttboxSessionFactory") SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	public void flushAndClear() {
		getHibernateTemplate().flush();
		getHibernateTemplate().clear();
	}

	public void flush() {
		getHibernateTemplate().flush();
	}

	public <T> T get(Class<T> entityClass, Serializable id) {
		return getHibernateTemplate().get(entityClass, id);
	}

	public <T> T load(Class<T> entityClass, Serializable id) {
		return getHibernateTemplate().load(entityClass, id);
	}
	
	public void deleteAll(@SuppressWarnings("rawtypes") Collection entities) {
		getHibernateTemplate().deleteAll(entities);
	}

	public void saveOrUpdate(Object entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	public void saveOrUpdateAll(@SuppressWarnings("rawtypes") Collection entities) {
        for (Object entity : entities) {
            getHibernateTemplate().saveOrUpdate(entity);
        }
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getProductIdWithTechId() {
        HibernateCallback<List<Object[]>> callback = new HibernateCallback<List<Object[]>>() {
            @Override
            public List<Object[]> doInHibernate(Session session) throws HibernateException, SQLException {
                org.hibernate.Query query = session.createQuery("select p.id, p.extIds.techId from Product p where p.extIds.techId is not null");
                return (List<Object[]>)query.list();
            }
        };
		List<Object[]> result = getHibernateTemplate().execute(callback);
		return result;
	}

	public Product getProductByTechId(String techProductId) { 
		List<Product> result = (List<Product>)getHibernateTemplate().findByNamedQuery("product.findByTechdataProductId", techProductId);
		Product entity = null;
		if (result != null && !result.isEmpty()) {
			return result.get(0);
		}
		return entity;
	}





	
	public Category getCategoryByTechId(String techId) {
		final String paramTechId = techId;
		@SuppressWarnings("unchecked")
		List<Category> result = (List<Category>)getHibernateTemplate().find("select p from Category p inner join p.srcs src where src.source=? and src.sourceId=?", CatalogSrcEnum.TECHDATA, paramTechId);
		Category entity = null;
		if (result != null && !result.isEmpty()) {
			return result.get(0);
		}
		return entity;
	}



	



	
	@SuppressWarnings("unchecked")
	public Brand getBrandByTechId(String techId) {
		final String paramTechId = techId;
		List<Brand> result = (List<Brand>)getHibernateTemplate().find ("select p from Brand p inner join p.srcs src where src.source=? and src.sourceId=?", CatalogSrcEnum.TECHDATA, paramTechId);
 		Brand entity = null;
		if (result != null && !result.isEmpty()) {
			return result.get(0);
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<String> getSupplierIds(SupplierEnum techdata) {
		List<String> result = (List<String>)getHibernateTemplate().find("select p.supplierId from SalespointSupplier p where p.supplier=?", techdata);
		return result;
	}

	// -- Category Converter -- //
	@SuppressWarnings("unchecked")
	public Map<String, Integer> getCategoryIdWithTechId() { 
		List<Object[]> mappingIds = (List<Object[]> )getHibernateTemplate().find ("select p.id, src.sourceId from Category p inner join p.srcs src where src.source=?", CatalogSrcEnum.TECHDATA  );
		Map<String, Integer> cache = new HashMap<String, Integer>(mappingIds.size());
		for (Object[] mappingId : mappingIds) {
			Integer categoryId = (Integer)mappingId[0];
			String techCategoryId = (String)mappingId[1];
			cache.put(techCategoryId, categoryId);
		}
		return cache; 
	}
	
	@SuppressWarnings("unchecked")
	public Category getCategoryByName(String name) {
		final String paramName = name;
		List<Category> result = (	List<Category>)getHibernateTemplate().find("select p from Category p where p.name=?", paramName);
		Category entity = null;
		if (result != null && !result.isEmpty()) {
			return result.get(0);
		}
		return entity;
	}

	
	// -- Brand Converter -- //

	@SuppressWarnings("unchecked")
	public Map<String, Integer> getBrandIdWithTechId() {
		List<Object[]> mappingIds = (List<Object[]>)getHibernateTemplate().find ("select p.id, src.sourceId from Brand p inner join p.srcs src where src.source=?", CatalogSrcEnum.TECHDATA  );
		Map<String, Integer> cache = new HashMap<String, Integer>(mappingIds.size());
		for (Object[] mappingId : mappingIds) {
			Integer brandId = (Integer)mappingId[0];
			String techBrandId = (String)mappingId[1];
			cache.put(techBrandId, brandId);
		}
		return cache;
	}
	
	
	// -- ProductBrandAssosiater  --//
	@SuppressWarnings("unchecked")
	public Map<Integer, Integer> getMapByProductIdAndBrandId() {
		List<Object[]> mappingIds = (List<Object[]>)getHibernateTemplate().find ("select p.id, p.brand.id from Product p");
		Map<Integer, Integer> cache = new HashMap<Integer, Integer>(mappingIds.size());
		for (Object[] mappingId : mappingIds) {
			Integer productId = (Integer)mappingId[0];
			Integer brandId = (Integer)mappingId[1];
			cache.put(productId, brandId );
		}
		return cache;
	}
	
	@SuppressWarnings("unchecked")
	public Brand getBrandByProductId(Integer productId) {
		List<Brand> result = (List<Brand>)getHibernateTemplate().find ("select p.brand from Product p where p.id=?", productId);
		Brand entity = null;
		if (result.size()==1) {
			entity = result.get(0);
		}
		return entity;
	}

	// -- ProductCategoryAssosiater  --//
	
	@SuppressWarnings("unchecked")
	public Map<Integer, Integer> getMapByProductIdAndCategoryId() {
		List<Object[]> mappingIds = (List<Object[]>)getHibernateTemplate().find ("select p.id, p.category.id from Product p");
		Map<Integer, Integer> cache = new HashMap<Integer, Integer>(mappingIds.size());
		for (Object[] mappingId : mappingIds) {
			Integer productId = (Integer)mappingId[0];
			Integer categoryId = (Integer)mappingId[1];
			cache.put(productId, categoryId );
		}
		return cache;
	}
	
	@SuppressWarnings("unchecked")
	public Category getCategoryByProductId(Integer productId) {
		List<Category>  result = (List<Category> )getHibernateTemplate().find ("select p.category from Product p where p.id=?", productId);
		Category entity = null;
		if (result.size()==1) {
			entity = result.get(0);
		}
		return entity;
	}
	
}
