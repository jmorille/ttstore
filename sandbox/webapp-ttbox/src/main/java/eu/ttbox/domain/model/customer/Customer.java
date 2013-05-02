package eu.ttbox.domain.model.customer;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import eu.ttbox.domain.model.IBoxPersistantModelObject;
import eu.ttbox.domain.model.audit.AuditTrail;
import eu.ttbox.domain.model.audit.Auditable;
import eu.ttbox.domain.model.company.Company;

@SuppressWarnings("serial")
@Entity
@Table(name = "B_CUSTOMER", uniqueConstraints = { @UniqueConstraint(columnNames = { "MATRICULE" }) //
})
public class Customer implements IBoxPersistantModelObject, Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	Long id;

	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;

	@Embedded
	AuditTrail auditTrail;

	@Length(max=100)
	@Column(name = "LASTNAME", length=100,nullable = true)
	String lastname;

	@Length(max=100)
	@Column(name = "FIRSTNAME", length=100, nullable = true)
	String firstname;

	@Length(max=20)
	@Column(name = "MATRICULE", length=20, nullable = true)
	String matricule;

	@Length(max=100)
	@Email
	@Column(name = "EMAIL", length=100, nullable = true)
	String email;

	@Length(max=20)
	@Column(name = "PASSWORD", length=20, nullable = true)
	String password;

	@NotNull
	@Column(name = "COMPANY_ID", nullable = true)
	Company company;
	
	@Override
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

	@Override
	public AuditTrail getAuditTrail() {
		return auditTrail;
	}

	@Override
	public void setAuditTrail(AuditTrail auditTrail) {
		this.auditTrail = auditTrail;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

 


}
