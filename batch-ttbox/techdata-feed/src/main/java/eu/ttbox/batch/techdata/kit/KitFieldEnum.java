package eu.ttbox.batch.techdata.kit;

import com.google.common.base.Joiner;
import org.springframework.batch.item.file.transform.FieldSet;

public enum KitFieldEnum {

	TechKitId, TechProductId, Order, Quantity;

	public String readString(FieldSet fs) {
		return fs.readString(this.name());
	}
	
 
	public static String FIELDS_NAMES =  Joiner.on(',').join(values());
	public static String FIELDS_NAMES_ADD_ALL =  FIELDS_NAMES+",ALL";
	public static String FIELDS_IDS =  Joiner.on(',').join(TechKitId,TechProductId);
}
