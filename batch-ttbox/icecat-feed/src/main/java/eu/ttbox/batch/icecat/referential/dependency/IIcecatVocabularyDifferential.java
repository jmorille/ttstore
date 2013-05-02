package eu.ttbox.batch.icecat.referential.dependency;

import java.util.List;

import biz.icecat.referential.v1.Name;
import biz.icecat.referential.v1.Names;
import eu.ttbox.icecat.model.referential.IcecatVocabulary;

public interface IIcecatVocabularyDifferential extends IDependencyDifferential<Integer, IcecatVocabulary, Name> {

	void importVocabulary(Integer sid, Names name);

	void importVocabulary(Integer sid, List<Name> names);
}
