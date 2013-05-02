package eu.ttbox.batch.core.reader.aggregator;

public interface LineAggregatorItemFactory<MASTER> {

	LineAggregatorItem<MASTER> createLine() ;

}
