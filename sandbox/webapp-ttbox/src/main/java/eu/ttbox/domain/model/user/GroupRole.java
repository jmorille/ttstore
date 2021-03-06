package eu.ttbox.domain.model.user;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import eu.ttbox.domain.model.PersistantModelObject;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "B_GROUP", uniqueConstraints = { @UniqueConstraint(columnNames = { "NAME" }) })
// @SecondaryTables({ @SecondaryTable(name = "B_GROUP_ROLE", //
// pkJoinColumns = { @PrimaryKeyJoinColumn(name = "GROUP_ID",
// referencedColumnName = "ID") }, //
// uniqueConstraints = { @UniqueConstraint(columnNames = { "GROUP_ID", "ROLE_ID"
// }) } //
// ) //
// })
public class GroupRole implements PersistantModelObject<Long> {

	private static final long serialVersionUID = 7155986108775894636L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGroupRole")
	@SequenceGenerator(name = "seqGroupRole", sequenceName = "SEQ_GROUP_ROLE", initialValue = 100)
	@Column(name = "ID")
	private Long id;

	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;

	@Column(name = "NAME", length = 50, nullable = true)
	private String name;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "B_GROUP_ROLE", joinColumns = { @JoinColumn(name = "GROUP_ID") }, inverseJoinColumns = @JoinColumn(name = "ROLE_ID"), uniqueConstraints = { @UniqueConstraint(columnNames = {
			"GROUP_ID", "ROLE_ID" }) })
	private List<Role> roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setGroups(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
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
		GroupRole other = (GroupRole) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RoleGroup [id=" + id + ", name=" + name + ", version="
				+ version + "]";
	}

}
