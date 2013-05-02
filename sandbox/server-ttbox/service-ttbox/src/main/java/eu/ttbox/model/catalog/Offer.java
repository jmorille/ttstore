package eu.ttbox.model.catalog;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.granite.messaging.amf.io.util.externalizer.annotation.ExternalizedProperty;
import org.granite.tide.data.DataPublishListener;

import eu.ttbox.core.listener.BoxUUIDInterceptor;
import eu.ttbox.model.IBoxPersistantModelObject;
import eu.ttbox.model.IBoxUUID;
import eu.ttbox.model.pricing.Pricing;
import eu.ttbox.model.product.Product;
import eu.ttbox.model.salespoint.Salespoint;
import eu.ttbox.model.supplier.SupplierPrice;

@Entity
@Table(name = "B_OFFER", uniqueConstraints = { 
		@UniqueConstraint(columnNames = { "SALESPOINT_ID", "PRODUCT_ID" }) //
})
@EntityListeners({ DataPublishListener.class, BoxUUIDInterceptor.class })
public class Offer implements IBoxPersistantModelObject, IBoxUUID {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqOffer")
	@SequenceGenerator(name = "seqOffer", sequenceName = "SEQ_OFFER", initialValue = 100)
	@Column(name = "ID", nullable = false, unique = true, scale = 0)
	Long id;

	@Column(name = "ENTITY_UID", unique = true, nullable = false, updatable = false, length = 36)
	String uid;

	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "SALESPOINT_ID", nullable = false)
	Salespoint salespoint;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	Product product;

 
	@ManyToOne(fetch = FetchType.EAGER )
	@JoinColumn(name = "SUPPLIER_PRICE_ID", nullable = true)
	SupplierPrice supplierPrice;
	
 	@ManyToOne(fetch = FetchType.EAGER )
	@JoinColumn(name = "PRICING_ID", nullable = true)
	Pricing pricing;
 	
	@OneToOne(fetch = FetchType.LAZY)
	OfferStock stock;

	@Override
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this);
		sb.append("id", id);
		sb.append("version", version);
		sb.append("product", product);
		sb.append("salespoint", salespoint);
		return sb.toString();
	}

	@ExternalizedProperty
	public BigDecimal getPriceHT() {
		BigDecimal priceHT = null;
		if (pricing!=null) {
			BigDecimal supplierPriceHT = BigDecimal.TEN;
			BigDecimal publicPriceHT = BigDecimal.TEN;
			priceHT = pricing.getPriceHT(supplierPriceHT, publicPriceHT);
		}
		return priceHT;
	}

	@ExternalizedProperty
	public BigDecimal getPriceTTC() {
		return BigDecimal.TEN;
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
 

	public Salespoint getSalespoint() {
		return salespoint;
	}

	public void setSalespoint(Salespoint salespoint) {
		this.salespoint = salespoint;
	}

	public SupplierPrice getSupplierPrice() {
		return supplierPrice;
	}

	public void setSupplierPrice(SupplierPrice supplierPrice) {
		this.supplierPrice = supplierPrice;
	}

	public Pricing getPricing() {
		return pricing;
	}

	public void setPricing(Pricing pricing) {
		this.pricing = pricing;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getPictureUrl() {
		return getProduct().getPictureUrl();
	}

	public OfferStock getStock() {
		return stock;
	}

	public void setStock(OfferStock stock) {
		this.stock = stock;
	}

}
