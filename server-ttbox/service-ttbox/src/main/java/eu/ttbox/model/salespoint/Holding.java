package eu.ttbox.model.salespoint;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.ToStringBuilder;

import eu.ttbox.core.listener.AuditTrailInterceptor;
import eu.ttbox.model.IBoxPersistantModelObject;
import eu.ttbox.model.audit.AuditTrail;
import eu.ttbox.model.audit.Auditable;

@Entity
@Table(name = "B_HOLDING")
@EntityListeners({ AuditTrailInterceptor.class })
public class Holding implements IBoxPersistantModelObject, Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqHolding")
	@SequenceGenerator(name = "seqHolding", sequenceName = "SEQ_HOLDING", initialValue = 100)
	@Column(name = "ID", nullable = false, unique = true, scale = 0)
	Long id;
 

	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;

	@NotNull
	@Size(max = 50)
	@Column(name = "NAME", length = 50, nullable = false, unique = true)
	String name;

	@Embedded
	AuditTrail auditTrail;

	@OneToMany(mappedBy = "holding", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	List<Salespoint> salespoints;

	@Override
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this);
		sb.append("id", id);
		sb.append("name", name);
		sb.append("version", version);
		return sb.toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public AuditTrail getAuditTrail() {
		return auditTrail;
	}

	public void setAuditTrail(AuditTrail auditTrail) {
		this.auditTrail = auditTrail;
	}

	public List<Salespoint> getSalespoints() {
		return salespoints;
	}

	public void setSalespoints(List<Salespoint> salespoints) {
		this.salespoints = salespoints;
	}

}
