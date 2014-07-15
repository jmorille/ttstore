package eu.ttbox.batch.core.fs.partitionner.voter;

import eu.ttbox.batch.core.voter.DecisionVoter;

import java.io.File;

public class FilenameContainsVoter implements DecisionVoter<File> {

	private String contains;

	private boolean rejectIfMatche = false;

	public FilenameContainsVoter() {
		super(); 
	}
	public FilenameContainsVoter(String contains ) {
		this(contains, false); 
	}
	
	public FilenameContainsVoter(String contains, boolean rejectIfMatche) {
		super();
		this.contains = contains;
		this.rejectIfMatche = rejectIfMatche;
	}

	public void setRejectIfMatche(boolean rejectIfMatche) {
		this.rejectIfMatche = rejectIfMatche;
	}

	public void setContains(String contains) {
		this.contains = contains;
	}

	@Override
	public int vote(File subject) {
		String filename = subject.getAbsolutePath();
		if (filename.contains(contains)) {
			if (rejectIfMatche) {
				return DecisionVoter.ACCESS_DENIED;
			} else {
				return DecisionVoter.ACCESS_GRANTED;
			}
		}
		return DecisionVoter.ACCESS_ABSTAIN;
	}

}
