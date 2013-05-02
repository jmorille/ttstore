package eu.ttbox.batch.core.reader.aggregator;

public interface LineAggregatorItem<MASTER> {

	void addLine(MASTER joinItem);
}
