package eu.ttbox.batch.core.fs;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.elasticsearch.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {

	private final static Logger log = LoggerFactory.getLogger(FileUtils.class);

	public final static String EXT_GZIP = ".gz";
	public final static String EXT_ZIP = ".zip";

	public final static char SEPARATOR_URL = '/';

	public final static TimeZone GMT = TimeZone.getTimeZone("GMT");

	private FileUtils() {
		super();
	}

	public static SimpleDateFormat getDateFormatForLog() {
		SimpleDateFormat sfForLog = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss z"); 
		sfForLog.setTimeZone(GMT);
		return sfForLog;
	}
	
	public static boolean isGzipFile(File file) {
		return isGzipFileName(file.getName());
	}

	public static boolean isZipFile(File file) {
		return isZipFileName(file.getName());
	}

	public static boolean isGzipFileName(String fileName) {
		return fileName.endsWith(EXT_GZIP);
	}

	public static boolean isZipFileName(String fileName) {
		return fileName.endsWith(EXT_ZIP);
	}

	public static boolean isTextFile(File file) {
		return isTextFileName(file.getName());
	}

	public static boolean isTextFileName(String fileName) {
		if (fileName.endsWith(".xml")) {
			return true;
		}
		if (fileName.endsWith(".txt")) {
			return true;
		}
		return false;
	}

	public static boolean renameFileTo(File destFile, File tmp, Date headerLastModifiedDate, File backupFile) {
		boolean isRenamed = renameFileTo(destFile, tmp, headerLastModifiedDate);
		if (isRenamed) {
			if (backupFile != null) {
				boolean isBackupDel = backupFile.delete();
			}
		}
		return isRenamed;
	}

	private static boolean renameFileTo(File destFile, File originalFile) {
		boolean isDestFilePreExist = destFile.exists();
		boolean isDelete = true;
		if (isDestFilePreExist) {
			isDelete = destFile.delete();
		}
		boolean isRename = originalFile.renameTo(destFile);
		if (!isRename) {
			log.error("Could not rename file {} to {} => isDelete {} / isDestFilePreExist {} / isDestFilePostExist {} / isTmpExist {} size {}", new Object[] {
					originalFile, destFile, isDelete, isDestFilePreExist, destFile.exists(), originalFile.exists(), originalFile.getTotalSpace() });
		}
		return isDelete && isRename;

	}

	public static boolean renameFileTo(File destFile, File tmp, Date headerLastModifiedDate) {
		boolean renamed = renameFileTo(destFile, tmp);
		if (renamed) {
			if (headerLastModifiedDate != null) {
				destFile.setLastModified(headerLastModifiedDate.getTime());
			} else {
				destFile.setLastModified(0);
			}
		}
		return renamed;
	}

	public static boolean renameFileTo(File destFile, File tmp, Calendar headerLastModifiedDate) {
		boolean renamed = renameFileTo(destFile, tmp);
		if (renamed) {
			if (headerLastModifiedDate != null) {
				destFile.setLastModified(headerLastModifiedDate.getTimeInMillis());
			} else {
				destFile.setLastModified(0);
			}
		}
		return renamed;
	}

	public static boolean renameFileTo(File destFile, File tmp, long headerLastModifiedInMs) {
		boolean renamed = renameFileTo(destFile, tmp);
		if (renamed) {
			destFile.setLastModified(headerLastModifiedInMs);
		}
		return renamed;
	}

	/**
	 * Separate filename with this extention.
	 * @param filename as "price.txt.gz"
	 * @return an array with {"price", ".txt.gz"}
	 */
	public static String[] splitFileNameAndLongExtention(String filename) {
		if (Strings.isNullOrEmpty(filename)) {
			return null;
		}
		int filenameSize = filename.length();
		int idxOfExt = filename.indexOf('.');
		String name = filename.substring(0, idxOfExt);
		String ext = filename.substring(idxOfExt, filenameSize);
		String[] result = new String[] { name, ext };
		return result;
	}
	
	public static File getExtAliasFile(File file , String extAlias) {
		String filename = file.getName();
		String filenameAlias = getExtAliasFileName(filename, extAlias);
		File sortedFile = new File(file.getParentFile(), filenameAlias);
		return sortedFile;
	}
	/**
	 * Get an filename alias considering an another extension
	 * @param filename like price.zip
	 * @param extAlias like .txt
	 * @return price.txt
	 */
	public static String getExtAliasFileName(String filename, String extAlias) {
		String[] fileSplit = FileUtils.splitFileNameAndLongExtention(filename);
		String fileNameRoot = fileSplit[0];
		StringBuilder sb = new StringBuilder(filename.length() + extAlias.length() + 1);
		sb.append(fileNameRoot);
		if (extAlias.codePointAt(0)!='.'){
			sb.append('.');
		}
		sb.append(extAlias);
		return sb.toString();
	}

	public static File getSuffixedFile(File file, String fileSuffix, boolean forceCompress) {
		String fileName = file.getName();
		String sortedFileName = getSuffixedFileName(fileName, fileSuffix, forceCompress);
		File sortedFile = new File(file.getParentFile(), sortedFileName);
		return sortedFile;
	}

	/**
	 * Concat a filename with a prefix
	 * @param fileName like price.txt
	 * @param fileSuffix like -sort
	 * @param forceCompress as with gzip extention
	 * @return if (forceCompress==true) { price-sort.txt.gz } else price-sort.txt
	 */
	public static String getSuffixedFileName(String fileName, String fileSuffix, boolean forceCompress) {
		boolean isPreviousGzip = FileUtils.isGzipFileName(fileName);
		String[] fileSplit = FileUtils.splitFileNameAndLongExtention(fileName);
		String fileNameRoot = fileSplit[0];
		// Compute new Name
		StringBuilder sb = new StringBuilder(fileName.length() + fileSuffix.length());
		sb.append(fileNameRoot);
		if (!fileNameRoot.endsWith(fileSuffix)) {
			sb.append(fileSuffix);
		}
		sb.append(fileSplit[1]);
		if (forceCompress && !isPreviousGzip) {
			sb.append(FileUtils.EXT_GZIP);
		}
		return sb.toString();
	}

	/**
	 * Concat a path and a filename with managing of presence of / separator. 
	 * @param filePath like /fusion/fr/login/
	 * @param fileName like  /price.zip 
	 * @return the String /fusion/fr/login/price.zip
	 */
	public static String getFullPath(String filePath, String fileName) {
		int filePathSize = filePath.length();
		int fileNameSize = fileName.length();
		boolean isFilePathAsSeparator = filePath.codePointAt(filePathSize - 1) == SEPARATOR_URL;
		boolean isFileNameAsSeparator = fileName.codePointAt(0) == SEPARATOR_URL;
		StringBuilder sb = new StringBuilder(filePathSize + fileName.length() + 1);
		sb.append(filePath);
		if (isFilePathAsSeparator && isFileNameAsSeparator) {
			// Suppress one of 2 separator
			sb.append(fileName, 1, fileNameSize);
		} else if (!isFilePathAsSeparator && !isFileNameAsSeparator) {
			sb.append(SEPARATOR_URL);
			sb.append(fileName);
		} else {
			sb.append(fileName);
		}
		return sb.toString();
	}
}
