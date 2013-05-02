package eu.ttbox.model.supplier;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;

import eu.ttbox.model.IBoxPersistantModelObject;
import eu.ttbox.model.product.Product;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "B_STOCK", uniqueConstraints = { 
		@UniqueConstraint(columnNames = { "TYPE", "PRODUCT_ID" }) //
		,@UniqueConstraint(columnNames = { "PRODUCT_ID", "TYPE" }) //
}) 
public class SupplierStock implements IBoxPersistantModelObject {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(generator = "seqStock")
	@GenericGenerator(name = "seqStock", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQ_STOCK"))
	@Column(name = "id", nullable = false, unique = true, scale = 0)
	Integer id;

	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;

	@NotNull
	@Size(max = 20)
	@Column(name = "TYPE", length = 20, nullable = false)
	String type;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	Product product;
	
	@Column(name = "QUANTITY", nullable = true)
	Integer quantity;

	@Temporal(TemporalType.DATE)
	@Column(name = "NEXT_DELIVERY_DATE")
	Date nextDeliveryDate;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getNextDeliveryDate() {
		return nextDeliveryDate;
	}

	public void setNextDeliveryDate(Date nextDeliveryDate) {
		this.nextDeliveryDate = nextDeliveryDate;
	}

	
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() { 
		ToStringHelper sb =  Objects.toStringHelper(this).add("id", id).add("version", version); 
		sb.add("product", product);
		if (quantity != null) {
			sb.add("quantity", quantity);
		}
		if (nextDeliveryDate != null) {
			sb.add("nextDeliveryDate", nextDeliveryDate);
		}
		return sb.toString();
	}

}
