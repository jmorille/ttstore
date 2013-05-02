package eu.ttbox.batch.core.fs;

import java.io.File;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

public class ProxyCacheDownloadConnectorTest {

	@Test
	public void testDownloadFileStringDate() throws Exception {
		// Prepare Service
		ProxyCacheDownloadConnector service = new ProxyCacheDownloadConnector();
		TraceConnectorForTest tracerConnector = new TraceConnectorForTest();
		service.setDownloaderConnector(tracerConnector);
		service.afterPropertiesSet();

		// Create Tmp Root Folder
		File tmpFolder = File.createTempFile("test-ProxyCacheHttpConnector-", "");
		tmpFolder.delete();
		tmpFolder.mkdirs();
		tmpFolder.deleteOnExit();
		// Create TmpFile For test
		String relaticePath = "testLocalFile.xml";
		File tmpLocalFile = service.getWorkingLocalFolder().getPotentialLocalFile(relaticePath);
		tmpLocalFile.createNewFile();
		tmpLocalFile.deleteOnExit();
		try {
			DateTime localDate = new DateTime(2011, 06, 12, 14, 35, 25, 12);
			DateTime postDate = localDate.plusHours(1);
			DateTime preDate = localDate.minus(1);
			// Define local File
			tmpLocalFile.setLastModified(localDate.getMillis());
			// Download Always Without Date
			Assert.assertNotNull(service.downloadFile(relaticePath));
			Assert.assertTrue(tracerConnector.isAskedToDownloadAndReset());
			// Not Download if Same Date
			Assert.assertNotNull(service.downloadFile(relaticePath, localDate.toDate()));
			Assert.assertFalse(tracerConnector.isAskedToDownloadAndReset());
			// Download if Post Date
			Assert.assertTrue(postDate.isAfter(localDate));
			Assert.assertNotNull(service.downloadFile(relaticePath, postDate.toDate()));
			Assert.assertTrue(tracerConnector.isAskedToDownloadAndReset());
			// Not Download if Pre Date
			Assert.assertTrue(preDate.isBefore(localDate));
			Assert.assertNotNull(service.downloadFile(relaticePath, preDate.toDate()));
			Assert.assertFalse(tracerConnector.isAskedToDownloadAndReset());

		} finally {
			tmpLocalFile.delete();
		}

	}

}
