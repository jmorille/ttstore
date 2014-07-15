package eu.ttbox.feed.core.download;

import eu.ttbox.feed.core.download.filters.local.FtpAcceptOneLocalFileNameListFilter;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.integration.MessagingException;
import org.springframework.integration.file.remote.session.Session;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.util.Assert;

import java.io.*;
import java.util.List;
import java.util.zip.GZIPOutputStream;

public class FtpDownloaderItemWriter implements ItemWriter<FTPFile> {

	private static final Logger log = LoggerFactory.getLogger(FtpDownloaderItemWriter.class);

	/**
	 * Extension used when downloading files. We change it right after we know
	 * it's downloaded.
	 */
	private static final String INCOMPLETE_EXTENSION = ".writing";

	private static final String BACKUP_EXTENSION = ".backup";

	String remoteFileSeparator = "/";

	/**
	 * the {@link SessionFactory} for acquiring remote file Sessions.
	 */
	SessionFactory sessionFactory;

	/**
	 * Should we <emphasis>GZIP compress</emphasis> the local <b>destination</b>
	 * files during copying to the local directory? By default this is false.
	 */
	boolean gzipOutputCompressed = false;

	private StepExecution stepExecution;

	@BeforeStep
	public void saveStepExecution(StepExecution stepExecution) {
		this.stepExecution = stepExecution;
	}

	public void setGzipOutputCompressed(boolean gzipOutputCompressed) {
		this.gzipOutputCompressed = gzipOutputCompressed;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void write(List<? extends FTPFile> items) throws Exception {
		// Read Excecution Context
		ExecutionContext stepContext = this.stepExecution.getExecutionContext();
		File localDirectory = (File) stepContext.get("destinationFolder");
		String remoteDirectory = stepContext.getString("remoteDirectory");
		// Manage Session
		Session session = null;
		try {
			session = this.sessionFactory.getSession();
			Assert.state(session != null, "failed to acquire a Session");
			for (FTPFile file : items) {
				if (file != null) {
					this.copyFileToLocalDirectory(remoteDirectory, file, localDirectory, session);
				}
			}

		} catch (IOException e) {
			throw new MessagingException("Problem occurred while synchronizing remote to local directory : "
					+ e.getMessage(), e);
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (Exception ignored) {
					log.debug("failed to close Session", ignored);
				}
			}
		}

	}

	private void copyFileToLocalDirectory(String remoteDirectoryPath, FTPFile remoteFile, File localDirectory,
			Session session) throws IOException {
		boolean needCompressed = gzipOutputCompressed;
		String remoteFileName = this.getFilename(remoteFile);
		String remoteFilePath = remoteDirectoryPath + remoteFileSeparator + remoteFileName;
		if (!this.isFile(remoteFile)) {
			log.debug("cannot copy, not a file: {}", remoteFilePath);
			return;
		}
		needCompressed = needCompressed ? !remoteFileName.endsWith(".gz") : false;
		//
		String localFileName = needCompressed ? remoteFileName.toLowerCase() + ".gz" : remoteFileName.toLowerCase();
		File localFile = new File(localDirectory, localFileName);

		// TODO Need to Test Existing file for replace It
		boolean needDownload = !localFile.exists();
		if (!needDownload) {
			// Test the date
			needDownload = FtpAcceptOneLocalFileNameListFilter.replaceLocalFile(localFile, remoteFile);
		}
		if (needDownload) {
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

			try {
				InputStream inputStream = null;
				OutputStream fileOutputStream = new FileOutputStream(tempFile);
				fileOutputStream = new BufferedOutputStream(fileOutputStream, 8192 * 8);
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
						throw new MessagingException("Failure occurred while copying from remote to local directory : "
								+ e.getMessage(), e);
					}
				} finally {
					try {
						if (inputStream != null) {
							inputStream.close();
						}
					} catch (Exception ignored1) {
						log.error("Error closing inputStream : " + ignored1.getMessage(), ignored1);
					}
					try {
						fileOutputStream.flush();
						fileOutputStream.close();
					} catch (Exception ignored2) {
						log.error("Error closing fileOutputStream : " + ignored2.getMessage(), ignored2);
					}
				}
				if (tempFile.renameTo(localFile)) {
					if (backupLocalFile != null) {
						backupLocalFile.delete();
						backupLocalFile = null;
					}
					tempFile = null;
					// Persist Last Modified
					localFile.setLastModified(remoteFile.getTimestamp().getTimeInMillis());
					if (log.isInfoEnabled()) {
						long endCopy = System.currentTimeMillis();
						log.info("Copy successfully to Destination File {} in {} ms.", localFile, (endCopy - beginCopy));
					}
				}
			} finally {
				if (tempFile != null) {
					tempFile.delete();
				}
				if (backupLocalFile != null) {
					backupLocalFile.renameTo(localFile);
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
