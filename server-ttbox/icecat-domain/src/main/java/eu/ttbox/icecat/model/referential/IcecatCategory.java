package eu.ttbox.icecat.model.referential;

import java.util.Date;
import java.util.List;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import eu.ttbox.icecat.model.ICategory;
import eu.ttbox.icecat.model.IIcecatPersistantModelObject;
import eu.ttbox.icecat.model.IIcecatTexContainer;
import eu.ttbox.icecat.model.IIcecatVocabularyContainer;
import eu.ttbox.icecat.model.IcecatHelper;

/**
 * table that holds the category structure information.
 * 
 * Category names can be found in "vocabulary", referenced via the "sid" key.
 * 
 * @author Jerome Morille
 * 
 */
@Entity
@Cacheable(true)
@Table(name = "CATEGORY", schema = "icecat")
// TODO @org.hibernate.annotations.Table(appliesTo = "CATEGORY", indexes = {
// @Index(name = "IDX_CATEGORY_PARENT", columnNames = { "PCATID" }) //
// })
@SequenceGenerator(name = "seqSid", sequenceName = "SEQ_SID")
public class IcecatCategory implements IIcecatPersistantModelObject, IIcecatTexContainer, IIcecatVocabularyContainer,
		 ICategory 
		{

	private static final long serialVersionUID = 1348754889738160670L;

	// Fields
	@Id
	@Column(name = "CATID", nullable = false, length = 13)
	private Integer id;

	@Column(name = "UCATID", nullable = true, length = 255)
	private String unspcCategory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PCATID", nullable = true)
	private IcecatCategory parent;

	@Column(name = "SID", length = 13, nullable = false)
	private Integer sid;

	@Column(name = "TID", length = 13, nullable = false)
	private Integer tid;

	@Column(name = "SEARCHABLE", length = 3, nullable = false)
	private Integer searchable;

	@Column(name = "LOW_PIC", length = 255, nullable = true)
	private String lowDefPicture;

	@Column(name = "THUMB_PIC", length = 255, nullable = true)
	private String thumbPicture;

	@Version
	@Column(name = "UPDATED", nullable = false)
	private Date updated;

	@Column(name = "LAST_PUBLISHED", length = 14, nullable = true)
	private Integer lastPublished;

	@Column(name = "WATCHED_TOP10", length = 13, nullable = true)
	private Integer watchedTop10;

	@OneToMany(fetch = FetchType.LAZY)
	@MapKey(name = "langid")
	@JoinColumn(name = "SID", referencedColumnName = "SID")
	private Map<IcecatLanguageEnum, IcecatVocabulary> names;

	@OneToMany(fetch = FetchType.LAZY)
	@MapKey(name = "langid")
	@JoinColumn(name = "TID", referencedColumnName = "TID")
	private Map<IcecatLanguageEnum, IcecatTex> descriptions;

	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private List<IcecatCategoryKeywords> keywords;

	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private List<IcecatCategoryFeatureGroup> categoryFeatureGroups;

	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private List<IcecatCategoryFeature> categoryFeatures;

	// Constructors
	/** default constructor. */
	public IcecatCategory() {
		super();
	}

	/**
	 * Constructeur par defaut permettant d'initialiser un id a n'utiliser que
	 * dans les tests.
	 */
	public IcecatCategory(Integer pId) {
		this();
		this.id = pId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * eu.ttbox.icecat.model.referential.IIcecatCategory#
	 * getId()
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
		buffer.append("[IcecatCategory:");
		buffer.append(" id: ");
		buffer.append(getId());
		buffer.append(" sid: ");
		buffer.append(sid);
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
		if (!(o instanceof IcecatCategory)) {
			return false;
		}
		IcecatCategory castedObj = (IcecatCategory) o;
		return ((this.getId() == null ? castedObj.getId() == null : this.getId().equals(castedObj.getId())));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * eu.ttbox.icecat.model.referential.IIcecatCategory#
	 * getLowDefPicture()
	 */
	@Override
	public String getLowDefPicture() {
		return lowDefPicture;
	}

	public void setLowDefPicture(String lowDefPicture) {
		this.lowDefPicture = lowDefPicture;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * eu.ttbox.icecat.model.referential.IIcecatCategory#
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * eu.ttbox.icecat.model.referential.IIcecatCategory#
	 * getUnspcCategory()
	 */
	@Override
	public String getUnspcCategory() {
		return unspcCategory;
	}

	public void setUnspcCategory(String unspcCategory) {
		this.unspcCategory = unspcCategory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * eu.ttbox.icecat.model.referential.IIcecatCategory#
	 * getParent()
	 */
	@Override
	public IcecatCategory getParent() {
		return parent;
	}

	public void setParent(IcecatCategory parent) {
		this.parent = parent;
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

	public Integer getSearchable() {
		return searchable;
	}

	public void setSearchable(Integer searchable) {
		this.searchable = searchable;
	}

	public Integer getLastPublished() {
		return lastPublished;
	}

	public void setLastPublished(Integer lastPublished) {
		this.lastPublished = lastPublished;
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

	public List<IcecatCategoryKeywords> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<IcecatCategoryKeywords> keywords) {
		this.keywords = keywords;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * eu.ttbox.icecat.model.referential.IIcecatCategory#
	 * getName()
	 */
	@Override
	public String getName() {
		return IcecatHelper.getName(getNames());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * eu.ttbox.icecat.model.referential.IIcecatCategory#
	 * getDescription()
	 */
	@Override
	public String getDescription() {
		return IcecatHelper.getDescription(getDescriptions());
	}

	public List<IcecatCategoryFeatureGroup> getCategoryFeatureGroups() {
		return categoryFeatureGroups;
	}

	public void setCategoryFeatureGroups(List<IcecatCategoryFeatureGroup> categoryFeatureGroups) {
		this.categoryFeatureGroups = categoryFeatureGroups;
	}

	public List<IcecatCategoryFeature> getCategoryFeatures() {
		return categoryFeatures;
	}

	public void setCategoryFeatures(List<IcecatCategoryFeature> categoryFeatures) {
		this.categoryFeatures = categoryFeatures;
	}

	public Integer getWatchedTop10() {
		return watchedTop10;
	}

	public void setWatchedTop10(Integer watchedTop10) {
		this.watchedTop10 = watchedTop10;
	}

}
