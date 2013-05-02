package eu.ttbox.icecat.model.product;

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

import eu.ttbox.icecat.model.IIcecatPersistantModelObject;
import eu.ttbox.icecat.model.IIcecatProductDependency;

/**
 * in case that a product is a distri bundle, info about components is here .
 * 
 * @author Jerome Morille
 * 
 */
@Entity
@Cacheable(true)
@Table(name = "PRODUCT_BUNDLED", schema = "icecat", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"product_id", "BNDL_PRODUCT_ID" }) })
public class IcecatProductBundled implements IIcecatPersistantModelObject, IIcecatProductDependency {

	private static final long serialVersionUID = 7073628268518655435L;

	// Fields
	@Id
	@Column(name = "id", nullable = false, length = 17)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false)
	private IcecatProduct product;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BNDL_PRODUCT_ID", nullable = false)
	private IcecatProduct productBundled;

	// Constructors
	/** default constructor. */
	public IcecatProductBundled() {
		super();
	}

	/**
	 * Constructeur par defaut permettant d'initialiser un id a n'utiliser que
	 * dans les tests.
	 */
	public IcecatProductBundled(Integer pId) {
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

	@Override
	public IcecatProduct getProduct() {
		return product;
	}

	@Override
	public void setProduct(IcecatProduct product) {
		this.product = product;
	}

	public IcecatProduct getProductBundled() {
		return productBundled;
	}

	public void setProductBundled(IcecatProduct productBundled) {
		this.productBundled = productBundled;
	}

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

		IcecatProductBundled castedObj = (IcecatProductBundled) o;

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
