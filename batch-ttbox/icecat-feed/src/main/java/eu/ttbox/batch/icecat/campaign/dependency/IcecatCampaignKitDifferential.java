package eu.ttbox.batch.icecat.campaign.dependency;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import biz.icecat.campaigns.v1.Product;
import eu.ttbox.batch.icecat.referential.dependency.AbstractDependencyDifferential;
import eu.ttbox.icecat.model.campaign.IcecatCampaign;
import eu.ttbox.icecat.model.campaign.IcecatCampaignKit;
import eu.ttbox.icecat.model.product.IcecatProduct;

@Transactional
@Service("icecatCampaignKitDifferential")
public class IcecatCampaignKitDifferential extends AbstractDependencyDifferential<IcecatCampaign, IcecatCampaignKit, Product> {

	@Override
	public Integer getFeedElementId(Product elt) {
		return elt.getID();
	}

	@Override
	public boolean isValidEntity(IcecatCampaignKit entity) {
		if (entity.getProduct() == null) {
			return false;
		}
		if (entity.getCampaign() == null) {
			return false;
		}
		if (entity.getId() == null) {
			return false;
		}
		log.debug("Entity Valid {}", entity);
		return true;
	}

	@Override
	public boolean isValidFeed(Product elt) {
		if (elt.getID() == null) {
			return false;
		}
		return true;
	}

	@Override
	public IcecatCampaignKit createEntity(IcecatCampaign master, Product elt) {
		IcecatCampaignKit entity = new IcecatCampaignKit();
		entity.setId(elt.getID());
		entity.setCampaign(master);
		// log.debug("Entity Create  {}", entity);
		return entity;
	}

	@Override
	public IcecatCampaignKit updateEntity(IcecatCampaign master, Product elt, IcecatCampaignKit entity) {
		entity.setClickthroughCount(elt.getClicks());
		entity.setUpdated(master.getUpdated());
		entity.setCampaign(master);
		// Product
		if (elt.getID() != null && elt.getID() != null) {
			Integer productId = elt.getID();
			boolean needUpdate = true;
			if (!needUpdate) {
				needUpdate = !productId.equals(entity.getId());
			}
			if (needUpdate) {
				IcecatProduct product = (IcecatProduct) getIcecatDAO().getById(productId, IcecatProduct.class);
				entity.setProduct(product);
			}
		} else {
			entity.setProduct(null);
		}
		// log.debug("Entity Update  {}", entity);
		return entity;
	}

}
