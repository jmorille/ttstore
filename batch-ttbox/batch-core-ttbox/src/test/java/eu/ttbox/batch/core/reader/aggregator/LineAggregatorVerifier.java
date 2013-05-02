package eu.ttbox.batch.core.reader.aggregator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.ttbox.batch.core.reader.join.KeyValueLineVo;

public class LineAggregatorVerifier<MASTER extends KeyValueLineVo> {

	List<MASTER> lines;

	Map<String, List<MASTER>> lineByMasterKeys;

	int masterSize = 0;

	public LineAggregatorVerifier(List<MASTER> lines,int masterSize) {
		super();
		this.masterSize = masterSize;
		this.lines = lines;
		this.lineByMasterKeys = new HashMap<String, List<MASTER>>();
		for (MASTER master : lines) {
			String masterKey = master.getKey();
			List<MASTER> lineKey = lineByMasterKeys.get(masterKey);
			if (lineKey == null) {
				lineKey = new ArrayList<MASTER>();
				lineByMasterKeys.put(masterKey, lineKey);
			}
			lineKey.add(master);
		}
	}

	public List<MASTER> getJoins(String masterKey) {
		return lineByMasterKeys.get(masterKey);
	}

	public int getJoinSize(String masterKey) {
		int result = 0;
		List<MASTER> join = lineByMasterKeys.get(masterKey);
		if (join != null) {
			result = join.size();
		}
		return result;
	}

	public int getMasterSize() {
		return masterSize;
	}

}
