package eu.ttbox.feed.core.download.folder;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class LocalFolderByDateProviderTest {

	@Before
	public void setUp() throws Exception {
	}

	private LocalFolderByDateProvider getService() throws IOException {
		LocalFolderByDateProvider service = new LocalFolderByDateProvider();
		File rootFolder = File.createTempFile("techdat-", "");
		rootFolder.delete();
		rootFolder.deleteOnExit();
		service.setRootFolder(rootFolder);
		return service;
	}

	@Test
	public void testGetLocalFolderDate() throws Exception {
		LocalFolderByDateProvider service = getService();
		service.setLocalfolderPattern("%1$tY-%1$tm-%1$te");
		File folder = service.getLocalFolder();
		String folderName = folder.getName();
		System.out.println("Local Folder : " + folderName);
	}

}
