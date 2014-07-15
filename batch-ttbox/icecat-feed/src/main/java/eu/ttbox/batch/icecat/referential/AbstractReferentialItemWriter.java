package eu.ttbox.batch.icecat.referential;

import eu.ttbox.batch.icecat.dao.IcecatDAO;
import eu.ttbox.batch.icecat.referential.dependency.IIcecatTexDifferential;
import eu.ttbox.batch.icecat.referential.dependency.IIcecatVocabularyDifferential;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractReferentialItemWriter<T> implements ItemWriter<T> {

	public static Integer DEFAULT_USER_ID = Integer.valueOf(1);

	protected Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	protected IIcecatVocabularyDifferential icecatVocabularyDifferential;

	@Autowired
	protected IIcecatTexDifferential icecatTexDifferential;

	@Autowired
	protected IcecatDAO icecatDAO;

	public IcecatDAO getIcecatDAO() {
		return icecatDAO;
	}

	@Override
	public void write(List<? extends T> items) throws Exception {
		int idx = 0;
		int itemSize = items.size();
		for (T item : items) {
			idx++;
			// log.debug("* Item {} : {}", idx, item);
			this.doImport(item);
			getIcecatDAO().flushAndClear();
			if (idx % 100 == 0) {
				log.info("* {} / {}", idx, itemSize);
			}
		}
		log.info("* {} / {} End Page.", idx, itemSize);

	}

	public abstract int doImport(T item);
}
