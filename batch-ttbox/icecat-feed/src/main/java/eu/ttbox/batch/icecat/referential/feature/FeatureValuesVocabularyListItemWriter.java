package eu.ttbox.batch.icecat.referential.feature;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Service;

import biz.icecat.referential.v1.FeatureValuesVocabularyList.FeatureValuesVocabulary;
import eu.ttbox.batch.icecat.referential.AbstractReferentialItemWriter;

@Service("featureValuesVocabularyListIcecatItemWriter")
public class FeatureValuesVocabularyListItemWriter extends AbstractReferentialItemWriter<FeatureValuesVocabulary> implements
		ItemWriter<FeatureValuesVocabulary> {

	@Override
	public int doImport(FeatureValuesVocabulary item) {
		log.debug("TODO FeatureValuesVocabulary : {}", item);
		return 0;
	}

}
