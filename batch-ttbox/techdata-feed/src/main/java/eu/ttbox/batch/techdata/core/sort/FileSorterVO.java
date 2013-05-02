package eu.ttbox.batch.techdata.core.sort;

import java.io.Serializable;
import java.util.List;

public class FileSorterVO  implements Serializable {

 
	private static final long serialVersionUID = 1L;

	String fileName;

	List<SortVO> items;

	public FileSorterVO(String fileName, List<SortVO> items) {
		super();
		this.fileName = fileName;
		this.items = items;
	}

	public String getFileName() {
		return fileName;
	}

	public List<SortVO> getItems() {
		return items;
	}
	
}
