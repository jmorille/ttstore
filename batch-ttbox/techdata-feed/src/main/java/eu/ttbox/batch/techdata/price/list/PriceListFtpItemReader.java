package eu.ttbox.batch.techdata.price.list;

import eu.ttbox.batch.techdata.core.fs.TechdataCacheDownloadConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.*;
import org.springframework.batch.item.support.ListItemReader;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PriceListFtpItemReader implements ItemReader<File>, ItemStream {

	private static Logger log = LoggerFactory.getLogger(PriceListFtpItemReader.class);

	TechdataCacheDownloadConnector downloadConnector;
	
	String filenamePattern;
	
	 
	public void setDownloadConnector(TechdataCacheDownloadConnector techdataProxyCacheFtpConnector) {
		this.downloadConnector = techdataProxyCacheFtpConnector;
	}


	public void setFilenamePattern(String relativePath) {
		this.filenamePattern = relativePath;
	}

	private ItemReader<File>  delegated; 
	
	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		 try {
			List<File> files  = downloadConnector.downloadTechPrices(filenamePattern);
			log.info("Register {} files to ItemReader", files.size());
			delegated= new ListItemReader<File>(files);
		} catch (IOException e) {
			throw new ItemStreamException("Error in getting Files List " + filenamePattern+
					" : " + e.getMessage(), e);
		}
		
	}
	
	
	@Override
	public File read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		 if (delegated!=null  ) {
			 File currentFile = delegated.read();
			 log.debug("Read Local File {}", currentFile);
			 return currentFile;
		 }
		return null;
	}



	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() throws ItemStreamException {
		delegated = null;
		
	}

}
