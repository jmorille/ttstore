package eu.ttbox.batch.techdata.core.fs.filter;

import eu.ttbox.batch.core.download.ftp.FtpFileDownloadRequest;
import eu.ttbox.batch.core.download.ftp.listfilter.FileListFilter;
import org.apache.commons.net.ftp.FTPFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DedupByNameLowerCaseAndLastModifiedFileListFilter implements FileListFilter<FtpFileDownloadRequest> {

	@Override
	public List<FtpFileDownloadRequest> filterFiles(List<FtpFileDownloadRequest> files) {
		Map<String, FtpFileDownloadRequest> acceptedMap = new HashMap<String, FtpFileDownloadRequest>();
		if (files != null) { 
			for (FtpFileDownloadRequest fileRequest : files) {
				FTPFile file = fileRequest.getFtpFile();
				String key = file.getName().toLowerCase();
				if (!acceptedMap.containsKey(key)) {
					acceptedMap.put(key, fileRequest);
				} else {
					FtpFileDownloadRequest ftpFileRequest = acceptedMap.get(key);
					FTPFile ftpFile = ftpFileRequest.getFtpFile();
					// In Confilt keep the most recent
					if (ftpFile.getTimestamp().compareTo(file.getTimestamp()) < 0) {
						acceptedMap.put(key, ftpFileRequest);
					}
				}
			}
		}
		return new ArrayList<FtpFileDownloadRequest>(acceptedMap.values());
	}
	
	
	@Override
	public String getEndToString() { 
	  return null;
	}
}
