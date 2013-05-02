package eu.ttbox.batch.core.download.ftp.listfilter;

import java.util.List;


public class ChainFileListFilter<F> implements FileListFilter<F> {

	private FileListFilter<F>[] filters;

	public ChainFileListFilter(FileListFilter<F>... filters) {
		super();
		this.filters = filters;
	}

	@Override
	public List<F> filterFiles(List<F> files) {
		List<F> acceptFiles = files;
		for (FileListFilter<F> filter : filters) {
			acceptFiles = filter.filterFiles(acceptFiles);
		}
		return acceptFiles;
	}
	
	@Override
	public String getEndToString() {
		StringBuilder sb = new StringBuilder(1024);
		boolean isNotFirst = false;
		for (FileListFilter<F> filter : filters) {
			String line = filter.getEndToString();
			if (isNotFirst) {
				sb.append("\n");
			}
			if (line!= null) {
				sb.append(line); 
				isNotFirst=true;
			}
		}
		return sb.toString();
	}
	
	
	
}  

