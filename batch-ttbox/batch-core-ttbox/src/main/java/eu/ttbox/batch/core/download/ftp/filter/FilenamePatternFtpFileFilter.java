package eu.ttbox.batch.core.download.ftp.filter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;

public class FilenamePatternFtpFileFilter implements FTPFileFilter {

	Pattern pattern;

	public FilenamePatternFtpFileFilter(Pattern pattern) {
		super();
		this.pattern = pattern;
	}

	public FilenamePatternFtpFileFilter(String patternString) {
		super();
		this.pattern = Pattern.compile(patternString);
	}

	@Override
	public boolean accept(FTPFile file) {
		boolean addFile = true;
		if (pattern != null) {
			Matcher m = pattern.matcher(file.getName());
			if (addFile) {
				addFile = m.matches();
			}
			addFile = m.matches();
		}
		return addFile;
	}

 
}
