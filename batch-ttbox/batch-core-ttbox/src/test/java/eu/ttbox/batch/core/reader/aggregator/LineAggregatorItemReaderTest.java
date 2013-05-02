package eu.ttbox.batch.core.reader.aggregator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.support.ListItemReader;

import eu.ttbox.batch.core.reader.aggregator.impl.LineAggregatorItemVO;
import eu.ttbox.batch.core.reader.join.KeyValueLineVo;

 

public class LineAggregatorItemReaderTest {

	private static final Logger log = LoggerFactory
			.getLogger(LineAggregatorItemReaderTest.class);

	private List<KeyValueLineVo> getJoinList(String key, int number) {
		List<KeyValueLineVo> joinList = new ArrayList<KeyValueLineVo>();
		for (int i = 0; i < number; i++) {
			KeyValueLineVo vo = new KeyValueLineVo(key, key + " : Line " + i);
			joinList.add(vo);
		}
		return joinList;
	}

	private List<KeyValueLineVo> createMasterItems(List<String> masterKey) {
		List<KeyValueLineVo> masterList = new ArrayList<KeyValueLineVo>();

		for (String key : masterKey) {
			// Parse Key for number
			int number = 0;
			int idxSepNumb = key.indexOf("-");
			if (idxSepNumb > -1) {
				String numb = key.substring(idxSepNumb + 1, key.length());
				number = Integer.valueOf(numb).intValue();
			}
			// Create Joins
			List<KeyValueLineVo> joins = getJoinList(key, number);
			masterList.addAll(joins);
		}
		// shuffle
		for (int i = 0; i < 10; i++) {
			Collections.shuffle(masterList);
		}
		// Sort
		Collections.sort(masterList);
		return masterList;
	}

	@Test
	public void testWithSingleConstantAggregate() throws Exception {
		List<String> masterKey = Arrays.asList("A-1", "B-1", "C-1", "D-1",
				"E-1", "F-1", "I-1", "J-1");
		List<KeyValueLineVo> masterList = createMasterItems(masterKey);
		LineAggregatorVerifier<KeyValueLineVo> verifier = new LineAggregatorVerifier<KeyValueLineVo>(masterList, masterKey.size());
		doReadTest(masterList,  verifier);
	}	

	@Test
	public void testWithConstantAggregate() throws Exception {
		List<String> masterKey = Arrays.asList("A-3", "B-3", "C-3", "D-3",
				"E-3", "F-3", "I-3", "J-3");
		List<KeyValueLineVo> masterList = createMasterItems(masterKey);
		LineAggregatorVerifier<KeyValueLineVo> verifier = new LineAggregatorVerifier<KeyValueLineVo>(masterList, masterKey.size());
		doReadTest(masterList,  verifier);
	}

	@Test
	public void testWithHetegousConstantAggregate() throws Exception {
		List<String> masterKey = Arrays.asList("A-3", "B-1", "C-3", "D-1",
				"E-1", "F-3", "I-3", "J-3");
		List<KeyValueLineVo> masterList = createMasterItems(masterKey);
		LineAggregatorVerifier<KeyValueLineVo> verifier = new LineAggregatorVerifier<KeyValueLineVo>(masterList, masterKey.size());
		doReadTest(masterList,  verifier);
	}

	@Test
	public void testWithSingleConstantAggregateEndMulti() throws Exception {
		List<String> masterKey = Arrays.asList("A-1", "B-1", "C-1", "D-1",
				"E-1", "F-1", "I-1", "J-50");
		List<KeyValueLineVo> masterList = createMasterItems(masterKey);
		LineAggregatorVerifier<KeyValueLineVo> verifier = new LineAggregatorVerifier<KeyValueLineVo>(masterList, masterKey.size());
		doReadTest(masterList,  verifier);
	}	

	@Test
	public void testWithSingleConstantAggregateEndBeginMulti() throws Exception {
		List<String> masterKey = Arrays.asList("A-17", "B-1", "C-1", "D-1",
				"E-1", "F-1", "I-1", "J-14");
		List<KeyValueLineVo> masterList = createMasterItems(masterKey);
		LineAggregatorVerifier<KeyValueLineVo> verifier = new LineAggregatorVerifier<KeyValueLineVo>(masterList, masterKey.size());
		doReadTest(masterList,  verifier);
	}	
	public void doReadTest(List<KeyValueLineVo> masterList,
			LineAggregatorVerifier<KeyValueLineVo> expecteJoin) throws Exception {
		// Sources

		ListItemReader<KeyValueLineVo> masterReader = new ListItemReader<KeyValueLineVo>(
				masterList);
		// Expected result

		// Create Differential
		Comparator<KeyValueLineVo> comparator = new Comparator<KeyValueLineVo>() {
			@Override
			public int compare(KeyValueLineVo o1, KeyValueLineVo o2) {
				return o1.getKey().compareTo(o2.getKey());
			}
		};
		LineAggregatorItemReader<KeyValueLineVo> service = new LineAggregatorItemReader<KeyValueLineVo>(
				masterReader, comparator);
		service.open(null);

		LineAggregatorItemVO<KeyValueLineVo> line = (LineAggregatorItemVO<KeyValueLineVo> )service.read();

		int count = 0;
		log.debug("--- Begin Reading -----------------------------------------------------------------------");
		do {
			count++;
			log.debug("Read line {} : {} ", count, line);
			// Test for master presence
			Assert.assertNotNull(line.getLines());
			Assert.assertFalse(line.getLines().isEmpty());

			// Test Master & Join Key
			String masterKey = null;
			if (line.getLines() != null && !line.getLines().isEmpty()) {
				for (KeyValueLineVo join : line.getLines()) {
					if (masterKey == null) {
						masterKey = join.getKey();
					} else {
						// Check Master Key
						Assert.assertEquals(masterKey, join.getKey());
					}
				}
				// Test Join Size
				int joinSize = 0;
				if (line.getLines() != null) {
					joinSize = line.getLines().size();
				}
				Assert.assertEquals(expecteJoin.getJoinSize(masterKey),
						joinSize);
				// Test Join Content
				List<KeyValueLineVo> joinExpectedList = new ArrayList<KeyValueLineVo>(
						expecteJoin.getJoins(masterKey));
				if (line.getLines() != null && !line.getLines().isEmpty()) {
					for (KeyValueLineVo join : line.getLines()) {
						// Check Master Key
						Assert.assertEquals(masterKey, join.getKey());
						// Check is in expected Join
						Assert.assertTrue(joinExpectedList.contains(join));
						Assert.assertTrue(joinExpectedList.remove(join));
					}
				}
				Assert.assertEquals(0, joinExpectedList.size());
			}

		} while ((line = (LineAggregatorItemVO<KeyValueLineVo> ) service.read()) != null);
		Assert.assertEquals(expecteJoin.getMasterSize(), count);
		// Release Dependencies
		service.close();

		// test All ListEletment was tested
		// Assert.assertEquals(0, testedExpected.size());
		// Assert.assertEquals(0, manageExpected.size());
		log.debug("--- End Reading  -----------------------------------------------------------------------");
	}
}
