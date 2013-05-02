package eu.ttbox.batch.core.reader.join;

import eu.ttbox.batch.core.reader.join.impl.JoinItemVO;

public interface JoinItemFactory<MASTER, JOIN> {

	JoinItemVO<MASTER, JOIN> createLine(MASTER master);

}
