package eu.ttbox.batch.core.reader.join;

public interface JoinItem<MASTER, JOIN> {

	 void setMaster(MASTER master);
	 
	 void addJoin(JOIN joinItem) ;
}
