package eu.ttbox.icecat.model.product;

import java.util.Map;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import eu.ttbox.icecat.model.IIcecatCategoryContainer;
import eu.ttbox.icecat.model.IIcecatPersistantModelObject;
import eu.ttbox.icecat.model.IIcecatTexContainer;
import eu.ttbox.icecat.model.IIcecatVocabularyContainer;
import eu.ttbox.icecat.model.IcecatHelper;
import eu.ttbox.icecat.model.referential.IcecatBrand;
import eu.ttbox.icecat.model.referential.IcecatCategory;
import eu.ttbox.icecat.model.referential.IcecatLanguageEnum;
import eu.ttbox.icecat.model.referential.IcecatTex;
import eu.ttbox.icecat.model.referential.IcecatVocabulary;

/**
 * product lines families per supplier.
 * 
 * @author Jerome Morille
 * 
 */
@Entity
@Cacheable(true)
@Table(name = "PRODUCT_FAMILY", schema = "icecat")
// TODO @org.hibernate.annotations.Table(appliesTo = "PRODUCT_FAMILY", indexes =
// {
// @Index(name = "IDX_PRODFAM_PARENTFAM", columnNames = { "PARENT_FAMILY_ID" }),
// @Index(name = "IDX_PRODFAM_CAT", columnNames = { "CATID" }),
// @Index(name = "IDX_PRODFAM_BRAND", columnNames = { "SUPPLIER_ID" })
// })
public class IcecatProductLine implements IIcecatPersistantModelObject,
		IIcecatTexContainer, IIcecatVocabularyContainer,
		IIcecatCategoryContainer {

	private static final long serialVersionUID = 7073628268518655435L;

	// Fields
	@Id
	@Column(name = "FAMILY_ID", nullable = false, length = 17)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_FAMILY_ID", nullable = true)
	private IcecatProductLine parentFamily;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATID", nullable = true)
	private IcecatCategory category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUPPLIER_ID", nullable = true)
	private IcecatBrand brand;

	@Column(name = "SID", nullable = false, length = 13)
	private Integer sid;

	@Column(name = "TID", nullable = false, length = 13)
	private Integer tid;

	@Column(name = "LOW_PIC", nullable = true, length = 255)
	private String lowDefPicture;

	@Column(name = "THUMB_PIC", nullable = true, length = 255)
	private String thumbPicture;

	@OneToMany(fetch = FetchType.LAZY)
	@MapKey(name = "langid")
	@JoinColumn(name = "SID", referencedColumnName = "SID")
	private Map<IcecatLanguageEnum, IcecatVocabulary> names = null;

	@OneToMany(fetch = FetchType.LAZY)
	@MapKey(name = "langid")
	@JoinColumn(name = "TID", referencedColumnName = "TID")
	private Map<IcecatLanguageEnum, IcecatTex> descriptions = null;

	// Constructors

	/** default constructor. */
	public IcecatProductLine() {
		super();
	}

	/**
	 * Constructeur par defaut permettant d'initialiser un id a n'utiliser que
	 * dans les tests.
	 */
	public IcecatProductLine(Integer pId) {
		this();
		this.id = pId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.ttbox.icecat.model.product.IwsIcecatProductLine #getId()
	 */
	@Override
	public Integer getId() {
		return this.id;
	}

	// /**
	// * Create a Lucene term which identifies the document to update.
	// *
	// * @return the term which identifies the document to update
	// */
	// public Term getIdentifier() {
	// return LuceneProductFieldEnum.PRODUCT_BRAND_ID.getTerm(this.id);
	// }

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("[IcecatProductLine:");
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

		IcecatProductLine castedObj = (IcecatProductLine) o;

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

 
	public IcecatProductLine getParentFamily() {
		return parentFamily;
	}

	public void setParentFamily(IcecatProductLine parentFamily) {
		this.parentFamily = parentFamily;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.ttbox.icecat.model.product.IwsIcecatProductLine #getCategory()
	 */
	@Override
	public IcecatCategory getCategory() {
		return category;
	}

	@Override
	public void setCategory(IcecatCategory category) {
		this.category = category;
	}

 
	public IcecatBrand getBrand() {
		return brand;
	}

	public void setBrand(IcecatBrand brand) {
		this.brand = brand;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

 
	public String getLowDefPicture() {
		return lowDefPicture;
	}

	public void setLowDefPicture(String lowDefPicture) {
		this.lowDefPicture = lowDefPicture;
	}
 
	public String getThumbPicture() {
		return thumbPicture;
	}

	public void setThumbPicture(String thumbPicture) {
		this.thumbPicture = thumbPicture;
	}

	@Override
	public Map<IcecatLanguageEnum, IcecatVocabulary> getNames() {
		return names;
	}

	@Override
	public void setNames(Map<IcecatLanguageEnum, IcecatVocabulary> names) {
		this.names = names;
	}

	@Override
	public Map<IcecatLanguageEnum, IcecatTex> getDescriptions() {
		return descriptions;
	}

	@Override
	public void setDescriptions(Map<IcecatLanguageEnum, IcecatTex> descriptions) {
		this.descriptions = descriptions;
	}

 
	public String getName() {
		return IcecatHelper.getName(getNames());
	}

 
	public String getDescription() {
		return IcecatHelper.getDescription(getDescriptions());
	}

}
