package eu.ttbox.batch.techdata.core.fs;

import java.io.File;
import java.io.IOException;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import eu.ttbox.batch.core.download.ftp.FtpConnector;
import eu.ttbox.batch.core.download.ftp.FtpFileDownloadRequest;
import eu.ttbox.batch.core.download.ftp.filter.ChainFtpFileFilter;
import eu.ttbox.batch.core.download.ftp.filter.DateRangeFtpFileFilter;
import eu.ttbox.batch.core.download.ftp.filter.FilenamePatternFtpFileFilter;
import eu.ttbox.batch.core.download.ftp.listfilter.ChainFileListFilter;
import eu.ttbox.batch.core.download.ftp.listfilter.FileListFilter;
import eu.ttbox.batch.core.fs.FileUtils;
import eu.ttbox.batch.core.fs.ProxyCacheDownloadConnector;
import eu.ttbox.batch.core.fs.partitionner.AliasFilenamePartitionner;
import eu.ttbox.batch.techdata.core.fs.filter.DedupByNameLowerCaseAndLastModifiedFileListFilter;
import eu.ttbox.batch.techdata.core.fs.filter.FtpFileIdFileListFilter;
import eu.ttbox.batch.techdata.core.sort.SortFileService;
import eu.ttbox.batch.techdata.price.filter.SupplierPriceIdFilterFactory;

public class TechdataCacheDownloadConnector extends ProxyCacheDownloadConnector {

	private Logger log = LoggerFactory.getLogger(getClass());

	private FtpConnector ftpConnector;

	private SortFileService postDownload;

	private AliasFilenamePartitionner aliasFilenamePartitionner;

	private  SupplierPriceIdFilterFactory filterFactory;
	 
	
	public void setFilterFactory(SupplierPriceIdFilterFactory filterFactory) {
		this.filterFactory = filterFactory;
	}

	public void setPostDownload(SortFileService postDownload) {
		this.postDownload = postDownload;
	}

	public void setAliasFilenamePartitionner(AliasFilenamePartitionner aliasFilenamePartitionner) {
		this.aliasFilenamePartitionner = aliasFilenamePartitionner;
	}

	@Autowired
	public void setFtpConnector(FtpConnector ftpConnector) {
		this.ftpConnector = ftpConnector;
		setDownloaderConnector(ftpConnector);
	}

	private String getFilename(FTPFile ftpFile) {
		String filename = ftpFile.getName();
		filename = filename.toLowerCase();
		return filename;
	}

	private FileListFilter<FtpFileDownloadRequest> getFilter() {
		FileListFilter<FtpFileDownloadRequest> filter =	new DedupByNameLowerCaseAndLastModifiedFileListFilter();
		if (filterFactory!=null) {
			try {
				FileListFilter<FtpFileDownloadRequest> facFilter  = filterFactory.getObject();
				if (facFilter!=null) {
					filter = new ChainFileListFilter<FtpFileDownloadRequest>(facFilter, filter);
				}
			} catch (Exception e) {
				log.error("Error in acceding filterFactory : " + e.getMessage(), e);
			}
		}

		return filter;
	}
	@Override
	public File downloadFile(String filenamePattern) throws IOException {
		log.debug("downloadFile {}", filenamePattern);
		FileListFilter<FtpFileDownloadRequest> filter =getFilter();
		int maxExpected = 1;
		List<File> files = downloadFiles(filenamePattern, filter, Integer.valueOf(maxExpected));
		int fileSize = 0;
		if (files != null) {
			fileSize = files.size();
		}
		File localFile = null;
		if (fileSize == maxExpected) {
			localFile = files.get(0);
		} else if (fileSize > maxExpected) {
			throw new UnexpectedException("Max expected " + maxExpected + " files but was " + fileSize);
		}
		log.info("For relatif path {}, select local file {}", filenamePattern, localFile);
		return localFile;
	}

	public List<File> downloadTechPrices(String filenamePattern) throws IOException {
		FileListFilter<FtpFileDownloadRequest> filter =getFilter();
		List<File> result =  downloadFiles(filenamePattern, filter, null);
		if (filter!=null) {
			String filterResult = filter.getEndToString();
			log.info(filterResult);
		}
		return result;
	}

//	public List<File> downloadFiles(String filenamePattern) throws IOException {
//		FileListFilter<FtpFileDownloadRequest> filter =getFilter();
//		return downloadFiles(filenamePattern, filter, null);
//	}

