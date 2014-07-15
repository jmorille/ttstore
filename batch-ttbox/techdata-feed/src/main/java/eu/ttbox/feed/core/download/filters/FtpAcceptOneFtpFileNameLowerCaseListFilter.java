package eu.ttbox.feed.core.download.filters;

import org.apache.commons.net.ftp.FTPFile;
import org.springframework.integration.file.filters.FileListFilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FtpAcceptOneFtpFileNameLowerCaseListFilter implements FileListFilter<FTPFile> {

	@Override
	public List<FTPFile> filterFiles(FTPFile[] files) {
		Map<String, FTPFile> acceptedMap = new HashMap<String, FTPFile>();
		if (files != null) {
			for (FTPFile file : files) {
				String key = file.getName().toLowerCase();
				if (!acceptedMap.containsKey(key)) {
					acceptedMap.put(key, file);
				} else {
					FTPFile ftpFile = acceptedMap.get(key);
					// In Confilt keep the most recent
					if (ftpFile.getTimestamp().compareTo(file.getTimestamp()) < 0) {
						acceptedMap.put(key, file);
					}
				}
			}
		}
		return new ArrayList<FTPFile>(acceptedMap.values());
	}

}
