package eu.ttbox.batch.core.reader.aggregator.impl;

import eu.ttbox.batch.core.reader.aggregator.LineAggregatorItemFactory;


public class LineAggregatorItemVOFactory<MASTER> implements LineAggregatorItemFactory<MASTER>{

	@Override
	public LineAggregatorItemVO<MASTER> createLine() {
		return new LineAggregatorItemVO<MASTER>();
	}
}
