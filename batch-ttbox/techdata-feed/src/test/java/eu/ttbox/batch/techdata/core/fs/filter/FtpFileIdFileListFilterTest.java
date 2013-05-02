package eu.ttbox.batch.techdata.core.fs.filter;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class FtpFileIdFileListFilterTest {

	@Test
	public void testAccept() {
		String patternString = "(?i)Price_Feed_([0-9]+)\\.txt(\\.gz)?";
		int groupId =1;
		List<String> acceptFileIds = new ArrayList<String>();
		acceptFileIds.add("480676");
		acceptFileIds.add("480727");
		acceptFileIds.add("481444");
		FtpFileIdFileListFilter filter = new FtpFileIdFileListFilter(patternString, groupId, acceptFileIds);
		Assert.assertTrue( filter.accept("price_feed_480676.txt.gz"));
		Assert.assertFalse( filter.accept("price_feed_980676.txt.gz"));
		Assert.assertFalse( filter.accept("price_feed_480676-bad.txt.gz"));
	}

}
