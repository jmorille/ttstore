package eu.ttbox.batch.ingram.price;

import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Service;

import eu.ttbox.batch.core.reader.differential.DifferentialComparator;
import eu.ttbox.model.supplier.SupplierPrice;

@Service("priceIngramItemComparator")
public class PriceItemComparator implements DifferentialComparator<SupplierPrice, FieldSet> {

	private static String KEY_01_FIELD_NAME = PriceFieldEnum.Sku.name();

	private int compareString(String key1, String key2) {
		return key1.compareTo(key2);
	}

	@Override
	public int compareJoins(FieldSet j1, FieldSet j2) {
		String key01 = j1.readString(KEY_01_FIELD_NAME);
		String key02 = j2.readString(KEY_01_FIELD_NAME);
		int result = compareString(key01, key02);
		return result;
	}

	@Override
	public int compareMasters(SupplierPrice m1, SupplierPrice m2) {
		String key01 = m1.getSupplierProductId();
		String key02 = m2.getSupplierProductId();
		int result = compareString(key01, key02);
		return result;
	}

	@Override
	public int compareMasterJoin(SupplierPrice master, FieldSet join) {
		String key01 = master.getSupplierProductId();
		String key02 = join.readString(KEY_01_FIELD_NAME);
		int result = compareString(key01, key02);
		return result;

	}

}
