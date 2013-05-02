package eu.ttbox.batch.core.fs.partitionner.voter;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import eu.ttbox.batch.core.voter.DecisionVoter;

public class FilenameContainsVoterTest {

	@Test
	public void test() {
		String containString = "repo-test";
		FilenameContainsVoter service = new FilenameContainsVoter(containString);
		Assert.assertEquals(DecisionVoter.ACCESS_GRANTED,
				service.vote(new File("/tmp/repo-test/bobo.txt")));
		Assert.assertEquals(DecisionVoter.ACCESS_ABSTAIN,
				service.vote(new File("/tmp/repo2-test/bobo.txt")));
		service = new FilenameContainsVoter(containString, true);
		Assert.assertEquals(DecisionVoter.ACCESS_DENIED,
				service.vote(new File("/tmp/repo-test/bobo.txt")));
		Assert.assertEquals(DecisionVoter.ACCESS_ABSTAIN,
				service.vote(new File("/tmp/repo2-test/bobo.txt")));
	}

}
