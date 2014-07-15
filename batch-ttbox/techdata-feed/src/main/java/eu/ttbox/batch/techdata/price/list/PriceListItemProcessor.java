package eu.ttbox.batch.techdata.price.list;

import eu.ttbox.batch.core.fs.GzipResource;
import eu.ttbox.batch.core.reader.differential.DifferentialFileHibernateItemReader;
import eu.ttbox.batch.techdata.price.PriceDifferentialVO;
import eu.ttbox.batch.techdata.price.PriceItemProcessor;
import eu.ttbox.batch.techdata.price.diff.PriceDifferentialItem;
import eu.ttbox.model.supplier.SupplierEnum;
import eu.ttbox.model.supplier.SupplierPrice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.transform.FieldSet;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

 
public class PriceListItemProcessor implements ItemProcessor<File, PriceDifferentialVO>{

	private static Logger log = LoggerFactory.getLogger(PriceListItemProcessor.class);
	
	// Reader 
	DifferentialFileHibernateItemReader<SupplierPrice, FieldSet> differentialItemReader;
	// Processor
	PriceItemProcessor itemProcessor;
	
	Pattern filenamePattern;
	
	int filenamePatternGroupId = 1;
	
	public void setDifferentialItemReader(DifferentialFileHibernateItemReader<SupplierPrice, FieldSet> differentialItemReader) {
		this.differentialItemReader = differentialItemReader;
	}



	public void setItemProcessor(PriceItemProcessor itemProcessor) {
		this.itemProcessor = itemProcessor;
	}



	public void setFilenamePattern(String filenamePattern) {
		this.filenamePattern = Pattern.compile(filenamePattern) ;
	}



	@Override
	public PriceDifferentialVO process(File item) throws Exception {
		Matcher m = filenamePattern.matcher(item.getName());
		String supplierId  = null;
		if (m.matches()) {
			supplierId  = m.group(filenamePatternGroupId);
			log.info("Select File Id {} for filemname {}", supplierId, item);
		}
		// Init Master
		Map<String, Object> parameterValues  = new HashMap<String, Object>();
		parameterValues.put("supplier", SupplierEnum.TECHDATA);
		parameterValues.put("supplierId", supplierId);
		differentialItemReader.setMasterResource(parameterValues);
		// Init Join
		differentialItemReader.setJoinResource(new GzipResource(item));
		// Read the differential  
		long fileSize = item.length();
		PriceDifferentialVO result =new PriceDifferentialVO(supplierId, new Date(item.lastModified()),fileSize );
		boolean isResult = false;
		try {
			ExecutionContext executionContext = new ExecutionContext();
			differentialItemReader.open(executionContext);
			PriceDifferentialItem<SupplierPrice, FieldSet> line ;
			int i = 0;
			long begin = System.currentTimeMillis();
			while ((line = (PriceDifferentialItem)differentialItemReader.read()) !=null) {
				i++;
//				if (i % 1000==0) {
//					log.info("Read Line {}", i);
//				}
				line = itemProcessor.process(line);
				if (line!=null) {
					isResult=true;
					result.add(  line );
				}
			}
			long end = System.currentTimeMillis();
			log.info("Read Line {} End in {} ms", i, (end-begin));
		} finally {
			differentialItemReader.close();
		}
		// TODO Validate Delta
		if (result.getCountDeletes() > (result.getCountRetrieve() / 10)) {
			log.warn( "/!\\ ROLLBACK  file {} ", item); 
			// rollback();
			return null;
		}
		// Values
		if (isResult) {
			return result;
		} else {
			return null;
		}
	
	}

}
