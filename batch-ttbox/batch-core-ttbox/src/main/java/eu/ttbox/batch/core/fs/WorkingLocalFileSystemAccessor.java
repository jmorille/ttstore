package eu.ttbox.batch.core.fs;

import com.google.common.io.PatternFilenameFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class WorkingLocalFileSystemAccessor {
	
	private static Logger log = LoggerFactory.getLogger(WorkingLocalFileSystemAccessor.class);
	
	private String cacheFolder;
	
	private FilePartitionner filePartitionner;
	
	private Boolean compressionGzip;
	
	 
	public WorkingLocalFileSystemAccessor() {
		super(); 
	}
 
	
	public WorkingLocalFileSystemAccessor(String cacheFolder, FilePartitionner filePartitionner, Boolean compressionGzip) {
		super();
		this.cacheFolder = cacheFolder;
		this.filePartitionner = filePartitionner;
		this.compressionGzip = compressionGzip;
	}



	public void setCacheFolder(String tempDir) {
		this.cacheFolder = tempDir;
	}
 
	public void setCompressionGzip(Boolean compressionGzip) {
		this.compressionGzip = compressionGzip;
	}
   
	public void setFilePartitionner(FilePartitionner fileRelocator) {
		this.filePartitionner = fileRelocator;
	}

	public List<File> getLocalFilesInFolder(String filenamePattern) throws IOException {
		return getLocalFilesInFolder(Pattern.compile(filenamePattern));
	}
	
	public List<File> getLocalFilesInFolder(Pattern filenamePattern) throws IOException {
		File localFilePattern = getPotentialLocalFile("testFile");
		File folder = localFilePattern.getParentFile();
		// Filter
		FilenameFilter filter = new PatternFilenameFilter(filenamePattern);
		File[] filteredFiles = folder.listFiles(filter);
		List<File> localFiles = null;
		if (filteredFiles!=null && filteredFiles.length>0) {
			localFiles = Arrays.asList(filteredFiles);
		} else {
			localFiles =  Collections.EMPTY_LIST;
		}
		return localFiles;
	}
	
	public File getPotentialLocalFile(String relativeFilePath) {
		return getPotentialLocalFile(relativeFilePath, compressionGzip);
	}

	private File getPotentialLocalFile(String relativeFilePath, Boolean isLocalFileCompressed) {
		String currentFileName = relativeFilePath;
		if (isLocalFileCompressed != null) {
			if (isLocalFileCompressed.booleanValue()) {
				if (!FileUtils.isGzipFileName(currentFileName)) {
					currentFileName += ".gz";
				}
			} else {
				if (FileUtils.isGzipFileName(currentFileName)) {
					currentFileName = currentFileName.substring(0, currentFileName.indexOf(".gz"));
				}
			}
		}
		File destFile = new File(cacheFolder, currentFileName);
		// Relocate File
		if (filePartitionner!=null) {
			destFile = filePartitionner.relocate(destFile); 
		}
		return destFile;

	}

	
	public boolean deleteLocalFile(String relativePath) {
		File destFile = getPotentialLocalFile(relativePath, Boolean.TRUE);
		File destFileUncompress = getPotentialLocalFile(relativePath, Boolean.FALSE);
		return destFile.delete() || destFileUncompress.delete();
	}

	/**
	 * Return a Existing File from the local fs.
	 * 
	 * @param relativePath
	 *            The relatif path
	 * @return The File is existing or null if not.
	 */
	public File getLocalFile(String relativePath) {
		File destFile = getPotentialLocalFile(relativePath);
		if (destFile.exists()) {
			return destFile;
		}
		return null;
	}
}
