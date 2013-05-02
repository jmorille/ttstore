package eu.ttbox.batch.core.fs;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import eu.ttbox.batch.core.download.DownloaderConnector;

public class ProxyCacheDownloadConnector {

	private static Logger log = LoggerFactory.getLogger(ProxyCacheDownloadConnector.class);

	private DownloaderConnector downloaderConnector;

	private boolean online = true;

	private boolean preferLocal = false;

	private WorkingLocalFileSystemAccessor workingLocalFolder;

	public void setDownloaderConnector(DownloaderConnector downloaderConnector) {
		this.downloaderConnector = downloaderConnector;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public void setPreferLocal(boolean preferLocal) {
		this.preferLocal = preferLocal;
	}

	public WorkingLocalFileSystemAccessor getWorkingLocalFolder() {
		return workingLocalFolder;
	}

	public void setWorkingLocalFolder(WorkingLocalFileSystemAccessor localFs) {
		this.workingLocalFolder = localFs;
	}

	@PostConstruct
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(workingLocalFolder, "workingLocalFolder is mandatory property");
		if (downloaderConnector == null) {
			online = false;
		}
		if (!online) {
			log.warn("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			log.warn("!!! The mode offline is activated, Nothing will be downloaded !!!");
			log.warn("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		}
	}

	public File downloadFile(String relativePath) throws IOException {
		return downloadFile(relativePath, (Date) null);
	}

	public File downloadFile(String relativePath, Date espectedVersionDate) throws IOException {
		File destFile = workingLocalFolder.getPotentialLocalFile(relativePath);
		boolean destFilePreExist = destFile.exists();
		boolean needToDownload = true;
		boolean manageExpectedDate = espectedVersionDate != null;
		// Check Version Date
		if (destFilePreExist) {
			if (preferLocal) {
				needToDownload = false;
			} else if (manageExpectedDate && (destFile.lastModified() >= espectedVersionDate.getTime())) {
				needToDownload = false;
				if (log.isInfoEnabled()) {
					SimpleDateFormat sfToLog = FileUtils.getDateFormatForLog();
					log.info("Use Local File Destination {} \t({}) >= (espected {})", new Object[] { destFile, sfToLog.format( new Date(destFile.lastModified())),
							sfToLog.format(espectedVersionDate) });
				}
			}
		}
		// Download It
		if (needToDownload && online) {
			destFile = downloaderConnector.downloadFile(relativePath, destFile);
			if (manageExpectedDate && (destFile.lastModified() < espectedVersionDate.getTime())) {
				// Override the version Date
				if (log.isDebugEnabled()) {
 					SimpleDateFormat sfForLog = FileUtils.getDateFormatForLog();
					log.debug("Override the Local File Date {} \t({}) ==> to (expected {})", new Object[] { destFile, sfForLog.format( new Date(destFile.lastModified())),
							sfForLog.format( espectedVersionDate )});
				}
				destFile.setLastModified(espectedVersionDate.getTime());
			}
		} else if (!destFilePreExist) {
			destFile = null;
		}
		log.debug("For relatif path {}, select local file {}", relativePath, destFile);
		return destFile;
	}

}
