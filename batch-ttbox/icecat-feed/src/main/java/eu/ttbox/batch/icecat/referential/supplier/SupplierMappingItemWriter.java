package eu.ttbox.batch.icecat.referential.supplier;

import biz.icecat.suppliermapping.v1.SupplierMapping;
import biz.icecat.suppliermapping.v1.Symbol;
import eu.ttbox.batch.icecat.referential.AbstractReferentialItemWriter;
import eu.ttbox.icecat.model.referential.IcecatBrand;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("supplierMappingIcecatItemWriter")
public class SupplierMappingItemWriter extends AbstractReferentialItemWriter<SupplierMapping> implements ItemWriter<SupplierMapping> {

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int doImport(SupplierMapping mapping) {
		int countCreate = 0;
		// int countDelete = 0;
		Integer entityId = mapping.getSupplierId();
		IcecatBrand entity = (IcecatBrand) getIcecatDAO().getById(entityId, IcecatBrand.class);
		if (entity != null) {
			List<String> currentAlias = entity.getAliases();
			List<String> feedAlias = new ArrayList<String>();
			if (mapping.getSymbols() != null && !mapping.getSymbols().isEmpty()) {
				// Create New
				for (Symbol symbol : mapping.getSymbols()) {
					String symbolString = symbol.getContent();
					feedAlias.add(symbolString);
					if (!currentAlias.contains(symbolString)) {
						currentAlias.add(symbolString);
						countCreate++;
					}
				}
			}
			// Delete Old
			List<String> toDelete = new ArrayList<String>();
			for (String refAlias : currentAlias) {
				if (!feedAlias.contains(refAlias)) {
					toDelete.add(refAlias);
				}
			}
			if (!toDelete.isEmpty()) {
				// countDelete = toDelete.size();
				currentAlias.removeAll(toDelete);
			}
		}
		return countCreate;
	}

}
