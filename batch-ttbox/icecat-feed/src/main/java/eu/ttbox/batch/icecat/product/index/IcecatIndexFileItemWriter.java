package eu.ttbox.batch.icecat.product.index;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biz.icecat.files.v1.IcecatFile;
import eu.ttbox.batch.core.fs.ProxyCacheDownloadConnector;
import eu.ttbox.batch.icecat.product.detail.ProductImporter;
import eu.ttbox.batch.icecat.product.detail.model.ProductHelper;

@Service("icecatIndexFileItemWriter")
public class IcecatIndexFileItemWriter implements ItemWriter<IcecatFile> {

	private static Logger LOG = LoggerFactory.getLogger(IcecatIndexFileItemWriter.class);

	@Autowired
	ProductListItemImporter productListItemImporter;

	@Autowired
	ProxyCacheDownloadConnector icecatProxyCacheHttpConnector;

	@Autowired
	ProductImporter productImporter;

	@Autowired
	ProxyCacheDownloadConnector icecatImageProxyCacheHttpConnector;

	@Override
	public void write(List<? extends IcecatFile> items) throws Exception {
		int count = 0;
		for (IcecatFile ifile : items) {
			// Manage Update Date Version
			Date declaredUpdated = ProductHelper.getUpdatedDate(ifile);

			// File Data Information
			// SimpleDateFormat dateHttpDateFormat = new
			// SimpleDateFormat("EEE, dd MMM  HH:mm:ss z", Locale.US);
			// dateHttpDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
			// log.info("*** Declared Updated {}  --- Quality {} on market {}",
			// new Object[] { dateHttpDateFormat.format(declaredUpdated),
			// ifile.getQuality(), ifile.isOnMarket() });

			// TODO manage ifile.getUpdated()
			boolean downloadProductFile = true;

			// Manage File
			if ("REMOVED".equals(ifile.getQuality())) {
				downloadProductFile = false;
				// log.warn("TO DELETE file {}", ifile.getPath());
				icecatProxyCacheHttpConnector.getWorkingLocalFolder().deleteLocalFile(ifile.getPath());
			} else if ("NOEDITOR".equals(ifile.getQuality())) {
				// Ignore for products for which we have no data-sheet
				downloadProductFile = false;
			}
			if (ifile.isOnMarket()) {
				// downloadProductFile = false;
			}
			if (downloadProductFile) {

				// String imgFile = ifile.getHighPic();
				// if (StringUtils.isNotBlank(imgFile)) {
				// String imgDomain = "http://images.icecat.biz/";
				// String relatifImgPath = imgFile.substring(imgDomain.length(),
				// imgFile.length());
				// log.info("File with path {} and Model name {}",
				// ifile.getPath(), relatifImgPath);
				// icecatImageProxyCacheHttpConnector.downloadFile(relatifImgPath);
				// }

				// Do Import
				productListItemImporter.doImport(ifile);
				productListItemImporter.flush();
				// productListItemImporter.downloadProductFile(ifile.getPath(),
				// declaredUpdated);

				count++;
			} else {
				// TODO Delete
			}
			productListItemImporter.flushAndClear();
		}
		// log.info("File count {}", count);

	}

}
