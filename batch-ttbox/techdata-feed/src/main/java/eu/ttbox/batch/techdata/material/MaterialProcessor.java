package eu.ttbox.batch.techdata.material;

import com.google.common.base.Objects;
import eu.ttbox.batch.core.reader.differential.DifferentialItem;
import eu.ttbox.batch.techdata.core.converter.BrandConverter;
import eu.ttbox.batch.techdata.core.converter.CategoryConverter;
import eu.ttbox.model.product.CatalogSrcEnum;
import eu.ttbox.model.product.Product;
import eu.ttbox.model.product.brand.Brand;
import eu.ttbox.model.product.category.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service("materialTechdataProcessor")
public class MaterialProcessor implements
		ItemProcessor<DifferentialItem<Product, FieldSet>, DifferentialItem<Product, FieldSet>> {

	private static Logger log = LoggerFactory.getLogger(MaterialProcessor.class);

	int countIgnoreLicence = 0;
	int countIgnoreNoPricePublic = 0;

	@Autowired
	CategoryConverter categoryConverter;

	@Autowired
	BrandConverter brandConverter;

	@Override
	public DifferentialItem<Product, FieldSet> process(DifferentialItem<Product, FieldSet> item) throws Exception {
		if (DifferentialItem.CUDStatus.DELETE.equals(item.getStatus())) {
			return item;
		}
		// init variable
		Product product = item.getMasterItem();
		FieldSet fs = item.getJoinItem();

		// --- Validate ---
		// -----------------
		// Exclude licence
		boolean isValid = true;
		if (MaterialFieldEnum.Licence.readBoolean(fs)) {
			isValid = false;
			countIgnoreLicence++;
		}
		BigDecimal pricePub = null;
		if (isValid) {
			pricePub = MaterialFieldEnum.ListPrice.readBigDecimal(fs);
			if (pricePub == null) {
				isValid = false;
				countIgnoreNoPricePublic++;
			}
		}

		// Manage Invalid
		// -----------------
		if (!isValid) {
			if (DifferentialItem.CUDStatus.UPDATE.equals(item.getStatus())) {
				log.info("Change invalid product {} to Delete Status", item.getMasterItem().getId());
				item.setStatus(DifferentialItem.CUDStatus.DELETE);
				return item;
			}
			return null;
		}

		// Manage Valid
		// -----------------
		boolean isSame = true;
		if (product == null) {
			product = new Product();
			product.addSrc(CatalogSrcEnum.TECHDATA, MaterialFieldEnum.TechId.readString(fs), null);
			item.setMasterItem(product);
			isSame = false;
		}
		// Ids
		if (  !isEqualOrpdateCnetMapping(item)) {
			isSame = false;
		}

		// Descriptions
		if (  !isEqualOrUpdateTechDataDescription(item)) {
			isSame = false;
		}

		// Create Date and Weight
		if (  !isEqualOrUpdateTechDataPriceWeight(item)) {
			isSame = false;
		}

		// Price Public
		if (  !isEquals(pricePub, product.getPriceHT())) {
			product.setPriceHT(pricePub);
			isSame = false;
		}

		// Brand
		if (  !isEqualOrUpdateBrand(item)) {
			isSame = false;
		}
		// Category
		if (  !isEqualOrUpdateCategory(item)) {
			isSame = false;
		}

//		log.warn("Not Managed Material Field {} = {}", "ProdFamilyID", MaterialFieldEnum.ProdFamilyID.readString(fs));
//		log.warn("Not Managed Material Field {} = {}", "ProdFamily", MaterialFieldEnum.ProdFamily.readString(fs));
//		
//		log.warn("Not Managed Material Field {} = {}", "ProdClassID", MaterialFieldEnum.ProdClassID.readString(fs));
//		log.warn("Not Managed Material Field {} = {}", "ProdClass", MaterialFieldEnum.ProdClass.readString(fs));
//		
//		log.warn("Not Managed Material Field {} = {}", "ProdSubclassID", MaterialFieldEnum.ProdSubclassID.readString(fs));
//		log.warn("Not Managed Material Field {} = {}", "ProdSubclass", MaterialFieldEnum.ProdSubclass.readString(fs));
// 
		// MaterialFieldEnum.ProdFamilyID;
		// MaterialFieldEnum.ProdFamily;
		// MaterialFieldEnum.ProdClassID;
		// MaterialFieldEnum.ProdClass;
		// MaterialFieldEnum.ProdSubclassID;
		// MaterialFieldEnum.ProdSubclass;
		// MaterialFieldEnum.Weight;
		//
		if (isSame) {
			return null;
		} else {
			return item;
		}
	}

	private boolean isEquals(BigDecimal feedVal, BigDecimal refVal) {
		boolean isSame = (feedVal == null ? refVal == null : feedVal.compareTo((refVal == null ? BigDecimal.ZERO
				: refVal)) == 0);
		return isSame;
	}

	private boolean isEqualOrUpdateBrand(DifferentialItem<Product, FieldSet> item) {
		Product entity = item.getMasterItem();
		FieldSet fs = item.getJoinItem();

		Brand brand = entity.getBrand();
		String techId = MaterialFieldEnum.Manufacturer.readString(fs);

		boolean needUpdate = false;
		if (techId != null) {
			Brand brandConv = brandConverter.getBrandByTechId(techId);
			needUpdate = brandConv==null?brand!=null: brand==null?true: !brand.getId().equals(brandConv.getId());
			if (needUpdate) {
				brand = brandConv;
			}
		} else if (brand!= null) {
			brand = null;
			needUpdate = true;
		}
		if (needUpdate) {
			entity.setBrand(brand);
		}
		return !needUpdate;
	}

	private boolean isEqualOrUpdateCategory(DifferentialItem<Product, FieldSet> item) {
		Product entity = item.getMasterItem();
		FieldSet fs = item.getJoinItem();

		Category category = entity.getCategory(); 
		String techId = MaterialFieldEnum.ProdSubclassID.readString(fs); 
		boolean needUpdate = false;
		if (techId != null) {
			String name = MaterialFieldEnum.ProdSubclass.readString(fs);
			Category categoryConv = categoryConverter.getCategoryByTechId(techId, name);
			needUpdate = categoryConv==null?category!=null: category==null?true: !category.getId().equals(categoryConv.getId());
			if (needUpdate) {
				category = categoryConv;
			}
		} else if (category!= null) {
				needUpdate = true;
				category = null; 
		}
		if (needUpdate) {
			entity.setCategory(category);
		}
		return !needUpdate;
	}

	private boolean isEqualOrpdateCnetMapping(DifferentialItem<Product, FieldSet> item) {
		boolean isSame = true;
		Product product = item.getMasterItem();
		FieldSet fs = item.getJoinItem();
		// Mapping
		String cnetId = MaterialFieldEnum.CnetId.readString(fs);
		String ean = MaterialFieldEnum.EAN.readString(fs);
		String partNumber = MaterialFieldEnum.ManufPartNr.readString(fs);
		// Compare
		if (!Objects.equal(cnetId, product.getExtIdsNotNull().getCnetId())) {
			product.getExtIdsNotNull().setCnetId(cnetId);
			isSame = false;
		}
		if (!Objects.equal(ean, product.getEan())) {
			product.setEan(ean);
			if (ean != null && ean.length() > 13) {
				product.setEan(null);
			} else {
				isSame = false;
			}
		}
		if (!Objects.equal(partNumber, product.getPartNumber())) {
			product.setPartNumber(partNumber);
			isSame = false;
		}
		// TODO Manage ratachement
		return isSame;
	}

	private boolean isEqualOrUpdateTechDataPriceWeight(DifferentialItem<Product, FieldSet> item) {
		Product product = item.getMasterItem();
		FieldSet fs = item.getJoinItem();
		boolean isSame = true;
		// Creation Date
		Date articleCreationDate = MaterialFieldEnum.ArticleCreationDate.readDate(fs);
		if (!Objects.equal(articleCreationDate, product.getArticleCreationDate())) {
			product.setArticleCreationDate(articleCreationDate);
			isSame = false;
		}
		// TODO MaterialFieldEnum.Weight;
		return isSame;
	}

	private boolean isEqualOrUpdateTechDataDescription(DifferentialItem<Product, FieldSet> item) {
		Product product = item.getMasterItem();
		FieldSet fs = item.getJoinItem();
		boolean isSame = true;
		String name = MaterialFieldEnum.ShortDescription.readString(fs);
		if (!Objects.equal(name, product.getName())) {
			product.setName(name);
			isSame = false;
		}

		String desc = MaterialFieldEnum.LongDescription.readString(fs);
		if (!Objects.equal(desc, product.getDescription())) {
			product.setDescription(desc);
			isSame = false;
		}
		boolean isKit = MaterialFieldEnum.Kit.readBoolean(fs);
		if (isKit != product.isKit()) {
			product.setKit(isKit);
			isSame = false;

		}
		return isSame;
	}

}
