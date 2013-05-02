package eu.ttbox.batch.techdata.ship;

import org.springframework.batch.item.file.transform.FieldSet;

import com.google.common.base.Joiner;

public enum ShipToRsFieldEnum {

	TechId,TechAddressId,Address;
	
	public String readString(FieldSet fs) {
		return fs.readString(this.name());
	}

	public static String FIELDS_NAMES =  Joiner.on(',').join(values());
	public static String FIELDS_NAMES_ADD_ALL =  FIELDS_NAMES+",ALL";
 
	public static String FIELDS_IDS =  TechId.name();
}
