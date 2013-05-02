package eu.ttbox.feed.core.sorter;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class MultiKeysItemComparator implements Comparator<Map<String, Comparable>> {

	private final List<String> keys;

	public MultiKeysItemComparator(List<String> keys) {
		super();
		this.keys = new LinkedList<String>(keys);
	}

	@Override
	public int compare(Map<String, Comparable> o1, Map<String, Comparable> o2) {
		for (String key : keys) {
			Comparable i1 = o1.get(key);
			Comparable i2 = o2.get(key);
			@SuppressWarnings("unchecked")
			int compare = i1.compareTo(i2);
			if (compare != 1) {
				return compare;
			}
		}
		return 0;
	}

}
