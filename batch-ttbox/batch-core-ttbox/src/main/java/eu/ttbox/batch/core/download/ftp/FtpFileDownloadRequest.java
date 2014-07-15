package eu.ttbox.batch.core.download.ftp;

import org.apache.commons.net.ftp.FTPFile;

import java.io.File;

public class FtpFileDownloadRequest {

	private String ftpFilePath;
	
	private FTPFile ftpFile;
	
	private File localFile;

	public FtpFileDownloadRequest(String ftpFilePath, FTPFile ftpFile, File localFile) {
		super();
		this.ftpFilePath = ftpFilePath;
		this.ftpFile = ftpFile;
		this.localFile = localFile;
	}

	public FTPFile getFtpFile() {
		return ftpFile;
	}

	public File getLocalFile() {
		return localFile;
	}

	public String getFtpFilePath() {
		return ftpFilePath;
	}

 
	
	
}
