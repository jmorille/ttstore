package eu.ttbox.icecat.model.relations;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import eu.ttbox.icecat.model.IIcecatCategoryContainer;
import eu.ttbox.icecat.model.IIcecatPersistantModelObject;
import eu.ttbox.icecat.model.referential.IcecatBrand;
import eu.ttbox.icecat.model.referential.IcecatCategory;
import eu.ttbox.icecat.model.referential.IcecatFeature;

@Entity
@Cacheable(true)
@Table(name = "relation_rule", schema = "icecat")
public class IcecatRelationRule implements IIcecatPersistantModelObject, IIcecatCategoryContainer {

	private static final long serialVersionUID = 7073628268518655435L;

	@Id
	@Column(name = "relation_rule_id", nullable = false, length = 13)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUPPLIER_ID", nullable = true)
	private IcecatBrand brand;

	@Column(name = "supplier_family_id", length = 13, nullable = true)
	private Integer supplierFamilyId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATID", nullable = true)
	private IcecatCategory category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FEATURE_ID", nullable = true)
	private IcecatFeature feature;

	@Column(name = "feature_value", length = 255, nullable = true)
	private String featureValue;

	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "exact_value", nullable = true)
	// private IcecatRelationExactValues exactValue;

	@Column(name = "exact_value_text", length = 60, nullable = true)
	private String exactValueText;

	@Column(name = "PROD_ID", length = 60, nullable = true)
	private String partNumber;

	@Column(name = "start_date", nullable = true)
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date startDate;

	@Column(name = "end_date", nullable = true)
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date endDate;

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public IcecatBrand getBrand() {
		return brand;
	}

	public void setBrand(IcecatBrand brand) {
		this.brand = brand;
	}

	@Override
	public IcecatCategory getCategory() {
		return category;
	}

	@Override
	public void setCategory(IcecatCategory category) {
		this.category = category;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getExactValueText() {
		return exactValueText;
	}

	public void setExactValueText(String exactValueText) {
		this.exactValueText = exactValueText;
	}

	public Integer getSupplierFamilyId() {
		return supplierFamilyId;
	}

	public void setSupplierFamilyId(Integer supplierFamilyId) {
		this.supplierFamilyId = supplierFamilyId;
	}

	public IcecatFeature getFeature() {
		return feature;
	}

	public void setFeature(IcecatFeature feature) {
		this.feature = feature;
	}

	public String getFeatureValue() {
		return featureValue;
	}

	public void setFeatureValue(String featureValue) {
		this.featureValue = featureValue;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		IcecatRelationRule castedObj = (IcecatRelationRule) o;

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
