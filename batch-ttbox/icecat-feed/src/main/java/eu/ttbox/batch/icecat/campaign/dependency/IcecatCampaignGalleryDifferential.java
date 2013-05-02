package eu.ttbox.batch.icecat.campaign.dependency;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import biz.icecat.campaigns.v1.Image;
import eu.ttbox.batch.icecat.referential.dependency.AbstractDependencyDifferential;
import eu.ttbox.icecat.model.campaign.IcecatCampaign;
import eu.ttbox.icecat.model.campaign.IcecatCampaignGallery;

@Transactional
@Service("icecatCampaignGalleryDifferential")
public class IcecatCampaignGalleryDifferential extends AbstractDependencyDifferential<IcecatCampaign, IcecatCampaignGallery, Image> {

	@Override
	public Integer getFeedElementId(Image elt) {
		return elt.getID();
	}

	@Override
	public boolean isValidEntity(IcecatCampaignGallery entity) {
		if (entity.getCampaign() == null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isValidFeed(Image elt) {
		return true;
	}

	@Override
	public IcecatCampaignGallery createEntity(IcecatCampaign master, Image elt) {
		IcecatCampaignGallery entity = new IcecatCampaignGallery();
		entity.setId(elt.getID());
		entity.setCampaign(master);
		return entity;
	}

	@Override
	public IcecatCampaignGallery updateEntity(IcecatCampaign master, Image elt, IcecatCampaignGallery entity) {
		entity.setLogoPicture(elt.getLogoPic());
		entity.setThumbPicture(elt.getThumbPic());
		return entity;
	}

}
