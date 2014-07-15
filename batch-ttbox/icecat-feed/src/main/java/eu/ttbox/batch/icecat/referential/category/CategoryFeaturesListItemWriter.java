package eu.ttbox.batch.icecat.referential.category;

import biz.icecat.referential.v1.Category;
import biz.icecat.referential.v1.CategoryFeatureGroup;
import biz.icecat.referential.v1.Feature;
import biz.icecat.referential.v1.Measure;
import eu.ttbox.batch.icecat.referential.AbstractReferentialItemWriter;
import eu.ttbox.batch.icecat.referential.dependency.IDependencyDifferential;
import eu.ttbox.icecat.model.referential.IcecatCategory;
import eu.ttbox.icecat.model.referential.IcecatCategoryFeature;
import eu.ttbox.icecat.model.referential.IcecatCategoryFeatureGroup;
import eu.ttbox.icecat.model.referential.IcecatMeasure;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("categoryFeaturesListIcecatItemWriter")
public class CategoryFeaturesListItemWriter extends AbstractReferentialItemWriter<Category> implements ItemWriter<Category> {

	@Autowired
	@Qualifier("icecatCategoryFeatureGroupDifferential")
	IDependencyDifferential<IcecatCategory, IcecatCategoryFeatureGroup, CategoryFeatureGroup> categoryFeatureGroupDifferential;

	@Autowired
	@Qualifier("icecatCategoryFeatureDifferential")
	IDependencyDifferential<IcecatCategory, IcecatCategoryFeature, Feature> categoryFeatureDifferential;

	/**
	 * TODO FINIR IMPORT FEATURE ET
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int doImport(Category category) {
		int importCount = 0;
		Integer categoryId = category.getID();
		IcecatCategory icecatCategory = (IcecatCategory) getIcecatDAO().getById(categoryId, IcecatCategory.class);

		//
		if (icecatCategory != null) {
			// ------------------------------
			// --- Category Feature Group ---
			// ------------------------------
			log.debug("Start Category Feature Group for {}", icecatCategory);
			List<IcecatCategoryFeatureGroup> refCatFeatGroups = icecatCategory.getCategoryFeatureGroups();
			if (refCatFeatGroups == null) {
				refCatFeatGroups = new ArrayList<IcecatCategoryFeatureGroup>();
				icecatCategory.setCategoryFeatureGroups(refCatFeatGroups);
			}
			this.categoryFeatureGroupDifferential.doImportDependencies(icecatCategory, refCatFeatGroups, category.getCategoryFeatureGroups());
			getIcecatDAO().flush();
			log.debug("End   Category Feature Group for {}", icecatCategory);

			// -------------------------
			// --- Category Feature ---
			// -------------------------
			// Category Feature
			log.debug("Start Category Feature for {}", icecatCategory);
			List<Feature> categoryFeatures = category.getFeatures();
			List<IcecatCategoryFeature> refCategoryFeatures = icecatCategory.getCategoryFeatures();
			this.categoryFeatureDifferential.doImportDependencies(icecatCategory, refCategoryFeatures, categoryFeatures);
			getIcecatDAO().flush();
			log.debug("End   Category Feature for {}", icecatCategory);

		}

		return importCount;
	}

	public IcecatMeasure updateIcecatMeasure(IcecatMeasure ori, Measure measure) {
		IcecatMeasure icecatMeasure = ori;
		if (measure != null) {
			Integer measureId = measure.getID();
			if (ori == null || !ori.getId().equals(measureId)) {
				icecatMeasure = (IcecatMeasure) getIcecatDAO().getById(measureId, IcecatMeasure.class);
				if (icecatMeasure == null) {
					log.warn("No measure for Id {}", measureId);
				}
			}
		} else {
			icecatMeasure = null;
		}
		return icecatMeasure;
	}
}
