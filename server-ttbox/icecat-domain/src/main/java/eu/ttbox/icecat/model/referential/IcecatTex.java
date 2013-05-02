package eu.ttbox.icecat.model.referential;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import eu.ttbox.icecat.model.IIcecatLangModelObject;
import eu.ttbox.icecat.model.IIcecatPersistantModelObject;

/**
 * Vocabulary for large data elements.
 * 
 * @author Jerome Morille
 * 
 */
@Entity
@Cacheable(true)
@Table(name = "TEX", schema = "icecat", uniqueConstraints = { @UniqueConstraint(columnNames = { "TID", "LANGID" }) })
public class IcecatTex implements IIcecatPersistantModelObject, IIcecatLangModelObject {

	private static final long serialVersionUID = -3536171251162482795L;

	// Fields
	@Id
	@Column(name = "TEX_ID", nullable = false, length = 13)
	private Integer id;

	@NotNull
	@Column(name = "TID", length = 13, nullable = false)
	private Integer tid;

	@NotNull
	@Column(name = "LANGID", length = 2, nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private IcecatLanguageEnum langid;

	//@Lob @Basic(fetch=FetchType.EAGER) // @Type(type="string")
	@Size(max=4000)
	@Column(name = "VALUE", length = 4000, nullable = false) 
	private String value;

	// Constructors

	/** default constructor. */
	public IcecatTex() {
		super();
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Constructeur par defaut permettant d'initialiser un id a n'utiliser que
	 * dans les tests.
	 */
	public IcecatTex(Integer pId) {
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

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("[IcecatText:");
		buffer.append(" record_id: ");
		buffer.append(getId());
		buffer.append(" tid: ");
		buffer.append(tid);
		buffer.append(" langid: ");
		buffer.append(langid);
		buffer.append(" value: ");
		buffer.append(value);
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

		IcecatTex castedObj = (IcecatTex) o;

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

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
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

}
