package eu.ttbox.batch.core.reader.multijoin;

import eu.ttbox.batch.core.reader.join.KeyValueLineVo;

public class KeyValueLineJoinComparator implements JoinComparator<KeyValueLineVo, Object> {

	@Override
	public int compare(Object p1, Object p2) {
		return ((KeyValueLineVo)p1).getKey().compareTo(((KeyValueLineVo)p2).getKey());
	}

	@Override
	public int compareMasterJoin(KeyValueLineVo master, Object join) {
		return master.getKey().compareTo(((KeyValueLineVo)join).getKey());
	}

}
