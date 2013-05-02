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

/**
 * Product gallery - some more product images.
 * 
 * @author Jerome Morille
 * 
 */
@Entity
@Cacheable(true)
@Table(name = "PRODUCT_GALLERY", schema = "icecat", uniqueConstraints = { //
@UniqueConstraint(columnNames = { "PRODUCT_ID", "LINK" }) })
// TODO @org.hibernate.annotations.Table(appliesTo = "PRODUCT_GALLERY", indexes
// = {
// @Index(name = "IDX_PRODGALLERY_PRODUCT", columnNames = { "PRODUCT_ID" })
// })
public class IcecatProductGallery implements IIcecatPersistantModelObject, IIcecatProductDependency {

	private static final long serialVersionUID = 3424193161457140978L;

	// Fields
	@Id
	@Column(name = "ID", nullable = false, length = 13)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	private IcecatProduct product;

	@Column(name = "LINK", length = 255, nullable = false)
	private String link;

	@Column(name = "THUMB_LINK", length = 255, nullable = false)
	private String thumbLink;

	@Column(name = "HEIGHT", length = 10, nullable = true)
	private Integer height;

	@Column(name = "WIDTH", length = 10, nullable = true)
	private Integer width;

	@Column(name = "SIZE", length = 10, nullable = true)
	private Integer size;

	@Column(name = "QUALITY", length = 2, nullable = true)
	private Integer quality;

	@Version
	@Column(name = "UPDATED", nullable = false)
	private Date updated;

	// Constructors

	/** default constructor. */
	public IcecatProductGallery() {
		super();
	}

	/**
	 * Constructeur par defaut permettant d'initialiser un id a n'utiliser que
	 * dans les tests.
	 */
	public IcecatProductGallery(Integer pId) {
		this();
		this.id = pId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * eu.ttbox.icecat.model.product.IwsIcecatProductGallery
	 * #getId()
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

 
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

 
	public String getThumbLink() {
		return thumbLink;
	}

	public void setThumbLink(String thumbLink) {
		this.thumbLink = thumbLink;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

 
	public Integer getQuality() {
		return quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("[IcecatProductGallery:");
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

		IcecatProductGallery castedObj = (IcecatProductGallery) o;

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

}
