package eu.ttbox.batch.techdata.kit;

import eu.ttbox.batch.core.reader.differential.DifferentialComparator;
import eu.ttbox.model.product.Product;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Service;

@Service("kitTechdataItemComparator")
public class KitItemComparator implements DifferentialComparator<Product, FieldSet> {

	private static String KEY_01_FIELD_NAME = KitFieldEnum.TechKitId.name();
	private static String KEY_02_FIELD_NAME = KitFieldEnum.TechProductId.name();

	private int compareString(Integer key1, Integer key2) {
		return key1.compareTo(key2);
	}

	@Override
	public int compareJoins(FieldSet j1, FieldSet j2) {
		Integer key01 = j1.readInt(KEY_01_FIELD_NAME);
		Integer key02 = j2.readInt(KEY_01_FIELD_NAME);
		int result = compareString(key01, key02);
		if (result == 0) {
			Integer key11 = j1.readInt(KEY_02_FIELD_NAME);
			Integer key12 = j2.readInt(KEY_02_FIELD_NAME);
			result = compareString(key11, key12);
		}
		return result;
	}

	@Override
	public int compareMasters(Product m1, Product m2) {
		Integer key01 = m1.getId();
		Integer key02 = m2.getId();
		int result = compareString(key01, key02);
		if (result == 0) {
			Integer key11 = m1.getId();
			Integer key12 = m2.getId();
			result = compareString(key11, key12);
		}
		return result;
	}

	@Override
	public int compareMasterJoin(Product master, FieldSet join) {
		Integer key01 = master.getId();
		Integer key02 = join.readInt(KEY_01_FIELD_NAME);
		int result = compareString(key01, key02);
		if (result == 0) {
			Integer key11 = master.getId();
			Integer key12 = join.readInt(KEY_02_FIELD_NAME);
			result = compareString(key11, key12);
		}
		return result;

	}

}
