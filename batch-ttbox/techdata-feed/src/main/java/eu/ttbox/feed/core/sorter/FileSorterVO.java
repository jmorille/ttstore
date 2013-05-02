package eu.ttbox.feed.core.sorter;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class FileSorterVO<I> implements Serializable {

	String fileName;

	List<? extends I> items;

	public FileSorterVO(String fileName, List<? extends I> items) {
		super();
		this.fileName = fileName;
		this.items = items;
	}

	public String getFileName() {
		return fileName;
	}

	public List<? extends I> getItems() {
		return items;
	}

}
