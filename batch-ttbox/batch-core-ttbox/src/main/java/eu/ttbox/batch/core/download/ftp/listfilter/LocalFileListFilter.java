package eu.ttbox.batch.core.download.ftp.listfilter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.ttbox.batch.core.download.ftp.FtpFileDownloadRequest;

public class LocalFileListFilter implements
		FileListFilter<FtpFileDownloadRequest> {
	private static Logger log = LoggerFactory.getLogger(LocalFileListFilter.class);

	@Override
	public List<FtpFileDownloadRequest> filterFiles(
			List<FtpFileDownloadRequest> files) {
		List<FtpFileDownloadRequest> acceptFiles = new ArrayList<FtpFileDownloadRequest>();
		for (FtpFileDownloadRequest file : files) {
			File localFile = file.getLocalFile();
			if (localFile.exists()) {
				if (replaceLocalFile(file.getLocalFile(), file.getFtpFile())) {
					acceptFiles.add(file);
				} else {
					log.debug("Ignore in favor of local file {}", localFile);
				}
			} else {
				acceptFiles.add(file);
			}
		}
		return acceptFiles;
	}

	private boolean replaceLocalFile(File localFile, FTPFile file) {
		return (localFile.lastModified() < file.getTimestamp()
				.getTimeInMillis());
	}
	
	@Override
	public String getEndToString() {
		return null;
	}

}
