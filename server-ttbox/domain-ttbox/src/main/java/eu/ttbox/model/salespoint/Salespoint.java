package eu.ttbox.model.salespoint;

import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.google.common.base.Objects;

import eu.ttbox.core.listener.AuditTrailInterceptor;
import eu.ttbox.model.IBoxPersistantModelObject;
import eu.ttbox.model.audit.AuditTrail;
import eu.ttbox.model.audit.Auditable;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "S_SALESPOINT", uniqueConstraints = { @UniqueConstraint(columnNames = { "NAME" }) //
})
@EntityListeners({ AuditTrailInterceptor.class })
public class Salespoint implements IBoxPersistantModelObject, Auditable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "seqSalespoint", strategy=GenerationType.SEQUENCE)
//	@GenericGenerator(name = "seqSalespoint", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQ_SALESPOINT"))
	@SequenceGenerator(name="seqSalespoint", sequenceName="SEQ_SALESPOINT", initialValue=100)
	@Column(name = "ID", nullable = false, unique = true, scale = 0)
	Integer id;

	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;

	@Embedded
	AuditTrail auditTrail;

	@NotNull
	@Size(min=2, max = 50)
	@Column(name = "NAME", length = 100, nullable = false)
	String name;

	@OneToMany(mappedBy = "salespoint", fetch = FetchType.LAZY)
	@OrderBy("supplier")
	private Set<SalespointSupplier> suppliers;

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("id", id).add("name", name).toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Salespoint other = (Salespoint) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<SalespointSupplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(Set<SalespointSupplier> suppliers) {
		this.suppliers = suppliers;
	}

	public AuditTrail getAuditTrail() {
		return auditTrail;
	}

	public void setAuditTrail(AuditTrail auditTrail) {
		this.auditTrail = auditTrail;
	}

}
