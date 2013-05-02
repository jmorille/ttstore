package eu.ttbox.icecat.model.campaign;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import eu.ttbox.icecat.model.IIcecatPersistantModelObject;

@Entity
@Cacheable(true)
@Table(name = "CAMPAIGN_GALLERY", schema = "icecat")
// TODO @org.hibernate.annotations.Table(appliesTo = "CAMPAIGN_GALLERY", indexes
// = {
// @Index(name = "IDX_CAMPAIGNGAL_CAMPAIGNID", columnNames = { "CAMPAIGN_ID" })
// })
public class IcecatCampaignGallery implements IIcecatPersistantModelObject {

	private static final long serialVersionUID = 3256720667469493817L;

	// Fields
	@Id
	@Column(name = "CAMPAIGN_GALLERY_ID", nullable = false, length = 13)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CAMPAIGN_ID", nullable = false)
	private IcecatCampaign campaign;

	@Column(name = "LOGO_PIC", length = 255, nullable = true)
	private String logoPicture;

	@Column(name = "THUMB_PIC", length = 255, nullable = true)
	private String thumbPicture;

	public IcecatCampaignGallery() {
		super();
	}

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public IcecatCampaign getCampaign() {
		return campaign;
	}

	public void setCampaign(IcecatCampaign campaign) {
		this.campaign = campaign;
	}

	public String getLogoPicture() {
		return logoPicture;
	}

	public void setLogoPicture(String logoPicture) {
		this.logoPicture = logoPicture;
	}

	public String getThumbPicture() {
		return thumbPicture;
	}

	public void setThumbPicture(String thumbPicture) {
		this.thumbPicture = thumbPicture;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		IcecatCampaignGallery castedObj = (IcecatCampaignGallery) o;

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
		return "IcecatCampaignGallery [id=" + id + ", campaign=" + campaign + ", logoPicture=" + logoPicture
				+ ", thumbPicture=" + thumbPicture + "]";
	}

}
