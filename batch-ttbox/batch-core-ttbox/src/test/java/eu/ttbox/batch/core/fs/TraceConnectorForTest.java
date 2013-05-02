package eu.ttbox.batch.core.fs;

import java.io.File;
import java.io.IOException;

import eu.ttbox.batch.core.download.DownloaderConnector;

public class TraceConnectorForTest implements DownloaderConnector {

	private boolean isAskedToDownload = false;

	@Override
	public File downloadFile(String relativePath, File destFile) throws IOException {
		isAskedToDownload = true;
		return destFile;
	}

	public boolean isAskedToDownloadAndReset() {
		boolean result = isAskedToDownload;
		isAskedToDownload = false;
		return result;
	}

}
