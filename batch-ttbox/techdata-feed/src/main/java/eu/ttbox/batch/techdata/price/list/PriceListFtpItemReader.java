package eu.ttbox.batch.techdata.price.list;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.ListItemReader;

import eu.ttbox.batch.techdata.core.fs.TechdataCacheDownloadConnector;

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
