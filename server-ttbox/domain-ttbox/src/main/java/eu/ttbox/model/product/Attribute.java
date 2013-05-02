package eu.ttbox.model.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.google.common.base.Objects;

@Entity
@Table(name = "B_ATTRIBUTE")
public class Attribute {

	@Id
	@GeneratedValue(generator = "seqAttribute")
	@GenericGenerator(name = "seqAttribute", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQ_ATTRIBUTE")) 
	@Column(name = "ID", nullable = false, unique=true, scale=0)
	Long id;

 
 
	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;

	@Size(max = 50) 
	@Column(name = "NAME", length = 50, nullable = false)
	String name;

	
	@Override
	public String toString() {
		return  Objects.toStringHelper(this).add("id", id).add("name", name).toString();  
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
