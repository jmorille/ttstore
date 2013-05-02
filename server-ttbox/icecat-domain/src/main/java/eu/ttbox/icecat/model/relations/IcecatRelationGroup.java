package eu.ttbox.icecat.model.relations;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import eu.ttbox.icecat.model.IIcecatPersistantModelObject;

@Entity
@Cacheable(true)
@Table(name = "RELATION_GROUP", schema = "icecat")
public class IcecatRelationGroup implements IIcecatPersistantModelObject {

	private static final long serialVersionUID = 7073628268518655435L;

	// Fields
	@Id
	@Column(name = "relation_group_id", nullable = false, length = 13)
	private Integer id;

	@Column(name = "name", length = 255, nullable = false)
	private String name;

	@Column(name = "description", length = 255, nullable = false)
	private String description;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "relationGroup", cascade = { CascadeType.REMOVE }, orphanRemoval = true)
	private List<IcecatRelation> relations;

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public void setDescription(String description) {
		this.description = description;
	}

	public List<IcecatRelation> getRelations() {
		return relations;
	}

	public void setRelations(List<IcecatRelation> relations) {
		this.relations = relations;
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("[IcecatRelationGroup:");
		buffer.append(" id: ");
		buffer.append(getId());
		buffer.append(" name: ");
		buffer.append(name);
		buffer.append("]");
		return buffer.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		IcecatRelationGroup castedObj = (IcecatRelationGroup) o;

		if (id == null) {
			if (castedObj.uidInEquals == null) {
				return false;
			}
			if (uidInEquals == null) {
				uidInEquals = new java.rmi.dgc.VMID();

			}
			return (castedObj.uidInEquals.equals(uidInEquals));
		}

		return id.equals(castedObj.id);

	}

	@Override
	public int hashCode() {
		if (id == null) {
			if (uidInEquals == null) {
				uidInEquals = new java.rmi.dgc.VMID();
			}
			return uidInEquals.hashCode();
		}
		return id.hashCode();
	}

	@Transient
	private java.rmi.dgc.VMID uidInEquals;

}
