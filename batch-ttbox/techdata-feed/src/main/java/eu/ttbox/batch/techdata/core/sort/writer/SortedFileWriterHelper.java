package eu.ttbox.batch.techdata.core.sort.writer;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.zip.GZIPOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.util.FileUtils;

import com.google.common.base.Charsets;

import eu.ttbox.batch.techdata.core.sort.SortVO;

public class SortedFileWriterHelper {

	private static final Logger log = LoggerFactory.getLogger(SortedFileWriterHelper.class);

	private static String SORT_TEMP = ".sorting";

	private static int BUFFERSIZE = 2048;
	
	public static void writeLines(Collection<SortVO> lines, File file, long lastModifiedInMs) throws IOException {
		boolean isFileExist = file.exists();
		File sortedFile = new File(file.getParent(), file.getName() + SORT_TEMP);
		// Write File 
		FileUtils.setUpOutputFile(sortedFile, false,  false, true);
		OutputStream out = new BufferedOutputStream(new FileOutputStream(sortedFile), BUFFERSIZE);
		if (eu.ttbox.batch.core.fs.FileUtils.isGzipFile( file)) {
			out = new GZIPOutputStream(out, BUFFERSIZE);
		}
		try {
			writeLines(lines, out, Charsets.UTF_8);
		} finally {
			out.flush();
			out.close();
		}
		// Switch File
		boolean isOriDeleted = true;
		if (isFileExist) {
			isOriDeleted = file.delete();
		}
		boolean isRenamed = sortedFile.renameTo(file); 
		sortedFile.setLastModified(lastModifiedInMs);
		log.debug("Rename Sorted File {} to File {}", sortedFile, file);
		if (!isOriDeleted && !isRenamed) {
			throw new RuntimeException("Could not rename sorted file to "+ file.getName());
		}
	}

	private static void writeLines(Collection<SortVO> lines, OutputStream out, Charset cs) throws IOException { 
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(out, cs), BUFFERSIZE);
		for (SortVO vo : lines) {
			wr.write(vo.getLine());
			wr.newLine();
		}
		wr.flush();
	}

}
