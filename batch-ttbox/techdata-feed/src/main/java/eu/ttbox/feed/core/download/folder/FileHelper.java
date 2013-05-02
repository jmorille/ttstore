package eu.ttbox.feed.core.download.folder;

public class FileHelper {

	private FileHelper() {
		super();
	}

	public static String getFileNameExt(String filename) {
		int indexOfExt = filename.indexOf(".");
		// String fname = filename.substring(0, indexOfExt);
		String fext = filename.substring(indexOfExt, filename.length());
		return fext;
	}

}
