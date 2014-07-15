package eu.ttbox.batch.core.writer.unzip;

import eu.ttbox.batch.core.fs.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import java.io.*;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnZipItemWriter implements ItemWriter<File> {

	private static final Logger LOG = LoggerFactory.getLogger(UnZipItemWriter.class);

	private int bufferSize = 10240;

	private char separator = '_';

	private boolean compress = false;

	private boolean keepOriginalFile = false;

	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}

	public void setSeparator(char separator) {
		this.separator = separator;
	}

	public void setCompress(boolean compress) {
		this.compress = compress;
	}

	public void setKeepOriginalFile(boolean keepOriginalFile) {
		this.keepOriginalFile = keepOriginalFile;
	}

	@Override
	public void write(List<? extends File> items) throws Exception {
		for (File localFile : items) {
			if (FileUtils.isZipFile(localFile)) {
				unzip(localFile);
			}
		}

	}

	private void unzip(File localFile) throws IOException {
		if (localFile != null && localFile.exists()) {
			InputStream is = new BufferedInputStream(new FileInputStream(localFile), bufferSize);
			try {
				ZipInputStream zin = new ZipInputStream(is);
				ZipEntry ze = null;
				while ((ze = zin.getNextEntry()) != null) {
					String zipEntryName = getFilename(ze);
					String fileName = localFile.getName();
					// Compute unzipped filename
					boolean isPreviousGzip = FileUtils.isGzipFileName(zipEntryName); 
					boolean toCompress = compress && !isPreviousGzip; 
					String unzipFileName = getUnzippedFileName(fileName, zipEntryName);
					// Do unzip
					File unzipFile = new File(localFile.getParentFile(), unzipFileName);
					unzipFile.delete();
					OutputStream bos = new BufferedOutputStream(new FileOutputStream(unzipFile), bufferSize);
					if (toCompress) {
						bos = new GZIPOutputStream(bos, bufferSize);
					}
					byte[] buffer = new byte[2048];
					int size;
					while ((size = zin.read(buffer, 0, buffer.length)) != -1) {
						bos.write(buffer, 0, size);
					}
					bos.flush();
					bos.close();
					if (LOG.isInfoEnabled()) {
						LOG.info("File {} contains entry {} as unzip as {}", new Object[] { localFile, ze.getName(), unzipFile });
					}
				}
				zin.closeEntry();
			} catch (Exception e) {
				LOG.error("Error reding file {} : {}", localFile, e.getMessage());
			} finally {
				is.close();
			}
			if (!keepOriginalFile) {
				localFile.delete();
			}
		}
	}

	private String getUnzippedFileName(String zipFilename , String zipEntryName) {
		boolean isPreviousGzip = FileUtils.isGzipFileName(zipEntryName);
		String[] fileSplit = FileUtils.splitFileNameAndLongExtention(zipFilename);
		String[] zipEntryNameSplit = FileUtils.splitFileNameAndLongExtention(zipEntryName);
		String zipEntryNameRoot = zipEntryNameSplit[0];
		String zipEntryNameExt = zipEntryNameSplit[1];
		String fileNameRoot = fileSplit[0];
		// Compute new name
		StringBuilder sb = new StringBuilder(fileNameRoot.length() + zipEntryName.length() + 5);
		sb.append(fileNameRoot);
		if (!fileNameRoot.endsWith(zipEntryNameRoot)) {
			sb.append(separator).append(zipEntryName);
		} else {
			sb.append(zipEntryNameExt);
		}
		boolean toCompress = compress && !isPreviousGzip;
		if (toCompress) {
			sb.append(FileUtils.EXT_GZIP);
		}
		String unzipFileName = sb.toString();
		return unzipFileName;
	}
	
	protected String getFilename(ZipEntry ze) {
		String zipEntryName = ze.getName();
		zipEntryName = zipEntryName.toLowerCase();
		return zipEntryName;
	}

}
