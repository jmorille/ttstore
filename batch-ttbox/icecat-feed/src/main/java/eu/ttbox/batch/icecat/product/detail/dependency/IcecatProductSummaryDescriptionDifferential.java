package eu.ttbox.batch.icecat.product.detail.dependency;

import biz.icecat.referential.v1.SummaryDescription;
import eu.ttbox.batch.icecat.referential.dependency.AbstractLangDependencyDifferential;
import eu.ttbox.icecat.model.product.IcecatProduct;
import eu.ttbox.icecat.model.product.IcecatProductSummaryDescription;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Service("icecatProductSummaryDescriptionDifferential")
public class IcecatProductSummaryDescriptionDifferential extends
		AbstractLangDependencyDifferential<IcecatProduct, IcecatProductSummaryDescription, SummaryDescription> {

	@Override
	public Integer getFeedElementId(SummaryDescription elt) {
		// TODO return elt.getID();
		return null;
	}

	@Override
	public Integer getFeedLangId(SummaryDescription elt) {
		// TODO return elt.getLangid();
		return null;
	}

	@Override
	public boolean isValidEntity(IcecatProductSummaryDescription entity) {
		return true;
	}

	@Override
	public boolean isValidFeed(SummaryDescription elt) {
		return true;
	}

	@Override
	public IcecatProductSummaryDescription createEntity(IcecatProduct product, SummaryDescription desc) {
		Integer entityId = getFeedElementId(desc);
		IcecatProductSummaryDescription entity = new IcecatProductSummaryDescription();
		entity.setId(entityId);
		entity.setProduct(product);
		entity.setUpdated(product.getUpdated());
		return entity;
	}

	@Override
	public IcecatProductSummaryDescription updateEntity(IcecatProduct product, SummaryDescription desc, IcecatProductSummaryDescription entity) {

		// Field
		// entity.setUpdated(product.getUpdated());

		// Lang Id
		// Integer langId = desc.getLangid();
		// entity.setLangid(langId);

		// Long Desc
		String longDesc = null;
		if (desc.isSetLongSummaryDescription()) {
			longDesc = StringUtils.trimToNull(desc.getLongSummaryDescription().getValue());
		}
		// TODO Parse To HTML longDesc = left(longDesc, 50000,
		// "LongSummaryDescription");
		entity.setLongDescription(longDesc);
		// Short Desc
		String sortDes = null;
		if (desc.isSetShortSummaryDescription()) {
			sortDes = StringUtils.trimToNull(desc.getShortSummaryDescription().getValue());
		}
		entity.setShortDescription(StringUtils.trimToNull(sortDes));

		return entity;
	}

}
