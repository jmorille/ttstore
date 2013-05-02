package eu.ttbox.batch.icecat.product.detail.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import biz.icecat.files.v1.IcecatFile;

public class ProductHelper {

	private static Logger log = LoggerFactory.getLogger(ProductHelper.class);

	public static Date getUpdatedDate(IcecatFile productFile) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
		sf.setTimeZone(TimeZone.getTimeZone("GMT"));
		Date declaredUpdated = null;
		String dateToParse = productFile.getUpdated();
		try {
			declaredUpdated = sf.parse(dateToParse);
		} catch (ParseException pe) {
			log.error("Error parsing IcecatFile update Date " + dateToParse + " : ", pe);
		}
		return declaredUpdated;
	}

}
