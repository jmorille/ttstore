package eu.ttbox.batch.techdata.price.diff;

import org.springframework.stereotype.Service;

import eu.ttbox.batch.core.reader.differential.DifferentialItem.CUDStatus;
import eu.ttbox.batch.core.reader.differential.DifferentialItemFactory;

@Service("priceTechdataDifferentialItemVOFactory")
public class PriceDifferentialItemVOFactory<MASTER, JOIN> implements DifferentialItemFactory<MASTER, JOIN> {

	@Override
	public PriceDifferentialItem<MASTER, JOIN> createLine(CUDStatus status,
			MASTER master, JOIN join) {
		return new PriceDifferentialItem<MASTER, JOIN>(status, master, join);
	}

}
