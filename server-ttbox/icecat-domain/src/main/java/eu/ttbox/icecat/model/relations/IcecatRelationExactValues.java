package eu.ttbox.icecat.model.relations;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

import eu.ttbox.icecat.model.IIcecatPersistantModelObject;

//@Entity
//@Cacheable(true)
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//@Table(name = "relation_exact_values", schema="icecat")
public class IcecatRelationExactValues implements IIcecatPersistantModelObject {

	private static final long serialVersionUID = 7073628268518655435L;

	@Id
	@Column(name = "exact_value", nullable = false, length = 13)
	private Integer id;

	@Column(name = "exact_value_text", length = 60, nullable = false)
	private String exactValueText;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExactValueText() {
		return exactValueText;
	}

	public void setExactValueText(String exactValueText) {
		this.exactValueText = exactValueText;
	}

	@Override
	public String toString() {
		return "IcecatRelationExactValues [id=" + id + ", exactValueText="
				+ exactValueText + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		IcecatRelationExactValues castedObj = (IcecatRelationExactValues) o;

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

