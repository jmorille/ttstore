package eu.ttbox.batch.icecat.product.detail.dependency;

import biz.icecat.referential.v1.ProductDescription;
import eu.ttbox.batch.icecat.referential.dependency.AbstractLangDependencyDifferential;
import eu.ttbox.icecat.model.product.IcecatProduct;
import eu.ttbox.icecat.model.product.IcecatProductDescription;
import eu.ttbox.icecat.model.referential.IcecatLanguageEnum;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Service("icecatProductDescriptionDifferential")
public class IcecatProductDescriptionDifferential extends AbstractLangDependencyDifferential<IcecatProduct, IcecatProductDescription, ProductDescription> {

	@Override
	public Integer getFeedElementId(ProductDescription elt) {
		return elt.getID();
	}

	@Override
	public Integer getFeedLangId(ProductDescription elt) {
		return elt.getLangid();
	}

	@Override
	public boolean isValidEntity(IcecatProductDescription entity) {
		if (entity.getLangid() == null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isValidFeed(ProductDescription elt) {
		return true;
	}

	@Override
	public IcecatProductDescription createEntity(IcecatProduct product, ProductDescription desc) {
		Integer entityId = desc.getID();
		IcecatProductDescription entity = new IcecatProductDescription();
		entity.setId(entityId);
		entity.setProduct(product);
		entity.setUpdated(product.getUpdated());
		return entity;
	}

	@Override
	public IcecatProductDescription updateEntity(IcecatProduct product, ProductDescription desc, IcecatProductDescription entity) {

		// Lang Id
		Integer langId = desc.getLangid();
		entity.setLangid(IcecatLanguageEnum.getByLangId(langId));

		// Pdf
		entity.setPdfURL(StringUtils.trimToNull(desc.getPDFURL()));
		if (desc.isSetPDFSize()) {
			entity.setPdfSize(desc.getPDFSize());
		} else {
			entity.setPdfSize(null);
		}

		// Manual
		entity.setManualPdfURL(StringUtils.trimToNull(desc.getManualPDFURL()));
		if (desc.isSetManualPDFSize()) {
			entity.setManualPdfSize(desc.getManualPDFSize());
		} else {
			entity.setManualPdfSize(null);
		}

		// Official Url
		entity.setOfficialURL(StringUtils.trimToNull(desc.getURL()));

		// Long Desc
		String longDesc = StringUtils.trimToNull(desc.getLongDesc());
		// TODO Parse to Html longDesc = left(longDesc, 9000, "Description");
		entity.setLongDescription(longDesc);
		// Short Desc
		entity.setShortDescription(StringUtils.trimToNull(desc.getShortDesc()));
		// Warranty
		String warranty = StringUtils.trimToNull(desc.getWarrantyInfo());
		// TODO Parse to Html warranty = left(warranty, 5000, "Warranty");
		entity.setWarranty(warranty);

		return entity;
	}

}
