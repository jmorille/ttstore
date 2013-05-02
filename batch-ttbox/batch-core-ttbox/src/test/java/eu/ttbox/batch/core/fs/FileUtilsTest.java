package eu.ttbox.batch.core.fs;

import org.junit.Assert;
import org.junit.Test;

public class FileUtilsTest {

	@Test
	public void isGzipFileName() {
		Assert.assertTrue(FileUtils.isGzipFileName("toto.xml.gz"));
		Assert.assertTrue(FileUtils.isGzipFileName("toto.txt.gz"));
		Assert.assertFalse(FileUtils.isGzipFileName("toto.xml"));
	}

	@Test
	public void isTextFileName() {
		Assert.assertTrue(FileUtils.isTextFileName("toto.xml"));
		Assert.assertTrue(FileUtils.isTextFileName("toto.txt"));
		Assert.assertFalse(FileUtils.isTextFileName("toto.xml.gz"));
		Assert.assertFalse(FileUtils.isTextFileName("toto.txt.gz"));
		Assert.assertFalse(FileUtils.isTextFileName("toto.gif"));
	}

	@Test
	public void splitFileNameAndLongExtention() {
		
		String[] simple = FileUtils.splitFileNameAndLongExtention("toto.txt");
		Assert.assertEquals(2, simple.length);
		Assert.assertEquals("toto",simple[0]);
		Assert.assertEquals(".txt",simple[1]);

		
		String[] simpleGzip = FileUtils.splitFileNameAndLongExtention("toto.txt.gz");
		Assert.assertEquals(2, simpleGzip.length);
		Assert.assertEquals("toto",simpleGzip[0]);
		Assert.assertEquals(".txt.gz",simpleGzip[1]);

	}
	
	@Test
	public void getExtAliasFileName(){
		Assert.assertEquals("price.zip", FileUtils.getExtAliasFileName("price.txt", ".zip"));
		Assert.assertEquals("price.zip", FileUtils.getExtAliasFileName("price.txt", ".zip"));
	}
	
	@Test
	public void getFullPath() {
		String paths[] = new String[]{"/fusion/FR/gloha", "/fusion/FR/gloha/"};
		String filenames[] = new String[]{"/PRICE.ZIP", "PRICE.ZIP"};
		String expectedPath = "/fusion/FR/gloha/PRICE.ZIP";
		for (String path: paths){
			for (String filename : filenames) {
				String testPath = FileUtils.getFullPath(path, filename);
				Assert.assertEquals(expectedPath, testPath);
			}
		}
	}
}
