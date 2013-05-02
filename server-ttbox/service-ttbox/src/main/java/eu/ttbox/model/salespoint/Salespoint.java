package eu.ttbox.model.salespoint;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.ToStringBuilder;

import eu.ttbox.model.IBoxPersistantModelObject;

 
@Entity
@Table(name = "B_SALESPOINT" , uniqueConstraints = { 
		@UniqueConstraint(columnNames = { "HOLDING_ID", "NAME" }) //
})

public class Salespoint  implements IBoxPersistantModelObject  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqSalespoint")
	@SequenceGenerator(name = "seqSalespoint", sequenceName = "SEQ_SALESPOINT", initialValue = 100)
	@Column(name = "ID", nullable = false, unique=true, scale=0)
	Long id;
 
	
	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;
	 

	@NotNull
	@Size(max=50)
	@Column(name = "NAME", length=100,nullable = false)
	String name;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "HOLDING_ID", nullable = true)
	Holding holding;
 
	@Override
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this);
		sb.append("id", id);
		sb.append("name", name);
		sb.append("version", version);
		return sb.toString();
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
		Salespoint other = (Salespoint) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



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
 
 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Holding getHolding() {
		return holding;
	}

	public void setHolding(Holding holding) {
		this.holding = holding;
	}

  	
	
	
}
