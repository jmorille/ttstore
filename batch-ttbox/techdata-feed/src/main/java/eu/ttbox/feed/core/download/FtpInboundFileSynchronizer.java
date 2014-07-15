package eu.ttbox.feed.core.download;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.integration.MessagingException;
import org.springframework.integration.file.filters.FileListFilter;
import org.springframework.integration.file.remote.session.Session;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.file.remote.synchronizer.InboundFileSynchronizer;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.io.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.zip.GZIPOutputStream;

public class FtpInboundFileSynchronizer implements InboundFileSynchronizer, InitializingBean {

	private String remoteFileSeparator = "/";
	/**
	 * Extension used when downloading files. We change it right after we know
	 * it's downloaded.
	 */
	static final String INCOMPLETE_EXTENSION = ".writing";

	protected final Log logger = LogFactory.getLog(this.getClass());

	/**
	 * the path on the remote mount as a String.
	 */
	private volatile String remoteDirectory;

	/**
	 * the {@link SessionFactory} for acquiring remote file Sessions.
	 */
	private final SessionFactory sessionFactory;

	/**
	 * An {@link FileListFilter} that runs against the
	 * <emphasis>remote</emphasis> file system view.
	 */
	private volatile FileListFilter<FTPFile> filter;

	/**
	 * Should we <emphasis>delete</emphasis> the remote <b>source</b> files
	 * after copying to the local directory? By default this is false.
	 */
	private boolean deleteRemoteFiles = false;

	/**
	 * Should we <emphasis>GZIP compress</emphasis> the local <b>destination</b>
	 * files during copying to the local directory? By default this is false.
	 */
	boolean gzipOutputCompressed = false;

	/**
	 * Create a synchronizer with the {@link SessionFactory} used to acquire
	 * {@link Session} instances.
	 */
	public FtpInboundFileSynchronizer(SessionFactory sessionFactory) {
		Assert.notNull(sessionFactory, "sessionFactory must not be null");
		this.sessionFactory = sessionFactory;
	}

	public void setRemoteFileSeparator(String remoteFileSeparator) {
		Assert.hasText(remoteFileSeparator, "'remoteFileSeparator' must not be empty");
		this.remoteFileSeparator = remoteFileSeparator;
	}

	/**
	 * Specify the full path to the remote directory.
	 */
	public void setRemoteDirectory(String remoteDirectory) {
		this.remoteDirectory = remoteDirectory;
	}

	public void setFilter(FileListFilter<FTPFile> filter) {
		this.filter = filter;
	}

	public void setDeleteRemoteFiles(boolean deleteRemoteFiles) {
		this.deleteRemoteFiles = deleteRemoteFiles;
	}

	public final void afterPropertiesSet() {
		Assert.notNull(this.remoteDirectory, "remoteDirectory must not be null");
	}

	protected final List<FTPFile> filterFiles(FTPFile[] files) {
		return (this.filter != null) ? this.filter.filterFiles(files) : Arrays.asList(files);
	}

	public void synchronizeToLocalDirectory(File localDirectory) {
		Session session = null;
		try {
			session = this.sessionFactory.getSession();
			Assert.state(session != null, "failed to acquire a Session");
			FTPFile[] files = session.<FTPFile> list(this.remoteDirectory);
			if (!ObjectUtils.isEmpty(files)) {
				Collection<FTPFile> filteredFiles = this.filterFiles(files);
				for (FTPFile file : filteredFiles) {
					if (file != null) {
						this.copyFileToLocalDirectory(this.remoteDirectory, file, localDirectory, session);
					}
				}
			}
		} catch (IOException e) {
			throw new MessagingException("Problem occurred while synchronizing remote to local directory", e);
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (Exception ignored) {
					if (logger.isDebugEnabled()) {
						logger.debug("failed to close Session", ignored);
					}
				}
			}
		}
	}

	private void copyFileToLocalDirectory(String remoteDirectoryPath, FTPFile remoteFile, File localDirectory,
			Session session) throws IOException {
		boolean needCompressed = false;
		String remoteFileName = this.getFilename(remoteFile);
		String remoteFilePath = remoteDirectoryPath + remoteFileSeparator + remoteFileName;
		if (!this.isFile(remoteFile)) {
			if (logger.isDebugEnabled()) {
				logger.debug("cannot copy, not a file: " + remoteFilePath);
			}
			return;
		}
		needCompressed = needCompressed ? !remoteFileName.endsWith(".gz") : false;
		//
		String localFileName = needCompressed ? remoteFileName.toLowerCase() + ".gz" : remoteFileName;
		File localFile = new File(localDirectory, localFileName);
		// TODO Need to Test Existing file for replace It
		if (!localFile.exists()) {
			String tempFileName = localFile.getAbsolutePath() + INCOMPLETE_EXTENSION;
			File tempFile = new File(tempFileName);
			InputStream inputStream = null;
			OutputStream fileOutputStream = new BufferedOutputStream(new FileOutputStream(tempFile), 8192 * 8);
			// Add Gzip Support
			if (needCompressed) {
				fileOutputStream = new GZIPOutputStream(fileOutputStream, 8192 * 8);
			}
			try {
				session.read(remoteFilePath, fileOutputStream);
			} catch (Exception e) {
				if (e instanceof RuntimeException) {
					throw (RuntimeException) e;
				} else {
					throw new MessagingException("Failure occurred while copying from remote to local directory", e);
				}
			} finally {
				try {
					if (inputStream != null) {
						inputStream.close();
					}
				} catch (Exception ignored1) {
				}
				try {
					fileOutputStream.flush();
					fileOutputStream.close();
				} catch (Exception ignored2) {
				}
			}
			if (tempFile.renameTo(localFile)) {
				// Persist Last Modified
				localFile.setLastModified(remoteFile.getTimestamp().getTimeInMillis());
				// Test If Need Deleted
				if (this.deleteRemoteFiles) {
					session.remove(remoteFilePath);
					if (logger.isDebugEnabled()) {
						logger.debug("deleted " + remoteFilePath);
					}
				}
			}
		}
	}

	protected boolean isFile(FTPFile file) {
		return file != null && file.isFile();
	}

	protected String getFilename(FTPFile file) {
		return (file != null ? file.getName() : null);
	}

}
