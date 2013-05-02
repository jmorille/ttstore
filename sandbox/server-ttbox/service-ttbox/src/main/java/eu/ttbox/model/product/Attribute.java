package eu.ttbox.model.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "B_ATTRIBUTE")
public class Attribute {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqAttribute")
	@SequenceGenerator(name = "seqAttribute", sequenceName = "SEQ_ATTRIBUTE", initialValue = 100)
	@Column(name = "ID", nullable = false, unique=true, scale=0)
	Long id;

	@Column(name = "ENTITY_UID", unique = true, nullable = false, updatable = false, length = 36)
	String uid;
 
	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;

	@Size(max = 50) 
	@Column(name = "NAME", length = 50, nullable = false)
	String name;

	
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


	public String getUid() {
		return uid;
	}


	public void setUid(String uid) {
		this.uid = uid;
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
