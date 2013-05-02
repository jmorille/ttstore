package eu.ttbox.batch.techdata.stock;

import java.util.Date;

import org.springframework.batch.item.file.transform.FieldSet;

import com.google.common.base.Joiner;

public enum StockFieldEnum {

	TechId, PartNumber, Manufacturer, Quantity, NextDeliveryDate;

	public String readString(FieldSet fs) {
		return fs.readString(this.name());
	}

	public Integer readInteger(FieldSet fs) {
		return fs.readInt(this.name());
	}

	public Date readDate(FieldSet fs) {
		return fs.readDate(this.name(), (Date)null);
	}

	public static String FIELDS_NAMES = Joiner.on(',').join(values());
	public static String FIELDS_NAMES_ADD_ALL = FIELDS_NAMES + ",ALL";

	public static String FIELDS_IDS = TechId.name();
}
