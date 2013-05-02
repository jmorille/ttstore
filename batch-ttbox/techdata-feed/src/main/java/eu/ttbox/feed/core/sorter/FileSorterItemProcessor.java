package eu.ttbox.feed.core.sorter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import eu.ttbox.batch.core.fs.GzipResource;

public class FileSorterItemProcessor<I> implements ItemProcessor<File, FileSorterVO<I>> {

	private static Logger log = LoggerFactory.getLogger(FileSorterItemProcessor.class);

	private ResourceAwareItemReaderItemStream<? extends I> delegate;

	protected final Comparator<I> itemComparator;

	public FileSorterItemProcessor(ResourceAwareItemReaderItemStream<? extends I> delegate, Comparator<I> itemComparator) {
		this.delegate = delegate;
		this.itemComparator = itemComparator;
	}

	@Override
	public FileSorterVO<I> process(File fileName) throws Exception {
		boolean sorted = true;
		String path = null;
		// Open Reader
		File file = setResourceToDelegate(path);
		Resource currentResource = new GzipResource(file);
		delegate.setResource(currentResource);
		delegate.open(new ExecutionContext());
		// Read Items

		List<I> items = new ArrayList<I>();
		try {
			I item = delegate.read();
			while (item != null) {
				I currentItem = delegate.read();
				if (currentItem != null) {
					items.add(currentItem);
					if (sorted) {
						int compare = itemComparator.compare(item, currentItem);
						if (compare <= 0) {
							sorted = false;
						}
					}
				}
				item = currentItem;
			}
		} finally {
			delegate.close();
		}

		// Sort File
		if (!sorted) {
			Collections.sort(items, itemComparator);
			// TODO Write
			String fileNameToRename = file.getName();
			int indexOfExt = fileNameToRename.indexOf(".");
			String fname = fileNameToRename.substring(0, indexOfExt);
			String fext = fileNameToRename.substring(indexOfExt, fileNameToRename.length());
			// Destination File
			File destFile = new File(file.getParentFile(), fname + "-sorted" + fext);
			FileSorterVO<I> sortedResult = new FileSorterVO<I>(destFile.getCanonicalPath(), items);

			return sortedResult;
		} else {
			// Rename File
			return null;
		}
	}

	private File setResourceToDelegate(String path) throws IOException {
		// String path = resource.getFile().getAbsolutePath() +
		// suffixCreator.getSuffix(resourceIndex);
		File file = new File(path);
		delegate.setResource(new FileSystemResource(file));
		return file;
	}

}
