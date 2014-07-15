package eu.ttbox.batch.techdata.price.diff;

import eu.ttbox.batch.core.reader.differential.DifferentialItem.CUDStatus;
import eu.ttbox.batch.core.reader.differential.DifferentialItemFactory;
import org.springframework.stereotype.Service;

@Service("priceTechdataDifferentialItemVOFactory")
public class PriceDifferentialItemVOFactory<MASTER, JOIN> implements DifferentialItemFactory<MASTER, JOIN> {

	@Override
	public PriceDifferentialItem<MASTER, JOIN> createLine(CUDStatus status,
			MASTER master, JOIN join) {
		return new PriceDifferentialItem<MASTER, JOIN>(status, master, join);
	}

}
