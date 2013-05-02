package eu.ttbox.icecat.model.referential;

import java.util.Map;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import eu.ttbox.icecat.model.IIcecatPersistantModelObject;
import eu.ttbox.icecat.model.IIcecatVocabularyContainer;

/**
 * Identifier of the language of a data element.
 * 
 * @author Jerome Morille
 * 
 */
@Entity
@Cacheable(true)
@Table(name = "LANGUAGE", schema = "icecat")
// FIXME Inject schema !!
public class IcecatLanguage implements IIcecatPersistantModelObject, IIcecatVocabularyContainer {

	private static final long serialVersionUID = 3256720667469493817L;

	// Fields
	@Id
	@Column(name = "LANGID", nullable = false, length = 13)
	private Integer id;

	@Column(name = "SID", nullable = false)
	private Integer sid;

	@Column(name = "CODE", length = 20, nullable = false)
	private String code;

	@Column(name = "SHORT_CODE", length = 5, nullable = false)
	private String shortCode;

	@OneToMany(fetch = FetchType.LAZY)
	@MapKey(name = "langid")
	@JoinColumn(name = "SID", referencedColumnName = "SID")
	private Map<IcecatLanguageEnum, IcecatVocabulary> names;

	// Constructors

	/** default constructor. */
	public IcecatLanguage() {
		super();
	}

	/**
	 * Constructeur par defaut permettant d'initialiser un id a n'utiliser que
	 * dans les tests.
	 */
	public IcecatLanguage(Integer pId) {
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

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("[IcecatLanguage:");
		buffer.append(" id: ");
		buffer.append(getId());
		buffer.append(" code: ");
		buffer.append(code);
		buffer.append("]");
		return buffer.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	/**
	 * Returns <code>true</code> if this <code>Brand</code> is the same as the o
	 * argument.
	 * 
	 * @return <code>true</code> if this <code>Brand</code> is the same as the o
	 *         argument.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (!(o instanceof IcecatLanguage)) {
			return false;
		}
		IcecatLanguage castedObj = (IcecatLanguage) o;
		return ((this.getId() == null ? castedObj.getId() == null : this.getId().equals(castedObj.getId())));
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getShortCode() {
		return shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	@Override
	public Map<IcecatLanguageEnum, IcecatVocabulary> getNames() {
		return names;
	}

	@Override
	public void setNames(Map<IcecatLanguageEnum, IcecatVocabulary> names) {
		this.names = names;
	}

}
