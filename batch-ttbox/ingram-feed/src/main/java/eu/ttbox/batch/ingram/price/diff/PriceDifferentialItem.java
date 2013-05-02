package eu.ttbox.batch.ingram.price.diff;

import eu.ttbox.batch.core.reader.differential.DifferentialItem;
import eu.ttbox.batch.ingram.price.list.PriceDifferentialVO;

public class PriceDifferentialItem<MASTER, JOIN> extends DifferentialItem<MASTER, JOIN> {

	private Integer productId;

	public PriceDifferentialVO parent;

	public PriceDifferentialItem(eu.ttbox.batch.core.reader.differential.DifferentialItem.CUDStatus status, MASTER referential, JOIN provider) {
		super(status, referential, provider);
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getSupplierId() {
		String supplierId = parent.getIngramId();
		return supplierId;
	}

}
