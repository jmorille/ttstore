package eu.ttbox.batch.core.reader.aggregator.impl;

import com.google.common.base.Objects;
import eu.ttbox.batch.core.reader.aggregator.LineAggregatorItem;

import java.util.ArrayList;
import java.util.List;
 

public class LineAggregatorItemVO<MASTER> implements LineAggregatorItem<MASTER> {

	private List<MASTER> lines = new ArrayList<MASTER>();

	public LineAggregatorItemVO() {
		super();
	}

	public LineAggregatorItemVO(MASTER master) {
		super();
		lines.add(master);
	}

	public void addLine(MASTER joinItem) {
		lines.add(joinItem);	
	}

	public List<MASTER> getLines() {
		return lines;
	}

	public void setJoins(List<MASTER> source) {
		this.lines = source;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("lines", lines).toString();
	}

}
