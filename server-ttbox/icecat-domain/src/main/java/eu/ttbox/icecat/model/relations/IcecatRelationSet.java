package eu.ttbox.icecat.model.relations;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import eu.ttbox.icecat.model.IIcecatPersistantModelObject;

//@Entity
//@Cacheable(true)
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//@Table(name = "relation_set", schema="icecat", uniqueConstraints = {  
//			@UniqueConstraint(columnNames = { "relation_set_id", "relation_rule_id"   }) 
//})
public class IcecatRelationSet implements IIcecatPersistantModelObject {

	private static final long serialVersionUID = 7073628268518655435L;

	@Id
	@Column(name = "id", nullable = false, length = 13)
	private Integer id;

	@Column(name = "relation_set_id", length = 13, nullable = false)
	private Integer relationSetId;
	

	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name="relation_rule_id", nullable = false)
	private IcecatRelationRule relationRule;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRelationSetId() {
		return relationSetId;
	}

	public void setRelationSetId(Integer relationSetId) {
		this.relationSetId = relationSetId;
	}

	public IcecatRelationRule getRelationRule() {
		return relationRule;
	}

	public void setRelationRule(IcecatRelationRule relationRule) {
		this.relationRule = relationRule;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((relationRule == null) ? 0 : relationRule.hashCode());
		result = prime * result
				+ ((relationSetId == null) ? 0 : relationSetId.hashCode());
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
		IcecatRelationSet other = (IcecatRelationSet) obj;
		if (relationRule == null) {
			if (other.relationRule != null)
				return false;
		} else if (!relationRule.equals(other.relationRule))
			return false;
		if (relationSetId == null) {
			if (other.relationSetId != null)
				return false;
		} else if (!relationSetId.equals(other.relationSetId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IcecatRelationSet [relationSetId=" + relationSetId
				+ ", relationRule=" + relationRule + "]";
	}
	
	
}
