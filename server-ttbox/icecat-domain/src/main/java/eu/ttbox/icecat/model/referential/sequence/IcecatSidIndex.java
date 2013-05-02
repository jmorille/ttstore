package eu.ttbox.icecat.model.referential.sequence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import eu.ttbox.icecat.model.IIcecatPersistantModelObject;

@Entity
@Table(name = "SID_INDEX", schema = "icecat")
@SequenceGenerator(name = "seqSid", sequenceName = "SEQ_SID", schema = "icecat") 
public class IcecatSidIndex implements IIcecatPersistantModelObject // PersistantModelObject<Integer>  
{

	private static final long serialVersionUID = -3536171251162482795L;

	// Fields
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "SID", nullable = false, length = 13)
	private Integer id;

	@Column(name = "DUMMY", length = 13, nullable = true)
	private Integer dummy;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDummy() {
		return dummy;
	}

	public void setDummy(Integer dummy) {
		this.dummy = dummy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		IcecatSidIndex other = (IcecatSidIndex) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IcecatTidIndex [id=" + id + ", dummy=" + dummy + "]";
	}
 
	
	
}
