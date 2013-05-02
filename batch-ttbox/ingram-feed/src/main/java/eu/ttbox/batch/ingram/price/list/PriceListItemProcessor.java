package eu.ttbox.batch.ingram.price.list;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.transform.FieldSet;

import eu.ttbox.batch.core.fs.GzipResource;
import eu.ttbox.batch.core.reader.differential.DifferentialFileHibernateItemReader;
import eu.ttbox.batch.ingram.price.diff.PriceDifferentialItem;
import eu.ttbox.batch.ingram.price.diff.SupplierPriceIngramItemProcessor;
import eu.ttbox.model.supplier.SupplierEnum;
import eu.ttbox.model.supplier.SupplierPrice;

public class PriceListItemProcessor implements ItemProcessor<PriceDifferentialVO, PriceDifferentialVO> {

	private static final Logger LOG = LoggerFactory.getLogger(PriceListItemProcessor.class);

	// Reader
	DifferentialFileHibernateItemReader<SupplierPrice, FieldSet> differentialItemReader;
	// Processor
	SupplierPriceIngramItemProcessor itemProcessor;

	Pattern filenamePattern;

	int filenamePatternGroupId = 1;

	public void setDifferentialItemReader(DifferentialFileHibernateItemReader<SupplierPrice, FieldSet> differentialItemReader) {
		this.differentialItemReader = differentialItemReader;
	}

	public void setItemProcessor(SupplierPriceIngramItemProcessor itemProcessor) {
		this.itemProcessor = itemProcessor;
	}

	public void setFilenamePattern(String filenamePattern) {
		this.filenamePattern = Pattern.compile(filenamePattern);
	}

	@Override
	public PriceDifferentialVO process(PriceDifferentialVO item) throws Exception {
		File localFile = item.getSupplierFile();
		Matcher m = filenamePattern.matcher(localFile.getName());
		String supplierId = null;
		if (m.matches()) {
			supplierId = m.group(filenamePatternGroupId);
			LOG.info("SupplierPrice[{}] with filemname {}", supplierId, item.getSupplierFile());
		}
		// Init Master
		Map<String, Object> parameterValues = new HashMap<String, Object>();
		parameterValues.put("supplier", SupplierEnum.INGRAM);
		parameterValues.put("supplierId", supplierId);
		differentialItemReader.setMasterResource(parameterValues);
		// Init Join
		differentialItemReader.setJoinResource(new GzipResource(localFile));
		// Read the differential
		boolean isResult = false;
		try {
			ExecutionContext executionContext = new ExecutionContext();
			differentialItemReader.open(executionContext);
			PriceDifferentialItem<SupplierPrice, FieldSet> line;
			int i = 0;
			long begin = System.currentTimeMillis();
			while ((line = (PriceDifferentialItem<SupplierPrice, FieldSet>) differentialItemReader.read()) != null) {
				i++;
				// if (i % 1000==0) {
				// log.info("Read Line {}", i);
				// }
				line.parent = item;
				line = itemProcessor.process(line);
				if (line != null) {
					isResult = true;
					item.add(line);
				}
			}
			long end = System.currentTimeMillis();
			LOG.info("SupplierPrice[{}] : {} Lines read in {} ms", new Object[] { supplierId, i, (end - begin) });
		} finally {
			differentialItemReader.close();
		}

		// TODO Delete promoDetail.getPromoFreeProductSku()

		// Validate Delta
		if (item.getCountDeletes() > (item.getCountRetrieve() / 10)) {
			LOG.warn("/!\\ /!\\ ROLLBACK  file {} /!\\ /!\\", item);
			// rollback();
			return null;
		}
		// Manage result
		if (!isResult) {
			isResult = item.products.getCountCUD() > 0;
		}
		if (isResult) {
			LOG.info("Supplier Price file Id {} as {}", supplierId, item);
			return item;
		} else {
			return null;
		}

	}

}
