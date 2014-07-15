package eu.ttbox.batch.icecat.product.detail;

import biz.icecat.files.v1.IcecatFile;
import eu.ttbox.batch.icecat.referential.AbstractReferentialItemWriter;

import java.io.IOException;
import java.util.Date;

public interface ProductImporter {

	public static Date ZERO_VERSION_DATE = new Date(0);

	public static Integer DEFAULT_USER_ID = AbstractReferentialItemWriter.DEFAULT_USER_ID;

	public void doImport(IcecatFile productFile) throws IOException;

	public int deleteProduct(Integer productId);

	public Date getFeedVersionDate(IcecatFile productFile);

}