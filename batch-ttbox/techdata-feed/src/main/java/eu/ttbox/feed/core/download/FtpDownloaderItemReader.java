package eu.ttbox.feed.core.download;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.integration.MessagingException;
import org.springframework.integration.file.filters.FileListFilter;
import org.springframework.integration.file.remote.session.Session;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import eu.ttbox.feed.core.download.filters.local.FtpAcceptOneLocalFileNameListFilter;
import eu.ttbox.feed.core.download.folder.LocalFolderByDateProvider;

public class FtpDownloaderItemReader implements ItemReader<FTPFile> {

	private static final Logger log = LoggerFactory.getLogger(FtpDownloaderItemReader.class);

	/**
	 * the {@link SessionFactory} for acquiring remote file Sessions.
	 */
	SessionFactory sessionFactory;

	/**
	 * An {@link FileListFilter} that runs against the
	 * <emphasis>remote</emphasis> file system view.
	 */
	FileListFilter<FTPFile> filter;

	FtpAcceptOneLocalFileNameListFilter filterLocal;

	File destinationFolder;

	LocalFolderByDateProvider localFolderProvider;

	String remoteDirectory = "/";

	private List<FTPFile> list;

	private StepExecution stepExecution;

	public void setRemoteDirectory(String remoteDirectory) {
		this.remoteDirectory = remoteDirectory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void setFilter(FileListFilter<FTPFile> filter) {
		this.filter = filter;
	}

	public void setFilterLocal(FtpAcceptOneLocalFileNameListFilter filterLocal) {
		this.filterLocal = filterLocal;
	}

	public void setLocalFolderProvider(LocalFolderByDateProvider localFolderProvider) {
		this.localFolderProvider = localFolderProvider;
	}

	@BeforeStep
	public void saveStepExecution(StepExecution stepExecution) {
		this.stepExecution = stepExecution;
		// Define folder destination
		ExecutionContext stepContext = this.stepExecution.getExecutionContext();
		destinationFolder = localFolderProvider.getLocalFolder();
		stepContext.put("destinationFolder", destinationFolder);
		stepContext.put("remoteDirectory", remoteDirectory);
		log.debug("Define local folder destination {}", destinationFolder);
	}

	@Override
	public FTPFile read() {
		// Init Ftp List
		if (list == null) {
			list = getRemoteList();
			log.info("Need to download remote {} files.", list.size());
		}
		// Consume
		if (!list.isEmpty()) {
			return list.remove(0);
		}
		return null;
	}

	protected final List<FTPFile> filterFiles(FTPFile[] files) {
		return (this.filter != null) ? this.filter.filterFiles(files) : Arrays.asList(files);
	}

	private List<FTPFile> getRemoteList() {
		// TODO Manage destination

		@SuppressWarnings("unchecked")
		List<FTPFile> remoteFiles = Collections.EMPTY_LIST;
		// Manage Session
		Session session = null;
		try {
			session = this.sessionFactory.getSession();
			Assert.state(session != null, "failed to acquire a Session");
			// File File List
			FTPFile[] files = session.<FTPFile> list(remoteDirectory);
			if (!ObjectUtils.isEmpty(files)) {
				// Various Filter
				List<FTPFile> filteredFiles = this.filterFiles(files);
				// Local File Filter
				if (filterLocal != null) {
					filteredFiles = filterLocal.filterFiles(filteredFiles, destinationFolder);
				}
				remoteFiles = filteredFiles;
			}
		} catch (IOException e) {
			throw new MessagingException("Problem occurred while synchronizing remote to local directory", e);
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (Exception ignored) {
					log.debug("failed to close Session", ignored);
				}
			}
		}
		return new ArrayList<FTPFile>(remoteFiles);
	}

}
