package eu.ttbox.model.product;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.granite.tide.data.DataPublishListener;

import eu.ttbox.core.listener.BoxUUIDInterceptor;
import eu.ttbox.model.IBoxPersistantModelObject;
import eu.ttbox.model.IBoxUUID;

@Entity
@Table(name = "B_PRODUCT")
@EntityListeners({ DataPublishListener.class, BoxUUIDInterceptor.class })
public class Product implements IBoxPersistantModelObject, IBoxUUID {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqProduct")
	@SequenceGenerator(name = "seqProduct", sequenceName = "SEQ_PRODUCT", initialValue = 100)
	@Column(name = "ID", nullable = false, unique = true, scale = 0)
	Long id;

	@Column(name = "ENTITY_UID", unique = true, nullable = false, updatable = false, length = 36)
	String uid;

	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;

	@Size(max = 100)
	@Column(name = "NAME", length = 100, nullable = false)
	String name;

	@Size(max = 2000)
	@Column(name = "DESCRIPTION", length = 2000, nullable = true)
	String description;

	@Size(max = 13)
	@Column(name = "EAN", length = 13, nullable = true)
	String ean;

	@Column(name = "PICTURE_URL", length = 512, nullable = true)
	String pictureUrl;

	@Column(name = "PRICE_HT", nullable = true)
	BigDecimal priceHT;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	List<ProductTag> tags;

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

	@Override
	public String getUid() {
		return uid;
	}

	@Override
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public BigDecimal getPriceHT() {
		return priceHT;
	}

	public void setPriceHT(BigDecimal priceHT) {
		this.priceHT = priceHT;
	}

	public List<ProductTag> getTags() {
		return tags;
	}

	public void setTags(List<ProductTag> tags) {
		this.tags = tags;
	}

}
