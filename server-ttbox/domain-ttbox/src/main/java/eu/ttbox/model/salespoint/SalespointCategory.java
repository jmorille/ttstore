package eu.ttbox.model.salespoint;

import javax.persistence.Cacheable;
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
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;

import eu.ttbox.model.IBoxPersistantModelObject;
import eu.ttbox.model.product.category.Category;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "S_SALESPOINT_CATEGORY", uniqueConstraints = { @UniqueConstraint(columnNames = { "SALESPOINT_ID", "CATEGORY_ID", "NAME" }) //
})
public class SalespointCategory implements IBoxPersistantModelObject {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "seqSalespointCategory")
	@GenericGenerator(name = "seqSalespointCategory", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQ_SALESPOINT_CATEGORY"))
	@Column(name = "id", nullable = false, unique = true, scale = 0)
	Integer id;
	
	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "SALESPOINT_ID", nullable = false)
	Salespoint salespoint;
	
 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_ID", nullable = true)
	Category category;

	@Size(max = 25)
	@Column(name = "NAME", length = 25, nullable = true)
	String name; 
	
	@Column(name = "VISIBLE", length = 25, nullable = false)
	private boolean visible;
 

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Salespoint getSalespoint() {
		return salespoint;
	}


	public void setSalespoint(Salespoint salespoint) {
		this.salespoint = salespoint;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getVersion() {
		return version;
	}


	public void setVersion(Integer version) {
		this.version = version;
	}


	public boolean isVisible() {
		return visible;
	}


	public void setVisible(boolean visible) {
		this.visible = visible;
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
		SalespointCategory other = (SalespointCategory) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		ToStringHelper sb = Objects.toStringHelper(this).add("id", id).add("version", version);
		//sb.add("salespoint", salespoint).add("category", category);
		sb.add("name", name).add("visible", visible);
		return sb.toString();
	}
 
	
	
}
