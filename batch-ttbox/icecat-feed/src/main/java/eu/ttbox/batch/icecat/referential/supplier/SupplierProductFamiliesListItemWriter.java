package eu.ttbox.batch.icecat.referential.supplier;

import biz.icecat.referential.v1.ParentProductFamily;
import biz.icecat.referential.v1.ProductFamily;
import biz.icecat.referential.v1.Supplier;
import eu.ttbox.batch.icecat.referential.AbstractReferentialItemWriter;
import eu.ttbox.icecat.model.product.IcecatProductLine;
import eu.ttbox.icecat.model.referential.IcecatBrand;
import eu.ttbox.icecat.model.referential.IcecatCategory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("supplierProductFamiliesListIcecatItemWriter")
public class SupplierProductFamiliesListItemWriter extends AbstractReferentialItemWriter<ProductFamily> implements ItemWriter<ProductFamily> {

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int doImport(ProductFamily referential) {
		int importCount = 0;
		// boolean isUpdated = false;
		Integer sid = null;
		Integer tid = null;
		Integer entityId = referential.getID();
		IcecatProductLine entity = (IcecatProductLine) getIcecatDAO().getById(entityId, IcecatProductLine.class);
		if (entity == null) {
			sid = getIcecatDAO().getSidSequenceNextVal();
			tid = getIcecatDAO().getTidSequenceNextVal();
			entity = new IcecatProductLine();
			entity.setId(entityId);
			entity.setSid(sid);
			entity.setTid(tid);
			// TODO importParentCategory(referential.getParentCategory());
			// Updated
			importCount++;
		} else {
			sid = entity.getSid();
			tid = entity.getTid();
		}
		// Values
		entity.setLowDefPicture(referential.getLowPic());
		entity.setThumbPicture(referential.getThumbPic());

		// Category
		if (referential.getCategoryID() != null) {
			Integer categoryId = Integer.valueOf(referential.getCategoryID().intValue());
			boolean updateCategory = true;
			if (entity.getCategory() != null && entity.getCategory().getId().equals(categoryId)) {
				updateCategory = false;
			}
			if (updateCategory) {
				IcecatCategory icecatCategory = (IcecatCategory) getIcecatDAO().getById(categoryId, IcecatCategory.class);
				entity.setCategory(icecatCategory);
			}
		}
		// Brand
		Supplier supplier = referential.getSupplier();
		if (supplier != null) {
			Integer bandId = Integer.valueOf(supplier.getID());
			boolean updateBrand = true;
			if (entity.getBrand() != null && entity.getBrand().getId().equals(bandId)) {
				updateBrand = false;
			}
			if (updateBrand) {
				IcecatBrand icecatBrand = (IcecatBrand) getIcecatDAO().getById(bandId, IcecatBrand.class);
				entity.setBrand(icecatBrand);
			}
		}

		// Parent ProductFamily
		List<ParentProductFamily> parentFamilies = referential.getParentProductFamilies();
		for (ParentProductFamily parentFamily : parentFamilies) {
			Integer parentFamilyId = parentFamily.getID();
			IcecatProductLine parentEntity = (IcecatProductLine) getIcecatDAO().getById(parentFamilyId, IcecatProductLine.class);
			if (parentEntity == null) {
				// Create Parent Product Family
				Integer parentSid = getIcecatDAO().getSidSequenceNextVal();
				Integer parentTid = getIcecatDAO().getTidSequenceNextVal();
				parentEntity = new IcecatProductLine();
				parentEntity.setId(parentFamilyId);
				parentEntity.setSid(parentSid);
				parentEntity.setTid(parentTid);
				// Perstist parent
				getIcecatDAO().saveObject(parentEntity);
				getIcecatDAO().flush();
			}
			entity.setParentFamily(parentEntity);
		}

		// Save Entity
		getIcecatDAO().saveObject(entity);

		// Names
		this.icecatVocabularyDifferential.importVocabulary(sid, referential.getNames());

		// Description
		this.icecatTexDifferential.importDescription(tid, referential.getDescriptions());

		// Keywords
		// TODO importKeywords(entity, referential.getKeywords());

		// Save
		getIcecatDAO().saveObject(entity);
		return importCount;
	}

}
