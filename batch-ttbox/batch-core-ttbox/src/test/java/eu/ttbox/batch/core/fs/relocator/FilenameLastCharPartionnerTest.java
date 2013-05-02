package eu.ttbox.batch.core.fs.relocator;

import java.io.File;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;

import eu.ttbox.batch.core.fs.partitionner.FilenameLastCharPartionner;

public class FilenameLastCharPartionnerTest {

	@Test
	public void testpartitionWithLongFileName() throws Exception {
		int[] expectedPart = new int[] { 26, 676 };
		int[] lastChars = new int[] { 1, 2 };
		String[] posFileChar = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
				"o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
		for (int idx = 0; idx < lastChars.length; idx++) {
			FilenameLastCharPartionner relocator = new FilenameLastCharPartionner();
			relocator.setNumLastCharPartition(lastChars[idx]);
			relocator.afterPropertiesSet();
			HashSet<String> parents = new HashSet<String>();
			for (String fileidx1 : posFileChar) {
				for (String fileidx2 : posFileChar) {
					for (String fileName : posFileChar) {
						File file = new File("/tmp/repo-test/blabla" + fileidx1 + fileidx2 + fileName + ".txt");
						File relocatorFile = relocator.relocate(file);
						parents.add(relocatorFile.getParent());
						// System.out.println("relocatorFile : " +
						// relocatorFile);
					}
				}
			}
			Assert.assertEquals(expectedPart[idx], parents.size());
		}
	}

	@Test
	public void testpartitionWithShortFileName() throws Exception {
		int[] expectedPart = new int[] { 26, 26 };
		int[] lastChars = new int[] { 1, 2 };
		String[] posFileChar = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
				"o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
		for (int idx = 0; idx < lastChars.length; idx++) {
			FilenameLastCharPartionner relocator = new FilenameLastCharPartionner();
			relocator.setNumLastCharPartition(lastChars[idx]);
			relocator.afterPropertiesSet();
			HashSet<String> parents = new HashSet<String>();
			for (String fileidx1 : posFileChar) {
				for (String fileName : posFileChar) {
					File file = new File("/tmp/repo-test/" + fileName + ".txt");
					File relocatorFile = relocator.relocate(file);
					parents.add(relocatorFile.getParent());
					// System.out.println("relocatorFile : " + relocatorFile);
				}
			}
			Assert.assertEquals(expectedPart[idx], parents.size());
		}
	}

}
