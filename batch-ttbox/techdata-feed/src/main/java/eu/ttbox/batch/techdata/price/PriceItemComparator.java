package eu.ttbox.batch.techdata.price;

import eu.ttbox.batch.core.reader.differential.DifferentialComparator;
import eu.ttbox.model.supplier.SupplierPrice;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Service;

@Service("priceTechdataItemComparator")
public class PriceItemComparator implements DifferentialComparator<SupplierPrice, FieldSet> {

	private static String KEY_01_FIELD_NAME = PriceFieldEnum.ShopId.name();
	private static String KEY_02_FIELD_NAME = PriceFieldEnum.TechProductId.name();

	private int compareString(String key1, String key2) {
		return key1.compareTo(key2);
	}

	@Override
	public int compareJoins(FieldSet j1, FieldSet j2) {
		String key01 = j1.readString(KEY_01_FIELD_NAME);
		String key02 = j2.readString(KEY_01_FIELD_NAME);
		int result = compareString(key01, key02);
		if (result == 0) {
			String key11 = j1.readString(KEY_02_FIELD_NAME);
			String key12 = j2.readString(KEY_02_FIELD_NAME);
			result = compareString(key11, key12);
		}
		return result;
	}

	@Override
	public int compareMasters(SupplierPrice m1, SupplierPrice m2) {
		String key01 = m1.getSupplierId();
		String key02 = m2.getSupplierId();
		int result = compareString(key01, key02);
		if (result == 0) {
			String key11 = m1.getSupplierProductId();
			String key12 = m2.getSupplierProductId();
			result = compareString(key11, key12);
		}
		return result;
	}

	@Override
	public int compareMasterJoin(SupplierPrice master, FieldSet join) {
		String key01 = master.getSupplierId();
		String key02 = join.readString(KEY_01_FIELD_NAME);
		int result = compareString(key01, key02);
		if (result == 0) {
			String key11 = master.getSupplierProductId();
			String key12 = join.readString(KEY_02_FIELD_NAME);
			result = compareString(key11, key12);
		}
		return result;

	}

}
