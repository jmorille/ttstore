package eu.ttbox.batch.techdata.tax;

import com.google.common.base.Joiner;
import org.springframework.batch.item.file.transform.FieldSet;

import java.math.BigDecimal;

public enum TaxFieldEnum {

	TechId,PartNumber,TaxType,TaxValue;
	
	public String readString(FieldSet fs) {
		return fs.readString(this.name());
	}
	
	public BigDecimal readBigDecimal(FieldSet fs) {
		return fs.readBigDecimal(this.name());
	}
	
	public static String FIELDS_NAMES =  Joiner.on(',').join(values());
	public static String FIELDS_NAMES_ADD_ALL =  FIELDS_NAMES+",ALL";
 
	public static String FIELDS_IDS =  TechId.name();

}
