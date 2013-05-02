package eu.ttbox.batch.core.reader.multijoin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.ttbox.batch.core.reader.join.KeyValueLineVo;

public class ExpectedMultiJoinVerifier<MASTER extends KeyValueLineVo, JOIN> {

	Map<String, List<JOIN>>[] lineByMasterKeys;

	int masterSize = 0;

	int joinItems = 0;

	public int getJoinItems() {
		return joinItems;
	}

	public ExpectedMultiJoinVerifier(int masterSize, Map<MASTER, List<JOIN>>... joinLines) {
		super();
		this.masterSize = masterSize;
		this.joinItems = joinLines.length;
		this.lineByMasterKeys = new Map[joinItems];
		for (int i = 0; i < joinItems; i++) {
			this.lineByMasterKeys[i] = new HashMap<String, List<JOIN>>();
			Map<MASTER, List<JOIN>> lines = joinLines[i];
			for (KeyValueLineVo master : lines.keySet()) {
				lineByMasterKeys[i].put(master.getKey(), lines.get(master));
			}
		}

	}

	public List<JOIN> getJoins(String masterKey, int joinIndex) {
		return lineByMasterKeys[joinIndex].get(masterKey);
	}

	public int getJoinSize(String masterKey, int joinIndex) {
		int result = 0;
		List<JOIN> join = lineByMasterKeys[joinIndex].get(masterKey);
		if (join != null) {
			result = join.size();
		}
		return result;
	}

	public int getMasterSize() {
		return masterSize;
	}

}
