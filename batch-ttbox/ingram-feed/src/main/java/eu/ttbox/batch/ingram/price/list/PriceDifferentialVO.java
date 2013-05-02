package eu.ttbox.batch.ingram.price.list;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.transform.FieldSet;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;

import eu.ttbox.batch.core.reader.differential.DifferentialItem.CUDStatus;
import eu.ttbox.batch.ingram.price.diff.PriceDifferentialItem;
import eu.ttbox.batch.ingram.product.ProductEntityDifferentialVO;
import eu.ttbox.model.catalog.Offer;
import eu.ttbox.model.salespoint.Salespoint;
import eu.ttbox.model.supplier.SupplierPrice;

public class PriceDifferentialVO {

	private static final Logger LOG = LoggerFactory.getLogger(PriceDifferentialVO.class);

	private String ingramId;

	private Date fileLastModified;

	private long fileSizeInBytes;

	private File supplierFile;

	private Salespoint salespoint;

	private List<PriceDifferentialItem<SupplierPrice, FieldSet>> creates = new ArrayList<PriceDifferentialItem<SupplierPrice, FieldSet>>(100000);

	public ListEntityDifferentialVO<SupplierPrice> supplierPrices = new ListEntityDifferentialVO<SupplierPrice>();

	public ProductEntityDifferentialVO products = new ProductEntityDifferentialVO();

	public ListEntityDifferentialVO<Offer> offers = new ListEntityDifferentialVO<Offer>();

	private int ignoreNothingToDo = 0;

	private int ignoreNoProduct = 0;

	public PriceDifferentialVO(String supplierId, File supplierFile, Salespoint salespoint) {
		super();
		this.ingramId = supplierId;
		this.supplierFile = supplierFile;
		this.salespoint = salespoint;
		this.fileLastModified = new Date(supplierFile.lastModified());
		this.fileSizeInBytes = supplierFile.length();
	}

	// *** *** *** Create *** *** *** //

	public List<PriceDifferentialItem<SupplierPrice, FieldSet>> getCreates() {
		return creates;
	}

	public int getCountCreates() {
		return creates.size();
	}

	public void addCreate(PriceDifferentialItem<SupplierPrice, FieldSet> creates) {
		this.creates.add(creates);
		this.supplierPrices.addCreate(creates.getMasterItem());
	}

	// *** *** *** Update *** *** *** //

	public List<SupplierPrice> getUpdates() {
		return this.supplierPrices.getUpdates();
	}

	public int getCountUpdates() {
		return supplierPrices.getCountUpdates();
	}

	public void addUpdate(SupplierPrice updates) {
		this.supplierPrices.addUpdate(updates);
	}

	// *** *** *** Delete *** *** *** //

	public List<SupplierPrice> getDeletes() {
		return supplierPrices.getDeletes();
	}

	public int getCountDeletes() {
		return supplierPrices.getCountDeletes();
	}

	public void addDelete(SupplierPrice deletes) {
		this.supplierPrices.addDelete(deletes);
	}

	// *** *** *** Business *** *** *** //

	public File getSupplierFile() {
		return supplierFile;
	}

	public Salespoint getSalespoint() {
		return salespoint;
	}

	public String getIngramId() {
		return ingramId;
	}

	public Date getFileLastModified() {
		return fileLastModified;
	}

	public long getFileSizeInBytes() {
		return fileSizeInBytes;
	}

	// *** *** *** Ignore *** *** *** //

	public int getIgnoreNoProduct() {
		return ignoreNoProduct;
	}

	public void addIgnoreNoProduct() {
		this.ignoreNoProduct++;
	}

	public int getIgnoreNothingToDo() {
		return ignoreNothingToDo;
	}

	public void addIgnoreNothingToDo() {
		this.ignoreNothingToDo++;
	}

	// *** *** *** Business *** *** *** //

	public int getCountRetrieve() {
		return getCountUpdates() + getCountCreates() + getIgnoreNothingToDo();
	}

	public void add(PriceDifferentialItem<SupplierPrice, FieldSet> item) {
		CUDStatus status = item.getStatus();
		switch (status) {
		case CREATE:
			// LOG.info("add create {}", item );
			addCreate(item);
			break;
		case UPDATE:
			addUpdate(item.getMasterItem());
			break;
		case DELETE:
			// LOG.warn("add delete {}", item );
			addDelete(item.getMasterItem());
			break;

		default:
			break;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ingramId == null) ? 0 : ingramId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PriceDifferentialVO other = (PriceDifferentialVO) obj;
		if (ingramId == null) {
			if (other.ingramId != null)
				return false;
		} else if (!ingramId.equals(other.ingramId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		ToStringHelper sb = Objects.toStringHelper(this) //
				.add("ingramId", ingramId);//
		if (supplierPrices.getCountCUD() > 0) {
			sb.add("supplierPrices", supplierPrices); //
		}
		if (products.getCountCUD() > 0) {
			sb.add("products", products); //
		}
		return sb.toString();
	}

}
