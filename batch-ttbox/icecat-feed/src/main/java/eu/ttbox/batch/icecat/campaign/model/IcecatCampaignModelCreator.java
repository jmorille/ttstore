package eu.ttbox.batch.icecat.campaign.model;

import biz.icecat.campaigns.v1.Campaign;
import biz.icecat.campaigns.v1.Image;
import biz.icecat.campaigns.v1.Product;
import eu.ttbox.batch.icecat.referential.AbstractReferentialItemWriter;
import eu.ttbox.batch.icecat.referential.dependency.IDependencyDifferential;
import eu.ttbox.batch.icecat.referential.model.AbstractIcecatModelCreatorImpl;
import eu.ttbox.icecat.model.campaign.IcecatCampaign;
import eu.ttbox.icecat.model.campaign.IcecatCampaignGallery;
import eu.ttbox.icecat.model.campaign.IcecatCampaignKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Service("icecatCampaignModelCreator")
public class IcecatCampaignModelCreator extends AbstractIcecatModelCreatorImpl<IcecatCampaign, Campaign> {

	@Autowired
	@Qualifier("icecatCampaignGalleryDifferential")
	IDependencyDifferential<IcecatCampaign, IcecatCampaignGallery, Image> campaignGalleryDifferential;

	@Autowired
	@Qualifier("icecatCampaignKitDifferential")
	IDependencyDifferential<IcecatCampaign, IcecatCampaignKit, Product> campaignKitDifferential;

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public Integer getFeedElementId(Campaign elt) {
		return elt.getID();
	}

	@Override
	public IcecatCampaign createEntity(Campaign elt) {
		Integer entityId = elt.getID();
		IcecatCampaign entity = new IcecatCampaign();
		entity.setId(entityId);
		entity.setUserId(AbstractReferentialItemWriter.DEFAULT_USER_ID);
		return entity;
	}

	@Override
	public IcecatCampaign updateEntity(Campaign campaign, IcecatCampaign entity) {
		entity.setName(campaign.getName());
		entity.setShortDescription(campaign.getTitle());
		entity.setLink(campaign.getMotivation());
		entity.setLink(campaign.getLandingPage());
		Date updated = null;
		try {
			updated = dateFormat.parse(campaign.getUpdated());
		} catch (ParseException e) {
			log.error("Error parsing date " + campaign.getUpdated() + " Use Now as Date : " + e.getMessage(), e);
			updated = new Date();
		}
		entity.setUpdated(updated);
		//
		getIcecatDAO().saveObject(entity);
		getIcecatDAO().flush();

		// Campaign Gallery Dependencies
		List<IcecatCampaignGallery> entityGalleries = entity.getGalleries();
		if (entityGalleries == null) {
			entityGalleries = new ArrayList<IcecatCampaignGallery>();
			entity.setGalleries(entityGalleries);
		}
		List<Image> imagesList = null;
		if (campaign.getImagesList() != null) {
			imagesList = campaign.getImagesList().getImages();
		}
		this.campaignGalleryDifferential.doImportDependencies(entity, entityGalleries, imagesList);
		getIcecatDAO().flush();

		// Campaign Kit Dependencies
		List<IcecatCampaignKit> kits = entity.getKits();
		if (kits == null) {
			kits = new ArrayList<IcecatCampaignKit>();
			// entity.setKits(kits);
		}
		List<Product> campaignProducts = null;
		if (campaign.getProductsList() != null) {
			campaignProducts = campaign.getProductsList().getProducts();
		}
		this.campaignKitDifferential.doImportDependencies(entity, kits, campaignProducts);
		getIcecatDAO().flush();

		return entity;
	}

}
