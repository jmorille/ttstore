package eu.ttbox.batch.techdata.stock;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.transform.FieldSet;

//@Service("stockTechdataItemWriter")
public class StockItemWriter implements ItemWriter<FieldSet> {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public void write(List<? extends FieldSet> items) throws Exception {
		for (FieldSet item : items) {
			log.info("line : {} ", item);
		}

	}

}
