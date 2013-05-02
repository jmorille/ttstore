package eu.ttbox.model.catalog;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Parameter;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;

import eu.ttbox.model.IBoxPersistantModelObject;
import eu.ttbox.model.pricing.Pricing;
import eu.ttbox.model.product.Product;
import eu.ttbox.model.salespoint.Salespoint;
import eu.ttbox.model.salespoint.SalespointBrand;
import eu.ttbox.model.salespoint.SalespointCategory;
import eu.ttbox.model.supplier.SupplierPrice;

@Entity
@Table(name = "S_OFFER", uniqueConstraints = { 
		@UniqueConstraint(columnNames = { "SALESPOINT_ID", "PRODUCT_ID" }) //
}) 
public class Offer implements IBoxPersistantModelObject {

 
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(generator = "seqOffer")
	@GenericGenerator(name = "seqOffer", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQ_OFFER"))  
	@Column(name = "id", nullable = false, unique = true, scale = 0)
	Integer id;
 
	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "SALESPOINT_ID", nullable = false)
	Salespoint salespoint;

 	@ManyToOne(fetch = FetchType.EAGER )
	@JoinColumn(name = "SBRAND_ID", nullable = false)
 	@Index(name = "IDX_OFFER_SBRAND")
 	SalespointBrand brand;

 	@ManyToOne(fetch = FetchType.EAGER )
	@JoinColumn(name = "SCATEGORY_ID", nullable = false)
 	@Index(name = "IDX_OFFER_SCATEGORY")
	SalespointCategory category;

 	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	Product product;
 
	@ManyToOne(fetch = FetchType.EAGER )
	@JoinColumn(name = "SUPPLIER_PRICE_ID", nullable = true)
	SupplierPrice supplierPrice;
	
 	@ManyToOne(fetch = FetchType.EAGER )
	@JoinColumn(name = "PRICING_ID", nullable = true)
	Pricing pricing;
 	

	@Override
	public String toString() {
		ToStringHelper sb =  Objects.toStringHelper(this);
		sb.add("id", id);
		sb.add("version", version);
		sb.add("product", product);
		sb.add("salespoint", salespoint);
		return sb.toString();
	}

	
	public BigDecimal getPriceHT() {
		BigDecimal priceHT = null;
		if (pricing!=null) {
			BigDecimal supplierPriceHT = BigDecimal.TEN;
			BigDecimal publicPriceHT = BigDecimal.TEN;
			priceHT = pricing.getPriceHT(supplierPriceHT, publicPriceHT);
		}
		return priceHT;
	}

	
	public BigDecimal getPriceTTC() {
		return BigDecimal.TEN;
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


	public SalespointBrand getBrand() {
		return brand;
	}


	public void setBrand(SalespointBrand brand) {
		this.brand = brand;
	}


	public SalespointCategory getCategory() {
		return category;
	}


	public void setCategory(SalespointCategory category) {
		this.category = category;
	}


}
