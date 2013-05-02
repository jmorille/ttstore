package eu.ttbox.feed.core.sorter;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.ResourceAwareItemWriterItemStream;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import eu.ttbox.batch.core.fs.GzipResource;

public class FileSorterItemWriter<I> implements ItemWriter<FileSorterVO<I>> {

	private ResourceAwareItemWriterItemStream<I> delegate;

	/**
	 * Delegate used for actual writing of the output.
	 */
	public void setDelegate(ResourceAwareItemWriterItemStream<I> delegate) {
		this.delegate = delegate;
	}

	@Override
	public void write(List<? extends FileSorterVO<I>> items) throws Exception {
		for (FileSorterVO<I> item : items) {
			String path = item.getFileName();
			File file = setResourceToDelegate(path);
			// Open Writer
			Resource currentResource = new GzipResource(file);
			delegate.setResource(currentResource);
			delegate.open(new ExecutionContext());
			try {
				// Write
				delegate.write(item.getItems());
			} finally {
				delegate.close();
			}
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
