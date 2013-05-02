package eu.ttbox.batch.icecat.campaign;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import biz.icecat.campaigns.v1.Campaign;
import eu.ttbox.batch.icecat.dao.IcecatDAO;
import eu.ttbox.batch.icecat.referential.model.IIcecatModelCreator;
import eu.ttbox.icecat.model.campaign.IcecatCampaign;

@Service("campaignListIcecatItemWriter")
public class CampaignListItemWriter implements ItemWriter<Campaign> {

	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("icecatDAO")
	IcecatDAO icecatDAO;

	@Autowired
	@Qualifier("icecatCampaignModelCreator")
	IIcecatModelCreator<IcecatCampaign, Campaign> campaignModelCreator;

	@Override
	public void write(List<? extends Campaign> items) throws Exception {
		for (Campaign ifile : items) {
			// log.debug("Campaign : {}", ifile);
			doImport(ifile);
		}

	}

	private int doImport(Campaign campaign) {
		int importCount = 0;
		IcecatCampaign entity = this.campaignModelCreator.doImport(campaign);
		icecatDAO.flushAndClear();
		if (entity != null) {
			importCount++;
		}
		return importCount;
	}
}
