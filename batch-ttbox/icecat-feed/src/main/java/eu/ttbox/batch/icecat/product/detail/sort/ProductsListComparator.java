package eu.ttbox.batch.icecat.product.detail.sort;

import biz.icecat.files.v1.IcecatFile;

import java.io.Serializable;
import java.util.Comparator;

public class ProductsListComparator implements Serializable, Comparator<IcecatFile> {

	private static final long serialVersionUID = 3256720667469493817L;

	private ProductsListComparatorEnum fieldComparator;

	private boolean asc;

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	public ProductsListComparator(ProductsListComparatorEnum fieldComparator) {
		super();
		this.fieldComparator = fieldComparator;
		this.asc = fieldComparator.isAsc();
	}

	@SuppressWarnings("unchecked")
	@Override
	public int compare(IcecatFile o1, IcecatFile o2) {
		Comparable o1String = fieldComparator.getFieldValue(o1);
		Comparable o2String = fieldComparator.getFieldValue(o2);
		if (asc) {
			return o1String.compareTo(o2String);
		} else {
			return o2String.compareTo(o1String);
		}
	}

}
