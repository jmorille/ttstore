package eu.ttbox.model.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.google.common.base.Objects;

import eu.ttbox.model.IBoxPersistantModelObject;

@Entity
@Table(name = "B_PRODUCT_TAG" )

public class ProductTag  implements IBoxPersistantModelObject  {

	@Id
	@GeneratedValue(generator = "seqProductTag")
	@GenericGenerator(name = "seqProductTag", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQ_PRODUCT_TAG"))  
	@Column(name = "ID", nullable = false, unique=true, scale=0)
	Integer id;
 
	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	private Product product;
	
	@Size(max = 100) 
	@Column(name = "NAME", length = 100, nullable = false)
	String name;
	
	@Override
	public String toString() {
		return  Objects.toStringHelper(this).add("id", id).add("name", name).toString();  
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
 

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	
	
}
