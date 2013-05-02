package eu.ttbox.batch.core.reader.join.impl;

import eu.ttbox.batch.core.reader.join.JoinItemFactory;


public class JoinItemVOFactory<MASTER, JOIN>  implements  JoinItemFactory<MASTER, JOIN> {

	@Override
	public JoinItemVO<MASTER, JOIN> createLine(MASTER master) {
		return new JoinItemVO<MASTER, JOIN>(master);
	}
}
