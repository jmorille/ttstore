package eu.ttbox.model.product;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Index;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;

@Embeddable
public class ProductExtId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Size(max = 16)
	@Index(name = "IDX_PRODUCT_EXT_ICECAT_ID")
	@Column(name = "EXT_ICECAT_ID", length = 16, nullable = true)
	String icecatId;

	@Size(max = 6)
	@Index(name = "IDX_PRODUCT_EXT_TECH_ID")
	@Column(name = "EXT_TECH_ID", length = 16, nullable = true)
	String techId;

	@Size(max = 6)
	@Index(name = "IDX_PRODUCT_EXT_INGRAM_ID")
	@Column(name = "EXT_INGRAM_ID", length = 16, nullable = true)
	String ingramId;

	@Size(max = 16)
	@Index(name = "IDX_PRODUCT_EXT_CNET_ID")
	@Column(name = "EXT_CNET_ID", length = 16, nullable = true)
	String cnetId;

	public void manageMapping(ProductMapping mapping) {
		if (CatalogSrcEnum.ICECAT.equals(mapping.getSource())) {
			setIcecatId(mapping.getSourceId());
		} else if (CatalogSrcEnum.TECHDATA.equals(mapping.getSource())) {
			setTechId(mapping.getSourceId());
		}
	}

	public ProductExtId() {
		super();
	}

	public String getIcecatId() {
		return icecatId;
	}

	public void setIcecatId(String icecatId) {
		this.icecatId = icecatId;
	}

	public String getTechId() {
		return techId;
	}

	public void setTechId(String techId) {
		this.techId = techId;
	}

	public String getCnetId() {
		return cnetId;
	}

	public void setCnetId(String cnetId) {
		this.cnetId = cnetId;
	}

	@Override
	public String toString() {
		ToStringHelper sb =  Objects.toStringHelper(this);
		if (techId!=null) {
			sb.add("techId", techId);
		}
		if (ingramId!=null) {
			sb.add("ingramId", ingramId);
		}
		if (icecatId!=null) {
			sb.add("icecatId", icecatId);
		}
		if (cnetId!=null) {
			sb.add("cnetId", cnetId);
		}
		return sb.toString();
	}

	public String getIngramId() {
		return ingramId;
	}

	public void setIngramId(String ingramId) {
		this.ingramId = ingramId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnetId == null) ? 0 : cnetId.hashCode());
		result = prime * result + ((icecatId == null) ? 0 : icecatId.hashCode());
		result = prime * result + ((ingramId == null) ? 0 : ingramId.hashCode());
		result = prime * result + ((techId == null) ? 0 : techId.hashCode());
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
		ProductExtId other = (ProductExtId) obj;
		if (cnetId == null) {
			if (other.cnetId != null)
				return false;
		} else if (!cnetId.equals(other.cnetId))
			return false;
		if (icecatId == null) {
			if (other.icecatId != null)
				return false;
		} else if (!icecatId.equals(other.icecatId))
			return false;
		if (ingramId == null) {
			if (other.ingramId != null)
				return false;
		} else if (!ingramId.equals(other.ingramId))
			return false;
		if (techId == null) {
			if (other.techId != null)
				return false;
		} else if (!techId.equals(other.techId))
			return false;
		return true;
	}

	 
}
