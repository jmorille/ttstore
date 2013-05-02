package eu.ttbox.batch.techdata.stock;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Objects;

import eu.ttbox.batch.core.reader.differential.DifferentialItem;
import eu.ttbox.batch.core.reader.differential.DifferentialItem.CUDStatus;
import eu.ttbox.batch.techdata.core.converter.ProductConverter;
import eu.ttbox.model.product.CatalogSrcEnum;
import eu.ttbox.model.product.Product;
import eu.ttbox.model.supplier.SupplierEnum;
import eu.ttbox.model.supplier.SupplierStock;

@Service("stockTechdataItemProcessor")
public class StockItemProcessor implements
		ItemProcessor<DifferentialItem<SupplierStock, FieldSet>, DifferentialItem<SupplierStock, FieldSet>> {

	private static Logger log = LoggerFactory.getLogger(StockItemProcessor.class);

 	@Autowired
 	ProductConverter productConverter;
 	
 	int ignoreForNoProduct = 0;
 	

	
	@Override
	public DifferentialItem<SupplierStock, FieldSet> process(DifferentialItem<SupplierStock, FieldSet> item)
			throws Exception {
		if (CUDStatus.DELETE.equals( item.getStatus())) {
			return item;
		}
		// init variable
		SupplierStock entity = item.getMasterItem();
		FieldSet fs = item.getJoinItem();
		boolean isSame = true;
		if (entity==null) {
			Product product = productConverter.getProductByTechId(StockFieldEnum.TechId.readString(fs));
			if (product==null) {
				ignoreForNoProduct++;
//				log.warn("Ignore Stock for no product for line {}", fs);
				return null;
			}
			entity =  new SupplierStock();
			item.setMasterItem(entity); 
			entity.setType( SupplierEnum.TECHDATA.name());
			entity.setProduct(product);
			isSame = false;
		}
		// Quantity 
 		Integer quantity = StockFieldEnum.Quantity.readInteger(fs);
		if (!Objects.equal(quantity, entity.getQuantity())) {
			entity.setQuantity(quantity);
			isSame = false;
		}
		
		Date nextDeliveryDate = StockFieldEnum.NextDeliveryDate.readDate(fs);
		if (!Objects.equal(nextDeliveryDate, entity.getNextDeliveryDate())) {
			entity.setNextDeliveryDate(nextDeliveryDate);
			isSame = false;
		}
		
		// Return
		if (isSame) {
			return null;
		} else {
			return item;
		} 
	}
}
