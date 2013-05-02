package eu.ttbox.domain.model.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import eu.ttbox.domain.model.IBoxPersistantModelObject;
import eu.ttbox.domain.model.audit.AuditTrail;
import eu.ttbox.domain.model.audit.Auditable;

@SuppressWarnings("serial")
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "B_USER", uniqueConstraints = { @UniqueConstraint(columnNames = { "MATRICULE" }) //
})
/*
 * @SecondaryTables({ @SecondaryTable(name = "B_USER_GROUP", // pkJoinColumns= {
 * @PrimaryKeyJoinColumn(name="USER_ID", referencedColumnName="ID"),
 * 
 * @PrimaryKeyJoinColumn(name="GROUP_ID", referencedColumnName="ID")},
 * uniqueConstraints = { @UniqueConstraint(columnNames = { "USER_ID", "GROUP_ID"
 * }) } // ) // })
 */
public class User implements IBoxPersistantModelObject, Auditable, UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqUser")
	@SequenceGenerator(name = "seqUser", sequenceName = "SEQ_USER", initialValue = 100)
	@Column(name = "ID", nullable = false)
	Long id;

	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;

	@Embedded
	AuditTrail auditTrail;

	@Length(max = 100)
	@Column(name = "LASTNAME", length = 100, nullable = true)
	String lastname;

	@Length(max = 100)
	@Column(name = "FIRSTNAME", length = 100, nullable = true)
	String firstname;

	@Length(max = 20)
	@Column(name = "MATRICULE", length = 20, nullable = true)
	String matricule;

	@Length(max = 100)
	@Email
	@Column(name = "EMAIL", length = 100, nullable = true)
	String email;

	@Length(max = 20)
	@Column(name = "PASSWORD", length = 20, nullable = true)
	String password;

	/**
	 * Indicates whether the user's account has expired. An expired account
	 * cannot be authenticated.
	 * 
	 * @return <code>true</code> if the user's account is valid (ie
	 *         non-expired), <code>false</code> if no longer valid (ie expired)
	 */
	@Column(name = "SEC_ACCOUNT_NON_EXPIRED", nullable = false)
	boolean accountNonExpired = true;

	/**
	 * Indicates whether the user is locked or unlocked. A locked user cannot be
	 * authenticated.
	 * 
	 * @return <code>true</code> if the user is not locked, <code>false</code>
	 *         otherwise
	 */
	@Column(name = "SEC_ACCOUNT_NON_LOCKED", nullable = false)
	boolean accountNonLocked = true;

	/**
	 * Indicates whether the user's credentials (password) has expired. Expired
	 * credentials prevent authentication.
	 * 
	 * @return <code>true</code> if the user's credentials are valid (ie
	 *         non-expired), <code>false</code> if no longer valid (ie expired)
	 */
	@Column(name = "SEC_CREDENTIAL_NON_EXPIRED", nullable = false)
	boolean credentialsNonExpired = true;

	/**
	 * Indicates whether the user is enabled or disabled. A disabled user cannot
	 * be authenticated.
	 * 
	 * @return <code>true</code> if the user is enabled, <code>false</code>
	 *         otherwise
	 */
	@Column(name = "SEC_ENABLED", nullable = false)
	boolean enabled = true;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY, targetEntity = GroupRole.class)
	@JoinTable(name = "B_USER_GROUP", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = @JoinColumn(name = "GROUP_ID"), uniqueConstraints = { @UniqueConstraint(columnNames = {
			"USER_ID", "GROUP_ID" }) })
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<GroupRole> groups;

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

	public List<GroupRole> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupRole> groups) {
		this.groups = groups;
	}

	@Override
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this);
		sb.append("id", id);
		sb.append("lastname", lastname);
		sb.append("firstname", firstname);
		sb.append("version", version);
		return sb.toString();
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		List<GroupRole> groups = getGroups();
		for (GroupRole group : groups) {
			List<Role> roleUnit = group.getRoles();
			if (roleUnit != null && !roleUnit.isEmpty()) {
				roles.addAll(roleUnit);
			}
		}
		return roles;
	}

	@Override
	public String getUsername() {
		return getMatricule();
	}

}
