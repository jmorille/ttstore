package eu.ttbox.batch.techdata.price;

import com.google.common.base.Joiner;
import org.springframework.batch.item.file.transform.FieldSet;

import java.math.BigDecimal;
import java.util.Date;

public enum PriceFieldEnum {

	ShopId,TechProductId,PromoUntilString,SupplierPrice,Surcharge,Currency;
	
	public String readString(FieldSet fs) {
		return fs.readString(this.name());
	}
	
	public Date readDate(FieldSet fs) {
		return fs.readDate(this.name(), (Date)null);
	}

	
	public BigDecimal readBigDecimal(FieldSet fs) {
		return fs.readBigDecimal(this.name());
	}
	
	public static String FIELDS_NAMES =  Joiner.on(',').join(values());
	public static String FIELDS_NAMES_ADD_ALL =  FIELDS_NAMES+",ALL";
 
	public static String FIELDS_IDS =  Joiner.on(',').join(ShopId,TechProductId);

}
