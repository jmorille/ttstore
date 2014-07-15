package eu.ttbox.batch.techdata.material.consult;

import eu.ttbox.model.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

@Service
public class MaterialConsulterProcessor implements ItemProcessor<Product, Product>{

	private static Logger log = LoggerFactory.getLogger(MaterialConsulterProcessor.class);
	
	@Override
	public Product process(Product item) throws Exception {
		// Consult Data
		log.info("Product category {}", item.getCategory());
		log.info("Product brand {}", item.getBrand());
		
		
		return item;
	}

}
