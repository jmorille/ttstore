package eu.ttbox.batch.core.reader.join;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JoinTestHelper {

	public static List<KeyValueLineVo> getJoinList(String key, int number) {
		List<KeyValueLineVo> joinList = new ArrayList<KeyValueLineVo>();
		for (int i = 0; i < number; i++) {
			KeyValueLineVo vo = new KeyValueLineVo(key, key + "-" + (i + 1) + "/" + number);
			joinList.add(vo);
		}
		return joinList;
	}

	public static List<KeyValueLineVo> getMasterList(List<String> listSrc) {
		List<KeyValueLineVo> result = new ArrayList<KeyValueLineVo>();
		for (String key : listSrc) {
			KeyValueLineVo vo = new KeyValueLineVo(key, key + " Label");
			result.add(vo);
		}
		return result;
	}

	public static Map<KeyValueLineVo, List<KeyValueLineVo>> createMasterJoinItems(List<String> masterKey) {
		Map<KeyValueLineVo, List<KeyValueLineVo>> expectedLines = new HashMap<KeyValueLineVo, List<KeyValueLineVo>>();

		List<KeyValueLineVo> masterList = getMasterList(masterKey);
		for (KeyValueLineVo master : masterList) {
			String key = master.getKey();
			// Parse Key for number
			int number = 0;
			int idxSepNumb = key.indexOf("-");
			if (idxSepNumb > -1) {
				String numb = key.substring(idxSepNumb + 1, key.length());
				number = Integer.valueOf(numb).intValue();
				key = key.substring(0, idxSepNumb);
				master.setKey(key);
			}
			// Create Joins
			List<KeyValueLineVo> joins = getJoinList(key, number);
			expectedLines.put(master, joins);
		}
		return expectedLines;
	}

	public static List<KeyValueLineVo> extractJoinList(Map<KeyValueLineVo, List<KeyValueLineVo>> expectedLines) {
		List<KeyValueLineVo> joinList = new ArrayList<KeyValueLineVo>();
		for (List<KeyValueLineVo> join : expectedLines.values()) {
			joinList.addAll(join);
		}
		// shuffle
		for (int i = 0; i < 10; i++) {
			Collections.shuffle(joinList);
		}
		// Sort
		Collections.sort(joinList);
		return joinList;
	}

	public static List<KeyValueLineVo> extractMasterList(Map<KeyValueLineVo, List<KeyValueLineVo>>... joinExpectedLines) {
		List<KeyValueLineVo> masterList = new ArrayList<KeyValueLineVo>();
		List<String> keys = new ArrayList<String>();
		for (Map<KeyValueLineVo, List<KeyValueLineVo>> expectedLines : joinExpectedLines) {
			for (KeyValueLineVo keyVo : expectedLines.keySet()) {
				String key = keyVo.getKey();
				if (!keys.contains(key))  {
					keys.add(key);
					masterList.add(keyVo);
				}
			}

		}
		// shuffle
		for (int i = 0; i < 10; i++) {
			Collections.shuffle(masterList);
		}
		// Sort
		Collections.sort(masterList);
		return masterList;

	}
}
