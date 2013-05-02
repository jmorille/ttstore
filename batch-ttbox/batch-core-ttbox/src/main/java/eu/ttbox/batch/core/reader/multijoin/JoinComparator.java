package eu.ttbox.batch.core.reader.multijoin;

import java.util.Comparator;

public interface JoinComparator<MASTER, JOIN> extends Comparator<JOIN> {

	 public int compareMasterJoin(MASTER master, JOIN join);
}
