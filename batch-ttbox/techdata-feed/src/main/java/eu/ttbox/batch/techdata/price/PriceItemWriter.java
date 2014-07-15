package eu.ttbox.batch.techdata.price;

import eu.ttbox.batch.techdata.dao.SupplierOfferDAO;
import eu.ttbox.batch.techdata.price.diff.PriceDifferentialItem;
import eu.ttbox.model.supplier.SupplierEnum;
import eu.ttbox.model.supplier.SupplierFeedStatus;
import eu.ttbox.model.supplier.SupplierPrice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("priceTechdataItemWriter")
public class PriceItemWriter implements ItemWriter<PriceDifferentialVO> {

	@Autowired
	SupplierOfferDAO supplierOfferDAO;

	private Logger log = LoggerFactory.getLogger(getClass());
 

	@Override
	public void write(List<? extends PriceDifferentialVO> items) throws Exception {
		for (PriceDifferentialVO item : items) { 
			//
			SupplierFeedStatus status  = new SupplierFeedStatus();
			status.setFileLastModified(item.getFileLastModified());
			status.setFileSizeInBytes(item.getFileSizeInBytes());
			status.setSupplier(SupplierEnum.TECHDATA);
			status.setSupplierId(item.getTechId());
			// Update
			List<SupplierPrice> updates =  item.getUpdates();
			supplierOfferDAO.saveOfferUpdates(status, updates);
//			for (SupplierPrice vo : updates) {
//				log.info("Update : {} ", vo);
//			}
			// Create 
			List<PriceDifferentialItem<SupplierPrice, FieldSet>> creates =  item.getCreates();
			supplierOfferDAO.saveOfferCreates(status, creates); 
//			for (SupplierPrice vo : creates) {
//				log.info("Create : {} ", vo);
//			}
			// Delete 
			List<SupplierPrice> deletes =  item.getDeletes();
			supplierOfferDAO.saveOfferDeletes(status, deletes); 
			for (SupplierPrice vo : deletes) {
				log.info("Delete : {} ", vo);
			}

		}
		
	}

}
