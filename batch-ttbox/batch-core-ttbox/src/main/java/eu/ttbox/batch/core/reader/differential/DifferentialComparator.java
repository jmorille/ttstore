package eu.ttbox.batch.core.reader.differential;

public interface DifferentialComparator<MASTER, JOIN > {

    public int compareJoins(JOIN j1, JOIN j2);

    public int compareMasters(MASTER m1, MASTER m2);

    public int compareMasterJoin(MASTER master, JOIN join);
    
}
