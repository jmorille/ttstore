package eu.ttbox.batch.core.fs.partitionner.voter;

import java.io.File;

import junit.framework.Assert;

import org.junit.Test;

import eu.ttbox.batch.core.voter.DecisionVoter;

public class FilenamePatternVoterTest {

	@Test
	public void testVote() {
//		String pattern = "export(\\|/)freexml.int(\\|/)INT(\\|/)[0-9]\\.xml";
		String pattern = ".*export(\\\\|/)freexml\\.int(\\\\|/)INT(\\\\|/)([0-9]+)\\.xml.*";
		FilenamePatternVoter service = new FilenamePatternVoter(pattern);
		
		File file = new File("C:\\Users\\username\\repository-ttstore\\icecat\\export\\freexml.int\\INT\\1493.xml.gz");
		Assert.assertEquals(DecisionVoter.ACCESS_GRANTED, service.vote(file));

		File file2 = new File("/home/username/repository-ttstore/icecat/export/freexml.int/INT/1493.xml.gz");
		Assert.assertEquals(DecisionVoter.ACCESS_GRANTED, service.vote(file2));
}

}
