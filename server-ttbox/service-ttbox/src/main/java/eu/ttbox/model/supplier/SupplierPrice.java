package eu.ttbox.model.supplier;

import java.math.BigDecimal;

import javax.persistence.Cacheable;
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
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import eu.ttbox.model.product.Product;
import eu.ttbox.model.salespoint.Holding;

@Entity
@Cacheable
@Table(name = "B_SUPPLIER_PRICE", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "PRODUCT_ID", "SUPPLIER_ID", "HOLDING_ID" }) //
		, @UniqueConstraint(columnNames = { "SUPPLIER_PRODUCT_ID", "SUPPLIER_ID", "HOLDING_ID" }) //
})

public class SupplierPrice {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqSupplierPrice")
	@SequenceGenerator(name = "seqSupplierPrice", sequenceName = "SEQ_SUPPLIER_PRICE", initialValue = 100)
	@Column(name = "ID", nullable = false, unique = true, scale = 0)
	Long id;

	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	Product product;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "HOLDING_ID", nullable = false)
	Holding holding;

	@Size(max = 32)
	@Column(name = "SUPPLIER_ID", length = 32, nullable = true)
	String supplierId;

	@Size(max = 32)
	@Column(name = "SUPPLIER_PRODUCT_ID", length = 32, nullable = true)
	String supplierProductId;

	@Min(0)
	@Column(name = "SUPPLIER_PRICE_HT", nullable = true, precision = 20, scale = 2)
	BigDecimal supplierPriceHT;

	@NotNull
	@Size(min = 3, max = 3)
	@Column(name = "CURRENCY", length = 3, nullable = true)
	String currency;

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

	public Holding getHolding() {
		return holding;
	}

	public void setHolding(Holding holding) {
		this.holding = holding;
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

	public BigDecimal getSupplierPriceHT() {
		return supplierPriceHT;
	}

	public void setSupplierPriceHT(BigDecimal supplierPriceHT) {
		this.supplierPriceHT = supplierPriceHT;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
