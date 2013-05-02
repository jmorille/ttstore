package eu.ttbox.batch.core.reader.multijoin;

import java.util.Comparator;

import eu.ttbox.batch.core.reader.join.KeyValueLineVo;

public class KeyValueLineComparator implements Comparator<KeyValueLineVo> {

	public int compare(KeyValueLineVo p1, KeyValueLineVo p2) {
		return p1.getKey().compareTo(p2.getKey());
	}

}
