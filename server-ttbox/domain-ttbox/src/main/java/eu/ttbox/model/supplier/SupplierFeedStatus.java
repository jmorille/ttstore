package eu.ttbox.model.supplier;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Length;

import eu.ttbox.model.IBoxPersistantModelObject;
 

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "S_LOG_SUPPLIER_PRICE", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"SUPPLIER_TYPE", "SUPPLIER_ID", "LAST_SYNCHRO" }) })
public class SupplierFeedStatus implements Serializable, IBoxPersistantModelObject  {
 

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "seqLogSupplierFeedStatus")
	@GenericGenerator(name = "seqLogSupplierFeedStatus", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQ_LOG_SUPPLIER_FEED_STAT"))
	@Column(name = "id", nullable = false, unique = true, scale = 0)
	private Integer id;
	
	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;

	@Column(name = "LAST_SYNCHRO", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date synchroDate;

	@Column(name = "FILE_LAST_MODIFIED", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fileLastModified;
	
	@Column(name = "FILE_SIZE_IN_BYTES", nullable = true)
	private long fileSizeInBytes;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "SUPPLIER_TYPE", length = 32, nullable = false)
	private SupplierEnum supplier;

	@NotNull
	@Length(max=32)
	@Column(name = "SUPPLIER_ID", length = 32, nullable = false)
	private String supplierId;

	@Column(name = "LAST_COUNT", nullable = true)
	private Integer count;
	
	@Column(name = "LAST_COUNT_CREATE", nullable = true)
	private Integer countCreate;

	@Column(name = "LAST_COUNT_UPDATE", nullable = true)
	private Integer countUpdate;

	@Column(name = "LAST_COUNT_DELETE", nullable = true)
	private Integer countDelete;
 
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public SupplierEnum getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierEnum supplier) {
		this.supplier = supplier;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Date getSynchroDate() {
		return synchroDate;
	}

	public void setSynchroDate(Date synchroDate) {
		this.synchroDate = synchroDate;
	}

	public Integer getCountCreate() {
		return countCreate;
	}

	public void setCountCreate(Integer countCreate) {
		this.countCreate = countCreate;
	}

	public Integer getCountUpdate() {
		return countUpdate;
	}

	public void setCountUpdate(Integer countUpdate) {
		this.countUpdate = countUpdate;
	}

	public Integer getCountDelete() {
		return countDelete;
	}

	public void setCountDelete(Integer countDelete) {
		this.countDelete = countDelete;
	}

	 
	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	
	 

	public Date getFileLastModified() {
		return fileLastModified;
	}

	public void setFileLastModified(Date fileLastModified) {
		this.fileLastModified = fileLastModified;
	}

	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("[SupplierFeedStatus:"); 
		buffer.append(" id ");
		buffer.append(id);
		buffer.append(" synchroDate ");
		buffer.append(synchroDate);
		buffer.append(" CUD( ");
		buffer.append(countCreate);
		buffer.append(" / ");
		buffer.append(countUpdate);
		buffer.append(" / ");
		buffer.append(countDelete);
		buffer.append(" ) ");
		buffer.append("]");
		return buffer.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof SupplierFeedStatus))
			return false;
		SupplierFeedStatus other = (SupplierFeedStatus) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

	public long getFileSizeInBytes() {
		return fileSizeInBytes;
	}

	public void setFileSizeInBytes(long fileSizeInBytes) {
		this.fileSizeInBytes = fileSizeInBytes;
	}

	
	
}
