package eu.ttbox.feed.core.download.filters.local;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FtpAcceptOneLocalFileNameListFilter // implements
													// FileListFilter<FTPFile>
{

	private static final Logger log = LoggerFactory.getLogger(FtpAcceptOneLocalFileNameListFilter.class);

	private Pattern pattern;

	// private File rootFoder;

	// public FtpAcceptOneLocalFileNameListFilter(File rootFoder, String
	// pattern) {
	// this( rootFoder, Pattern.compile(pattern));
	// }

	public FtpAcceptOneLocalFileNameListFilter(Pattern pattern) {
		// this.rootFoder = rootFoder;
		this.pattern = pattern;
	}

	private Map<String, File> getLocalDestinationFiles(File folder) {
		FilenameFilter filter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				Matcher m = pattern.matcher(name);
				return m.matches();
			}
		};
		Map<String, File> localFiles = new HashMap<String, File>();
		if (folder != null && folder.isDirectory()) {
			File[] files = folder.listFiles(filter);
			// Convert to Map
			for (File file : files) {
				String key = getFileKey(file);
				localFiles.put(key, file);
			}
		}
		return localFiles;
	}

	private String getFileKey(File file) {
		String key = file.getName().toLowerCase();
		// Extract .gz extention
		int gzIndexOf = key.lastIndexOf(".gz");
		if (gzIndexOf > -1) {
			key = key.substring(0, gzIndexOf);
		}
		return key;
	}

	private String getFileKey(FTPFile file) {
		String key = file.getName().toLowerCase();
		return key;
	}

	// @Override
	public List<FTPFile> filterFiles(List<FTPFile> files, File rootFoder) {
		List<FTPFile> accepted = new ArrayList<FTPFile>();
		if (files != null) {
			Map<String, File> localFiles = getLocalDestinationFiles(rootFoder);
			for (FTPFile file : files) {
				String key = getFileKey(file);
				if (!localFiles.containsKey(key)) {
					accepted.add(file);
				} else {
					File localFile = localFiles.get(key);
					// log.debug("Duplicated Remote and Local File for key {}",
					// key);
					if (replaceLocalFile(localFile, file)) {
						log.info("Replace local File {} by remote File {}", localFile, file);
						accepted.add(file);
					}
				}
			}
		}
		return accepted;
	}

	public static boolean replaceLocalFile(File localFile, FTPFile file) {
		return localFile.lastModified() < file.getTimestamp().getTimeInMillis();
	}
}