	// public List<File> getLocalFilesInFolder(String filenamePattern) throws
	// IOException {
	// File localFilePattern = getPotentialLocalFile(filenamePattern);
	// File folder = localFilePattern.getParentFile();
	// // Filter
	// FilenameFilter filter = new PatternFilenameFilter(filenamePattern);
	// File[] filteredFiles = folder.listFiles(filter);
	// List<File> localFiles = Arrays.asList(filteredFiles);
	// return localFiles;
	// }

	private List<File> downloadFiles(String filenamePattern, FileListFilter<FtpFileDownloadRequest> filter, Integer maxExpected) throws IOException {
		List<File> result = new ArrayList<File>();
		String remoteFilePath = "/";
		// Define download filter
		FilenamePatternFtpFileFilter filename = new FilenamePatternFtpFileFilter(filenamePattern);
		DateRangeFtpFileFilter dateFilter = new DateRangeFtpFileFilter(DateRangeFtpFileFilter.getNowAtMidnight(), DateRangeFtpFileFilter.getNowAddMinutes(-5));
		ChainFtpFileFilter chainFilter = new ChainFtpFileFilter(dateFilter, filename); //
		// Get Files List
		FTPClient client = ftpConnector.createClient();
		try {
			// Prepare Download list
			List<FTPFile> ftpFiles = ftpConnector.getRemoteList(client, remoteFilePath, chainFilter);

			// Prepare Download list
			List<FtpFileDownloadRequest> toDownloads = new ArrayList<FtpFileDownloadRequest>(ftpFiles.size());
			for (FTPFile ftpFile : ftpFiles) {
				String localFilename = getFilename(ftpFile);
				File localFile = getWorkingLocalFolder().getPotentialLocalFile(localFilename);
				File testedLocalFile = localFile;
				if (aliasFilenamePartitionner != null) {
					testedLocalFile = aliasFilenamePartitionner.relocate(localFile);
				}
				boolean needToDownload = !testedLocalFile.exists();
				if (!needToDownload) {
					needToDownload = replaceLocalFile(testedLocalFile, ftpFile);
				}
				if (needToDownload) {
					log.debug("Add Ftp file to download list {}", ftpFile.getName());
					toDownloads.add(new FtpFileDownloadRequest(remoteFilePath, ftpFile, localFile));
				} else {
					result.add(localFile);
					log.debug("Ignore Ftp File download in favor of local file {}", localFile);
				}
			}
			// Do Download
			if (filter != null) {
				toDownloads = filter.filterFiles(toDownloads);
			}
			if (maxExpected != null) {
				if (toDownloads.size() > maxExpected.intValue()) {
					throw new UnexpectedException("Max expected " + maxExpected + " files but was " + toDownloads.size());
				}
			}
			if (!toDownloads.isEmpty()) {
				if (filter!=null) {
					String filterResult = filter.getEndToString();
					log.info(filterResult);
				}
				// Do Download
				ftpConnector.downloadFiles(toDownloads, client);
				// Convert to Download List File List
				for (FtpFileDownloadRequest request : toDownloads) {
					File localFile = request.getLocalFile();
					postDownload(localFile);
					if (localFile != null && localFile.exists()) {
						result.add(localFile);
					}
				}
			}
		} finally {
			ftpConnector.releaseClient(client);
			client = null;
		}

		return result;
	}

	private boolean replaceLocalFile(File localFile, FTPFile ftpFile) {
		return (localFile.lastModified() < ftpFile.getTimestamp().getTimeInMillis());
	}

	protected void postDownload(File localFile) {
		if (postDownload != null) {
			try {
				// FIXME Manage different file name for sorting files
				postDownload.sortFile(localFile);
			} catch (Exception e) {
				File badFile = FileUtils.getSuffixedFile(localFile, "-bad", false);
				FileUtils.renameFileTo(badFile, localFile, localFile.lastModified());
				// localFile.delete();
				throw new RuntimeException("Could not sort file " + localFile + " : " + e.getMessage(), e);
			}
		}
	}

}
