package eu.ttbox.batch.techdata.core.fs.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Joiner;

import eu.ttbox.batch.core.download.ftp.FtpFileDownloadRequest;
import eu.ttbox.batch.core.download.ftp.listfilter.FileListFilter;

public class FtpFileIdFileListFilter implements
		FileListFilter<FtpFileDownloadRequest> {

	private Pattern pattern;

	private int groupId = 1;

	private List<String> acceptFileIds;

	private List<String> excludeFileIds = new ArrayList<String>();
	
	public FtpFileIdFileListFilter(String patternString, int groupId,
			List<String> acceptFileIds) {
		this(Pattern.compile(patternString), groupId, acceptFileIds);
	}

	public FtpFileIdFileListFilter(Pattern pattern, int groupId,
			List<String> acceptFileIds) {
		this.pattern = pattern;
		this.groupId = groupId;
		this.acceptFileIds = acceptFileIds;
	}

	@Override
	public List<FtpFileDownloadRequest> filterFiles(
			List<FtpFileDownloadRequest> files) {
		List<FtpFileDownloadRequest> acceptFiles = new ArrayList<FtpFileDownloadRequest>();
		for (FtpFileDownloadRequest file : files) {
			if (accept(file.getFtpFile().getName())) {
				acceptFiles.add(file);
			}
		}
		return acceptFiles;
	}

	public boolean accept(String filename) {
		boolean accept = false;
		Matcher m = this.pattern.matcher(filename);
		if (m.matches()) {
			String groupValue = m.group(groupId);
			accept = true;
			if (acceptFileIds != null) {
				accept = acceptFileIds.contains(groupValue);
			}
			if (!accept) {
				excludeFileIds.add(groupValue);
			}
		}
		return accept;
	}

	public List<String> getExcludeFileIds() {
		return excludeFileIds;
	}

	@Override
	public String getEndToString() { 
		StringBuilder sb = new StringBuilder(20+ excludeFileIds.size()*10);
		sb.append("Ignore ").append(excludeFileIds.size()).append(" Files Ids  : [");
		sb.append( Joiner.on(", ").skipNulls().join(excludeFileIds));
		sb.append("]");
		return sb.toString();
	}
	
}
