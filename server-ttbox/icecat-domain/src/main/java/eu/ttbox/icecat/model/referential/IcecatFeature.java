package eu.ttbox.icecat.model.referential;

import java.util.Date;
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
import javax.persistence.Version;

import eu.ttbox.icecat.model.IIcecatPersistantModelObject;
import eu.ttbox.icecat.model.IIcecatTexContainer;
import eu.ttbox.icecat.model.IIcecatVocabularyContainer;
import eu.ttbox.icecat.model.IcecatHelper;

/**
 * Holds the information about the features available for product description.
 * 
 * @author Jerome Morille
 * 
 */
@Entity
@Cacheable(true)
@Table(name = "FEATURE", schema = "icecat")
public class IcecatFeature implements IIcecatPersistantModelObject, IIcecatTexContainer, IIcecatVocabularyContainer {

	private static final long serialVersionUID = -8862477794756172265L;

	// Fields
	@Id
	@Column(name = "FEATURE_ID", nullable = false, length = 13)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEASURE_ID", nullable = true)
	private IcecatMeasure measure;

	@Column(name = "SID", length = 13, nullable = false)
	private Integer sid;

	@Column(name = "TID", length = 13, nullable = true)
	private Integer tid;

	@Column(name = "TYPE", length = 255, nullable = true)
	private String type;

	@Column(name = "CLASS", length = 3, nullable = false)
	private Integer clazz;

	@Column(name = "LIMIT_DIRECTION", length = 3, nullable = false)
	private Integer limitDirection;

	@Column(name = "SEARCHABLE", length = 3, nullable = false)
	private Integer searchable;

	//@Lob @Basic(fetch=FetchType.EAGER) // @Type(type="string")
	@Column(name = "RESTRICTED_VALUES", length = 4000, nullable = true)
	private String restrictedValues;

	@Version
	@Column(name = "UPDATED")
	private Date updated;

	@Column(name = "LAST_PUBLISHED")
	private Integer lastPublished;

	@Column(name = "pattern", length = 255, nullable = true)
	private String pattern;

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
	public IcecatFeature() {
		super();
	}

	/**
	 * Constructeur par defaut permettant d'initialiser un id a n'utiliser que
	 * dans les tests.
	 */
	public IcecatFeature(Integer pId) {
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
		StringBuffer buffer = new StringBuffer();
		buffer.append("[IcecatFeature:");
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

		IcecatFeature castedObj = (IcecatFeature) o;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getClazz() {
		return clazz;
	}

	public void setClazz(Integer clazz) {
		this.clazz = clazz;
	}

	public Integer getLimitDirection() {
		return limitDirection;
	}

	public void setLimitDirection(Integer limitDirection) {
		this.limitDirection = limitDirection;
	}

	public Integer getSearchable() {
		return searchable;
	}

	public void setSearchable(Integer searchable) {
		this.searchable = searchable;
	}

	public String getRestrictedValues() {
		return restrictedValues;
	}

	public void setRestrictedValues(String restrictedValues) {
		this.restrictedValues = restrictedValues;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Integer getLastPublished() {
		return lastPublished;
	}

	public void setLastPublished(Integer lastPublished) {
		this.lastPublished = lastPublished;
	}

	public IcecatMeasure getMeasure() {
		return measure;
	}

	public void setMeasure(IcecatMeasure measure) {
		this.measure = measure;
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

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

}
