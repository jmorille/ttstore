package eu.ttbox.batch.core.download.ftp.listfilter;

import java.util.List;

public interface FileListFilter<F> {
 
    List<F> filterFiles(List<F> files);
 
    public String getEndToString() ;
}
