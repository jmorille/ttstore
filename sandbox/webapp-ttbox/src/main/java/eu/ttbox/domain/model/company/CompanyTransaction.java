package eu.ttbox.domain.model.company;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;

import eu.ttbox.domain.model.IBoxPersistantModelObject;
import eu.ttbox.domain.model.audit.AuditTrail;

@SuppressWarnings("serial")
@Entity
@Table(name = "B_COMPANY_TRANSACTION")
public class CompanyTransaction implements IBoxPersistantModelObject {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	Long id;

	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;

	@Embedded
	AuditTrail auditTrail;

	@Length(max = 100)
	@NotNull
	@Column(name = "NAME", length = 100, nullable = false)
	String name;

  	@Enumerated(EnumType.ORDINAL)
	@Column(name = "TYPE", nullable = false)
	CompanyTransactionType type;
	
	@Column(name = "AMOUNT", nullable = true)
	BigDecimal amount;

	@Column(name = "PERCENT_PER_PRODUCT", nullable = true)
	BigDecimal percentPerProduct;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "PROFILE_ID", nullable = false)
	CompanyProfilTransaction profile;
	
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

	public AuditTrail getAuditTrail() {
		return auditTrail;
	}

	public void setAuditTrail(AuditTrail auditTrail) {
		this.auditTrail = auditTrail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

 	public CompanyTransactionType getType() {
		return type;
	}

	public void setType(CompanyTransactionType type) {
		this.type = type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getPercentPerProduct() {
		return percentPerProduct;
	}

	public void setPercentPerProduct(BigDecimal percentPerProduct) {
		this.percentPerProduct = percentPerProduct;
	}

	public CompanyProfilTransaction getProfile() {
		return profile;
	}

	public void setProfile(CompanyProfilTransaction profile) {
		this.profile = profile;
	}

	
	
}
