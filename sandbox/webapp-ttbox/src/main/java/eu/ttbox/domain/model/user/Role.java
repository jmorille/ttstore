package eu.ttbox.domain.model.user;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.security.core.GrantedAuthority;

import eu.ttbox.domain.model.PersistantModelObject;

@SuppressWarnings("serial")
@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "B_ROLE")
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "Role")
@XmlType(name = "Role")
public class Role implements PersistantModelObject<String>, GrantedAuthority {

	@XmlElement(name = "authority")
	@Id
	@Column(name = "ID", nullable = false)
	String id;

	@Column(name = "NAME", nullable = false)
	String name;

	
	@ManyToMany(mappedBy="roles")
	List<GroupRole> groups;
	
	@Override
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
		sb.append("name", name);
		return sb.toString();
	}

	@Override
	public String getAuthority() {
		return getId();
	}
}
