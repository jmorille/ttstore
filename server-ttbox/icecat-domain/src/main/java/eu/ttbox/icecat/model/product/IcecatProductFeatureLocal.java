package eu.ttbox.icecat.model.product;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import eu.ttbox.icecat.model.IIcecatLangModelObject;
import eu.ttbox.icecat.model.IIcecatPersistantModelObject;
import eu.ttbox.icecat.model.IIcecatProductDependency;
import eu.ttbox.icecat.model.referential.IcecatCategoryFeature;
import eu.ttbox.icecat.model.referential.IcecatFeature;
import eu.ttbox.icecat.model.referential.IcecatLanguageEnum;

/**
 * Product specs features are here. resolution of features/measures is via - >
 * category_feature->feature->measure.
 * 
 * @author Jerome Morille
 * 
 */
@Entity
@Cacheable(true)
@Table(name = "PRODUCT_FEATURE_LOCAL", schema = "icecat", uniqueConstraints = { //
@UniqueConstraint(columnNames = { "PRODUCT_ID", "CATEGORY_FEATURE_ID", "LANGID" }) })
// TODO @org.hibernate.annotations.Table(appliesTo = "PRODUCT_FEATURE_LOCAL",
// indexes = { //
// @Index(name = "IDX_PRODFEATLOC_CATFEATURE", columnNames = {
// "CATEGORY_FEATURE_ID" }) })
public class IcecatProductFeatureLocal implements IIcecatPersistantModelObject, IIcecatProductDependency,
		IIcecatLangModelObject {

	private static final long serialVersionUID = -8862477794756172265L;

	// Fields
	@Id
	@Column(name = "PRODUCT_FEATURE_LOCAL_ID", nullable = false, length = 13)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	private IcecatProduct product = null;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_FEATURE_ID", nullable = false)
	private IcecatCategoryFeature categoryFeature;

	//@Lob  @Basic(fetch=FetchType.EAGER) //@Type(type="string")
	@Column(name = "VALUE",length=4000, nullable = true)
	private String value;

	@Column(name = "LANGID", length = 2, nullable = true)
	@Enumerated(EnumType.ORDINAL)
	private IcecatLanguageEnum langid;

	@Version
	@Column(name = "UPDATED")
	private Date updated;

	// Constructors

	/** default constructor. */
	public IcecatProductFeatureLocal() {
		super();
	}

	/**
	 * Constructeur par defaut permettant d'initialiser un id a n'utiliser que
	 * dans les tests.
	 */
	public IcecatProductFeatureLocal(Integer pId) {
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
		buffer.append(" langId: ");
		buffer.append(getLangid());
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

		IcecatProductFeatureLocal castedObj = (IcecatProductFeatureLocal) o;

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

	@Override
	public IcecatLanguageEnum getLangid() {
		return langid;
	}

	@Override
	public void setLangid(IcecatLanguageEnum langid) {
		this.langid = langid;
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

}
