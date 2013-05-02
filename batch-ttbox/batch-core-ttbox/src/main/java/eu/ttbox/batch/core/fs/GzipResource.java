package eu.ttbox.batch.core.fs;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import org.springframework.core.io.FileSystemResource;

public class GzipResource extends FileSystemResource {

	private int bufferSize = 20480*2; 
	
	public GzipResource(File file) {
		super(file);
	}

	public GzipResource(String path) {
		super(path);
	}

	@Override
	public InputStream getInputStream() throws IOException {
		InputStream inputStream = new BufferedInputStream(super.getInputStream(), bufferSize);
		if (FileUtils.isGzipFileName(getFilename())) {
			inputStream = new GZIPInputStream(inputStream, bufferSize);
		}
		return inputStream;
	}

}