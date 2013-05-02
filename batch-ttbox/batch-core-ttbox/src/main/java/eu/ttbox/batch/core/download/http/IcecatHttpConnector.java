package eu.ttbox.batch.core.download.http;

import java.io.File;
import java.io.IOException;

public interface IcecatHttpConnector {

	int doImport() throws InterruptedException, IOException;

 

	File importRefFile(String relativeFilePath) throws InterruptedException, IOException;

	File copyUrlToFile(String url, File destFile, String defaultEncoding) throws InterruptedException, IOException;

}