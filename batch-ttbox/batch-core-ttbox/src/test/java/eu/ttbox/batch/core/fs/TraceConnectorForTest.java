package eu.ttbox.batch.core.fs;

import java.io.File;
import java.io.IOException;

import eu.ttbox.batch.core.download.DownloaderConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TraceConnectorForTest implements DownloaderConnector {

    private static final Logger log = LoggerFactory.getLogger(TraceConnectorForTest.class);

	private boolean isAskedToDownload = false;

	@Override
	public File downloadFile(String relativePath, File destFile) throws IOException {
		isAskedToDownload = true;
        log.debug("downloadFile  {} to file {}", relativePath, destFile);
		return destFile;
	}

	public boolean isAskedToDownloadAndReset(String relativePath) {
		boolean result = isAskedToDownload;
        log.debug("isAskedToDownloadAndReset  {} => is Download {}", relativePath, result);
		isAskedToDownload = false;
		return result;
	}

}
