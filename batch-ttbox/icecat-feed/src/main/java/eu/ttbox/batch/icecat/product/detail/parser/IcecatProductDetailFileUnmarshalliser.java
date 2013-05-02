package eu.ttbox.batch.icecat.product.detail.parser;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.oxm.MarshallingException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;

import biz.icecat.referential.v1.ICECATRefInterface;
import biz.icecat.referential.v1.Product;
import eu.ttbox.batch.core.fs.FileUtils;

@Service
public class IcecatProductDetailFileUnmarshalliser {

	protected Logger log = LoggerFactory.getLogger(getClass());

	int bufferSize = 20480;

	@Autowired
	@Qualifier("icecatRefMarshaller")
	Jaxb2Marshaller marshaller;

	public Product convertFileToProduct(File resultDest) {
		Product product = null;
		if (resultDest != null && resultDest.exists()) {
			try {
				InputStream is = new BufferedInputStream(new FileInputStream(resultDest), bufferSize);
				try {
					if (FileUtils.isGzipFile(resultDest)) {
						is = new GZIPInputStream(is, bufferSize);
					}
					ICECATRefInterface root = (ICECATRefInterface)marshaller.unmarshal(new StreamSource(is));
					 product = root.getProduct();
				} finally {
					is.close(); 
				}
			} catch (FileNotFoundException e) {
				// log.error(e.getMessage()+ " for file " +resultDest , e);
				throw new RuntimeException("FileNotFoundException " + e.getMessage() + " for file " + resultDest, e);
			} catch (IOException e) {
				// log.error(e.getMessage()+ " for file " +resultDest , e);
				// resultDest.delete();
				boolean deleted = resultDest.delete();
				throw new RuntimeException("IOException " + e.getMessage() + " for file " + resultDest + " (deleted=" + deleted + ")", e);
			} catch (MarshallingException e) {
				// log.error(e.getMessage()+ " for file " +resultDest , e);
				boolean deleted = resultDest.delete();
				throw new RuntimeException("JAXBException " + e.getMessage() + " for file " + resultDest + " (deleted=" + deleted + ")", e);
			}
		} else {
			log.info("The file {} don't exist ignore", resultDest);
		}

		return product;
	}
}
