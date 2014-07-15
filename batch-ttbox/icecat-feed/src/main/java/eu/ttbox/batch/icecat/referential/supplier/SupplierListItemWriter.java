package eu.ttbox.batch.icecat.referential.supplier;

import biz.icecat.referential.v1.Supplier;
import eu.ttbox.batch.icecat.referential.AbstractReferentialItemWriter;
import eu.ttbox.icecat.model.referential.IcecatBrand;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("supplierListIcecatItemWriter")
public class SupplierListItemWriter extends AbstractReferentialItemWriter<Supplier> implements ItemWriter<Supplier> {

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int doImport(Supplier item) {
		int importCount = 0;
		Integer entityId = item.getID();
		IcecatBrand entity = (IcecatBrand) getIcecatDAO().getById(entityId, IcecatBrand.class);
		if (entity == null) {
			entity = new IcecatBrand();
			entity.setId(entityId);
			entity.setUserId(DEFAULT_USER_ID);
			importCount++;
		}
		// Update Value
		entity.setName(item.getName());
		entity.setLowPicture(item.getLogoPic());
		entity.setThumbPicture(item.getLogoPic());
		if (item.isSetSponsor()) {
			entity.setSponsor(item.isSponsor());
		} else {
			entity.setSponsor(false);
		}
		// Save
		getIcecatDAO().saveObject(entity);

		return importCount;
	}

}
