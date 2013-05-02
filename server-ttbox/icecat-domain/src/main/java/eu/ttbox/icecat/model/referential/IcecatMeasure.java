package eu.ttbox.icecat.model.referential;

import java.util.Date;
import java.util.Map;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import eu.ttbox.icecat.model.IIcecatPersistantModelObject;
import eu.ttbox.icecat.model.IIcecatTexContainer;
import eu.ttbox.icecat.model.IIcecatVocabularyContainer;
import eu.ttbox.icecat.model.IcecatHelper;

/**
 * Units, e.g. meter, megabyte etc.
 * 
 * @author Jerome Morille
 * 
 */
@Entity
@Cacheable(true)
@Table(name = "MEASURE", schema = "icecat")
public class IcecatMeasure implements IIcecatPersistantModelObject, IIcecatTexContainer, IIcecatVocabularyContainer {

	private static final long serialVersionUID = -6238696666291887407L;

	// Fields
	@Id
	@Column(name = "MEASURE_ID", nullable = false, length = 13)
	private Integer id;

	@Column(name = "SID", length = 13, nullable = false)
	private Integer sid;

	@Column(name = "TID", length = 13, nullable = true)
	private Integer tid;

	@Column(name = "SIGN", length = 255, nullable = true)
	private String sign;

	@Version
	@Column(name = "UPDATED")
	private Date updated;

	@Column(name = "LAST_PUBLISHED")
	private Integer lastPublished;

	@Column(name = "pattern", length = 255, nullable = true)
	private String pattern;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "system_of_measurement")
	private SystemOfMeasurementEnum systemOfMeasurement;

	@OneToMany(fetch = FetchType.LAZY)
	@MapKey(name = "langid")
	@JoinColumn(name = "SID", referencedColumnName = "SID")
	private Map<IcecatLanguageEnum, IcecatVocabulary> names;

	@OneToMany(fetch = FetchType.LAZY)
	@MapKey(name = "langid")
	@JoinColumn(name = "TID", referencedColumnName = "TID")
	private Map<IcecatLanguageEnum, IcecatTex> descriptions;

	// Constructors

	/** default constructor. */
	public IcecatMeasure() {
		super();
	}

	/**
	 * Constructeur par defaut permettant d'initialiser un id a n'utiliser que
	 * dans les tests.
	 */
	public IcecatMeasure(Integer pId) {
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
		buffer.append("[IcecatMeasure:");
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
		if (!(o instanceof IcecatMeasure)) {
			return false;
		}
		IcecatMeasure castedObj = (IcecatMeasure) o;
		return ((this.getId() == null ? castedObj.getId() == null : this.getId().equals(castedObj.getId())));
	}

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

	public String getName() {
		return IcecatHelper.getName(getNames());
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
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

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public SystemOfMeasurementEnum getSystemOfMeasurement() {
		return systemOfMeasurement;
	}

	public void setSystemOfMeasurement(SystemOfMeasurementEnum systemOfMeasurement) {
		this.systemOfMeasurement = systemOfMeasurement;
	}

}
