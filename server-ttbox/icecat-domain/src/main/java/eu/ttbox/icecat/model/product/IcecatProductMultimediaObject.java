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
import javax.persistence.Version;

import eu.ttbox.icecat.model.IIcecatLangModelObject;
import eu.ttbox.icecat.model.IIcecatPersistantModelObject;
import eu.ttbox.icecat.model.IIcecatProductDependency;
import eu.ttbox.icecat.model.referential.IcecatLanguageEnum;

/**
 * A place for storing multimedia data, like swf, animated gifs, etc.
 * 
 * @author Jerome Morille
 * 
 */
@Entity
@Cacheable(true)
@Table(name = "PRODUCT_MULTIMEDIA_OBJECT", schema = "icecat")
// TODO @org.hibernate.annotations.Table(appliesTo =
// "PRODUCT_MULTIMEDIA_OBJECT", indexes = {
// @Index(name = "IDX_MMO_PRODUCT", columnNames = { "PRODUCT_ID", "LANGID" })
// })
public class IcecatProductMultimediaObject implements IIcecatPersistantModelObject, IIcecatProductDependency,
		IIcecatLangModelObject {

	private static final long serialVersionUID = -7252105330595307957L;

	// Fields
	@Id
	@Column(name = "ID", nullable = false, length = 13)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	private IcecatProduct product;

	@Column(name = "LINK", length = 255, nullable = false)
	private String link = null;

	//@Lob  @Basic(fetch=FetchType.EAGER) //@Type(type="string")
	@Column(name = "SHORT_DESCR",length=4000, nullable = true)
	private String shortDescription = null;

	@Column(name = "LANGID", length = 2, nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private IcecatLanguageEnum langid = null;

	@Column(name = "LENGTH", length = 15, nullable = false)
	private Integer length = null;

	@Column(name = "TYPE", length = 255, nullable = true)
	private String type = null;

	@Column(name = "CONTENT_TYPE", length = 255, nullable = true)
	private String contentType = null;

	@Version
	@Column(name = "UPDATED", nullable = false)
	private Date updated = null;

	// Constructors

	/** default constructor. */
	public IcecatProductMultimediaObject() {
		super();
	}

	/**
	 * Constructeur par defaut permettant d'initialiser un id a n'utiliser que
	 * dans les tests.
	 */
	public IcecatProductMultimediaObject(Integer pId) {
		this();
		this.id = pId;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("[IcecatProductMultimediaObject:");
		buffer.append(" id: ");
		buffer.append(getId());
		buffer.append(" type: ");
		buffer.append(getType());
		buffer.append(" desc : ");
		buffer.append(getShortDescription());
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

		IcecatProductMultimediaObject castedObj = (IcecatProductMultimediaObject) o;

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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	@Override
	public IcecatLanguageEnum getLangid() {
		return langid;
	}

	@Override
	public void setLangid(IcecatLanguageEnum langid) {
		this.langid = langid;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
