package eu.ttbox.batch.icecat.product.detail.model;

import java.io.Reader;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Assert;
import org.junit.Test;

import biz.icecat.files.v1.IcecatFile;

public class UnmarsallingTest {

	@Test
	public void testUnmarshalling() throws Exception {
		JAXBContext jc = JAXBContext.newInstance(IcecatFile.class);
		IcecatFile prod = new IcecatFile();
		// Marshall
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.marshal(prod, System.out);

		// Unmarshall
		Reader reader = new StringReader("<file On_Market=\"false\"/>");
		Unmarshaller u = jc.createUnmarshaller();
		IcecatFile prodUn = (IcecatFile) u.unmarshal(reader);
		Assert.assertNull(prodUn.getPath());

	}

}
