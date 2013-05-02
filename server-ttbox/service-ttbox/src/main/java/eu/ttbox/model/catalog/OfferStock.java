package eu.ttbox.model.catalog;

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
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;

import eu.ttbox.model.IBoxPersistantModelObject;
import eu.ttbox.model.product.Product;

 
@Entity
@Table(name = "B_OFFER_STOCK" ) 
public class OfferStock implements IBoxPersistantModelObject  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqOfferStock")
	@SequenceGenerator(name = "seqOfferStock", sequenceName = "SEQ_OFFER_STOCK", initialValue = 100)
	@Column(name = "ID", nullable = false, unique=true, scale=0)
	Long id;
 

	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;
  
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	Product product;

 	
	@Column(name = "STOCK", nullable = false )
	Long stockUnit;
 
	@Override
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this);
		sb.append("id", id);
		sb.append("version", version);
		sb.append("product", product);
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Long getStockUnit() {
		return stockUnit;
	}

	public void setStockUnit(Long stock) {
		this.stockUnit = stock;
	}
  
}
