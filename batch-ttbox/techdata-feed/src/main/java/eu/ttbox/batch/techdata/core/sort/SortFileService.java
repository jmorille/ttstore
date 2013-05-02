package eu.ttbox.batch.techdata.core.sort;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import eu.ttbox.batch.core.fs.FileUtils;
import eu.ttbox.batch.core.fs.GzipResource;
import eu.ttbox.batch.techdata.core.sort.writer.SortedFileWriterHelper;

public class SortFileService implements ItemWriter<File>, InitializingBean {

	private static final Logger log = LoggerFactory.getLogger(SortFileService.class);

	private ResourceAwareItemReaderItemStream<SortVO> itemReader;

	private boolean compress = false;

	private String sortedFileSuffix = "-sorted";

	private boolean keepOriginalFile = false;

	public void setItemReader(ResourceAwareItemReaderItemStream<SortVO> itemReader) {
		this.itemReader = itemReader;
	}

	public void setCompress(boolean compress) {
		this.compress = compress;
	}

	public void setSortedFileSuffix(String sortedFileSuffix) {
		this.sortedFileSuffix = sortedFileSuffix;
	}

	public void setKeepOriginalFile(boolean keepOriginalFile) {
		this.keepOriginalFile = keepOriginalFile;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(itemReader, "itemReader is requiered");
	}

	@Override
	public void write(List<? extends File> items) throws Exception {
		int fileId = 0;
		int fileSize = items.size();
		for (File file : items) {
			fileId++;
			long beginCopy = System.currentTimeMillis();
			long fileSizeInBytes = file.length();
			List<SortVO> lines = sortFile(file);
			int lineCount = 0;
			if (lines != null) {
				lineCount = lines.size();
			}
			if (log.isInfoEnabled()) {
				long endCopy = System.currentTimeMillis();
				if (lines != null) {
					log.info("Sort file {}/{} : Sorted {} of {} lines in {} ms  (size {} bytes)", new Object[] { fileId, fileSize, file, lineCount,
							(endCopy - beginCopy), fileSizeInBytes });
				} else {
					log.info("Sort file {}/{} : Rename {} of {} lines in {} ms  (size {} bytes)", new Object[] { fileId, fileSize, file, lineCount,
							(endCopy - beginCopy), fileSizeInBytes });
				}
			}
		}

	}

	public List<SortVO> sortFile(File file) throws UnexpectedInputException, ParseException, NonTransientResourceException, Exception {
		if (file == null || !file.exists()) {
			return null;
		}
		setResourceToDelegate(file);
		ExecutionContext executionContext = new ExecutionContext();
		itemReader.open(executionContext);
		List<SortVO> sortedFileItems = null;
		try {
			sortedFileItems = read(itemReader);
		} finally {
			itemReader.close();
		}
		File sortedFileName = FileUtils.getSuffixedFile(file, sortedFileSuffix, compress);
		sortedFileName.delete();
		if (sortedFileItems != null) {
			// Files
			if (keepOriginalFile) {
				File oriFileName = FileUtils.getSuffixedFile(file, "-original", false);
				FileUtils.renameFileTo(oriFileName, file, file.lastModified());
			} else {
				file.delete();
			}
			// Resort File
			SortedFileWriterHelper.writeLines(sortedFileItems, sortedFileName, file.lastModified());
		} else {
			// Rename file
			FileUtils.renameFileTo(sortedFileName, file, file.lastModified());
		}
		return sortedFileItems;
	}

	public List<SortVO> read(ItemReader<SortVO> itemReader) throws UnexpectedInputException, ParseException, NonTransientResourceException, Exception {
		// Define Contexte
		// Read
		SortVO currentItem = itemReader.read();
		List<SortVO> lines = new ArrayList<SortVO>();
		boolean isSorted = true;
		// Read All File
		while (currentItem != null) {
			lines.add(currentItem);
			SortVO nextItem = itemReader.read();
			if (isSorted && nextItem != null) {
				if (currentItem.compareTo(nextItem) > 0) {
					isSorted = false;
				}
			}
			currentItem = nextItem;
		}
		// Re ort File
		if (!isSorted) {
			log.debug("Need to sort file");
			Collections.sort(lines);
		} else {
			lines = null;
		}
		return lines;
	}

	private File setResourceToDelegate(File localFile) throws IOException {
		itemReader.setResource(new GzipResource(localFile));
		return localFile;
	}

}
