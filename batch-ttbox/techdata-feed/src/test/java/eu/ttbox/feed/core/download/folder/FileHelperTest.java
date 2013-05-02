package eu.ttbox.feed.core.download.folder;

import junit.framework.Assert;

import org.junit.Test;

public class FileHelperTest {

	@Test
	public void testGetFileNameExt() {
		String filename = "toto.txt.gz";
		String fext = FileHelper.getFileNameExt(filename);
		Assert.assertEquals(".txt.gz", fext);

	}
}
