package eu.ttbox.batch.core.download.ftp.filter;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;

import java.util.Calendar;
import java.util.Date;

public class DateRangeFtpFileFilter implements FTPFileFilter {

	private Date startDate;

	private Date endDate;

	public DateRangeFtpFileFilter(Date startDate, Date endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}

	@Override
	public boolean accept(FTPFile file) {
		Date fileDate = file.getTimestamp().getTime();
		boolean addFile = true;
		if (addFile) {
			if (startDate != null) {
				addFile = fileDate.compareTo(startDate) >= 0;
			}
		}
		if (addFile) {
			if (endDate != null) {
				addFile = fileDate.compareTo(endDate) <= 0;
			}
		}
		return addFile;
	}

	public static Date getNowAtMidnight() {
		Calendar begin = Calendar.getInstance();
		begin.set(Calendar.HOUR_OF_DAY, 0);
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 1);
		begin.set(Calendar.MILLISECOND, 0);
		return begin.getTime();
	}

	public static Date getNowAddMinutes(int addMinutes) {
		Calendar end = Calendar.getInstance();
		end.add(Calendar.MINUTE, addMinutes);
		return end.getTime();
	}
	
	

		
}
