package eu.ttbox.icecat.model.referential;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import eu.ttbox.icecat.model.IBrand;
import eu.ttbox.icecat.model.IIcecatPersistantModelObject;

/**
 * manufacturers are stored in this table
 * 
 * @author Jerome Morille
 * 
 */
@Entity
@Table(name = "SUPPLIER", schema = "icecat")
// TODO @org.hibernate.annotations.Table(appliesTo = "SUPPLIER", indexes = {
// @Index(name = "IDX_SUPPLIER_NAME", columnNames = { "name" }),
// @Index(name = "IDX_SUPPLIER_SPONSOR", columnNames = { "is_sponsor" })
// })
public class IcecatBrand implements IIcecatPersistantModelObject, IBrand  {

	private static final long serialVersionUID = 3256720667469493817L;

	// Fields
	@Id
	@Column(name = "SUPPLIER_ID", nullable = false, length = 13)
	private Integer id;

	@Column(name = "USER_ID", nullable = false, length = 13)
	private Integer userId;

	@Column(name = "NAME", length = 255, nullable = false)
	private String name;

	@Column(name = "LOW_PIC", length = 255, nullable = true)
	private String lowPicture;

	@Column(name = "THUMB_PIC", length = 255, nullable = true)
	private String thumbPicture;

	@Column(name = "is_sponsor", length = 1, nullable = true)
	private boolean sponsor;

	@Version
	@Column(name = "UPDATED", nullable = false)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date updated;

	@ElementCollection(fetch = FetchType.LAZY)
	@JoinTable(schema = "icecat", name = "SUPPLIER_MAPPING", joinColumns = @JoinColumn(name = "SUPPLIER_ID"))
	@Column(name = "SYMBOL", nullable = false)
	private List<String> aliases;

	// Constructors

	/** default constructor. */
	public IcecatBrand() {
		super();
	}

	public void setAliases(List<String> aliases) {
		this.aliases = aliases;
	}

	/**
	 * Constructeur par defaut permettant d'initialiser un id a n'utiliser que
	 * dans les tests.
	 */
	public IcecatBrand(Integer pId) {
		this();
		this.id = pId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * eu.ttbox.icecat.model.referential.IIcecatBrand#getId()
	 */
	@Override
	public Integer getId() {
		return this.id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * eu.ttbox.icecat.model.referential.IIcecatBrand#getName
	 * ()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * @param pName
	 *            The name to set.
	 */
	public void setName(String pName) {
		this.name = pName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.ttbox.icecat.model.referential.IIcecatBrand#
	 * getLowPicture()
	 */
	@Override
	public String getLowPicture() {
		return lowPicture;
	}

	public void setLowPicture(String lowPicture) {
		this.lowPicture = lowPicture;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.ttbox.icecat.model.referential.IIcecatBrand#
	 * getThumbPicture()
	 */
	@Override
	public String getThumbPicture() {
		return thumbPicture;
	}

	public void setThumbPicture(String thumbPicture) {
		this.thumbPicture = thumbPicture;
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

	public boolean isSponsor() {
		return sponsor;
	}

	public void setSponsor(boolean sponsor) {
		this.sponsor = sponsor;
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("[IcecatBrand:");
		buffer.append(" id: ");
		buffer.append(getId());
		buffer.append(" name: ");
		buffer.append(name);
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

		IcecatBrand castedObj = (IcecatBrand) o;

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

	public List<String> getAliases() {
		return aliases;
	}

}
