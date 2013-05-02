package eu.ttbox.model.product.category;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.google.common.base.Objects;

import eu.ttbox.model.IBoxPersistantModelObject;
import eu.ttbox.model.product.CatalogSrcEnum;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "S_CATEGORY", uniqueConstraints = { @UniqueConstraint(columnNames = { "NAME" }) //
})
public class Category implements IBoxPersistantModelObject {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "seqCategory")
	@GenericGenerator(name = "seqCategory", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQ_CATEGORY")) 
	@Column(name = "id", nullable = false, unique = true, scale = 0)
	Integer id;

	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;

	@Size(max = 100)
	@Column(name = "NAME", length = 100, nullable = false)
	String name;

	@ElementCollection(targetClass = CategoryMapping.class, fetch = FetchType.LAZY)
	@Cascade(value = { CascadeType.ALL })
	@CollectionTable(name = "S_CATEGORY_SRC", joinColumns = @JoinColumn(name = "ID") //
	, uniqueConstraints = { @UniqueConstraint(columnNames = { "ID", "SRC", "SRC_ID" }, name = "PK_CATEGORY_SRC") })
	private Set<CategoryMapping> srcs = new HashSet<CategoryMapping>();

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("id", id).add("name", name).add("version", version).toString();
	}

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
		Category other = (Category) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<CategoryMapping> getSrcs() {
		return srcs;
	}

	public void setSrcs(Set<CategoryMapping> srcs) {
		this.srcs = srcs;
	}

	public void addSrc(CatalogSrcEnum source, String sourceId, String cause) {
		CategoryMapping mapping = new CategoryMapping();
		mapping.setCategory(this);
		mapping.setSource(source);
		mapping.setSourceId(sourceId);
		mapping.setCause(cause);
		addSrc(mapping);
	}

	public void addSrc(CategoryMapping mapping) {
		if (!this.srcs.contains(mapping)) {
			mapping.setCategory(this);
			this.srcs.add(mapping);
		}
	}

}
