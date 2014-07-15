package eu.ttbox.batch.icecat.dao;

import eu.ttbox.icecat.model.product.*;
import eu.ttbox.icecat.model.referential.IcecatTex;
import eu.ttbox.icecat.model.referential.IcecatVocabulary;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public interface IcecatDAO {

	void saveObject(Object entity);

	void insertObject(Object entity);

	void updateObject(Object entity);

	boolean saveObjects(@SuppressWarnings("rawtypes") Collection entities);

	void deleteAll(Object entity);

	boolean deleteAll(@SuppressWarnings("rawtypes") Collection entities);

	int deleteProductDependenciesByProductId(Integer productId);

	public void flushAndClear();

	public void flush();

	void evict(Object entity);

	public Object getById(Integer entityId, Class entityClass);

	public Object loadById(Integer entityId, Class entityClass);

	public Integer getTidSequenceNextVal();

	public Integer getSidSequenceNextVal();

	public List<IcecatVocabulary> getIcecatVocabularyBySid(Integer sid);

	public List<IcecatTex> getDescriptionByTid(Integer tid);

	public List<Object[]> getAllProductIds();

	public Iterator<IcecatProductRelated> getIteratorAllProducts();

	public List<IcecatProductRelated> getRelatedByProduct(IcecatProduct product);

	public List<IcecatProductBundled> getBundledByProduct(IcecatProduct product);

	public List<IcecatProductDescription> getIcecatProductDescriptions(IcecatProduct product);

	public List<IcecatProductMultimediaObject> getIcecatProductMultimediaObjects(IcecatProduct iceProduct);

	public List<IcecatProductGallery> getIcecatProductGalleries(IcecatProduct iceProduct);

	public List<IcecatProductFeature> getIcecatProductFeatures(IcecatProduct product);

	public List<IcecatProductSummaryDescription> getIcecatProductSummaryDescriptions(IcecatProduct product);

}