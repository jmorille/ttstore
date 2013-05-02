package eu.ttbox.model.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.granite.tide.data.DataPublishListener;

import eu.ttbox.core.listener.BoxUUIDInterceptor;
import eu.ttbox.model.IBoxPersistantModelObject;
import eu.ttbox.model.IBoxUUID;

@Entity
@Table(name = "B_PRODUCT_TAG" )
@EntityListeners({ DataPublishListener.class, BoxUUIDInterceptor.class })
public class ProductTag  implements IBoxPersistantModelObject, IBoxUUID  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqProductTag")
	@SequenceGenerator(name = "seqProductTag", sequenceName = "SEQ_PRODUCT_TAG", initialValue = 100)
	@Column(name = "ID", nullable = false, unique=true, scale=0)
	Long id;

	@Column(name = "ENTITY_UID", unique = true, nullable = false, updatable = false, length = 36)
	String uid;
 
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

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	
	
}
