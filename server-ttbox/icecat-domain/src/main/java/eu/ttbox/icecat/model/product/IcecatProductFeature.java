package eu.ttbox.icecat.model.product;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import eu.ttbox.icecat.model.IIcecatPersistantModelObject;
import eu.ttbox.icecat.model.IIcecatProductDependency;
import eu.ttbox.icecat.model.referential.IcecatCategoryFeature;
import eu.ttbox.icecat.model.referential.IcecatFeature;

/**
 * Product specs features are here. resolution of features/measures is via - >
 * category_feature->feature->measure.
 * 
 * @author Jerome Morille
 * 
 */
@Entity
@Cacheable(true)
@Table(name = "PRODUCT_FEATURE", schema = "icecat", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"PRODUCT_ID", "CATEGORY_FEATURE_ID" }) })
// TODO @org.hibernate.annotations.Table(appliesTo = "PRODUCT_FEATURE", indexes
// = {
// @Index(name = "IDX_PRODFEAT_CATFEATURE", columnNames = {
// "CATEGORY_FEATURE_ID" })
// })
public class IcecatProductFeature implements IIcecatPersistantModelObject, IIcecatProductDependency {

	private static final long serialVersionUID = -8862477794756172265L;

	// Fields
	@Id
	@Column(name = "PRODUCT_FEATURE_ID", nullable = false, length = 13)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	private IcecatProduct product = null;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_FEATURE_ID", nullable = false)
	private IcecatCategoryFeature categoryFeature;

	//@Lob  @Basic(fetch=FetchType.EAGER) // @Type(type="string")
	@Column(name = "VALUE",length=4000, nullable = true)
	private String value;

	@Column(name = "PRESENTATION_VALUE", length = 4000, nullable = true)
	private String presentationValue;

	@Version
	@Column(name = "UPDATED")
	private Date updated;

	// Constructors

	/** default constructor. */
	public IcecatProductFeature() {
		super();
	}

	/**
	 * Constructeur par defaut permettant d'initialiser un id a n'utiliser que
	 * dans les tests.
	 */
	public IcecatProductFeature(Integer pId) {
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

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("[IcecatFeature:");
		buffer.append(" id: ");
		buffer.append(getId());
		buffer.append("]");
		return buffer.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		IcecatProductFeature castedObj = (IcecatProductFeature) o;

		if (id == null) {
			if (castedObj.uidInEquals == null) {
				return false;
			}
			if (uidInEquals == null) {
				uidInEquals = new java.rmi.dgc.VMID();

			}
			return (castedObj.uidInEquals.equals(uidInEquals));
		}

		return id.equals(castedObj.id);

	}

	@Override
	public int hashCode() {
		if (id == null) {
			if (uidInEquals == null) {
				uidInEquals = new java.rmi.dgc.VMID();
			}
			return uidInEquals.hashCode();
		}
		return id.hashCode();
	}
	
	@Transient
	private java.rmi.dgc.VMID uidInEquals;

	@Override
	public IcecatProduct getProduct() {
		return product;
	}

	@Override
	public void setProduct(IcecatProduct product) {
		this.product = product;
	}

	public IcecatCategoryFeature getCategoryFeature() {
		return categoryFeature;
	}

	public void setCategoryFeature(IcecatCategoryFeature categoryFeature) {
		this.categoryFeature = categoryFeature;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPresentationValue() {
		return presentationValue;
	}

	public void setPresentationValue(String presentationValue) {
		this.presentationValue = presentationValue;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public IcecatFeature getAttribute() {
		if (getCategoryFeature() != null) {
			return getCategoryFeature().getFeature();
		}
		return null;
	}

	public String getValueName() {
		return getPresentationValue();
	}

}
