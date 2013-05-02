package eu.ttbox.model.user;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import eu.ttbox.model.IBoxPersistantModelObject;

@Entity
@Cacheable
@Table(name = "B_GROUP", uniqueConstraints = { 
		@UniqueConstraint(columnNames = { "NAME" }) //
}) 
public class GroupRole implements IBoxPersistantModelObject  {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "seqGroupRole")
	@GenericGenerator(name = "seqGroupRole", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQ_GROUP_ROLE"))
	@Column(name = "id", nullable = false, unique=true, scale=0)
	Integer id; 

 
	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;

	@Basic
	@Size(max = 50)
	@Column(name = "NAME", length = 50, nullable = true)
	String name;

	@ManyToMany(cascade = { CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinTable(name = "B_GROUP_ROLE", joinColumns = { @JoinColumn(name = "GROUP_ID") }, inverseJoinColumns = @JoinColumn(name = "ROLE_ID"), uniqueConstraints = { @UniqueConstraint(columnNames = {
			"GROUP_ID", "ROLE_ID" }) })
	List<Role> roles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
