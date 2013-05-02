package eu.ttbox.batch.core.reader.differential;

import java.util.UUID;

import com.google.common.base.Objects;

public class DifferentialItem<MASTER, JOIN> {


	
	public enum CUDStatus {
		CREATE, UPDATE, DELETE;
	}

	private CUDStatus status;

	private JOIN joinItem;

	private MASTER masterItem;

	private CRUDStatusReport report;
	
	private long uuid;
	/**
	 * 
	 * @param status
	 *            The Line Status
	 * @param provider
	 *            The source of the data. Should be Null for a DELETE status.
	 * @param referential
	 *            The destination of the data. Should be Null for a CREATE
	 *            status.
	 */
	public DifferentialItem(CUDStatus status, MASTER referential, JOIN provider) {
		super();
		this.status = status;
		this.joinItem = provider;
		this.masterItem = referential;
		this.uuid = UUID.randomUUID().getMostSignificantBits();
	}

	public CUDStatus getStatus() {
		return status;
	}

	public void setStatus(CUDStatus status) {
		this.status = status;
	}

	public boolean isStatusDelete() {
		if (CUDStatus.DELETE.equals(status)) {
			return true;
		}
		return false;
	}

	public boolean isStatusCreate() {
		if (CUDStatus.CREATE.equals(status)) {
			return true;
		}
		return false;
	}
	public boolean isStatusUpdate() {
		if (CUDStatus.UPDATE.equals(status)) {
			return true;
		}
		return false;
	}
	public JOIN getJoinItem() {
		return joinItem;
	}

	public void setJoinItem(JOIN join) {
		this.joinItem = join;
	}

	public MASTER getMasterItem() {
		return masterItem;
	}

	public void setMasterItem(MASTER entity) {
		this.masterItem = entity;
	}

	 
	public CRUDStatusReport getReport() {
		return report;
	}

	public void setReport(CRUDStatusReport report) {
		this.report = report;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("uuid", uuid)
				.add("status", status)
//				.add("masterItem", masterItem)
				.add("joinItem", joinItem)
				.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (uuid ^ (uuid >>> 32));
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
		@SuppressWarnings("unchecked")
		DifferentialItem<MASTER, JOIN> other = (DifferentialItem<MASTER, JOIN>) obj;
		if (uuid != other.uuid)
			return false;
		return true;
	}
 
 

	
	
}
