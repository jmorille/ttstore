package eu.ttbox.batch.ingram.product;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;

import eu.ttbox.batch.ingram.price.list.ListEntityDifferentialVO;
import eu.ttbox.model.product.Product;

public class ProductEntityDifferentialVO extends ListEntityDifferentialVO<Product> {

	private int ignoreLicence = 0;

	private int ignoreNotSellable = 0;

	// *** *** *** Ignore *** *** *** //

	public int getIgnoreLicence() {
		return ignoreLicence;
	}

	public void addIgnoreLicence() {
		this.ignoreLicence++;
	}

	public int getIgnoreNotSellable() {
		return ignoreNotSellable;
	}

	public void addIgnoreNotSellable() {
		this.ignoreNotSellable++;
	}

	// *** *** *** Business *** *** *** //

	@Override
	public String toString() {
		ToStringHelper sb = Objects.toStringHelper(this) //
				.add("", super.toString()) //
				.add("ignoreLicence", ignoreLicence) //
				.add("ignoreNotSellable", ignoreNotSellable); //
		return sb.toString();
	}

}
