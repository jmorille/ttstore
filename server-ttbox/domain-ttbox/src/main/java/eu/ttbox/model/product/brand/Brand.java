package eu.ttbox.model.product.brand;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "S_BRAND", uniqueConstraints = { @UniqueConstraint(columnNames = { "NAME" }) //
})
public class Brand  implements IBoxPersistantModelObject {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "seqBrand")
	@GenericGenerator(name = "seqBrand", strategy = "sequence-identity", parameters = { @Parameter(name = "sequence", value = "SEQ_BRAND"), 
			@Parameter(name = "initial_value", value = "100")}) 
//	@SequenceGenerator(name="seqBrand", sequenceName="SEQ_BRAND", initialValue=100)
	@Column(name = "id", nullable = false, unique = true, scale = 0)
	Integer id;

	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;

	@Size(max = 100)
	@Column(name = "NAME", length = 100, nullable = false)
	String name;
	
	@ElementCollection(targetClass=BrandMapping.class, fetch=FetchType.LAZY)
	@Cascade(value={CascadeType.ALL})
	@JoinTable(name="S_BRAND_SRC", joinColumns = @JoinColumn( name="ID") //
			, uniqueConstraints={  @UniqueConstraint(columnNames={"ID", "SRC", "SRC_ID"}, name="PK_BRAND_SRC"), @UniqueConstraint(columnNames={  "SRC", "SRC_ID"}, name="PK_BRAND_SRC_AK") }
	)
	private Set<BrandMapping> srcs = new HashSet<BrandMapping>();
	
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
		Brand other = (Brand) obj;
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

	public Set<BrandMapping> getSrcs() {
		return srcs;
	}

	public void setSrcs(Set<BrandMapping> srcs) {
		this.srcs = srcs;
	}

	public void addSrc(CatalogSrcEnum source, String sourceId, String cause) {
		BrandMapping mapping = new BrandMapping();
		mapping.setBrand(this);
		mapping.setSource(source);
		mapping.setSourceId(sourceId);
		mapping.setCause(cause);
		addSrc(mapping);
	}

	public void addSrc(BrandMapping mapping) {
		if (!this.srcs.contains(mapping)) {
			mapping.setBrand(this);
			this.srcs.add(mapping);
		}
	}
	
}
