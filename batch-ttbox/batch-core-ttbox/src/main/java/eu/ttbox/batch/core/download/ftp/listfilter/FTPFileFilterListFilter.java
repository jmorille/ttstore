package eu.ttbox.batch.core.download.ftp.listfilter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;


public class FTPFileFilterListFilter implements FileListFilter<FTPFile>  {

	private FTPFileFilter filter;

	private FTPFileFilterListFilter(FTPFileFilter filter) {
		super();
		this.filter = filter;
	}

	@Override
	public List<FTPFile> filterFiles(List<FTPFile> files) {
		List<FTPFile> acceptFiles = new ArrayList<FTPFile>(files.size());
		for (FTPFile file : files) {
			if (filter.accept(file)) {
				acceptFiles.add(file);
			}
		}
		return acceptFiles;
	}

	@Override
	public String getEndToString() {
		return null;
	}

	
}
