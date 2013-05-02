package eu.ttbox.batch.core.reader;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.core.io.Resource;

import eu.ttbox.batch.core.fs.GzipResource;
import eu.ttbox.batch.core.fs.ProxyCacheDownloadConnector;

public class ProxyCacheDelegateItemReader<T> implements ItemReader<T>, ItemStream, ResourceAwareItemReaderItemStream<T> {

	private Logger log = LoggerFactory.getLogger(getClass());

	ResourceAwareItemReaderItemStream<T> delegate;

	ProxyCacheDownloadConnector downloadConnector;

	String relativePath;

	boolean isOpen = false;

	boolean isOnline = true;
	
	
	public void setOnline(boolean online) {
		this.isOnline = online;
	}

	public void setDelegate(ResourceAwareItemReaderItemStream<T> delegate) {
		this.delegate = delegate;
	}

	public void setDownloadConnector(ProxyCacheDownloadConnector httpConnector) {
		this.downloadConnector = httpConnector;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	@Override
	public T read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		T item = null;
		if (isOpen) {
			item = delegate.read();
		}
		return item;
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		// Add Remote proxy Call
		log.debug("Opening Delegate ItemReader");
		File file = null;
		try {
			if (isOnline) {
				file = downloadConnector.downloadFile(relativePath);
			} else {
				file = downloadConnector.getWorkingLocalFolder().getLocalFile(relativePath);
			}
		} catch (IOException e) {
			throw new ItemStreamException(e.getMessage(), e);
		}
		if (file != null && file.exists()) {
			Resource resource = new GzipResource(file);
			log.debug("Define Delegate resource {}", file);
			delegate.setResource(resource);
			// Delegate to File
			delegate.open(executionContext);
			isOpen = true;
			log.info("Delegate ItemReader is Open");
		}

	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		if (isOpen) {
			delegate.update(executionContext);
		}

	}

	@Override
	public void close() throws ItemStreamException {
		if (isOpen) {
			delegate.close();
			isOpen = false;
			log.debug("Delegate ItemReader is Close");
		}

	}

	@Override
	public void setResource(Resource resource) {
		this.delegate.setResource(resource);
	}

}
