package eu.ttbox.batch.icecat.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;

import biz.icecat.referential.v1.ICECATRefInterface;
import biz.icecat.referential.v1.Product;
import eu.ttbox.batch.core.fs.GzipResource;

@Service
public class IcecatJaxb2Marshaller {

	@Autowired
	@Qualifier("icecatRefMarshaller")
	Jaxb2Marshaller icecatRefMarshaller;

	public Product convertFileToProduct(File productFile) throws IOException {
		ICECATRefInterface ref = (ICECATRefInterface) convertFileTo(productFile, icecatRefMarshaller);
		Product product = ref.getProduct();
		return product;
	}

	private Object convertFileTo(File localFile, Jaxb2Marshaller marshaller) throws IOException {
		Object result = null;
		if (localFile.exists()) {
			InputStream is = new GzipResource(localFile).getInputStream();
			try {
				result = marshaller.unmarshal(new StreamSource(is));
			} finally {
				is.close();
			}
		}
		return result;
	}
}
