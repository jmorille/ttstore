package eu.ttbox.batch.techdata.material;

import eu.ttbox.batch.core.reader.differential.DifferentialComparator;
import eu.ttbox.model.product.Product;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Service;

@Service("materialTechdataItemComparator")
public class MaterialItemComparator implements DifferentialComparator<Product, FieldSet>{

	private static String KEY_FIELD_NAME = MaterialFieldEnum.TechId.name();
	
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
	public int compareMasters(Product m1, Product m2) {
		String key1 = m1.getExtIdsNotNull().getTechId();
		String key2 = m2.getExtIdsNotNull().getTechId();
		return compareString(key1, key2);
	}

	@Override
	public int compareMasterJoin(Product master, FieldSet join) {
		String key1 = master.getExtIdsNotNull().getTechId();
		String key2 = join.readString(KEY_FIELD_NAME);
		return compareString(key1, key2);
	}

}
