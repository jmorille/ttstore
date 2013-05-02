package eu.ttbox.batch.core.fs.relocator;

import java.io.File;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;

import eu.ttbox.batch.core.fs.partitionner.NowDatePartitionner;

public class NowDatePartitionnerTest {

	@Test
	public void testRelocate() throws Exception {
		NowDatePartitionner relocator = new NowDatePartitionner();
		relocator.afterPropertiesSet();
		int numPart = 1;
		HashSet<String> parents = new HashSet<String>();
		for (String fileName : new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
				"o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" }) {
			File file = new File("/tmp/repo-test/" + fileName + ".txt");
			File relocatorFile = relocator.relocate(file);
			parents.add(relocatorFile.getParent());
			// System.out.println("relocatorFile : " + relocatorFile);
		}
		Assert.assertEquals(numPart, parents.size());
	}

}
