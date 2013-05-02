package eu.ttbox.batch.core.reader.join;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpectedJoinVerifier<MASTER extends KeyValueLineVo, JOIN> {

	Map<MASTER, List<JOIN>> lines;
	Map<String, List<JOIN>> lineByMasterKeys;

	int masterSize = 0;

	public ExpectedJoinVerifier(Map<MASTER, List<JOIN>> lines, int masterSize) {
		super();
		this.masterSize = masterSize;
		this.lines = lines; 
		this.lineByMasterKeys = new HashMap<String, List<JOIN>>();
		for (KeyValueLineVo master : lines.keySet()) {
			lineByMasterKeys.put(master.getKey(), lines.get(master));
		}
	}



	public  List<JOIN>  getJoins(String masterKey) {
		return lineByMasterKeys.get(masterKey);
	}


	public  int  getJoinSize(String masterKey) {
		int result=0;
		List<JOIN> join =  lineByMasterKeys.get(masterKey);
		if (join!=null) {
			result = join.size();
		}
		return result;
	}



	public int getMasterSize() {
		return masterSize;
	}



 
	 

}
