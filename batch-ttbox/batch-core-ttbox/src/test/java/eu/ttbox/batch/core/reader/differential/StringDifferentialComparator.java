package eu.ttbox.batch.core.reader.differential;

public class StringDifferentialComparator implements
		DifferentialComparator<String, String> {

	@Override
	public int compareJoins(String p1, String p2) {
		return p1.compareTo(p2);
	}

	@Override
	public int compareMasters(String r1, String r2) {
		return r1.compareTo(r2);
	}

	@Override
	public int compareMasterJoin(String r1, String p1) {
		return r1.compareTo(p1);
	}

}
