package eu.ttbox.batch.ingram.price.download;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.ttbox.batch.ingram.dao.IngramCacheDownloadConnector;
import eu.ttbox.model.salespoint.SalespointSupplier;

@Service("priceDownloadIngramItemProcessor")
public class PriceDownloadIngramItemProcessor implements ItemProcessor<SalespointSupplier, File> {

	private static Logger LOG = LoggerFactory.getLogger(PriceDownloadIngramItemProcessor.class);

	@Autowired
	IngramCacheDownloadConnector downloadConnector;

	String folderPattern = "/fusion/FR/%s/";

	String filename = "PRICE.ZIP";

	@Override
	public File process(SalespointSupplier item) throws Exception {
		if (item.getPassword() == null) {
			LOG.warn("No credential for {}", item);
			return null;
		}
		String login = item.getSupplierId();
		String password = item.getPassword();
		String remoteDirectory = String.format(folderPattern, login);
		File localFile = downloadConnector.downloadFile(login, password, remoteDirectory, filename);
		return localFile;
	}

}
