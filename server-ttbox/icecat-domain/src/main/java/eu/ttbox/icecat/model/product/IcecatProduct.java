package eu.ttbox.icecat.model.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cascade;

import eu.ttbox.icecat.model.IIcecatCategoryContainer;
import eu.ttbox.icecat.model.IIcecatPersistantModelObject;
import eu.ttbox.icecat.model.IProduct;
import eu.ttbox.icecat.model.IcecatHelper;
import eu.ttbox.icecat.model.referential.IcecatBrand;
import eu.ttbox.icecat.model.referential.IcecatCategory;
import eu.ttbox.icecat.model.referential.IcecatLanguageEnum;

/**
 * Main information about the product.
 * 
 * @author Jerome Morille
 * 
 */
@Entity
@Cacheable(true)
@Table(name = "PRODUCT", schema = "icecat", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"PROD_ID", "SUPPLIER_ID" }) })
// TODO @org.hibernate.annotations.Table(appliesTo = "PRODUCT", indexes = {
// @Index(name = "IDX_PRODUCT_CATEGORY", columnNames = { "CATID" }), //
// @Index(name = "IDX_PRODUCT_BRAND", columnNames = { "SUPPLIER_ID" }), //
// @Index(name = "IDX_PRODUCT_FAMILY", columnNames = { "FAMILY_ID" }) //
// })
public class IcecatProduct implements IIcecatPersistantModelObject, IProduct, // IProductIndex, 
		IIcecatCategoryContainer {

	private static final long serialVersionUID = 3256720667469493817L;

	// Fields
	@Id
	@Column(name = "PRODUCT_ID", nullable = false, length = 13)
	private Integer id;

	@Column(name = "DATE_ADDED", nullable = true)
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date dateAdded;

	@Column(name = "UPDATED", nullable = true)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date updated;

	@Column(name = "PATH_VERSION_DATE", nullable = true)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date pathVersionDate;
 	
	@Column(name = "PATH", length = 255, nullable = true)
	private String path;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUPPLIER_ID", nullable = false)
	private IcecatBrand brand;

	@NotNull
	@Column(name = "NAME", length = 255, nullable = false)
	private String name;

	@NotNull
	@Column(name = "PROD_ID", length = 235, nullable = false)
	private String partNumber;

	@Column(name = "USER_ID", nullable = true, length = 13)
	private Integer userId;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATID", nullable = false)
	private IcecatCategory category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FAMILY_ID", nullable = true)
	private IcecatProductLine line;

	@Column(name = "LOW_PIC", length = 255, nullable = true)
	private String lowDefPicture;

	@Column(name = "HIGH_PIC", length = 255, nullable = true)
	private String highDefPicture;

	@Column(name = "THUMB_PIC", length = 255, nullable = true)
	private String thumbPicture;

	@Column(name = "QUALITY", length = 10, nullable = true)
	private String editor;

	@NotNull
	@Column(name = "CODE", length = 1, nullable = false)
	private Integer code;

	@Column(name = "SCORE", length = 10, nullable = true)
	private Integer score;

	@Column(name = "LOW_PIC_SIZE", length = 13, nullable = true)
	private Integer lowDefPictureSize;

	@Column(name = "LOW_PIC_WIDTH", length = 13, nullable = true)
	private Integer lowDefPictureWidth;

	@Column(name = "LOW_PIC_HEIGHT", length = 13, nullable = true)
	private Integer lowDefPictureHeight;

	@Column(name = "HIGH_PIC_SIZE", length = 13, nullable = true)
	private Integer highDefPictureSize;

	@Column(name = "HIGH_PIC_WIDTH", length = 13, nullable = true)
	private Integer highDefPictureWidth;

	@Column(name = "HIGH_PIC_HEIGHT", length = 13, nullable = true)
	private Integer highDefPictureHeight;

	@Column(name = "THUMB_PIC_SIZE", length = 13, nullable = true)
	private Integer thumbPictureSize;

	@Column(name = "ON_MARKET", length = 13, nullable = true)
	private boolean onMarket;


	@ElementCollection(fetch = FetchType.LAZY)
	@JoinTable(schema = "icecat", name = "PRODUCT_EAN", joinColumns = @JoinColumn(name = "PRODUCT_ID"))
	@Column(name = "EAN", nullable = false)
	@Cascade(value={org.hibernate.annotations.CascadeType.ALL})
	private List<String> eans;

	@ElementCollection(fetch = FetchType.LAZY)
	@JoinTable(schema = "icecat", name = "PRODUCT_COUNTRY_MARKET", joinColumns = @JoinColumn(name = "PRODUCT_ID"))
	@Column(name = "COUNTRY", nullable = false)
	@Cascade(value={org.hibernate.annotations.CascadeType.ALL})
	private List<String> countryMarkets;

	@ElementCollection(fetch = FetchType.LAZY)
	@JoinTable(schema = "icecat", name = "PRODUCT_MPRODID", joinColumns = @JoinColumn(name = "PRODUCT_ID"))
	@Column(name = "M_PROD_ID", nullable = false)
	@Cascade(value={org.hibernate.annotations.CascadeType.ALL})
 	private List<String> alternativeMFPN;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade= {CascadeType.ALL})
	@MapKey(name = "langid")
	private Map<IcecatLanguageEnum, IcecatProductDescription> descriptions;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	@MapKey(name = "langid")
	private Map<IcecatLanguageEnum, IcecatProductSummaryDescription> summaryDescriptions;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private List<IcecatProductFeature> features;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private List<IcecatProductRelated> relateds;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private List<IcecatProductBundled> bundleds;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private List<IcecatProductGallery> galleries;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private List<IcecatProductMultimediaObject> mmo;

	// Constructors

	/** default constructor. */
	public IcecatProduct() {
		super();
	}

	/**
	 * Constructeur par defaut permettant d'initialiser un id a n'utiliser que
	 * dans les tests.
	 */
	public IcecatProduct(Integer pId) {
		this();
		this.id = pId;
	}

 
	@Override
	public Integer getId() {
		return this.id;
	}

 
	public void setId(Integer id) {
		this.id = id;
	}

 
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

 
	@Override
	public IcecatBrand getBrand() {
		return brand;
	}

	public void setBrand(IcecatBrand brand) {
		this.brand = brand;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String mfpn) {
		this.partNumber = mfpn;
	}

 
	@Override
	public String getLowDefPicture() {
		return lowDefPicture;
	}

	public void setLowDefPicture(String lowDefPicture) {
		this.lowDefPicture = lowDefPicture;
	}

 
	@Override
	public String getHighDefPicture() {
		return highDefPicture;
	}

	public void setHighDefPicture(String highDefPicture) {
		this.highDefPicture = highDefPicture;
	}

 
	@Override
	public String getThumbPicture() {
		return thumbPicture;
	}

	public void setThumbPicture(String thumbPicture) {
		this.thumbPicture = thumbPicture;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getLowDefPictureSize() {
		return lowDefPictureSize;
	}

	public void setLowDefPictureSize(Integer lowDefPictureSize) {
		this.lowDefPictureSize = lowDefPictureSize;
	}

	public Integer getHighDefPictureSize() {
		return highDefPictureSize;
	}

	public void setHighDefPictureSize(Integer highDefPictureSize) {
		this.highDefPictureSize = highDefPictureSize;
	}

	public Integer getThumbPictureSize() {
		return thumbPictureSize;
	}

	public void setThumbPictureSize(Integer thumbPictureSize) {
		this.thumbPictureSize = thumbPictureSize;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

 
	@Override
	public IcecatCategory getCategory() {
		return category;
	}

	@Override
	public void setCategory(IcecatCategory category) {
		this.category = category;
	}

	@Override
	public IcecatProductLine getLine() {
		return line;
	}

	public void setLine(IcecatProductLine line) {
		this.line = line;
	}

	public List<String> getEans() {
		return eans;
	}

	public void setEans(List<String> eans) {
		this.eans = eans;
	}

	public List<String> getAlternativeMFPN() {
		return alternativeMFPN;
	}

	public void setAlternativeMFPN(List<String> alternativeMFPN) {
		this.alternativeMFPN = alternativeMFPN;
	}

	public Map<IcecatLanguageEnum, IcecatProductDescription> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(
			Map<IcecatLanguageEnum, IcecatProductDescription> descriptions) {
		this.descriptions = descriptions;
	}

	public List<IcecatProductRelated> getRelateds() {
		return relateds;
	}

	public void setRelateds(List<IcecatProductRelated> relateds) {
		this.relateds = relateds;
	}

	public List<IcecatProductBundled> getBundleds() {
		return bundleds;
	}

	public void setBundleds(List<IcecatProductBundled> bundleds) {
		this.bundleds = bundleds;
	}

	public List<IcecatProductGallery> getGalleries() {
		return galleries;
	}

	public void setGalleries(List<IcecatProductGallery> galleries) {
		this.galleries = galleries;
	}

	public List<IcecatProductMultimediaObject> getMmo() {
		return mmo;
	}

	public void setMmo(List<IcecatProductMultimediaObject> mmo) {
		this.mmo = mmo;
	}

 
	@Override
	public String getDescription() {
		return getShortDescription();
	}

 
	@Override
	public String getShortDescription() {
		Map<IcecatLanguageEnum, IcecatProductDescription> names = getDescriptions();
		if (names==null) {
			return null;
		}
		final IcecatLanguageEnum[] lngs = new IcecatLanguageEnum[] {
				IcecatLanguageEnum.FR, IcecatLanguageEnum.EN };
		boolean notFound = true;
		String name = null;
		int i = 0;
		while (i < lngs.length && notFound) {
			IcecatProductDescription nameVoc = names.get(lngs[i++]);
			if (nameVoc != null) {
				name = nameVoc.getShortDescription();
				notFound = name == null;
			}
			// i++;
		}
		return name;
	}

	public IcecatProductDescription getProductDescription() {
		Map<IcecatLanguageEnum, IcecatProductDescription> names = getDescriptions();
		IcecatProductDescription desc = (IcecatProductDescription) IcecatHelper
				.getByIcecatLanguageEnum(names);
		return desc;
	}
 
	@Override
	public String getMarketingDescription() {
		Map<IcecatLanguageEnum, IcecatProductDescription> names = getDescriptions();
		if (names==null) {
			return null;
		}
		final IcecatLanguageEnum[] lngs = new IcecatLanguageEnum[] {
				IcecatLanguageEnum.FR, IcecatLanguageEnum.EN };
		boolean notFound = true;
		String name = null;
		int i = 0;
		while (i < lngs.length && notFound) {
			IcecatProductDescription nameVoc = names.get(lngs[i++]);
			if (nameVoc != null) {
				name = nameVoc.getLongDescription();
				notFound = name == null;
			}
		}
		return name;
	}

	public String getMarketingDescriptionHtmlFormated() {
		return IcecatHelper.getHtmlFormatedText(getMarketingDescription());
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("[IcecatProduct:");
		buffer.append(" id: ");
		buffer.append(getId());
		buffer.append(" name: ");
		buffer.append(name);
		buffer.append(" mfpn: ");
		buffer.append(partNumber);
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

		IcecatProduct castedObj = (IcecatProduct) o;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.ttbox.icecat.model.product.IwsIcecatProduct#getImageURI ()
	 */
	@Override
	public String getImageURI() {
		String imgUri = getLowDefPicture();
		if (StringUtils.isBlank(imgUri)) {
			imgUri = getHighDefPicture();
		}
		return imgUri;
	}

	public List<IcecatProductFeature> getFeatures() {
		return features;
	}

	public void setFeatures(List<IcecatProductFeature> features) {
		this.features = features;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}
 
	public Date getPathVersionDate() {
		return pathVersionDate;
	}

	public void setPathVersionDate(Date pathVersionDate) {
		this.pathVersionDate = pathVersionDate;
	}

	public Integer getLowDefPictureWidth() {
		return lowDefPictureWidth;
	}

	public void setLowDefPictureWidth(Integer lowDefPictureWidth) {
		this.lowDefPictureWidth = lowDefPictureWidth;
	}

	public Integer getLowDefPictureHeight() {
		return lowDefPictureHeight;
	}

	public void setLowDefPictureHeight(Integer lowDefPictureHeight) {
		this.lowDefPictureHeight = lowDefPictureHeight;
	}

	public Integer getHighDefPictureWidth() {
		return highDefPictureWidth;
	}

	public void setHighDefPictureWidth(Integer highDefPictureWidth) {
		this.highDefPictureWidth = highDefPictureWidth;
	}

	public Integer getHighDefPictureHeight() {
		return highDefPictureHeight;
	}

	public void setHighDefPictureHeight(Integer highDefPictureHeight) {
		this.highDefPictureHeight = highDefPictureHeight;
	}

	public Map<IcecatLanguageEnum, IcecatProductSummaryDescription> getSummaryDescriptions() {
		return summaryDescriptions;
	}

	public void setSummaryDescriptions(
			Map<IcecatLanguageEnum, IcecatProductSummaryDescription> summaryDescriptions) {
		this.summaryDescriptions = summaryDescriptions;
	}

 

	public boolean isOnMarket() {
		return onMarket;
	}

	public void setOnMarket(boolean onMarket) {
		this.onMarket = onMarket;
	}

	public List<String> getCountryMarkets() {
		return countryMarkets;
	}

	public void setCountryMarkets(List<String> countryMarkets) {
		this.countryMarkets = countryMarkets;
	}

	 
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean containEans(String eanVal) {
		if (eans != null) {
			return eans.contains(eanVal);
		}
		return false;
	}
	public void addEans(String eanVal) {
		List<String> eamList = getEans();
		if (eamList == null) {
			eamList = new ArrayList<String>();
			setEans(eamList);
		}
		if (!eamList.contains(eanVal)) {
			eamList.add(eanVal);
		}
	}
	public boolean removeEans(String eanVal) {
		List<String> eamList = getEans();
		if (eamList != null) {
			return eamList.remove(eanVal);
		}
		return false;
	}
	public void addAlternativeMFPN(String mfpn) {
		List<String> alternativeMFPN = getAlternativeMFPN();
		if (alternativeMFPN == null) {
			alternativeMFPN = new ArrayList<String>();
			setAlternativeMFPN(alternativeMFPN);
		}
		if (!alternativeMFPN.contains(mfpn)) {
			alternativeMFPN.add(mfpn);
		}
	}

	public void addCountryMarket(String country) {
		List<String> countryList = getCountryMarkets();
		if (countryList == null) {
			countryList = new ArrayList<String>();
			setCountryMarkets(countryList);
		}
		if (!countryList.contains(country)) {
			countryList.add(country);
		}
	}
 

}
