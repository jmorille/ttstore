package eu.ttbox.icecat.model.product;

import java.util.Date;

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
import javax.validation.constraints.Size;

import eu.ttbox.icecat.model.IIcecatLangModelObject;
import eu.ttbox.icecat.model.IIcecatPersistantModelObject;
import eu.ttbox.icecat.model.IIcecatProductDependency;
import eu.ttbox.icecat.model.referential.IcecatLanguageEnum;

/**
 * language specific description.
 * 
 * @author Jerome Morille
 * 
 */
@Entity
@Table(name = "PRODUCT_SUMMARY_DESCRIPTION", schema = "icecat", //
uniqueConstraints = { @UniqueConstraint(columnNames = { "PRODUCT_ID", "LANGID" }) //
})
public class IcecatProductSummaryDescription implements IIcecatPersistantModelObject, IIcecatProductDependency,
		IIcecatLangModelObject {

	private static final long serialVersionUID = 3256720667469493817L;

	// Fields
	@Id
	@Column(name = "PRODUCT_SUMMARY_DESCRIPTION_ID", nullable = false, length = 13)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	private IcecatProduct product;

	@Column(name = "LANGID", length = 2, nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private IcecatLanguageEnum langid;

	// @Lob @Basic(fetch=FetchType.EAGER) //@Type(type="string")
	@Size(max = 4000)
	@Column(name = "SHORT_DESC", length = 4000, nullable = true)
	private String shortDescription;

	// @Lob @Basic(fetch=FetchType.EAGER) //@Type(type="string")
	@Size(max = 4000)
	@Column(name = "LONG_DESC", length = 4000, nullable = true)
	private String longDescription;

	@Version
	@Column(name = "UPDATED", nullable = false)
	private Date updated;

	// Constructors
	/** default constructor. */
	public IcecatProductSummaryDescription() {
		super();
	}

	/**
	 * Constructeur par defaut permettant d'initialiser un id a n'utiliser que
	 * dans les tests.
	 */
	public IcecatProductSummaryDescription(Integer pId) {
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

	// /**
	// * Create a Lucene term which identifies the document to update.
	// *
	// * @return the term which identifies the document to update
	// */
	// public Term getIdentifier() {
	// return LuceneProductFieldEnum.PRODUCT_BRAND_ID.getTerm(this.id);
	// }

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public IcecatProduct getProduct() {
		return product;
	}

	@Override
	public void setProduct(IcecatProduct product) {
		this.product = product;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Override
	public IcecatLanguageEnum getLangid() {
		return langid;
	}

	@Override
	public void setLangid(IcecatLanguageEnum langid) {
		this.langid = langid;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		IcecatProductSummaryDescription castedObj = (IcecatProductSummaryDescription) o;

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
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("[IcecatProductDescription:");
		buffer.append(" id: ");
		buffer.append(getId());
		buffer.append("]");
		return buffer.toString();
	}

}
