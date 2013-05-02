package eu.ttbox.batch.icecat.dao;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import eu.ttbox.batch.icecat.dao.sequence.SequenceDAO;
import eu.ttbox.icecat.model.product.IcecatProduct;
import eu.ttbox.icecat.model.product.IcecatProductBundled;
import eu.ttbox.icecat.model.product.IcecatProductDescription;
import eu.ttbox.icecat.model.product.IcecatProductFeature;
import eu.ttbox.icecat.model.product.IcecatProductGallery;
import eu.ttbox.icecat.model.product.IcecatProductMultimediaObject;
import eu.ttbox.icecat.model.product.IcecatProductRelated;
import eu.ttbox.icecat.model.product.IcecatProductSummaryDescription;
import eu.ttbox.icecat.model.referential.IcecatTex;
import eu.ttbox.icecat.model.referential.IcecatVocabulary;

@Repository("icecatDAO")
// @Transactional(propagation = Propagation.SUPPORTS)
public class IcecatDAOImpl implements IcecatDAO {

	private static final Logger LOG = LoggerFactory.getLogger(IcecatDAOImpl.class);

	@Autowired
	@Qualifier("icecatSessionFactory")
	SessionFactory sessionFactory;

	@Autowired
	@Qualifier("autoSelectSequenceDAO")
	SequenceDAO sequenceDAO;

	public IcecatDAOImpl() {
		super();
	}

	public void flushAndClear() {
		LOG.debug("flushAndClear");
		Session session = getCurrentSession();
		session.flush();
		session.clear();
	}

	public void evict(Object entity) {
		getCurrentSession().evict(entity);
	}

	public void flush() {
		// LOG.warn("flush");
		getCurrentSession().flush();
	}

	// @Transactional(propagation = Propagation.SUPPORTS)
	public void insertObject(Object entity) {
		getCurrentSession().save(entity);
	}

	// @Transactional(propagation = Propagation.SUPPORTS)
	public void updateObject(Object entity) {
		getCurrentSession().save(entity);
	}

	// @Transactional(propagation = Propagation.SUPPORTS)
	public void saveObject(Object entity) {
		getCurrentSession().saveOrUpdate(entity);
	}


//	@Transactional(propagation = Propagation.SUPPORTS)
	public boolean saveObjects(	 @SuppressWarnings("rawtypes") final Collection entities) {
		Session session = getCurrentSession();
		boolean isSave = false;
		for (Object entity : entities) {
			if (entity!=null) {
			session.saveOrUpdate(entity);
			isSave = true;
			}
		} 
		return isSave;
	}
 
//	@Transactional(propagation = Propagation.SUPPORTS)
	public boolean deleteAll(@SuppressWarnings("rawtypes") Collection entities) { 
		boolean isDelete = false;
		Session session = getCurrentSession();
		for (Object entity : entities) {
			if (entity!=null) {
				session.delete(entity);
				isDelete = true;
			}
		}
		return isDelete;
	}

	// @Transactional(propagation = Propagation.SUPPORTS)
	public void deleteAll(Object entity) {
		getCurrentSession().delete(entity);
	}

	// @Transactional(propagation = Propagation.SUPPORTS)
	private int deleteProductBundledByProductId(Integer productId) {
		Session session = getCurrentSession();
		String queryDelete = "delete from IcecatProductBundled where (product.id=? or productBundled.id=?)";
		Query query = session.createQuery(queryDelete);
		query.setInteger(0, productId);
		query.setInteger(1, productId);
		return query.executeUpdate();
	}

	// @Transactional(propagation = Propagation.SUPPORTS)
	private int deleteProductRelatedByProductId(Integer productId) {
		Session session = getCurrentSession();
		String queryDelete = "delete from IcecatProductRelated where (product.id=? or productRelated.id=?)";
		Query query = session.createQuery(queryDelete);
		query.setInteger(0, productId);
		query.setInteger(1, productId);
		return query.executeUpdate();
	}

