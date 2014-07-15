package eu.ttbox.batch.techdata.tax;

import com.google.common.base.Objects;
import eu.ttbox.batch.core.reader.differential.DifferentialItem;
import eu.ttbox.batch.techdata.core.converter.ProductConverter;
import eu.ttbox.model.product.Product;
import eu.ttbox.model.supplier.SupplierTax;
import eu.ttbox.model.supplier.TaxGouvEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service("taxTechdataItemComparator")
public class TaxItemProcessor implements
		ItemProcessor<DifferentialItem<SupplierTax, FieldSet>, DifferentialItem<SupplierTax, FieldSet>> {

	private static Logger log = LoggerFactory.getLogger(TaxItemProcessor.class);

	@Autowired
	ProductConverter productConverter;
	

	
	@Override
	public DifferentialItem<SupplierTax, FieldSet> process(DifferentialItem<SupplierTax, FieldSet> item)
			throws Exception {
		// init variable
		SupplierTax entity = item.getMasterItem();
		FieldSet fs = item.getJoinItem();
		boolean isSame = true;
		if (entity == null) {
			entity = new SupplierTax();
			item.setMasterItem(entity);
			String techId = TaxFieldEnum.TechId.readString(fs);
			Product product = productConverter.getProductByTechId(techId);
			if (product==null) {
				return null;
			}
			entity.setProduct(product); 
			isSame = false;
		}
		// Tax Type
		String taxType = TaxFieldEnum.TaxType.readString(fs);
		TaxGouvEnum taxTypeEnum = TaxGouvEnum.valueOf(taxType);
		if (taxTypeEnum != null) {
			if (!Objects.equal(taxTypeEnum, entity.getType())) {
				entity.setType(taxTypeEnum);
				isSame = false;
			}
		} else {
			log.warn("No TaxGouvEnum define for the enum value {} \t  => Ignore feed {}", taxType, fs);
			return  null;
		}

		// Tax Value
		BigDecimal taxValue = TaxFieldEnum.TaxValue.readBigDecimal(fs);
		if (!Objects.equal(taxValue, entity.getValue())) {
			entity.setValue(taxValue);
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
