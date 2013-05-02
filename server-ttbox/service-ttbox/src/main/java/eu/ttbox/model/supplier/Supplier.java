package eu.ttbox.model.supplier;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Cacheable
@Table(name = "B_SUPPLIER" )
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqSupplier")
	@SequenceGenerator(name = "seqSupplier", sequenceName = "SEQ_SUPPLIER", allocationSize=20,  initialValue = 100)
	@GenericGenerator( name="seqSupplier", strategy="sequence-identity")
	@Column(name = "ID", nullable = false, unique = true, scale = 0)
	Long id;
  
	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;
  
 	@NotNull
	@Size(max = 20)
	@Column(name = "NAME", length = 20, nullable = true, unique=true)
 	String name;

 	
 	
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
		Supplier other = (Supplier) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this);
		sb.append("id", id);
		sb.append("name", name);
		sb.append("version", version);
		return sb.toString();
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

 	
	
}
