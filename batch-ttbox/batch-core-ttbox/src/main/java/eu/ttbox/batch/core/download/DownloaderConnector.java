package eu.ttbox.batch.core.download;

import java.io.File;
import java.io.IOException;

public interface DownloaderConnector {

	File downloadFile(String relativePath, File destFile) throws IOException;

}