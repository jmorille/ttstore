package eu.ttbox.batch.icecat.referential.model;

import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import biz.icecat.relations.v1.Rule;
import eu.ttbox.icecat.model.referential.IcecatBrand;
import eu.ttbox.icecat.model.referential.IcecatCategory;
import eu.ttbox.icecat.model.referential.IcecatFeature;
import eu.ttbox.icecat.model.relations.IcecatRelationRule;

@Transactional
@Service("icecatRelationRuleModelCreator")
public class IcecatRelationRuleModelCreatorImpl extends AbstractIcecatModelCreatorImpl<IcecatRelationRule, Rule> {

	@Override
	public Integer getFeedElementId(Rule elt) {
		return elt.getID();
	}

	@Override
	public IcecatRelationRule createEntity(Rule elt) {
		IcecatRelationRule rule = new IcecatRelationRule();
		rule.setId(getFeedElementId(elt));
		return rule;
	}

	@Override
	public IcecatRelationRule updateEntity(Rule elt, IcecatRelationRule entity) {

		if (elt.getProdId() != null && elt.getProdId().getValue() != null) {
			String prodId = elt.getProdId().getValue();
			entity.setPartNumber(prodId);
		} else {
			entity.setPartNumber(null);
		}

		// TODO elt.getSupplierFamily();
		if (elt.getSupplierFamily() != null && elt.getSupplierFamily().getID() != null) {
			Integer supplierFamillyId = elt.getSupplierFamily().getID();
			entity.setSupplierFamilyId(supplierFamillyId);
		} else {
			entity.setSupplierFamilyId(null);
		}

		// Date
		if (elt.getStartDate() != null && elt.getStartDate().getValue() != null) {
			XMLGregorianCalendar startCal = elt.getStartDate().getValue();
			Date startDate = startCal.toGregorianCalendar().getTime();
			entity.setStartDate(startDate);
		} else {
			entity.setStartDate(null);
		}

		if (elt.getEndDate() != null && elt.getEndDate().getValue() != null) {
			XMLGregorianCalendar endCal = elt.getEndDate().getValue();
			Date endDate = endCal.toGregorianCalendar().getTime();
			entity.setEndDate(endDate);
		} else {
			entity.setEndDate(null);
		}

		// Brand
		if (elt.getSupplier() != null && elt.getSupplier().getID() != null) {
			Integer brandId = elt.getSupplier().getID();
			boolean needUpdate = entity.getBrand() == null;
			if (!needUpdate) {
				needUpdate = !brandId.equals(entity.getBrand().getId());
			}
			if (needUpdate) {
				IcecatBrand brand = (IcecatBrand) getIcecatDAO().getById(brandId, IcecatBrand.class);
				entity.setBrand(brand);
			}
		} else {
			entity.setBrand(null);
		}

		// Category Dependency
		if (elt.getCategory() != null && elt.getCategory().getID() != null) {
			Integer catId = elt.getCategory().getID();
			boolean needUpdate = entity.getCategory() == null;
			if (!needUpdate) {
				needUpdate = !catId.equals(entity.getCategory().getId());
			}
			if (needUpdate) {
				IcecatCategory category = (IcecatCategory) getIcecatDAO().getById(catId, IcecatCategory.class);
				entity.setCategory(category);
			}
		} else {
			entity.setCategory(null);
		}

		// Feature Dependency
		if (elt.getFeature() != null && elt.getFeature().getID() != null) {
			Integer featureId = elt.getFeature().getID();
			boolean needUpdate = entity.getFeature() == null;
			if (!needUpdate) {
				needUpdate = !featureId.equals(entity.getFeature().getId());
			}
			if (needUpdate) {
				IcecatFeature feature = (IcecatFeature) getIcecatDAO().getById(featureId, IcecatFeature.class);
				entity.setFeature(feature);
			}
		} else {
			entity.setFeature(null);
		}

		if (elt.getFeature() != null) {
			// TODO String exact = elt.getFeature().getExact();
			entity.setFeatureValue(elt.getFeature().getValue());
		} else {
			entity.setFeatureValue(null);
		}

		return entity;
	}

}
