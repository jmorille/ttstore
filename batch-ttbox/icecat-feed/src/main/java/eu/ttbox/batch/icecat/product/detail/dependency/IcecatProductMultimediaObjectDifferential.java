package eu.ttbox.batch.icecat.product.detail.dependency;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import biz.icecat.referential.v1.ProductMultimediaObject.MultimediaObject;
import eu.ttbox.batch.icecat.referential.dependency.AbstractDependencyDifferential;
import eu.ttbox.icecat.model.product.IcecatProduct;
import eu.ttbox.icecat.model.product.IcecatProductMultimediaObject;
import eu.ttbox.icecat.model.referential.IcecatLanguageEnum;

@Service("icecatProductMultimediaObjectDifferential")
public class IcecatProductMultimediaObjectDifferential extends AbstractDependencyDifferential<IcecatProduct, IcecatProductMultimediaObject, MultimediaObject>
		implements InitializingBean {

	private String multimediaObjectDatePattenString = "yyyy-MM-dd HH:mm:ss";

	private SimpleDateFormat multimediaObjectDateFormat;

	@Override
	public void afterPropertiesSet() throws Exception {
		multimediaObjectDateFormat = new SimpleDateFormat(multimediaObjectDatePattenString);
	}

	@Override
	public Integer getFeedElementId(MultimediaObject elt) {
		return elt.getMultimediaObjectID();
	}

	// @Override
	// public Integer getFeedLangId(MultimediaObject elt) {
	// return elt.getLangid();
	// }

	@Override
	public boolean isValidEntity(IcecatProductMultimediaObject entity) {
		if (entity.getLangid() == null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isValidFeed(MultimediaObject elt) {
		return true;
	}

	@Override
	public IcecatProductMultimediaObject createEntity(IcecatProduct product, MultimediaObject elt) {
		Integer entityId = elt.getMultimediaObjectID();
		Integer langId = elt.getLangid();
		IcecatProductMultimediaObject entity = new IcecatProductMultimediaObject();
		entity.setId(entityId);
		entity.setProduct(product);
		entity.setLangid(IcecatLanguageEnum.getByLangId(langId));
		// Updated
		entity.setUpdated(product.getUpdated());
		return entity;
	}

	@Override
	public IcecatProductMultimediaObject updateEntity(IcecatProduct product, MultimediaObject mo, IcecatProductMultimediaObject entity) {
		Integer langId = mo.getLangid();
		entity.setLangid(IcecatLanguageEnum.getByLangId(langId));
		// Updated
		// entity.setUpdated(product.getUpdated());

		// Value
		entity.setContentType(mo.getContentType());
		entity.setLength(mo.getSize());
		entity.setLink(mo.getURL());
		entity.setShortDescription(mo.getDescription());
		entity.setType(mo.getType());

		// mo.getDate(); //2009-06-02 21:36:21
		Date moDate = null;
		if (StringUtils.isNotEmpty(mo.getDate())) {
			try {
				moDate = multimediaObjectDateFormat.parse(mo.getDate());
			} catch (ParseException e) {
				log.error("Could not parse date " + mo.getDate() + " with format " + multimediaObjectDatePattenString, e);
				moDate = product.getUpdated();
			}
		}
		entity.setUpdated(moDate);
		return entity;
	}

}
