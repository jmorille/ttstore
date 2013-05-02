package eu.ttbox.batch.techdata.price.diff;

import eu.ttbox.batch.core.reader.differential.DifferentialItem;

public class PriceDifferentialItem<MASTER, JOIN> extends DifferentialItem<MASTER, JOIN>{

	private Integer productId;
	
	public PriceDifferentialItem(eu.ttbox.batch.core.reader.differential.DifferentialItem.CUDStatus status, MASTER referential, JOIN provider) {
		super(status, referential, provider); 
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	
	
}
