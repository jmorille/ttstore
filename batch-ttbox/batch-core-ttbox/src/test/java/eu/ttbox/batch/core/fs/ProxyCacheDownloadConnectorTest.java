package eu.ttbox.batch.core.fs;

import java.io.File;
import java.util.Date;

import com.google.common.io.Files;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProxyCacheDownloadConnectorTest {

    private static Logger log = LoggerFactory.getLogger(ProxyCacheDownloadConnectorTest.class);
	@Test
	public void testDownloadFileStringDate() throws Exception {
		// Prepare Service
		ProxyCacheDownloadConnector service = new ProxyCacheDownloadConnector();
        // working folder
        WorkingLocalFileSystemAccessor workingFolder = new WorkingLocalFileSystemAccessor();
        workingFolder.setCacheFolder(System.getProperty("java.io.tmpdir"));
        service.setWorkingLocalFolder(workingFolder);

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
        log.debug("getPotentialLocalFile for {} : {}", relaticePath, tmpLocalFile);
		tmpLocalFile.createNewFile();
		tmpLocalFile.deleteOnExit();
		try {
			DateTime localDate = new DateTime(2011, 06, 12, 14, 35, 25, 12);

			DateTime postDate = localDate.plusSeconds(1);
			DateTime preDate = localDate.minusSeconds(1);
			// Define local File
			tmpLocalFile.setLastModified(localDate.getMillis());
			// Download Always Without Date
			Assert.assertNotNull(service.downloadFile(relaticePath));
			Assert.assertTrue(tracerConnector.isAskedToDownloadAndReset(relaticePath));
            // Check existing file
            Assert.assertTrue(tmpLocalFile.exists());
            log.debug("Existing file {} : date {}", tmpLocalFile,new Date( tmpLocalFile.lastModified()));
            // Not Download if Pre Date
            Assert.assertTrue(preDate.isBefore(localDate));
            Assert.assertNotNull(service.downloadFile(relaticePath, preDate.toDate()));
            Assert.assertFalse(tracerConnector.isAskedToDownloadAndReset(relaticePath));
			// Not Download if Same Date
			Assert.assertNotNull(service.downloadFile(relaticePath, localDate.toDate()));
			Assert.assertFalse(tracerConnector.isAskedToDownloadAndReset(relaticePath));
			// Download if Post Date
			Assert.assertTrue(postDate.isAfter(localDate));
			Assert.assertNotNull(service.downloadFile(relaticePath, postDate.toDate()));
			Assert.assertTrue(tracerConnector.isAskedToDownloadAndReset(relaticePath));

		} finally {
			tmpLocalFile.delete();
		}

	}

}
