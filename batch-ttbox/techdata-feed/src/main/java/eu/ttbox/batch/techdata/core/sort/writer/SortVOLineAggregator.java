package eu.ttbox.batch.techdata.core.sort.writer;

import eu.ttbox.batch.techdata.core.sort.SortVO;
import org.springframework.batch.item.file.transform.LineAggregator;

public class SortVOLineAggregator implements LineAggregator<SortVO>{

	@Override
	public String aggregate(SortVO item) {
		return item.getLine();
	}

}
