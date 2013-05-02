package eu.ttbox.icecat.model.product;

import java.io.StringWriter;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.junit.Test;

import eu.ttbox.icecat.model.IProductIndex;

public class IcecatProductTest {

	@Test
	public void jsonLightProduct() throws Exception {
		IcecatProduct product = new IcecatProduct();
		product.setName("Galaxy S2");
		product.setPartNumber("I9100");
		product.addEans("2000006035103");
		
		
		// Config Marshall
		ObjectMapper mapper = new ObjectMapper();
//		mapper.configure(Feature.DEFAULT_VIEW_INCLUSION, false);
//		mapper.getSerializationConfig().withView(IProductIndex.class);
		mapper.getSerializationConfig().addMixInAnnotations(IcecatProduct.class, IProductIndex.class);
		mapper.getSerializationConfig().withSerializationInclusion(Inclusion.NON_NULL);
 		//  Marshall
		StringWriter sb = new StringWriter(512);
		mapper.writeValue(sb, product); 
//		mapper.writerWithView(IProductIndex.class).writeValue(sb, product); 
		// Print Product
		System.out.println(sb.toString());
		
	}

}
