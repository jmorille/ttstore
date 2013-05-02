package eu.ttbox.batch.techdata.core.sort.writer;

import org.springframework.batch.item.file.transform.LineAggregator;

import eu.ttbox.batch.techdata.core.sort.SortVO;

public class SortVOLineAggregator implements LineAggregator<SortVO>{

	@Override
	public String aggregate(SortVO item) {
		return item.getLine();
	}

}
