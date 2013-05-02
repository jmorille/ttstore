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
import eu.ttbox.model.product.brand.Brand;

/**
 *  round((pol.coeff * (feed.supplier_price - feed.surcharge) + feed.surcharge),2)
 *
 */
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "S_SALESPOINT_BRAND", uniqueConstraints = { @UniqueConstraint(columnNames = { "SALESPOINT_ID", "BRAND_ID", "NAME" }) //
})
public class SalespointBrand implements IBoxPersistantModelObject {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "seqSalespointBrand")
	@GenericGenerator(name = "seqSalespointBrand", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQ_SALESPOINT_BRAND"))
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
	@JoinColumn(name = "BRAND_ID", nullable = true)
	Brand brand;

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

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
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
		SalespointBrand other = (SalespointBrand) obj;
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
		//sb.add("salespoint", salespoint).add("brand", brand);
		sb.add("name", name).add("visible", visible);
		return sb.toString();
	}

}
