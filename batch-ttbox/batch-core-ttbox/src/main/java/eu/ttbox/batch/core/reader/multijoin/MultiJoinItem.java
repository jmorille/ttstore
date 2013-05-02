package eu.ttbox.batch.core.reader.multijoin;

public interface MultiJoinItem<MASTER> {

	 void setMaster(MASTER master);
	 
	 void addJoin(int idx, Object joinItem) ;
}
