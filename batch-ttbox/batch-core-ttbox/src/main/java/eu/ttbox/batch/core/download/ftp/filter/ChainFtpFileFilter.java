package eu.ttbox.batch.core.download.ftp.filter;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;

public class ChainFtpFileFilter implements FTPFileFilter {

	FTPFileFilter[] filters;

	public ChainFtpFileFilter(FTPFileFilter... filters) {
		super();
		this.filters = filters;
	}

	@Override
	public boolean accept(FTPFile file) {
		boolean accept = true;
		for (FTPFileFilter filter : filters) {
			accept = filter.accept(file);
			if (!accept) {
				return false;
			}

		}
		return accept;
	}

}
