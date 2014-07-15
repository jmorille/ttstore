package eu.ttbox.batch.core.fs.partitionner.voter;

import eu.ttbox.batch.core.voter.DecisionVoter;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilenamePatternVoter implements DecisionVoter<File> {

	Pattern pattern;

	boolean rejectIfMatche = false;

	public FilenamePatternVoter() {
		super();
	}

	public FilenamePatternVoter(String patternString ) {
		this(Pattern.compile(patternString) ); 
	}

	public FilenamePatternVoter(Pattern pattern ) {
		this(pattern, false); 
	}
	
	public FilenamePatternVoter(Pattern pattern, boolean rejectIfMatche) {
		super();
		this.pattern = pattern;
		this.rejectIfMatche = rejectIfMatche;
	}

	public void setRejectIfMatche(boolean rejectIfMatche) {
		this.rejectIfMatche = rejectIfMatche;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}

 

	@Override
	public int vote(File subject) {
		String filename = subject.getAbsolutePath();
		if (pattern != null) {
			Matcher m = pattern.matcher(filename);
			if (m.matches()) {
				if (rejectIfMatche) {
					return DecisionVoter.ACCESS_DENIED;
				} else {
					return DecisionVoter.ACCESS_GRANTED;
				}
			}
		}
		return DecisionVoter.ACCESS_ABSTAIN;
	}

 
}