	// @Transactional(propagation = Propagation.SUPPORTS)
	public int deleteProductDependenciesByProductId(Integer productId) {
		int count = 0;
		// Delete External Product Link
		count += deleteProductBundledByProductId(productId);
		count += deleteProductRelatedByProductId(productId);
		// Delete Internal Product Dependencies
		String queryDeleteTempalte = "delete from {0} where product.id=?";
		MessageFormat queryFormat = new MessageFormat(queryDeleteTempalte);
		String[] tableNames = new String[] { "IcecatProductDescription", "IcecatProductFeature", "IcecatProductFeatureLocal", "IcecatProductGallery",
				"IcecatProductMultimediaObject", "IcecatProductSummaryDescription" };
		Session session = getCurrentSession();
		for (String table : tableNames) {
			String queryDelete = queryFormat.format(new Object[] { table });
			Query query = session.createQuery(queryDelete);
			query.setInteger(0, productId);
			count += query.executeUpdate();
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	public Object getById(Integer id, Class entityClass) {
		return getCurrentSession().get(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public Object loadById(Integer id, Class entityClass) {
		return getCurrentSession().load(entityClass, id);
	}

	public Integer getSidSequenceNextVal() {
		return this.sequenceDAO.getSidSequenceNextVal();
	}

	public Integer getTidSequenceNextVal() {
		return this.sequenceDAO.getTidSequenceNextVal();
	}

	@SuppressWarnings("unchecked")
	// @Transactional(propagation = Propagation.SUPPORTS)
	public List<IcecatVocabulary> getIcecatVocabularyBySid(Integer sid) {
		String queryString = "from IcecatVocabulary voc where voc.sid=?";
		Query query = getCurrentSession().createQuery(queryString);
		query.setInteger(0, sid);
		return query.list();
		// return getCurrentSession().find(queryString, sid);
	}

	@SuppressWarnings("unchecked")
	// @Transactional(propagation = Propagation.SUPPORTS)
	public List<IcecatTex> getDescriptionByTid(Integer tid) {
		String queryString = "from IcecatTex where tid=?";
		Query query = getCurrentSession().createQuery(queryString);
		query.setInteger(0, tid);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	// @Transactional(propagation = Propagation.SUPPORTS)
	public List<Object[]> getAllProductIds() {
		String queryString = "select id, updated from IcecatProduct"; // order by id asc
		Query query = getCurrentSession().createQuery(queryString);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	// @Transactional(propagation = Propagation.SUPPORTS)
	public Iterator<IcecatProductRelated> getIteratorAllProducts() {
		String queryString = "from IcecatProduct order by id asc";
		Query query = getCurrentSession().createQuery(queryString);
		return query.iterate();
	}

	@SuppressWarnings("unchecked")
	// @Transactional(propagation = Propagation.SUPPORTS)
	public List<IcecatProductRelated> getRelatedByProduct(IcecatProduct product) {
		String queryString = "from IcecatProductRelated where product=?";
		Query query = getCurrentSession().createQuery(queryString);
		query.setEntity(0, product);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	// @Transactional(propagation = Propagation.SUPPORTS)
	public List<IcecatProductBundled> getBundledByProduct(IcecatProduct product) {
		String queryString = "from IcecatProductBundled where product=?";
		Query query = getCurrentSession().createQuery(queryString);
		query.setEntity(0, product);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<IcecatProductDescription> getIcecatProductDescriptions(IcecatProduct product) {
		String queryString = "from IcecatProductDescription where product=?";
		Query query = getCurrentSession().createQuery(queryString);
		query.setEntity(0, product);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<IcecatProductSummaryDescription> getIcecatProductSummaryDescriptions(IcecatProduct product) {
		String queryString = "from IcecatProductSummaryDescription where product=?";
		Query query = getCurrentSession().createQuery(queryString);
		query.setEntity(0, product);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<IcecatProductMultimediaObject> getIcecatProductMultimediaObjects(IcecatProduct product) {
		String queryString = "from IcecatProductMultimediaObject where product=?";
		Query query = getCurrentSession().createQuery(queryString);
		query.setEntity(0, product);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<IcecatProductGallery> getIcecatProductGalleries(IcecatProduct product) {
		String queryString = "from IcecatProductGallery where product=?";
		Query query = getCurrentSession().createQuery(queryString);
		query.setEntity(0, product);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<IcecatProductFeature> getIcecatProductFeatures(IcecatProduct product) {
		String queryString = "from IcecatProductFeature where product=?";
		Query query = getCurrentSession().createQuery(queryString);
		query.setEntity(0, product);
		return query.list();

	}

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
}
