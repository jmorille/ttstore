package eu.ttbox.icecat.model.campaign;

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
import javax.persistence.UniqueConstraint;

import eu.ttbox.icecat.model.IIcecatPersistantModelObject;
import eu.ttbox.icecat.model.product.IcecatProduct;

@Entity
@Cacheable(true)
@Table(name = "CAMPAIGN_KIT", schema = "icecat", //
uniqueConstraints = { @UniqueConstraint(columnNames = { "CAMPAIGN_ID", "product_id" }) //
})
public class IcecatCampaignKit implements IIcecatPersistantModelObject {

	private static final long serialVersionUID = 3256720667469493817L;

	// Fields
	@Id
	@Column(name = "CAMPAIGN_KIT_ID", nullable = false, length = 13)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CAMPAIGN_ID", nullable = false)
	private IcecatCampaign campaign;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false)
	private IcecatProduct product;

	@Column(name = "clickthrough_count", length = 13, nullable = true)
	private Integer clickthroughCount;

	@Column(name = "UPDATED", nullable = true)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date updated;

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

	public IcecatProduct getProduct() {
		return product;
	}

	public void setProduct(IcecatProduct product) {
		this.product = product;
	}

	public Integer getClickthroughCount() {
		return clickthroughCount;
	}

	public void setClickthroughCount(Integer clickthroughCount) {
		this.clickthroughCount = clickthroughCount;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		IcecatCampaignKit castedObj = (IcecatCampaignKit) o;

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
		return "IcecatCampaignKit [id=" + id + ", campaign=" + campaign + ", product=" + product + "]";
	}

}
