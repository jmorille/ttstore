package eu.ttbox.batch.core.download.ftp;

import eu.ttbox.batch.core.download.DownloaderConnector;
import eu.ttbox.batch.core.fs.FileUtils;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.io.*;
import java.net.ConnectException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.zip.GZIPOutputStream;

public class FtpConnector implements DownloaderConnector, InitializingBean {

	private Logger log = LoggerFactory.getLogger(getClass());

	public static final String DEFAULT_ENCODING = "UTF-8";

	private static final String INCOMPLETE_EXTENSION = ".writing";

	private static final String BACKUP_EXTENSION = ".backup";

	int bufferSize = 8192 * 8;

	// Time Out For Getting a file
	private int ftpDataTimeOut = 6000 * 2;

	private boolean fileTypeBinary = false;

	private String hostname;
	private String username;
	private String password;

	@Override
	public void afterPropertiesSet() throws Exception {
		// System.getProperties().put( "ftp.proxHost", "ftpcache.generali.fr");
		// System.getProperties().put( "ftp.proxyPort", "3128");
		// System.getProperties().put( "socksProxyHost",
		// "ftpcache.generali.fr");
		// System.getProperties().put( "socksProxyPort", "3128");
	}

	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}

	public void setFtpDataTimeOut(int ftpDataTimeOut) {
		this.ftpDataTimeOut = ftpDataTimeOut;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFileTypeBinary(boolean fileTypeBinary) {
		this.fileTypeBinary = fileTypeBinary;
	}

	@Override
	public File downloadFile(String relativePath, File destFile) throws IOException {
		File resultFile = destFile;
		FTPClient client = createClient(this.username, this.password);
		try {

		} finally {
			releaseClient(client);
		}
		return resultFile;
	}

	public List<FTPFile> getRemoteList(FTPClient client, String remoteDirectory) throws IOException {
		return getRemoteList(client, remoteDirectory, FTPFileFilters.ALL);
	}

	public List<FTPFile> getRemoteList(FTPClient client, String remoteDirectory, FTPFileFilter filter) throws IOException {
		FTPListParseEngine engine = client.initiateListParsing(remoteDirectory);
		List<FTPFile> listFiles = new ArrayList<FTPFile>();
		while (engine.hasNext()) {
			FTPFile[] files = engine.getNext(25);
			for (FTPFile file : files) {
				if (filter.accept(file)) {
					listFiles.add(file);
				}
			}
		}
		return listFiles;
	}

	public void downloadFiles(Collection<FtpFileDownloadRequest> ftpFileRequests) throws IOException {
		FTPClient client = createClient(this.username, this.password);
		try {
			downloadFiles(ftpFileRequests, client);
		} finally {
			releaseClient(client);
		}

	}

	public void downloadFiles(Collection<FtpFileDownloadRequest> ftpFileRequests, FTPClient client) throws IOException {
		int fileSize = ftpFileRequests.size();
		log.info("Need to download {} files", fileSize);
		int fileId = 0;
		for (FtpFileDownloadRequest ftpFileRequest : ftpFileRequests) {
			fileId++;
			File localFile = ftpFileRequest.getLocalFile();
			long beginCopy = System.currentTimeMillis();
			copyFileToLocalDirectory(ftpFileRequest.getFtpFilePath(), ftpFileRequest.getFtpFile(), localFile, client);
			if (log.isInfoEnabled()) {
				long endCopy = System.currentTimeMillis();
				log.info("Copy file {}/{} to Destination File {} in {} ms  (size {} bytes)", new Object[] { fileId, fileSize, localFile, (endCopy - beginCopy),
						localFile.length() });
			}
		}

	}

	public void copyFileToLocalDirectory(FtpFileDownloadRequest ftpFileRequest, FTPClient ftpClient) throws IOException {
		copyFileToLocalDirectory(ftpFileRequest.getFtpFilePath(), ftpFileRequest.getFtpFile(), ftpFileRequest.getLocalFile(), ftpClient);
	}

	public void copyFileToLocalDirectory(String ftpFilePath, FTPFile ftpFile, File localFile, FTPClient ftpClient) throws IOException {
		log.debug("Starting to download FTP file {} into local file {}", ftpFile, localFile);
		String ftpFileName = ftpFile.getName();
		String ftpPath =  FileUtils.getFullPath(ftpFilePath, ftpFileName);
		// TODO Need to Test Existing file for replace It
		if (!localFile.exists()) {
			localFile.getParentFile().mkdirs();
		}

		long beginCopy = System.currentTimeMillis();
		File backupLocalFile = null;
		if (localFile.exists()) {
			backupLocalFile = new File(localFile.getParent(), localFile.getName() + BACKUP_EXTENSION);
			log.info("Replace Local File {} for most recent", localFile);
			if (!localFile.renameTo(backupLocalFile)) {
				log.error("Could not rename local file {} to backup file {}", localFile, backupLocalFile);
			}
		}
		File tempFile = new File(localFile.getParent(), localFile.getName() + INCOMPLETE_EXTENSION);
		tempFile.getParentFile().mkdirs();
		// Copy File
		boolean isDownloaded = false;
		try {
			OutputStream fileOutputStream = new BufferedOutputStream(new FileOutputStream(tempFile), bufferSize);
			// Add Gzip Support
			if (FileUtils.isGzipFile(localFile) && !FileUtils.isGzipFileName(ftpFileName)) {
				fileOutputStream = new GZIPOutputStream(fileOutputStream, bufferSize);
			}
			try {
				isDownloaded = ftpClient.retrieveFile(ftpPath, fileOutputStream);
				if (!isDownloaded) {
					log.warn("Could not download file ({}) : {}", ftpFile, ftpClient.getReplyString());
				}
			} finally {
				fileOutputStream.flush();
				fileOutputStream.close();
				fileOutputStream = null;
			}
			// Rename
			if (isDownloaded) {
				boolean isRenamed = FileUtils.renameFileTo(localFile, tempFile, ftpFile.getTimestamp());
				if (isRenamed) {
					if (backupLocalFile != null) {
						backupLocalFile.delete();
						backupLocalFile = null;
					}
					if (log.isDebugEnabled()) {
						long endCopy = System.currentTimeMillis();
						log.debug("Copy successfully to Destination File {} in {} ms.", localFile, (endCopy - beginCopy));
					}
				}
			}
		} finally {
			if (tempFile != null) {
				tempFile.delete();
			}
			if (backupLocalFile != null) {
				if (backupLocalFile.exists()) {
					backupLocalFile.renameTo(localFile);
				}
			}

		}
	}

	public FTPClient createClient() {
		return createClient(username, password);
	}

	/**
	 * @see http://www.java2s.com/Code/Java/Network-Protocol/UsetheFTPClient.htm
	 * @return
	 * @throws SocketException
	 * @throws IOException
	 */
	public FTPClient createClient(String username, String password) {
		log.debug("Begin create FTPClient");
		FTPClient ftp = new FTPClient();
		try {
			// Print to console
			if (log.isDebugEnabled()) {
				ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
			}
			// ftp.setSoTimeout(10000);
			// Connect
			log.debug("FTPClient connect : {}", hostname);
			ftp.connect(hostname);
			log.debug("FTPClient login : {}", username);
			ftp.login(username, password);
			// Check connection
			int reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				log.warn("Could not Connected to FTP {} : Reply code {}", hostname, ftp.getReplyString());
				releaseClient(ftp);
				return null;
			}
			log.info("Connected to FTP {} : Reply code {}", hostname, ftp.getReplyString());

			// Configure Connection
			ftp.enterLocalPassiveMode();
			ftp.setFileType(FTP.ASCII_FILE_TYPE);
			ftp.setDataTimeout(ftpDataTimeOut);
			ftp.setBufferSize(1024);
			if (fileTypeBinary) {
				ftp.setFileType(FTP.BINARY_FILE_TYPE);
				log.info("FTP transfert as {}", "BINARY_FILE_TYPE");
			}
			// Compression mode
			boolean compressMode = ftp.setFileTransferMode(FTPClient.COMPRESSED_TRANSFER_MODE);
			log.info("Transfert Compress mode {}", compressMode);

			// log.info("SystemType : {}", ftp.getSystemType());
			// ftp.features();
			// log.info("SystemType : {}", ftp.getReplyString());
		} catch (ConnectException ce) {
			// java.net.ConnectException: Connection timed out
			releaseClient(ftp);
//			log.error("Could not connect : " + ce.getMessage(), ce);
			throw new RuntimeException("Could not connect to hostname "+ hostname  + " : " + ce.getMessage(), ce);
		} catch (IOException e) {
			releaseClient(ftp);
//			log.error("Could not connect : " + e.getMessage(), e);
			throw new RuntimeException("Could not connect to hostname "+ hostname  + " : " + e.getMessage(), e );
		}
		log.debug("End  create FTPClient");

		return ftp;
	}

	public void releaseClient(FTPClient ftp) {
		if (ftp != null) {
			log.debug("release FTPClient");
			if (ftp.isConnected()) {
				// Logout from the FTP Server and disconnect
				try {
					ftp.logout();
				} catch (IOException e) {
					log.error("Could not logout FTP : " + e.getMessage(), e);
				}
				try {
					ftp.disconnect();
				} catch (IOException e) {
					log.error("Could not disconnect FTP : " + e.getMessage(), e);
				}

				log.info("Deconnected to FTP {} : {}", hostname, ftp.getReplyString());

			}
		}
	}

}
