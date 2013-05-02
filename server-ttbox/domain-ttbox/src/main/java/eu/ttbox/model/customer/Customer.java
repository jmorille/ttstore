package eu.ttbox.model.customer;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Email;

import eu.ttbox.model.IBoxPersistantModelObject;
import eu.ttbox.model.customer.company.Company;
import eu.ttbox.model.salespoint.Salespoint;

@Entity
@Table(name = "B_CUSTOMER", uniqueConstraints = { 
		@UniqueConstraint(columnNames = { "MATRICULE" }) //
})

public class Customer implements IBoxPersistantModelObject {

	@Id
	@GeneratedValue(generator = "seqCustomer")
	@GenericGenerator(name = "seqCustomer", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQ_CUSTOMER")) 
	@Column(name = "ID", nullable = false, unique=true, scale=0)
	Integer id;
 

	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "SALESPOINT_ID", nullable = false)
	Salespoint salespoint;
	
	
	@Size(max = 100)
	@Column(name = "LASTNAME", length = 100, nullable = true)
	String lastname;

	@Size(max = 100)
	@Column(name = "FIRSTNAME", length = 100, nullable = true)
	String firstname;

	@Size(max = 20)
	@Column(name = "MATRICULE", length = 20, nullable = true)
	String matricule;

	@Email
	@Size(max = 100)
	@Column(name = "EMAIL", length = 100, nullable = true)
	String email;

	@Size(max = 20)
	@Column(name = "PASSWORD", length = 20, nullable = true)
	String password;

	/**
	 * Indicates whether the user's account has expired. An expired account
	 * cannot be authenticated.
	 * 
	 * @return <code>true</code> if the user's account is valid (ie
	 *         non-expired), <code>false</code> if no longer valid (ie expired)
	 */
	@Basic
	@Column(name = "SEC_ACCOUNT_NON_EXPIRED", nullable = false)
	boolean accountNonExpired = true;

	/**
	 * Indicates whether the user is locked or unlocked. A locked user cannot be
	 * authenticated.
	 * 
	 * @return <code>true</code> if the user is not locked, <code>false</code>
	 *         otherwise
	 */
	@Basic
	@Column(name = "SEC_ACCOUNT_NON_LOCKED", nullable = false)
	boolean accountNonLocked = true;

	/**
	 * Indicates whether the user's credentials (password) has expired. Expired
	 * credentials prevent authentication.
	 * 
	 * @return <code>true</code> if the user's credentials are valid (ie
	 *         non-expired), <code>false</code> if no longer valid (ie expired)
	 */
	@Basic
	@Column(name = "SEC_CREDENTIAL_NON_EXPIRED", nullable = false)
	boolean credentialsNonExpired = true;

	/**
	 * Indicates whether the user is enabled or disabled. A disabled user cannot
	 * be authenticated.
	 * 
	 * @return <code>true</code> if the user is enabled, <code>false</code>
	 *         otherwise
	 */
	@Basic
	@Column(name = "SEC_ENABLED", nullable = false)
	boolean enabled = true;

	
	@NotNull
	@JoinColumn(name = "COMPANY_ID", nullable = true)
	@ManyToOne(fetch = FetchType.LAZY)
	Company company;

	
	@Override
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

	public Salespoint getSalespoint() {
		return salespoint;
	}

	public void setSalespoint(Salespoint salespoint) {
		this.salespoint = salespoint;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	
	
}
