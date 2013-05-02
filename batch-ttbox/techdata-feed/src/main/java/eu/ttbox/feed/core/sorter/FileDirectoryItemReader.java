package eu.ttbox.feed.core.sorter;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.integration.file.filters.FileListFilter;
import org.springframework.util.ObjectUtils;

import eu.ttbox.feed.core.download.folder.LocalFolderByDateProvider;

public class FileDirectoryItemReader implements ItemReader<File> {

	private static final Logger log = LoggerFactory.getLogger(FileDirectoryItemReader.class);

	LocalFolderByDateProvider folderProvider;

	FileListFilter<File> filter;

	private List<File> list;

	@Override
	public File read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		// Init Ftp List
		if (list == null) {
			list = getRemoteList();
			log.info("Need to manages remote {} files.", list.size());
		}
		// Consume
		if (!list.isEmpty()) {
			File currentFile = list.remove(0);
			return currentFile;
			// return currentFile.getCanonicalPath();
		}
		return null;
	}

	private List<File> getRemoteList() {
		@SuppressWarnings("unchecked")
		List<File> remoteFiles = Collections.EMPTY_LIST;
		File rootFolder = folderProvider.getLocalFolder();
		File[] files = rootFolder.listFiles();
		if (!ObjectUtils.isEmpty(files)) {
			// Various Filter
			List<File> filteredFiles = this.filterFiles(files);
			remoteFiles = filteredFiles;
		}
		// Local File Filter
		return remoteFiles;
	}

	protected final List<File> filterFiles(File[] files) {
		return (this.filter != null) ? this.filter.filterFiles(files) : Arrays.asList(files);
	}

}
