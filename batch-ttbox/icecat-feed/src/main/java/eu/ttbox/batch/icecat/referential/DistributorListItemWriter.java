package eu.ttbox.batch.icecat.referential;

import biz.icecat.referential.v1.DistributorList.Distributor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("distributorListIcecatItemWriter")
public class DistributorListItemWriter implements ItemWriter<Distributor> {

	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public void write(List<? extends Distributor> items) throws Exception {
		for (Distributor ifile : items) {
			log.debug("Distributor : {}", ifile);
		}

	}

}
