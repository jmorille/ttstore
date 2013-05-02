package eu.ttbox.batch.techdata.price;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.batch.item.file.transform.FieldSet;

import eu.ttbox.batch.core.reader.differential.DifferentialItem.CUDStatus;
import eu.ttbox.batch.techdata.price.diff.PriceDifferentialItem;
import eu.ttbox.model.supplier.SupplierPrice;

public class PriceDifferentialVO {
	
	private String techId;

	private Date fileLastModified;

	private long fileSizeInBytes;

	private List<PriceDifferentialItem<SupplierPrice, FieldSet>> creates = new ArrayList<PriceDifferentialItem<SupplierPrice, FieldSet>>(100000);

	private List<SupplierPrice> updates = new ArrayList<SupplierPrice>(100000);

	private List<SupplierPrice> deletes = new ArrayList<SupplierPrice>(100000);

	public PriceDifferentialVO(String techId,  Date fileLastModified, long fileSizeInBytes) {
		super();
		this.techId = techId;
		this.fileLastModified = fileLastModified;
		this.fileSizeInBytes = fileSizeInBytes;
	}

	public List<PriceDifferentialItem<SupplierPrice, FieldSet>> getCreates() {
		return creates;
	}
	public int getCountCreates() {
		return creates.size();
	}

	public void addCreate(PriceDifferentialItem<SupplierPrice, FieldSet> creates) {
		this.creates.add( creates);
	}

	public List<SupplierPrice> getUpdates() {
		return updates;
	}
	public int getCountUpdates() {
		return updates.size();
	}

	public void addUpdate(SupplierPrice updates) {
		this.updates.add( updates);
	}

	public List<SupplierPrice> getDeletes() {
		return deletes;
	}
	public int getCountDeletes() {
		return deletes.size();
	}

	public void addDelete(SupplierPrice deletes) {
		this.deletes.add( deletes);
	}

	public void add( PriceDifferentialItem<SupplierPrice, FieldSet> masterItem) {
		CUDStatus status = masterItem.getStatus();
		switch (status) {
		case CREATE:
			addCreate(masterItem );
			break;
		case UPDATE:
			addUpdate(masterItem.getMasterItem());
			break;
		case DELETE:
			addDelete(masterItem.getMasterItem());
			break;

		default:
			break;
		}
		
	}

	public String getTechId() {
		return techId;
	}

	public Date getFileLastModified() {
		return fileLastModified;
	}

	public long getFileSizeInBytes() {
		return fileSizeInBytes;
	}

	public int getCountRetrieve() {
		return getCountUpdates()+getCountCreates();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((techId == null) ? 0 : techId.hashCode());
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
		if (techId == null) {
			if (other.techId != null)
				return false;
		} else if (!techId.equals(other.techId))
			return false;
		return true;
	}

	


}
