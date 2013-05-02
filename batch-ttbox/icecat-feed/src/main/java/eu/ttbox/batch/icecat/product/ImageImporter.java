package eu.ttbox.batch.icecat.product;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import biz.icecat.files.v1.IcecatFile;
import eu.ttbox.batch.core.fs.ProxyCacheDownloadConnector;

public class ImageImporter {

	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	ProxyCacheDownloadConnector icecatImageProxyCacheHttpConnector;

	public void doImport(IcecatFile ifile) throws IOException {
		String imgFile = ifile.getHighPic();
		if (StringUtils.isNotBlank(imgFile)) {
			String imgDomain = "http://images.icecat.biz/";
			String relatifImgPath = imgFile.substring(imgDomain.length(), imgFile.length());
			icecatImageProxyCacheHttpConnector.downloadFile(relatifImgPath);
		}
	}

}
