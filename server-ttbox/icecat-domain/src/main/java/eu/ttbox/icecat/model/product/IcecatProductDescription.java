package eu.ttbox.icecat.model.product;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.Size;

import eu.ttbox.icecat.model.IIcecatLangModelObject;
import eu.ttbox.icecat.model.IIcecatPersistantModelObject;
import eu.ttbox.icecat.model.IIcecatProductDependency;
import eu.ttbox.icecat.model.referential.IcecatLanguageEnum;

/**
 * language specific description.
 * 
 * @author Jerome Morille
 * 
 */
@Entity
@Cacheable(true)
@Table(name = "PRODUCT_DESCRIPTION", schema = "icecat", //
uniqueConstraints = { @UniqueConstraint(columnNames = { "PRODUCT_ID", "LANGID" }) })
public class IcecatProductDescription implements IIcecatPersistantModelObject, IIcecatProductDependency,
		IIcecatLangModelObject {

	private static final long serialVersionUID = 3256720667469493817L;

	// Fields
	@Id
	@Column(name = "PRODUCT_DESCRIPTION_ID", nullable = false, length = 13)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	private IcecatProduct product;

	@Column(name = "LANGID", length = 2, nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private IcecatLanguageEnum langid;

	//@Lob @Basic(fetch=FetchType.EAGER) // @Type(type="string")
	@Size(max=1000)
	@Column(name = "SHORT_DESC",length=1000, nullable = true)
	private String shortDescription;

	//@Lob @Basic(fetch=FetchType.EAGER) //@Type(type="string")
	@Size(max=4000)
	@Column(name = "LONG_DESC",length=4000, nullable = true)
	private String longDescription;

	@Column(name = "specs_url", length = 255, nullable = true)
	private String specsURL;

	@Column(name = "support_url", length = 255, nullable = true)
	private String supportURL;

	@Column(name = "OFFICIAL_URL", length = 255, nullable = true)
	private String officialURL;

	@Lob  @Basic(fetch=FetchType.EAGER) //@Type(type="string")
	@Column(name = "WARRANTY_INFO", nullable = true)
	private String warranty;

	@Column(name = "option_field_1", length = 5000, nullable = true)
	private String optionField1;

	@Column(name = "option_field_2", length = 5000, nullable = true)
	private String optionField2;

	@Version
	@Column(name = "UPDATED", nullable = false)
	private Date updated;

	@Column(name = "PDF_URL", length = 255, nullable = true)
	private String pdfURL;

	@Column(name = "PDF_SIZE", length = 13, nullable = true)
	private Integer pdfSize;

	@Column(name = "MANUAL_PDF_URL", length = 255, nullable = true)
	private String manualPdfURL;

	@Column(name = "MANUAL_PDF_SIZE", length = 13, nullable = true)
	private Integer manualPdfSize;

	// Constructors
	/** default constructor. */
	public IcecatProductDescription() {
		super();
	}

	/**
	 * Constructeur par defaut permettant d'initialiser un id a n'utiliser que
	 * dans les tests.
	 */
	public IcecatProductDescription(Integer pId) {
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

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public IcecatProduct getProduct() {
		return product;
	}

	@Override
	public void setProduct(IcecatProduct product) {
		this.product = product;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getOfficialURL() {
		return officialURL;
	}

	public void setOfficialURL(String officialURL) {
		this.officialURL = officialURL;
	}

	public String getWarranty() {
		return warranty;
	}

	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getPdfURL() {
		return pdfURL;
	}

	public void setPdfURL(String pdfURL) {
		this.pdfURL = pdfURL;
	}

	public Integer getPdfSize() {
		return pdfSize;
	}

	public void setPdfSize(Integer pdfSize) {
		this.pdfSize = pdfSize;
	}

	@Override
	public IcecatLanguageEnum getLangid() {
		return langid;
	}

	@Override
	public void setLangid(IcecatLanguageEnum langid) {
		this.langid = langid;
	}

	public String getManualPdfURL() {
		return manualPdfURL;
	}

	public void setManualPdfURL(String manualPdfURL) {
		this.manualPdfURL = manualPdfURL;
	}

	public Integer getManualPdfSize() {
		return manualPdfSize;
	}

	public void setManualPdfSize(Integer manualPdfSize) {
		this.manualPdfSize = manualPdfSize;
	}

	public String getSpecsURL() {
		return specsURL;
	}

	public void setSpecsURL(String specsURL) {
		this.specsURL = specsURL;
	}

	public String getSupportURL() {
		return supportURL;
	}

	public void setSupportURL(String supportURL) {
		this.supportURL = supportURL;
	}

	public String getOptionField1() {
		return optionField1;
	}

	public void setOptionField1(String optionField1) {
		this.optionField1 = optionField1;
	}

	public String getOptionField2() {
		return optionField2;
	}

	public void setOptionField2(String optionField2) {
		this.optionField2 = optionField2;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		IcecatProductDescription castedObj = (IcecatProductDescription) o;

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
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("[IcecatProductDescription:");
		buffer.append(" id: ");
		buffer.append(getId());
		buffer.append("]");
		return buffer.toString();
	}

}
