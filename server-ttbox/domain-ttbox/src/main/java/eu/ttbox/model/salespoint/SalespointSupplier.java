package eu.ttbox.model.salespoint;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Length;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;

import eu.ttbox.model.IBoxPersistantModelObject;
import eu.ttbox.model.supplier.SupplierEnum;
import eu.ttbox.model.supplier.SupplierFeedStatus;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "S_SALESPOINT_SUPPLIER", uniqueConstraints = { @UniqueConstraint(columnNames = { "FK_SALESPOINT", "SUPPLIER_TYPE" }) })
public class SalespointSupplier implements IBoxPersistantModelObject {

	// ~ Static fields/initializers
	// ---------------------------------------------

	private static final long serialVersionUID = 1L;

	// ~ Instance fields
	// --------------------------------------------------------

	@Id
	@GeneratedValue(generator = "seqSalespointSupplier")
	@GenericGenerator(name = "seqSalespointSupplier", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQ_SALESPOINT_SUPPLIER"))
	@Column(name = "id", nullable = false, unique = true, scale = 0)
	private Integer id;

	@Version
	@Column(name = "VERSION", nullable = false)
	private Integer version;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_SALESPOINT", nullable = false)
	private Salespoint salespoint;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "SUPPLIER_TYPE", length = 32, nullable = false)
	private SupplierEnum supplier;

	@NotNull
	@Length(max = 6)
	@Column(name = "SUPPLIER_ID", length = 6, nullable = false)
	private String supplierId;

	@Length(max = 20)
	@Column(name = "SUPPLIER_PASSWORD", length = 20, nullable = true)
	private String password;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_SUPPLIER_PRICE_LOG", nullable = true)
	private SupplierFeedStatus lastFeedStatus;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "STATUS")
	private SalespointSupplierStatusEnum status = SalespointSupplierStatusEnum.ON;
 

	/**
	 * Default Constructor.
	 */
	public SalespointSupplier() {
		super();
	}

	/**
	 * Business Constructor.
	 */
	public SalespointSupplier(Salespoint salespoint, SupplierEnum supplier) {
		super();
		this.salespoint = salespoint;
		this.supplier = supplier;
	}

	public Integer getId() {
		return this.id;
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

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public Salespoint getSalespoint() {
		return salespoint;
	}

	public void setSalespoint(Salespoint salespoint) {
		this.salespoint = salespoint;
	}

	public SupplierFeedStatus getLastFeedStatus() {
		return lastFeedStatus;
	}

	public void setLastFeedStatus(SupplierFeedStatus lastFeedStatus) {
		this.lastFeedStatus = lastFeedStatus;
	}

	public SalespointSupplierStatusEnum getStatus() {
		return status;
	}

	public void setStatus(SalespointSupplierStatusEnum status) {
		this.status = status;
	}
 
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toString() {
		ToStringHelper sb = Objects.toStringHelper(this);
		sb.add("id", id).add("version", version).add("supplier", supplier).add("supplierId", supplierId);
		if (password != null) {
			sb.add("password", "********");
		} 
		sb.add("status", status).add("salespoint", salespoint);
		return sb.toString();
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
		if (!(obj instanceof SalespointSupplier))
			return false;
		SalespointSupplier other = (SalespointSupplier) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

}
