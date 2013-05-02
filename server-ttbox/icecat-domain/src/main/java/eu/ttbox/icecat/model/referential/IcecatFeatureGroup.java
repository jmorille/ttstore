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
import eu.ttbox.icecat.model.IcecatHelper;

/**
 * A generic features groups available in ICEcat.
 * 
 * @author Jerome Morille
 * 
 */
@Entity
@Cacheable(true)
@Table(name = "FEATURE_GROUP", schema = "icecat")
public class IcecatFeatureGroup implements IIcecatPersistantModelObject, IIcecatVocabularyContainer {

	private static final long serialVersionUID = -8862477794756172265L;

	// Fields
	@Id
	@Column(name = "FEATURE_GROUP_ID", nullable = false, length = 13)
	private Integer id;

	@Column(name = "SID", length = 13, nullable = false)
	private Integer sid;

	@OneToMany(fetch = FetchType.LAZY)
	@MapKey(name = "langid")
	@JoinColumn(name = "SID", referencedColumnName = "SID")
	private Map<IcecatLanguageEnum, IcecatVocabulary> names;

	// Constructors

	/** default constructor. */
	public IcecatFeatureGroup() {
		super();
	}

	/**
	 * Constructeur par defaut permettant d'initialiser un id a n'utiliser que
	 * dans les tests.
	 */
	public IcecatFeatureGroup(Integer pId) {
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
		buffer.append("[IcecatFeatureGroup:");
		buffer.append(" id: ");
		buffer.append(getId());
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
		if (!(o instanceof IcecatFeatureGroup)) {
			return false;
		}
		IcecatFeatureGroup castedObj = (IcecatFeatureGroup) o;
		return ((this.getId() == null ? castedObj.getId() == null : this.getId().equals(castedObj.getId())));
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	@Override
	public Map<IcecatLanguageEnum, IcecatVocabulary> getNames() {
		return names;
	}

	@Override
	public void setNames(Map<IcecatLanguageEnum, IcecatVocabulary> names) {
		this.names = names;
	}

	public String getName() {
		return IcecatHelper.getName(getNames());
	}

}
