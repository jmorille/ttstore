package eu.ttbox.icecat.model.campaign;

import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import eu.ttbox.icecat.model.IIcecatPersistantModelObject;

@Entity
@Cacheable(true)
@Table(name = "CAMPAIGN", schema = "icecat")
public class IcecatCampaign implements IIcecatPersistantModelObject {

	private static final long serialVersionUID = 3256720667469493817L;

	// Fields
	@Id
	@Column(name = "CAMPAIGN_ID", nullable = false, length = 13)
	private Integer id;

	@Column(name = "NAME", length = 255, nullable = true)
	private String name;

	@Column(name = "SHORT_DESCRIPTION", length = 255, nullable = true)
	private String shortDescription;

	//@Lob
	@Column(name = "LONG_DESCRIPTION",length=4000, nullable = true)
	private String longDescription;

	@Column(name = "LINK", length = 255, nullable = true)
	private String link;

	@Column(name = "START_DATE", length = 13, nullable = true)
	private Integer startDate;

	@Column(name = "END_DATE", length = 13, nullable = true)
	private Integer endDate;

	@Column(name = "UPDATED", nullable = true)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date updated;

	@Column(name = "USER_ID", length = 13, nullable = true)
	private Integer userId;

	@OneToMany(mappedBy = "campaign", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private List<IcecatCampaignGallery> galleries;

	@OneToMany(mappedBy = "campaign", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private List<IcecatCampaignKit> kits;

	public IcecatCampaign() {
		super();
	}

	public IcecatCampaign(Integer id) {
		super();
		this.id = id;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public List<IcecatCampaignGallery> getGalleries() {
		return galleries;
	}

	public void setGalleries(List<IcecatCampaignGallery> galleries) {
		this.galleries = galleries;
	}

	public List<IcecatCampaignKit> getKits() {
		return kits;
	}

	public void setKits(List<IcecatCampaignKit> kits) {
		this.kits = kits;
	}

	public Integer getStartDate() {
		return startDate;
	}

	public void setStartDate(Integer startDate) {
		this.startDate = startDate;
	}

	public Integer getEndDate() {
		return endDate;
	}

	public void setEndDate(Integer endDate) {
		this.endDate = endDate;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		IcecatCampaign castedObj = (IcecatCampaign) o;

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
		return "IcecatCampaign [id=" + id + ", updated=" + updated + ", shortDescription=" + shortDescription + "]";
	}

}
