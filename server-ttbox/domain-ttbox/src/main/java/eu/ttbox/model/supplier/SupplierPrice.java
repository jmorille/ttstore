package eu.ttbox.model.supplier;

import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;

import eu.ttbox.model.IBoxPersistantModelObject;
import eu.ttbox.model.pricing.Price;
import eu.ttbox.model.product.Product;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "S_SUPPLIER_PRICE", uniqueConstraints = { @UniqueConstraint(columnNames = { "PRODUCT_ID", "SUPPLIER_ID", "SUPPLIER_TYPE" }) //
		, @UniqueConstraint(columnNames = { "SUPPLIER_PRODUCT_ID", "SUPPLIER_ID", "SUPPLIER_TYPE" }) //
})
public class SupplierPrice implements IBoxPersistantModelObject {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "seqSupplierPrice")
	@GenericGenerator(name = "seqSupplierPrice", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQ_SUPPLIER_PRICE"))
	@Column(name = "id", nullable = false, unique = true, scale = 0)
	Integer id;

	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "SUPPLIER_TYPE", length = 1)
	private SupplierEnum supplier;

	@NotNull
	@Size(max = 32)
	@Column(name = "SUPPLIER_ID", length = 32, nullable = true)
	String supplierId;

	@NotNull
	@Size(max = 32)
	@Column(name = "SUPPLIER_PRODUCT_ID", length = 32, nullable = true)
	String supplierProductId;

	@Embedded
	@AttributeOverrides({ //
	@AttributeOverride(name = "amount", column = @Column(name = "SUPPLIER_PRICE_HT")),//
			@AttributeOverride(name = "currency", column = @Column(name = "SUPPLIER_PRICE_CURRENCY")) //
	})
	Price supplierPrice;

	@Min(value = 0)
	@Column(name = "SURCHARGE", precision = 8, scale = 2)
	private BigDecimal surcharge;


	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	// @Index(name="IDX_SUPPLIERPRICE_PRODUCT")
	Product product;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "VERSION_STATUS", length = 1)
	private VersionStatus versionStatus;
	
	@Column(name = "IS_PROMO" )
	boolean promo = false;
 	
	@Embedded
	SupplierPromoDetail promoDetail;
	//
	// @NotNull
	// @ManyToOne(fetch = FetchType.LAZY, optional = false)
	// @JoinColumn(name = "HOLDING_ID", nullable = false)
	// @Index(name = "IDX_SUPPLIERPRICE_HOLDING")
	// Holding holding;

	@Override
	public String toString() {
		ToStringHelper sb = Objects.toStringHelper(this)//
				.add("id", id).add("version", version)//
				.add("supplierPrice", supplierPrice) //
				.add("surcharge", surcharge)//
				.add("promo", promo); 
		return sb.toString();
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierProductId() {
		return supplierProductId;
	}

	public void setSupplierProductId(String supplierProductId) {
		this.supplierProductId = supplierProductId;
	}
 
	public SupplierEnum getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierEnum supplier) {
		this.supplier = supplier;
	}

	public Price getSupplierPrice() {
		return supplierPrice;
	}

	public void setSupplierPrice(Price supplierPrice) {
		this.supplierPrice = supplierPrice;
	}

	public BigDecimal getSurcharge() {
		return surcharge;
	}

	public void setSurcharge(BigDecimal surcharge) {
		this.surcharge = surcharge;
	}

	public VersionStatus getVersionStatus() {
		return versionStatus;
	}

	public void setVersionStatus(VersionStatus versionStatus) {
		this.versionStatus = versionStatus;
	}
	
	/* *** *** *** Promo *** *** *** */

	public boolean isPromo() {
		return promo;
	}

	public void setPromo(boolean promo) {
		this.promo = promo;
	}

	public SupplierPromoDetail getPromoDetail() {
		return promoDetail;
	}

	public void setPromoDetail(SupplierPromoDetail promoDetail) {
		this.promoDetail = promoDetail;
	}


	
	

}
