package eu.ttbox.batch.core.reader;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.ListItemReader;

import eu.ttbox.batch.core.fs.WorkingLocalFileSystemAccessor;

public class LocalFilenamePatternProxyCacheItemReader implements ItemReader<File>, ItemStream {

	private static Logger log = LoggerFactory.getLogger(LocalFilenamePatternProxyCacheItemReader.class);

	public final static Comparator<File> FILE_COMPARATOR_LAST_MODIFIED_ASC = new Comparator<File>() {
		@Override
		public int compare(File o1, File o2) {
			long thisVal = o1.lastModified();
			long anotherVal = o2.lastModified();
			return (thisVal < anotherVal ? -1 : (thisVal == anotherVal ? 0 : 1));
		}
	};

	public final static Comparator<File> FILE_COMPARATOR_LAST_MODIFIED_DESC = new Comparator<File>() {
		@Override
		public int compare(File o1, File o2) {
			long thisVal = o2.lastModified();
			long anotherVal = o1.lastModified();
			return (thisVal < anotherVal ? -1 : (thisVal == anotherVal ? 0 : 1));
		}
	};

	public final static Comparator<File> FILE_COMPARATOR_NAME = new Comparator<File>() {
		@Override
		public int compare(File o1, File o2) {
			return o1.getName().compareTo(o2.getName());
		}
	};

	WorkingLocalFileSystemAccessor workingLocalFolder;

	String filenamePattern;

	private ItemReader<File> delegated;

	private Comparator<File> fileComparator;

	public LocalFilenamePatternProxyCacheItemReader() {
		super();
	}

	public LocalFilenamePatternProxyCacheItemReader(WorkingLocalFileSystemAccessor workingLocalFolder, String filenamePattern) {
		super();
		this.workingLocalFolder = workingLocalFolder;
		this.filenamePattern = filenamePattern;
	}

	public void setWorkingLocalFolder(WorkingLocalFileSystemAccessor workingLocalFolder) {
		this.workingLocalFolder = workingLocalFolder;
	}

	public void setFilenamePattern(String relativePath) {
		this.filenamePattern = relativePath;
	}

	public void setFileComparator(Comparator<File> fileComparator) {
		this.fileComparator = fileComparator;
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		try {
			List<File> files = workingLocalFolder.getLocalFilesInFolder(filenamePattern);
			if (this.fileComparator != null) {
				Collections.sort(files, this.fileComparator);
			}
			if (!files.isEmpty()) {
				log.debug("Register {} files to ItemReader", files.size());
				delegated = new ListItemReader<File>(files);
			}
		} catch (IOException e) {
			throw new ItemStreamException("Error in getting Files List " + filenamePattern + " : " + e.getMessage(), e);
		}

	}

	@Override
	public File read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if (delegated != null) {
			File currentFile = delegated.read();
 			return currentFile;
		}
		return null;
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
	}

	@Override
	public void close() throws ItemStreamException {
		delegated = null;

	}
}
