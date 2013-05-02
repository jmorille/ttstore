package eu.ttbox.batch.core.reader.differential.impl;

import eu.ttbox.batch.core.reader.differential.DifferentialItem;
import eu.ttbox.batch.core.reader.differential.DifferentialItem.CUDStatus;
import eu.ttbox.batch.core.reader.differential.DifferentialItemFactory;

public class DifferentialItemVOFactory<MASTER, JOIN> implements DifferentialItemFactory<MASTER, JOIN> {

	@Override
	public DifferentialItem<MASTER, JOIN> createLine(CUDStatus status,
			MASTER master, JOIN join) {
		return new DifferentialItem<MASTER, JOIN>(status, master, join);
	}

}
