package eu.ttbox.model.product;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Parameter;

import com.google.common.base.Objects;

import eu.ttbox.model.IBoxPersistantModelObject;
import eu.ttbox.model.product.brand.Brand;
import eu.ttbox.model.product.category.Category;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "S_PRODUCT"
// , uniqueConstraints = { @UniqueConstraint(columnNames = { "EXT_ICECAT_ID" })
// //
// , @UniqueConstraint(columnNames = { "EXT_TECH_ID" }) //
// , @UniqueConstraint(columnNames = { "EXT_CNET_ID" }) //
// }
)
@NamedQueries({//
	@NamedQuery(name="product.findByTechdataProductId", query="from Product where extIds.techId = ?" ), //
	@NamedQuery(name="product.findByIngramProductId", query="from Product where extIds.ingramId = ?" )
	
})
public class Product implements IBoxPersistantModelObject {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "seqProduct")
	@GenericGenerator(name = "seqProduct", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQ_PRODUCT"))
	@Column(name = "id", nullable = false, unique = true, scale = 0)
	Integer id;

	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;

	@Size(max = 100)
	@Column(name = "NAME", length = 100, nullable = false)
	String name;

	@Size(max = 2000)
	@Column(name = "DESCRIPTION", length = 2000, nullable = true)
	String description;

	@Size(max = 13)
	@Index(name = "IDX_PRODUCT_EAN")
	@Column(name = "EAN", length = 13, nullable = true)
	String ean;

	@Size(max = 30)
	@Column(name = "PART_NUMBER", length = 30, nullable = true)
	@Index(name = "IDX_PRODUCT_PARTNUMB")
	String partNumber;

	@Size(max = 512)
	@Column(name = "PICTURE_URL", length = 512, nullable = true)
	String pictureUrl;

	@Embedded
	ProductExtId extIds = new ProductExtId();

	@Column(name = "PRICE_HT", nullable = true)
	BigDecimal priceHT;

	@Column(name = "IS_KIT", nullable = false)
	boolean kit = false;

	@Basic
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATION_DATE", nullable = true)
	Date articleCreationDate;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	List<ProductTag> tags;

	@ManyToOne(fetch = FetchType.LAZY, cascade= {CascadeType.PERSIST})
	@JoinColumn(name = "BRAND_ID", nullable = true)
	@Index(name = "IDX_PRODUCT_BRAND")
	Brand brand;

	@ManyToOne(fetch = FetchType.LAZY, cascade= {CascadeType.PERSIST})
	@JoinColumn(name = "CATEGORY_ID", nullable = true)
	@Index(name = "IDX_PRODUCT_CATEGORY")
	Category category;

	@ElementCollection(targetClass = ProductMapping.class, fetch = FetchType.LAZY)
	@Cascade(value = { org.hibernate.annotations.CascadeType.ALL })
	@JoinTable(name = "S_PRODUCT_SRC", joinColumns = @JoinColumn(name = "ID") //
	, uniqueConstraints = { @UniqueConstraint(columnNames = { "ID", "SRC", "SRC_ID" }, name = "PK_PRODUCT_SRC") })
	private Set<ProductMapping> srcs = new HashSet<ProductMapping>();

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public BigDecimal getPriceHT() {
		return priceHT;
	}

	public void setPriceHT(BigDecimal priceHT) {
		this.priceHT = priceHT;
	}

	public List<ProductTag> getTags() {
		return tags;
	}

	public void setTags(List<ProductTag> tags) {
		this.tags = tags;
	}

	private ProductExtId getExtIds() {
		return extIds;
	}
	public ProductExtId getExtIdsNotNull() {
		if (extIds==null) {
			extIds = new ProductExtId();
		}
		return extIds;
	}

	public void setExtIds(ProductExtId productExtId) {
		this.extIds = productExtId;
	}
	
	public String getExtIcecatId() { 
		String icecatId = null;
		if (getExtIds()!=null) {
			icecatId = getExtIds().getIcecatId();
		}
		return icecatId;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public Date getArticleCreationDate() {
		return articleCreationDate;
	}

	public void setArticleCreationDate(Date articleCreationDate) {
		this.articleCreationDate = articleCreationDate;
	}

	public boolean isKit() {
		return kit;
	}

	public void setKit(boolean kit) {
		this.kit = kit;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("id", id)
				.add("version", version)
				.add("name", name)
				.add("extIds", extIds)
				.toString();
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
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Set<ProductMapping> getSrcs() {
		return srcs;
	}

	public ProductMapping getSrc(CatalogSrcEnum source) {
		if (!getSrcs().isEmpty()) {
			for (ProductMapping mapping : getSrcs()) {
				if (mapping.getSource().equals(source)) {
					return mapping;
				}
			}
		}
		return null;
	}

	public void addSrc(CatalogSrcEnum source, String sourceId, String cause) {
		ProductMapping mapping  = new ProductMapping();  
		mapping.setProduct(this);
		mapping.setSource(source);
		mapping.setSourceId(sourceId);
		mapping.setCause(cause);
		// Test Mapping Denormat
		addSrc(mapping);
	}

	public void addSrc(ProductMapping mapping) { 
		if (this.srcs.contains(mapping)) {
			ProductMapping currentMapping = getSrc(mapping.getSource());
			if (!Objects.equal(currentMapping.getSourceId(), mapping.getSourceId())) {
				currentMapping.setSourceId(mapping.getSourceId());
				currentMapping.setCause(mapping.getCause());
				// Map The external Ids
				getExtIdsNotNull().manageMapping(mapping);
			}
		} else {
			mapping.setProduct(this);
			this.srcs.add(mapping);
			// Map The external Ids
			getExtIdsNotNull().manageMapping(mapping);
		}
		 
	}
}
