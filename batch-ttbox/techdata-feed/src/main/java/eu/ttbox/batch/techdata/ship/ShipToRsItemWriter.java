package eu.ttbox.batch.techdata.ship;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("shipToRsTechdataItemWriter")
public class ShipToRsItemWriter implements ItemWriter<FieldSet> {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public void write(List<? extends FieldSet> items) throws Exception {
		for (FieldSet item : items) {
			log.info("line : {} ", item);
		}

	}

}
