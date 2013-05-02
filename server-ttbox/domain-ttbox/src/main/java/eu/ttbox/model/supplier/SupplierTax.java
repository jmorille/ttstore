package eu.ttbox.model.supplier;

import java.math.BigDecimal;

import javax.persistence.Cacheable;
import javax.persistence.Column;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.google.common.base.Objects;

import eu.ttbox.model.IBoxPersistantModelObject;
import eu.ttbox.model.product.Product;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "B_SUPPLIER_TAX", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "PRODUCT_ID", "TAX_TYPE"  }) // 
}) 
public class SupplierTax  implements IBoxPersistantModelObject {

	@Id 
	@GeneratedValue(generator = "seqTax")
	@GenericGenerator(name = "seqTax", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQ_TAX"))
	@Column(name = "ID", nullable = false, unique = true, scale = 0)
	Integer id;
  
	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	Product product;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "TAX_TYPE", length = 8, nullable = true, unique=true)
	TaxGouvEnum type;
	
 	@NotNull
	@Size(max = 20)
	@Column(name = "TAX_VALUE", precision = 20, scale= 3 , nullable = false)
 	BigDecimal value;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SupplierTax other = (SupplierTax) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() { 
		return  Objects.toStringHelper(this).add("id", id).add("version", version).add("type", type).add("value", value) .toString(); 
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

	public TaxGouvEnum getType() {
		return type;
	}

	public void setType(TaxGouvEnum type) {
		this.type = type;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
 	
 	
 	
 	
}
