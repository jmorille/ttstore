package eu.ttbox.model.product;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;

import org.hibernate.annotations.Parent;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;

@Embeddable
public class ProductMapping implements Serializable{

 
	private static final long serialVersionUID = 1L;
	
	public ProductMapping() {
		super();
	}

	public ProductMapping(Product product, CatalogSrcEnum source, String sourceId) {
		super();
		this.product = product;
		this.source = source;
		this.sourceId = sourceId;
	}

	@Parent
	@Column(name = "ID", nullable = false)
	private Product product;

	@Enumerated
	@Column(name = "SRC", nullable = false, length = 10)
	private CatalogSrcEnum source;

	@Column(name = "SRC_ID", nullable = false, length = 20)
	private String sourceId;

	@Column(name = "CAUSE", nullable = true, length = 20)
	private String cause;

	@Override
	public String toString() {
		ToStringHelper sb =  Objects.toStringHelper(this).add("productId", product == null ? null : product.getId())
				.add("source", source).add("sourceId", sourceId);
		if (cause!=null) {
			sb.add("cause", cause);
		}
		return sb.toString();
	}

 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
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
		ProductMapping other = (ProductMapping) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (source != other.source)
			return false;
		return true;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public CatalogSrcEnum getSource() {
		return source;
	}

	public void setSource(CatalogSrcEnum source) {
		this.source = source;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}
	
	
	
}
