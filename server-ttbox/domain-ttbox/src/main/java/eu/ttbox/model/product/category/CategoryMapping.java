package eu.ttbox.model.product.category;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;

import org.hibernate.annotations.Parent;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;

import eu.ttbox.model.product.CatalogSrcEnum;

@Embeddable
public class CategoryMapping implements Serializable{

 
	private static final long serialVersionUID = 1L;
	
	public CategoryMapping() {
		super();
	}

	public CategoryMapping(Category category, CatalogSrcEnum source, String sourceId) {
		super();
		this.category = category;
		this.source = source;
		this.sourceId = sourceId;
	}

	@Parent
	@Column(name = "ID", nullable = false)
	private Category category;

	@Enumerated
	@Column(name = "SRC", nullable = false, length = 10)
	private CatalogSrcEnum source;

	@Column(name = "SRC_ID", nullable = false, length = 20)
	private String sourceId;

	@Column(name = "CAUSE", nullable = true, length = 20)
	private String cause;

	@Override
	public String toString() {
		ToStringHelper sb =  Objects.toStringHelper(this).add("categoryId", category == null ? null : category.getId())
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
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((sourceId == null) ? 0 : sourceId.hashCode());
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
		CategoryMapping other = (CategoryMapping) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (source != other.source)
			return false;
		if (sourceId == null) {
			if (other.sourceId != null)
				return false;
		} else if (!sourceId.equals(other.sourceId))
			return false;
		return true;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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
