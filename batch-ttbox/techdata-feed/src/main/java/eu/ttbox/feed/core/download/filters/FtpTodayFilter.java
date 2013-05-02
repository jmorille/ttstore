package eu.ttbox.feed.core.download.filters;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.net.ftp.FTPFile;
import org.springframework.integration.file.filters.FileListFilter;

public class FtpTodayFilter implements FileListFilter<FTPFile> {

	boolean downloadOnlyToday = true;

	@Override
	public List<FTPFile> filterFiles(FTPFile[] files) {
		List<FTPFile> accepted = new ArrayList<FTPFile>();
		if (files != null) {
			// Compute Date Range
			Calendar begin = null;
			if (downloadOnlyToday) {
				begin = Calendar.getInstance();
				begin.set(Calendar.HOUR_OF_DAY, 0);
				begin.set(Calendar.MINUTE, 0);
				begin.set(Calendar.SECOND, 1);
				begin.set(Calendar.MILLISECOND, 0);
			}
			Calendar end = Calendar.getInstance();
			end.add(Calendar.MINUTE, -5);
			// Check Ftp File
			for (FTPFile file : files) {
				boolean addFile = true;
				Calendar fileDate = file.getTimestamp();
				if (addFile) {
					if (begin != null) {
						addFile = fileDate.compareTo(begin) >= 0;
					}
				}
				if (addFile) {
					if (end != null) {
						addFile = fileDate.compareTo(end) <= 0;
					}
				}
				if (addFile) {
					accepted.add(file);
				}
			}
		}
		return accepted;
	}

}
