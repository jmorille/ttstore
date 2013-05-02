package eu.ttbox.feed.core.download.folder;

import java.io.File;
import java.util.Date;

public class LocalFolderByDateProvider {

	private File rootFolder;

	private static final String DEFAULT_FOLDER_PATTERN = "%1$tY-%1$tm-%1$td";

	private String localfolderPattern = DEFAULT_FOLDER_PATTERN;

	public void setRootFolder(File rootFolder) {
		this.rootFolder = rootFolder;
	}

	public void setLocalfolderPattern(String localfolderPattern) {
		this.localfolderPattern = localfolderPattern;
	}

	public File getLocalFolder() {
		return getLocalFolder(new Date());
	}

	public File getLocalFolder(Date date) {
		String folderName = String.format(localfolderPattern, date);
		File localFolder = new File(rootFolder, folderName);
		localFolder.mkdirs();
		return localFolder;
	}
}
