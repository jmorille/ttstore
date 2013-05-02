package eu.ttbox.model.user;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;

  
@Entity
@Table(name = "B_ROLE")
@Cacheable 
@NamedQuery(name = "Role.findAll", query = "select c from Role c")
public class Role implements Serializable, GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
 	@Column(name = "id", nullable = false, unique=true, scale=0, length = 16)
	String id; 

	@Basic
	@NotNull
	@Size(max = 10)
	@Column(name = "NAME", length = 10, nullable = false)
	String name;

	@Basic
	@NotNull
	@Size(max = 50)
	@Column(name = "DESCRIPTION", length = 50, nullable = false)
	String description;
    
	@ManyToMany(mappedBy="roles")
	List<GroupRole> groups;
	
 
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String name) {
		this.description = name;
	}


	public List<GroupRole> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupRole> groups) {
		this.groups = groups;
	}

	@Override
	public String toString() {
		ToStringHelper sb =  Objects.toStringHelper(this);
		sb.add("id", id);
		sb.add("name", name);
		return sb.toString();
	}
  
	@Override
	public String getAuthority() {
		return getId();
	}
}
