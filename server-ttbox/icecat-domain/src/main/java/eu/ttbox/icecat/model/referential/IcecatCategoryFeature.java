package eu.ttbox.icecat.model.referential;

import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import eu.ttbox.icecat.model.IIcecatCategoryContainer;
import eu.ttbox.icecat.model.IIcecatPersistantModelObject;
import eu.ttbox.icecat.model.product.IcecatProductFeature;
import eu.ttbox.icecat.model.product.IcecatProductFeatureLocal;

/**
 * link between feature and category.
 * 
 * @author Jerome Morille
 * 
 */
@Entity
@Cacheable(true)
@Table(name = "CATEGORY_FEATURE", schema = "icecat", uniqueConstraints = { @UniqueConstraint(columnNames = { "CATID",
		"FEATURE_ID" }) })
// TODO @org.hibernate.annotations.Table(appliesTo = "CATEGORY_FEATURE", indexes
// = {
// @Index(name = "IDX_CATFEATURE_FEATUREID", columnNames = { "FEATURE_ID" }), //
// @Index(name = "IDX_CATFEATURE_FEATUREGROUPID", columnNames = {
// "CATEGORY_FEATURE_GROUP_ID" })
// })
public class IcecatCategoryFeature implements IIcecatPersistantModelObject, IIcecatCategoryContainer {

	private static final long serialVersionUID = 1348754889738160670L;

	// Fields
	@Id
	@Column(name = "CATEGORY_FEATURE_ID", nullable = false, length = 13)
	private Integer id;

	// AK
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FEATURE_ID", nullable = false)
	private IcecatFeature feature;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATID", nullable = false)
	private IcecatCategory category;

	//
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_FEATURE_GROUP_ID", nullable = false)
	private IcecatCategoryFeatureGroup categoryFeatureGroup;

	@NotNull
	@Column(name = "NO", length = 10, nullable = false)
	private Integer displayOrder;

	@NotNull
	@Column(name = "SEARCHABLE", length = 3, nullable = false)
	private Integer searchable;

	//@Lob @Basic(fetch=FetchType.EAGER) //@Type(type="string")
	@Column(name = "RESTRICTED_SEARCH_VALUES", length = 4000, nullable = true)
	private String restrictedSearchValues;

	@Column(name = "USE_DROPDOWN_INPUT", length = 3, nullable = true)
	private String useDropDownInput;

	@Column(name = "MANDATORY", nullable = false)
	private Integer mandatory;

	@Version
	@Column(name = "UPDATED", nullable = false)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date updated;

	@Column(name = "PATTERN", length = 255, nullable = true)
	private String pattern;

	@OneToMany(mappedBy = "categoryFeature", fetch = FetchType.LAZY, cascade = { CascadeType.ALL, CascadeType.REMOVE })
	private List<IcecatProductFeature> productFeatures;

	@OneToMany(mappedBy = "categoryFeature", fetch = FetchType.LAZY, cascade = { CascadeType.ALL, CascadeType.REMOVE })
	private List<IcecatProductFeatureLocal> productFeatureLocals;

	// /* */
	// @OneToMany(mappedBy="sid", fetch=FetchType.LAZY)
	// @MapKey(name="langid")
	// //JoinColumn(name = "SID")
	// private Map<IcecatLanguageEnum, IcecatVocabulary> names = null;
	//
	// @OneToMany(mappedBy="tid", fetch=FetchType.LAZY)
	// @MapKey(name="langid")
	// //JoinColumn(name = "TID")
	// private Map<IcecatLanguageEnum, IcecatTex> descriptions = null;
	//

	// Constructors
	/** default constructor. */
	public IcecatCategoryFeature() {
		super();
	}

	/**
	 * Constructeur par defaut permettant d'initialiser un id a n'utiliser que
	 * dans les tests.
	 */
	public IcecatCategoryFeature(Integer pId) {
		this();
		this.id = pId;
	}

	/**
	 * @return Returns the id.
	 */
	@Override
	public Integer getId() {
		return this.id;
	}

	public IcecatFeature getFeature() {
		return feature;
	}

	public void setFeature(IcecatFeature feature) {
		this.feature = feature;
	}

	public IcecatCategoryFeatureGroup getCategoryFeatureGroup() {
		return categoryFeatureGroup;
	}

	public void setCategoryFeatureGroup(IcecatCategoryFeatureGroup categoryFeatureGroup) {
		this.categoryFeatureGroup = categoryFeatureGroup;
	}

	@Override
	public IcecatCategory getCategory() {
		return category;
	}

	@Override
	public void setCategory(IcecatCategory category) {
		this.category = category;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public Integer getSearchable() {
		return searchable;
	}

	public void setSearchable(Integer searchable) {
		this.searchable = searchable;
	}

	public String getRestrictedSearchValues() {
		return restrictedSearchValues;
	}

	public void setRestrictedSearchValues(String restrictedSearchValues) {
		this.restrictedSearchValues = restrictedSearchValues;
	}

	public String getUseDropDownInput() {
		return useDropDownInput;
	}

	public void setUseDropDownInput(String useDropDownInput) {
		this.useDropDownInput = useDropDownInput;
	}

	public Integer getMandatory() {
		return mandatory;
	}

	public void setMandatory(Integer mandatory) {
		this.mandatory = mandatory;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public List<IcecatProductFeature> getProductFeatures() {
		return productFeatures;
	}

	public void setProductFeatures(List<IcecatProductFeature> productFeatures) {
		this.productFeatures = productFeatures;
	}

	public List<IcecatProductFeatureLocal> getProductFeatureLocals() {
		return productFeatureLocals;
	}

	public void setProductFeatureLocals(List<IcecatProductFeatureLocal> productFeatureLocals) {
		this.productFeatureLocals = productFeatureLocals;
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
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		IcecatCategoryFeature other = (IcecatCategoryFeature) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("[IcecatCategoryFeature:");
		buffer.append(" id: ");
		buffer.append(getId());
		buffer.append("]");
		return buffer.toString();
	}

}
