package eu.ttbox.batch.core.reader.multijoin;

import eu.ttbox.batch.core.reader.multijoin.impl.MultiJoinItemVO;

public interface MultiJoinItemFactory<MASTER> {

	MultiJoinItemVO<MASTER> createLine(MASTER master, int maxCount);

}
