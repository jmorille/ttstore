package eu.ttbox.batch.core.reader.multijoin.impl;

import eu.ttbox.batch.core.reader.multijoin.MultiJoinItemFactory;


public class MultiJoinItemVOFactory<MASTER>  implements  MultiJoinItemFactory<MASTER> {

	@Override
	public MultiJoinItemVO<MASTER> createLine(MASTER master, int maxCount) {
		return new MultiJoinItemVO(master, maxCount);
	}
}
