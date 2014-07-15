package eu.ttbox.batch.icecat.referential.feature;

import biz.icecat.referential.v1.Feature;
import eu.ttbox.batch.icecat.referential.AbstractReferentialItemWriter;
import eu.ttbox.batch.icecat.referential.model.IIcecatModelCreator;
import eu.ttbox.icecat.model.referential.IcecatFeature;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("featureListIcecatItemWriter")
public class FeatureListItemWriter extends AbstractReferentialItemWriter<Feature> implements ItemWriter<Feature> {

	@Autowired
	@Qualifier("icecatFeatureModelCreator")
	IIcecatModelCreator<IcecatFeature, Feature> icecatFeatureModelCreator;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int doImport(Feature referential) {
		int importCount = 0;
		IcecatFeature entity = this.icecatFeatureModelCreator.doImport(referential);
		if (entity != null) {
			importCount++;
		}
		return importCount;
	}

}
