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

import eu.ttbox.model.IBoxPersistantModelObject;

@Entity
@Table(name = "B_PRODUCT_TYPE" )
public class ProductType  implements IBoxPersistantModelObject {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqProductType")
	@SequenceGenerator(name = "seqProductType", sequenceName = "SEQ_PRODUCT_TYPE", initialValue = 100)
	@Column(name = "ID", nullable = false, unique=true, scale=0)
	Long id;

	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;

	@Size(max = 100) 
	@Column(name = "NAME", length = 100, nullable = false)
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
