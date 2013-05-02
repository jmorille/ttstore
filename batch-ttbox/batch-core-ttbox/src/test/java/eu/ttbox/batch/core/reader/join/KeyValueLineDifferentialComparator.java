package eu.ttbox.batch.core.reader.join;

import eu.ttbox.batch.core.reader.differential.DifferentialComparator;
 

public class KeyValueLineDifferentialComparator implements
DifferentialComparator<KeyValueLineVo, KeyValueLineVo>  {

	@Override
	public int compareJoins(KeyValueLineVo p1, KeyValueLineVo p2) {
		return p1.getKey().compareTo(p2.getKey());
	}

	@Override
	public int compareMasters(KeyValueLineVo r1, KeyValueLineVo r2) {
		return r1.getKey().compareTo(r2.getKey());
	}

	@Override
	public int compareMasterJoin(KeyValueLineVo r2, KeyValueLineVo p1) {
 		return r2.getKey().compareTo(p1.getKey());
	}

	
 

	
}
