package eu.ttbox.batch.icecat.product.detail.sort;

import java.util.Comparator;

import biz.icecat.files.v1.IcecatFile;

public enum ProductsListComparatorEnum {

	ID() {
		@SuppressWarnings("unchecked")
		@Override
		public Comparable getFieldValue(IcecatFile mo) {
			return mo.getProductID();
		}
	},

	QUALITY() {
		@SuppressWarnings("unchecked")
		@Override
		public Comparable getFieldValue(IcecatFile mo) {
			return mo.getQuality();
		}
	},

	CATID() {
		@SuppressWarnings("unchecked")
		@Override
		public Comparable getFieldValue(IcecatFile mo) {
			return mo.getCatid();
		}
	},

	BRANDID() {
		@SuppressWarnings("unchecked")
		@Override
		public Comparable getFieldValue(IcecatFile mo) {
			return mo.getSupplierId();
		}
	},

	PARTNUMBER() {
		@SuppressWarnings("unchecked")
		@Override
		public Comparable getFieldValue(IcecatFile mo) {
			return mo.getProdID();
		}
	},

	MODELNAME() {
		@SuppressWarnings("unchecked")
		@Override
		public Comparable getFieldValue(IcecatFile mo) {
			return mo.getModelName();
		}
	},

	UPDATE() {
		@SuppressWarnings("unchecked")
		@Override
		public Comparable getFieldValue(IcecatFile mo) {
			return mo.getUpdated();
		}
	};

	private final boolean asc;

	ProductsListComparatorEnum() {
		this(true);
	}

	ProductsListComparatorEnum(boolean asc) {
		this.asc = asc;
	}

	@SuppressWarnings("unchecked")
	public Comparable getFieldValue(IcecatFile mo) {
		throw new RuntimeException("Not Implemented");
	}

	public Comparator<IcecatFile> getComparator() {
		Comparator<IcecatFile> comparator = new ProductsListComparator(this);
		return comparator;
	}

	public boolean isAsc() {
		return asc;
	}

}
