package eu.ttbox.batch.techdata.stock;

import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Service;

import eu.ttbox.batch.core.reader.differential.DifferentialComparator;
import eu.ttbox.model.supplier.SupplierStock;

@Service("stockTechdataItemComparator")
public class StockItemComparator implements DifferentialComparator<SupplierStock, FieldSet>{

	private static String KEY_FIELD_NAME = StockFieldEnum.TechId.name();
	
	private int compareString(String key1, String key2) {
		return key1.compareTo(key2);
	}
	
	@Override
	public int compareJoins(FieldSet j1, FieldSet j2) {
		String key1 = j1.readString(KEY_FIELD_NAME);
		String key2 = j2.readString(KEY_FIELD_NAME);
		return compareString(key1, key2);
	}

	@Override
	public int compareMasters(SupplierStock m1, SupplierStock m2) {
		String key1 = m1.getProduct().getExtIdsNotNull().getTechId();
		String key2 = m2.getProduct().getExtIdsNotNull().getTechId();
		return compareString(key1, key2);
	}

	@Override
	public int compareMasterJoin(SupplierStock master, FieldSet join) {
		String key1 = master.getProduct().getExtIdsNotNull().getTechId();
		String key2 = join.readString(KEY_FIELD_NAME);
		return compareString(key1, key2);
	}

}
