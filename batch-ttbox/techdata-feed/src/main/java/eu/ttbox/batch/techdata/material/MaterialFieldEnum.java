package eu.ttbox.batch.techdata.material;

import com.google.common.base.Joiner;
import org.springframework.batch.item.file.transform.FieldSet;

import java.math.BigDecimal;
import java.util.Date;

public enum MaterialFieldEnum {

	TechId,
	ShortDescription,
	LongDescription,
	ManufPartNr,
	Manufacturer,
	Kit,
	EAN,
	ProdFamilyID,
	ProdFamily,
	ProdClassID,
	ProdClass,
	ProdSubclassID,
	ProdSubclass,
	ArticleCreationDate,
	CNetAvailable,
	CnetId,
	Weight,
	ListPrice,
	Licence;

	public String readString(FieldSet fs) {
		return fs.readString(this.name());
	}

	public boolean readBoolean(FieldSet fs) {
		return fs.readBoolean(this.name(), "Y");
	}

	public Date readDate(FieldSet fs) {
		return fs.readDate(this.name());
	}

	public BigDecimal readBigDecimal(FieldSet fs) {
		return fs.readBigDecimal(this.name());
	}

	
	public static String FIELDS_NAMES =  Joiner.on(',').join(values());
	public static String FIELDS_NAMES_ADD_ALL =  FIELDS_NAMES+",ALL";
 
	public static String FIELDS_IDS =  TechId.name();
	 
}
