package eu.ttbox.domain.model.company;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;

import eu.ttbox.domain.model.IBoxPersistantModelObject;
import eu.ttbox.domain.model.audit.AuditTrail;

@SuppressWarnings("serial")
@Entity
@Table(name = "B_COMPANY_PROFIL_TRANSAC")
public class CompanyProfilTransaction implements IBoxPersistantModelObject {

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

	@NotNull 
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "COMPANY_ID", nullable = false)
	Company company;

	@OneToMany(mappedBy = "profile", fetch = FetchType.LAZY, orphanRemoval = true, cascade = { CascadeType.ALL })
	List<CompanyTransaction> transactions;

		
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

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<CompanyTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<CompanyTransaction> transactions) {
		this.transactions = transactions;
	}

	public void addTransaction( CompanyTransaction  transaction) {
		if (getTransactions()==null) {
		  this.transactions = new ArrayList<CompanyTransaction>();
		}
		transaction.setProfile(this);
		this.transactions.add(transaction);
	}
	
}
