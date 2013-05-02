package eu.ttbox.batch.icecat.referential.model;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import biz.icecat.referential.v1.Descriptions;
import biz.icecat.referential.v1.Feature;
import biz.icecat.referential.v1.Measure;
import biz.icecat.referential.v1.Names;
import eu.ttbox.icecat.model.referential.IcecatFeature;
import eu.ttbox.icecat.model.referential.IcecatMeasure;

@Transactional
@Service("icecatFeatureModelCreator")
public class IcecatFeatureModelCreatorImpl extends AbstractIcecatModelCreatorImpl<IcecatFeature, Feature> {

	@Override
	public Integer getFeedElementId(Feature elt) {
		return elt.getID();
	}

	@Override
	public IcecatFeature createEntity(Feature elt) {
		Integer sid = getIcecatDAO().getSidSequenceNextVal();
		Integer tid = getIcecatDAO().getTidSequenceNextVal();
		IcecatFeature entity = new IcecatFeature();
		entity.setId(getFeedElementId(elt));
		entity.setSid(sid);
		entity.setTid(tid);
		return entity;
	}

	@Override
	public IcecatFeature updateEntity(Feature elt, IcecatFeature entity) {
		Integer sid = entity.getSid();
		Integer tid = entity.getTid();

		// Values
		entity.setType(StringUtils.trimToNull(elt.getType()));
		if (elt.isClazz()) {
			entity.setClazz(Integer.valueOf(1));
		} else {
			entity.setClazz(Integer.valueOf(0));
		}
		// LimitDirection
		if (elt.getLimitDirection() != null) {
			entity.setLimitDirection(elt.getLimitDirection().intValue());
		} else {
			entity.setLimitDirection(Integer.valueOf(0));
		}
		// Searchable
		if (elt.isSetSearchable() && elt.isSearchable()) {
			entity.setSearchable(Integer.valueOf(1));
		} else {
			entity.setSearchable(Integer.valueOf(0));
		}
		// TODO Restricted Value
		// TODO entity.setRestrictedValues( referential.getRestrictedValues());

		// Measure
		Measure measure = elt.getMeasure();
		if (measure != null) {
			Integer measureId = measure.getID();
			IcecatMeasure iceMeasure = (IcecatMeasure) getIcecatDAO().getById(measureId, IcecatMeasure.class);
			if (iceMeasure != null) {
				entity.setMeasure(iceMeasure);
			} else {
				log.warn("TODO Manage Measure {} / {}", measureId, measure.getSignAttribute());
			}
		}

		// entity.setUpdated(new Date());

		// Vocabulary
		Names names = elt.getNamesList();
		if (names != null) {
			this.icecatVocabularyDifferential.importVocabulary(sid, names.getNames());
		}

		// Descriptions
		Descriptions descriptions = elt.getDescriptions();
		if (descriptions != null) {
			this.icecatTexDifferential.importDescription(tid, descriptions.getDescriptions());
		}

		return entity;
	}

}
