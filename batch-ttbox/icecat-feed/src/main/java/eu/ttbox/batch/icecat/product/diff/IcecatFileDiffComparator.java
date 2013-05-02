package eu.ttbox.batch.icecat.product.diff;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import biz.icecat.files.v1.IcecatFile;
import eu.ttbox.batch.core.reader.differential.DifferentialComparator;
import eu.ttbox.icecat.model.product.IcecatProduct;

@Service("indexFilesIcecatDifferentialComparator")
public class IcecatFileDiffComparator implements DifferentialComparator<IcecatProduct, IcecatFile> {

	private static Logger LOG = LoggerFactory.getLogger(IcecatFileDiffComparator.class);

	private int compareString(Integer key1, Integer key2) {
		return key1.compareTo(key2);
	}

	@Override
	public int compareJoins(IcecatFile j1, IcecatFile j2) {
		Integer key1 = j1.getProductID();
		Integer key2 = j2.getProductID();
		return compareString(key1, key2);
	}

	@Override
	public int compareMasters(IcecatProduct m1, IcecatProduct m2) {
		Integer key1 = m1.getId();
		Integer key2 = m2.getId();
		return compareString(key1, key2);
	}

	@Override
	public int compareMasterJoin(IcecatProduct master, IcecatFile join) {
		Integer key1 = master.getId();
		Integer key2 = join.getProductID();
		// log.debug("Compare master {} <?> join {}", key1, key2);
		return compareString(key1, key2);
	}

}
