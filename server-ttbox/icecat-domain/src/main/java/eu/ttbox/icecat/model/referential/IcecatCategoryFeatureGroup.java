package eu.ttbox.icecat.model.referential;

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
import javax.persistence.UniqueConstraint;

import eu.ttbox.icecat.model.IIcecatCategoryContainer;
import eu.ttbox.icecat.model.IIcecatPersistantModelObject;
import eu.ttbox.icecat.model.product.IcecatProductFeatureLocal;

/**
 * A group that holds a number of category features, to group them for display.
 * 
 * @author Jerome Morille
 * 
 */
@Entity
@Cacheable(true)
@Table(name = "CATEGORY_FEATURE_GROUP", schema = "icecat", uniqueConstraints = {//
@UniqueConstraint(columnNames = { "CATID", "FEATURE_GROUP_ID" }) //
})
public class IcecatCategoryFeatureGroup implements IIcecatPersistantModelObject, IIcecatCategoryContainer {

	private static final long serialVersionUID = -8862477794756172265L;

	// Fields
	@Id
	@Column(name = "CATEGORY_FEATURE_GROUP_ID", nullable = false, length = 13)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FEATURE_GROUP_ID", nullable = false)
	private IcecatFeatureGroup featureGroup;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATID", nullable = false)
	private IcecatCategory category;

	@Column(name = "NO", nullable = false, length = 15)
	private Integer displayOrder;

	
	@OneToMany(mappedBy = "categoryFeatureGroup", fetch = FetchType.LAZY, cascade = { CascadeType.ALL, CascadeType.REMOVE })
	private List<IcecatCategoryFeature> categoryFeatures;

	// Constructors
	/** default constructor. */
	public IcecatCategoryFeatureGroup() {
		super();
	}

	/**
	 * Constructeur par defaut permettant d'initialiser un id a n'utiliser que
	 * dans les tests.
	 */
	public IcecatCategoryFeatureGroup(Integer pId) {
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

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("[IcecatCategoryFeatureGroup:");
		buffer.append(" id: ");
		buffer.append(getId());
		buffer.append("]");
		return buffer.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	/**
	 * Returns <code>true</code> if this <code>Brand</code> is the same as the o
	 * argument.
	 * 
	 * @return <code>true</code> if this <code>Brand</code> is the same as the o
	 *         argument.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (!(o instanceof IcecatCategoryFeatureGroup)) {
			return false;
		}
		IcecatCategoryFeatureGroup castedObj = (IcecatCategoryFeatureGroup) o;
		return ((this.getId() == null ? castedObj.getId() == null : this.getId().equals(castedObj.getId())));
	}

	public IcecatFeatureGroup getFeatureGroup() {
		return featureGroup;
	}

	public void setFeatureGroup(IcecatFeatureGroup featureGroup) {
		this.featureGroup = featureGroup;
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

	public List<IcecatCategoryFeature> getCategoryFeatures() {
		return categoryFeatures;
	}

	public void setCategoryFeatures(List<IcecatCategoryFeature> categoryFeatures) {
		this.categoryFeatures = categoryFeatures;
	}
  
	
	
}
