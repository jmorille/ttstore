package eu.ttbox.feed.core.download.filters;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.file.filters.FileListFilter;
import org.springframework.util.Assert;

public class FtpFilePatternFilter implements FileListFilter<FTPFile> {

	private static final Logger log = LoggerFactory.getLogger(FtpFilePatternFilter.class);

	private Pattern pattern;

	private int groupId = 1;

	public FtpFilePatternFilter(Pattern pattern, int groupId) {
		this.pattern = pattern;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = Pattern.compile(pattern);
	}

	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.pattern, "'pattern' must not be null!");
	}

	//
	// @Override
	// public List<FTPFile> filterFiles(FTPFile[] files) {
	// List<FTPFile> accepted = new ArrayList<FTPFile>();
	// if (files != null) {
	// for (FTPFile file : files) {
	// Matcher m = pattern.matcher(file.getName());
	// if (m.matches()) {
	// String groupValue = m.group(groupId);
	// log.info("Group id {}", groupValue);
	// accepted.add(file);
	// }
	// }
	// }
	// return accepted;
	// }

	public List<FTPFile> filterFiles(FTPFile[] files) {
		List<FTPFile> accepted = new ArrayList<FTPFile>();
		if (files != null) {
			for (FTPFile file : files) {
				if (this.accept(file)) {
					accepted.add(file);
				}
			}
		}
		return accepted;
	}

	public boolean accept(FTPFile file) {
		boolean accept = false;
		if (file != null) {
			String filename = this.getFilename(file);
			Matcher m = this.pattern.matcher(filename);
			if (m.matches()) {
				String groupValue = m.group(groupId);
				log.debug("TODO Check Group id {} for filename {}", groupValue, filename);
				// TODO Check Ids
				accept = true;
			}
		}
		return accept;
	}

	protected String getFilename(FTPFile file) {
		return (file != null) ? file.getName() : null;
	}
}
