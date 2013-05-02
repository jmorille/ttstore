package eu.ttbox.batch.core.reader.differential;

import eu.ttbox.batch.core.reader.differential.DifferentialItem.CUDStatus;


public interface DifferentialItemFactory<MASTER, JOIN> {

	DifferentialItem<MASTER, JOIN> createLine(CUDStatus status, MASTER master,
			JOIN join);

}