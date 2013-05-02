package eu.ttbox.batch.icecat.referential;

import org.apache.commons.lang.StringUtils;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.icecat.referential.v1.Descriptions;
import biz.icecat.referential.v1.Measure;
import biz.icecat.referential.v1.Names;
import eu.ttbox.icecat.model.referential.IcecatMeasure;

@Transactional(propagation = Propagation.REQUIRED)
@Service("measuresListIcecatItemWriter")
public class MeasuresListItemWriter extends AbstractReferentialItemWriter<Measure> implements ItemWriter<Measure> {

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int doImport(Measure referential) {
		int importCount = 0;

		Integer sid = null;
		Integer tid = null;
		Integer entityId = referential.getID();
		// System.out.println("Measure id " + entityId);
		IcecatMeasure entity = (IcecatMeasure) getIcecatDAO().getById(entityId, IcecatMeasure.class);
		if (entity == null) {
			sid = getIcecatDAO().getSidSequenceNextVal();
			tid = getIcecatDAO().getTidSequenceNextVal();
			entity = new IcecatMeasure();
			entity.setId(entityId);
			entity.setSid(sid);
			entity.setTid(tid);
			importCount++;
		} else {
			sid = entity.getSid();
			tid = entity.getTid();
		}

		// Sign
		String signValue = referential.getSignAttribute();
		String sign = referential.getSign();
		if (sign != null) {
			if (StringUtils.isEmpty(signValue)) {
				signValue = sign;
			}
		}
		entity.setSign(signValue);

		// Vocabulary
		Names names = referential.getNames();
		this.icecatVocabularyDifferential.importVocabulary(sid, names.getNames());

		// Descriptions
		Descriptions descriptions = referential.getDescriptions();
		this.icecatTexDifferential.importDescription(tid, descriptions.getDescriptions());

		// Save
		getIcecatDAO().saveObject(entity);

		return importCount;
	}

}
