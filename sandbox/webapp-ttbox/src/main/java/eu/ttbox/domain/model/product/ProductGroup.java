package eu.ttbox.domain.model.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import eu.ttbox.domain.model.IBoxPersistantModelObject;

@SuppressWarnings("serial")
@Entity
@Table(name = "B_PRODUCT_GROUP" )
public class ProductGroup  implements IBoxPersistantModelObject  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	Long id;

	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;

	@Length(max = 100)
	@NotBlank
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
