package eu.ttbox.batch.ingram.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.ttbox.batch.core.download.ftp.FtpConnector;
import eu.ttbox.batch.core.download.ftp.FtpFileDownloadRequest;
import eu.ttbox.batch.core.download.ftp.filter.FilenamePatternFtpFileFilter;
import eu.ttbox.batch.core.fs.WorkingLocalFileSystemAccessor;
import eu.ttbox.batch.core.fs.partitionner.AliasFilenamePartitionner;

public class IngramCacheDownloadConnector {

	private Logger log = LoggerFactory.getLogger(getClass());

	private FtpConnector ftpConnector;

	private AliasFilenamePartitionner aliasFilenamePartitionner;

	private WorkingLocalFileSystemAccessor workingLocalFolder;

	public void setAliasFilenamePartitionner(AliasFilenamePartitionner aliasFilenamePartitionner) {
		this.aliasFilenamePartitionner = aliasFilenamePartitionner;
	}

	public void setFtpConnector(FtpConnector ftpConnector) {
		this.ftpConnector = ftpConnector;
	}

	public void setWorkingLocalFolder(WorkingLocalFileSystemAccessor workingLocalFolder) {
		this.workingLocalFolder = workingLocalFolder;
	}

	public File downloadFile(String login, String password, String remoteDirectory, String filename) throws IOException {
		File file = null;
		// Get Ftp Connector
		FTPClient ftp = ftpConnector.createClient(login, password);
		try {
			String patternString = filename;
			FTPFileFilter filter = new FilenamePatternFtpFileFilter(patternString);
			// List of potential download files
			List<FTPFile> remoteList = ftpConnector.getRemoteList(ftp, remoteDirectory, filter);
			// Prepare Downlaod
			List<FtpFileDownloadRequest> ftpFileRequests = new ArrayList<FtpFileDownloadRequest>();
			for (FTPFile ftpFile : remoteList) {
				String localFilename = getFilename(remoteDirectory, ftpFile);
				File localFile = workingLocalFolder.getPotentialLocalFile(localFilename);
				if (localFile != null && localFile.exists()) {
					file = localFile;
				}
				// Test local File
				File testedLocalFile = localFile;
				if (aliasFilenamePartitionner != null) {
					testedLocalFile = aliasFilenamePartitionner.relocate(localFile);
				}
				boolean needToDownload = !testedLocalFile.exists();
				if (!needToDownload) {
					needToDownload = replaceLocalFile(testedLocalFile, ftpFile);
					if (needToDownload) {
						log.warn("Need to replace LocalFile {} by FtpFile {}", testedLocalFile, ftpFile);
					}
				}
				// Register to donwload it
				if (needToDownload) {
					FtpFileDownloadRequest request = new FtpFileDownloadRequest(remoteDirectory, ftpFile, localFile);
					ftpFileRequests.add(request);
					// Delete Alias
					if (!testedLocalFile.getName().equals(localFile.getName())) {
						boolean isTestedDeleted = testedLocalFile.delete();
						log.warn("Delete  {} LocalFile {} in favorof futur {}",
								new Object[] { isTestedDeleted, testedLocalFile.getName(), localFile.getName() });
					}
				}

			}
			// Do Download Files
			if (!ftpFileRequests.isEmpty()) {
				ftpConnector.downloadFiles(ftpFileRequests, ftp);
				file = ftpFileRequests.get(0).getLocalFile();
			}
		} finally {
			ftpConnector.releaseClient(ftp);
		}
		return file;
	}

	private String getFilename(String remoteDirectory, FTPFile ftpFile) {
		String filename = ftpFile.getName();
		int filenameSize = 0;
		if (ftpFile != null) {
			filename = ftpFile.getName();
			filename = filename.toLowerCase();
			filenameSize = filename.length();
		}
		if (remoteDirectory != null) {
			int start = 0;
			int end = remoteDirectory.length();
			String folder = remoteDirectory.replaceAll("/", "_");
			folder = folder.toLowerCase();
			if (folder.codePointAt(start) == '_') {
				start++;
			}
			if (folder.codePointAt(end - 1) == '_') {
				end--;
			}
			StringBuffer sb = new StringBuffer(end + filenameSize + 1);
			sb.append(folder, start, end);
			sb.append('_');
			if (filename != null) {
				sb.append(filename);
			}
			filename = sb.toString();
		}
		log.debug("associate FtpFile {} to local file {}", ftpFile, filename);
		return filename;
	}

	private boolean replaceLocalFile(File localFile, FTPFile ftpFile) {
		return (localFile.lastModified() < ftpFile.getTimestamp().getTimeInMillis());
	}

}
