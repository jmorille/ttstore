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
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import eu.ttbox.icecat.model.IIcecatCategoryContainer;
import eu.ttbox.icecat.model.IIcecatPersistantModelObject;
import eu.ttbox.icecat.model.IIcecatProductDependency;
import eu.ttbox.icecat.model.referential.IcecatCategory;

/**
 * Product related product cross-sell-relations or alternatives are stored here.
 * The type of link can be determenined by categories. e.g. if the categories
 * are the same = > link gives an 'alternative'. Categories are different =>
 * link is 'option'.
 * 
 * @author Jerome Morille
 * 
 */
@Entity
@Cacheable(true)
@Table(name = "PRODUCT_RELATED", schema = "icecat", uniqueConstraints = { //
@UniqueConstraint(columnNames = { "product_id", "rel_product_id" }) //
})
// TODO @org.hibernate.annotations.Table(appliesTo = "PRODUCT_RELATED", indexes
// = { //
// @Index(name = "IDX_PRODUCT_RELATED_INV", columnNames = { "rel_product_id" })
// //
// })
public class IcecatProductRelated implements IIcecatPersistantModelObject, IIcecatProductDependency,
		IIcecatCategoryContainer {

	private static final long serialVersionUID = 7073628268518655435L;

	// Fields
	@Id
	@Column(name = "product_related_id", nullable = false, length = 17)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false)
	private IcecatProduct product;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rel_product_id", nullable = false)
	private IcecatProduct productRelated;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATID", nullable = true)
	private IcecatCategory category;

	@Column(name = "UPDATED", nullable = false)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date updated = null;

	@Column(name = "PREFERRED", nullable = true)
	private Integer preferred;

	@Column(name = "REVERSED", nullable = true)
	private Integer reversed;

	// Constructors

	/** default constructor. */
	public IcecatProductRelated() {
		super();
	}

	/**
	 * Constructeur par defaut permettant d'initialiser un id a n'utiliser que
	 * dans les tests.
	 */
	public IcecatProductRelated(Integer pId) {
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
		buffer.append("[IcecatProductRelated:");
		buffer.append(" id: ");
		buffer.append(getId());
		buffer.append(" Product Id : ");
		if (this.productRelated != null) {
			buffer.append(this.product.getId());
		}
		buffer.append(" Product Related Id : ");
		if (this.productRelated != null) {
			buffer.append(this.productRelated.getId());
		}

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

		IcecatProductRelated castedObj = (IcecatProductRelated) o;

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
	public IcecatCategory getCategory() {
		return category;
	}

	@Override
	public void setCategory(IcecatCategory category) {
		this.category = category;
	}

	@Override
	public IcecatProduct getProduct() {
		return product;
	}

	@Override
	public void setProduct(IcecatProduct product) {
		this.product = product;
	}

	public IcecatProduct getProductRelated() {
		return productRelated;
	}

	public void setProductRelated(IcecatProduct productRelated) {
		this.productRelated = productRelated;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Integer getPreferred() {
		return preferred;
	}

	public void setPreferred(Integer preferred) {
		this.preferred = preferred;
	}

	public Integer getReversed() {
		return reversed;
	}

	public void setReversed(Integer reversed) {
		this.reversed = reversed;
	}

}
