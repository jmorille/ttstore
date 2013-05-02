package eu.ttbox.batch.core.reader.multijoin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.ListItemReader;

import eu.ttbox.batch.core.reader.join.JoinTestHelper;
import eu.ttbox.batch.core.reader.join.KeyValueLineVo;
import eu.ttbox.batch.core.reader.multijoin.impl.MultiJoinItemVO;

public class MultiJoinItemReaderTest {

	private static final Logger log = LoggerFactory.getLogger(MultiJoinItemReaderTest.class);

	@Test
	public void testWithConstantJoin() throws Exception {
		// Master
		List<String> masterKey = Arrays.asList("A-3", "B-3", "C-3", "D-3", "E-3", "F-3", "I-3", "J-3");
		Map<KeyValueLineVo, List<KeyValueLineVo>> expectedLines = JoinTestHelper.createMasterJoinItems(masterKey);
		// Join List
		List<KeyValueLineVo> joinList = JoinTestHelper.extractJoinList(expectedLines);
		List<KeyValueLineVo> masterList = JoinTestHelper.extractMasterList(expectedLines);
		//
		ExpectedMultiJoinVerifier<KeyValueLineVo, KeyValueLineVo> expecteJoin = new ExpectedMultiJoinVerifier<KeyValueLineVo, KeyValueLineVo>(
				masterKey.size(), expectedLines);
		doReadTest(expecteJoin, masterList, joinList);
	}

	@Test
	public void testWithConstantJoinTwo() throws Exception {
		// Master
		List<String> masterKey = Arrays.asList("A-3", "B-3", "C-3", "D-3", "E-3", "F-3", "I-3", "J-3");
		Map<KeyValueLineVo, List<KeyValueLineVo>> expectedLines = JoinTestHelper.createMasterJoinItems(masterKey);
		// Join List
		List<KeyValueLineVo> joinList = JoinTestHelper.extractJoinList(expectedLines);
		List<KeyValueLineVo> masterList = JoinTestHelper.extractMasterList(expectedLines);
		//
		ExpectedMultiJoinVerifier<KeyValueLineVo, KeyValueLineVo> expecteJoin = new ExpectedMultiJoinVerifier<KeyValueLineVo, KeyValueLineVo>(
				masterKey.size(), expectedLines, expectedLines);
		doReadTest(expecteJoin, masterList, joinList, joinList);
	}

	@Test
	public void testWithConstantJoinTwoHeterogenous() throws Exception {
		// Master
		List<String> masterKey = Arrays.asList("A-3", "B-3", "C-3", "D-3", "E-3", "F-3", "I-3", "J-3");
		List<String> masterKey2 = Arrays.asList("A-1", "B-10", "C-8", "D-3", "E-12", "F-2", "I-7", "J-0");
		Map<KeyValueLineVo, List<KeyValueLineVo>> expectedLines = JoinTestHelper.createMasterJoinItems(masterKey);
		Map<KeyValueLineVo, List<KeyValueLineVo>> expectedLines2 = JoinTestHelper.createMasterJoinItems(masterKey2);
		// Join List
		List<KeyValueLineVo> masterList = JoinTestHelper.extractMasterList(expectedLines, expectedLines2);
		List<KeyValueLineVo> joinList = JoinTestHelper.extractJoinList(expectedLines);
		List<KeyValueLineVo> joinList2 = JoinTestHelper.extractJoinList(expectedLines2);
		//
		ExpectedMultiJoinVerifier<KeyValueLineVo, KeyValueLineVo> expecteJoin = new ExpectedMultiJoinVerifier<KeyValueLineVo, KeyValueLineVo>(
				masterList.size(), expectedLines, expectedLines2);
		doReadTest(expecteJoin, masterList, joinList, joinList2);
	}

	@Test
	public void testWithConstantJoinTwoHeterogenousUnContinusMaster() throws Exception {
		// Master
		List<String> masterKey = Arrays.asList("A-3", "B-3", "C-3", "D-3", "E-3", "F-3", "I-3", "J-3");
		List<String> masterKey2 = Arrays.asList("A-1", "B-10", "C-8", "D-3", "E-12", "F-2", "I-7", "J-0");
		Map<KeyValueLineVo, List<KeyValueLineVo>> expectedLines = JoinTestHelper.createMasterJoinItems(masterKey);
		Map<KeyValueLineVo, List<KeyValueLineVo>> expectedLines2 = JoinTestHelper.createMasterJoinItems(masterKey2);
		// Join List
		List<KeyValueLineVo> masterList = JoinTestHelper.extractMasterList(expectedLines, expectedLines2);
		KeyValueLineVo dupMaster = masterList.get(0);
		masterList.add(0, dupMaster);
		List<KeyValueLineVo> joinList = JoinTestHelper.extractJoinList(expectedLines);
		List<KeyValueLineVo> joinList2 = JoinTestHelper.extractJoinList(expectedLines2);
		//
		ExpectedMultiJoinVerifier<KeyValueLineVo, KeyValueLineVo> expecteJoin = new ExpectedMultiJoinVerifier<KeyValueLineVo, KeyValueLineVo>(
				masterList.size(), expectedLines, expectedLines2);
		try {
			doReadTest(expecteJoin, masterList, joinList, joinList2);
			Assert.assertTrue(false);
		} catch (UnexpectedInputException e) {
			Assert.assertNotNull(e);

		}
	}

	@Test
	public void testReadAllUnorderMasterSource() throws Exception {
		// Master
		List<String> masterKey = Arrays.asList("A-3", "B-3", "C-3", "D-3", "E-3", "F-3", "I-3", "J-3");
		Map<KeyValueLineVo, List<KeyValueLineVo>> expectedLines = JoinTestHelper.createMasterJoinItems(masterKey);
		// Join List
		List<KeyValueLineVo> joinList = JoinTestHelper.extractJoinList(expectedLines);
		List<KeyValueLineVo> masterList = JoinTestHelper.extractMasterList(expectedLines);
		Collections.shuffle(masterList);
		//
		ExpectedMultiJoinVerifier<KeyValueLineVo, KeyValueLineVo> expecteJoin = new ExpectedMultiJoinVerifier<KeyValueLineVo, KeyValueLineVo>(
				masterKey.size(), expectedLines);
		boolean asException = false;
		try {
			doReadTest(null, masterList, joinList);
			// Never Go as Far
			Assert.assertTrue(false);
		} catch (UnexpectedInputException uie) {
			asException = true;
			Assert.assertNotNull(uie);
		}
		Assert.assertTrue(asException);
	}

	@Test
	public void testReadAllUnorderJoinSource() throws Exception {
		// Master
		List<String> masterKey = Arrays.asList("A-3", "B-3", "C-3", "D-3", "E-3", "F-3", "I-3", "J-3");
		Map<KeyValueLineVo, List<KeyValueLineVo>> expectedLines = JoinTestHelper.createMasterJoinItems(masterKey);
		// Join List
		List<KeyValueLineVo> joinList = JoinTestHelper.extractJoinList(expectedLines);
		List<KeyValueLineVo> masterList = JoinTestHelper.extractMasterList(expectedLines);
		Collections.shuffle(joinList);
		//
		ExpectedMultiJoinVerifier<KeyValueLineVo, KeyValueLineVo> expecteJoin = new ExpectedMultiJoinVerifier<KeyValueLineVo, KeyValueLineVo>(
				masterKey.size(), expectedLines);
		boolean asException = false;
		try {
			doReadTest(null, masterList, joinList);
			// Never Go as Far
			Assert.assertTrue(false);
		} catch (UnexpectedInputException uie) {
			asException = true;
			Assert.assertNotNull(uie);
		}
		Assert.assertTrue(asException);
	}

	@Test
	public void testWithNoJoin() throws Exception {
		// Master
		List<String> masterKey = Arrays.asList("A", "B", "C", "D", "E", "F", "I", "J");
		Map<KeyValueLineVo, List<KeyValueLineVo>> expectedLines = JoinTestHelper.createMasterJoinItems(masterKey);
		// Join List
		List<KeyValueLineVo> joinList = JoinTestHelper.extractJoinList(expectedLines);
		List<KeyValueLineVo> masterList = JoinTestHelper.extractMasterList(expectedLines);
		//
		ExpectedMultiJoinVerifier<KeyValueLineVo, KeyValueLineVo> expecteJoin = new ExpectedMultiJoinVerifier<KeyValueLineVo, KeyValueLineVo>(
				masterKey.size(), expectedLines);
		doReadTest(expecteJoin, masterList, joinList);
	}

	@Test
	public void testWithOneMasterNoJoin() throws Exception {
		// Master
		List<String> masterKey = Arrays.asList("A");
		Map<KeyValueLineVo, List<KeyValueLineVo>> expectedLines = JoinTestHelper.createMasterJoinItems(masterKey);
		// Join List
		List<KeyValueLineVo> joinList = JoinTestHelper.extractJoinList(expectedLines);
		List<KeyValueLineVo> masterList = JoinTestHelper.extractMasterList(expectedLines);
		//
		ExpectedMultiJoinVerifier<KeyValueLineVo, KeyValueLineVo> expecteJoin = new ExpectedMultiJoinVerifier<KeyValueLineVo, KeyValueLineVo>(
				masterKey.size(), expectedLines);
		doReadTest(expecteJoin, masterList, joinList);
	}

	@Test
	public void testWithOneMaster50Join() throws Exception {
		// Master
		List<String> masterKey = Arrays.asList("A-50");
		Map<KeyValueLineVo, List<KeyValueLineVo>> expectedLines = JoinTestHelper.createMasterJoinItems(masterKey);
		// Join List
		List<KeyValueLineVo> joinList = JoinTestHelper.extractJoinList(expectedLines);
		List<KeyValueLineVo> masterList = JoinTestHelper.extractMasterList(expectedLines);
		//
		ExpectedMultiJoinVerifier<KeyValueLineVo, KeyValueLineVo> expecteJoin = new ExpectedMultiJoinVerifier<KeyValueLineVo, KeyValueLineVo>(
				masterKey.size(), expectedLines);
		doReadTest(expecteJoin, masterList, joinList);
	}

	@Test
	public void testWithHeterousJoin() throws Exception {
		// Master
		List<String> masterKey = Arrays.asList("A-10", "B-5", "C-2", "D-1", "E-0", "F-20", "I-3", "J-30");
		Map<KeyValueLineVo, List<KeyValueLineVo>> expectedLines = JoinTestHelper.createMasterJoinItems(masterKey);
		// Join List
		List<KeyValueLineVo> joinList = JoinTestHelper.extractJoinList(expectedLines);
		List<KeyValueLineVo> masterList = JoinTestHelper.extractMasterList(expectedLines);
		//
		ExpectedMultiJoinVerifier<KeyValueLineVo, KeyValueLineVo> expecteJoin = new ExpectedMultiJoinVerifier<KeyValueLineVo, KeyValueLineVo>(
				masterKey.size(), expectedLines);
		doReadTest(expecteJoin, masterList, joinList);
	}

	@Test
	public void testWithHeterousJoinLastNoJoin() throws Exception {
		// Master
		List<String> masterKey = Arrays.asList("A", "B-5", "C-2", "D-1", "E-0", "F-20", "I-3", "J");
		Map<KeyValueLineVo, List<KeyValueLineVo>> expectedLines = JoinTestHelper.createMasterJoinItems(masterKey);
		// Join List
		List<KeyValueLineVo> joinList = JoinTestHelper.extractJoinList(expectedLines);
		List<KeyValueLineVo> masterList = JoinTestHelper.extractMasterList(expectedLines);
		//
		ExpectedMultiJoinVerifier<KeyValueLineVo, KeyValueLineVo> expecteJoin = new ExpectedMultiJoinVerifier<KeyValueLineVo, KeyValueLineVo>(
				masterKey.size(), expectedLines);
		doReadTest(expecteJoin, masterList, joinList);
	}

	@Test
	public void testWithJoinWithoutMaster() throws Exception {
		// Master
		List<String> masterKey = Arrays.asList("A", "C-2", "D-1", "E-0", "F-20", "I-3", "J");
		Map<KeyValueLineVo, List<KeyValueLineVo>> expectedLines = JoinTestHelper.createMasterJoinItems(masterKey);
		// Join List
		List<KeyValueLineVo> joinList = JoinTestHelper.extractJoinList(expectedLines);
		List<KeyValueLineVo> masterList = JoinTestHelper.extractMasterList(expectedLines);
		// Add Join Without Master
		joinList.addAll(JoinTestHelper.getJoinList("B", 3));
		Collections.shuffle(joinList);
		Collections.sort(joinList);
		// Do Test
		ExpectedMultiJoinVerifier<KeyValueLineVo, KeyValueLineVo> expecteJoin = new ExpectedMultiJoinVerifier<KeyValueLineVo, KeyValueLineVo>(
				masterKey.size(), expectedLines);
		doReadTest(expecteJoin, masterList, joinList);
	}

	@Test
	public void testWithJoinWithoutMasterAsBegin() throws Exception {
		// Master
		List<String> masterKey = Arrays.asList("C-2", "D-1", "E-0", "F-20", "I-3", "J");
		Map<KeyValueLineVo, List<KeyValueLineVo>> expectedLines = JoinTestHelper.createMasterJoinItems(masterKey);
		// Join List
		List<KeyValueLineVo> joinList = JoinTestHelper.extractJoinList(expectedLines);
		List<KeyValueLineVo> masterList = JoinTestHelper.extractMasterList(expectedLines);
		// Add Join Without Master
		joinList.addAll(JoinTestHelper.getJoinList("A", 10));
		joinList.addAll(JoinTestHelper.getJoinList("B", 3));
		joinList.addAll(JoinTestHelper.getJoinList("G", 10));
		joinList.addAll(JoinTestHelper.getJoinList("H", 10));
		Collections.shuffle(joinList);
		Collections.sort(joinList);
		// Do Test
		ExpectedMultiJoinVerifier<KeyValueLineVo, KeyValueLineVo> expecteJoin = new ExpectedMultiJoinVerifier<KeyValueLineVo, KeyValueLineVo>(
				masterKey.size(), expectedLines);
		doReadTest(expecteJoin, masterList, joinList);
	}

	@Test
	public void testWithJoinWithoutMasterAsEnd() throws Exception {
		// Master
		List<String> masterKey = Arrays.asList("C-2", "D-1", "E-0", "F-20", "I-3", "J");
		Map<KeyValueLineVo, List<KeyValueLineVo>> expectedLines = JoinTestHelper.createMasterJoinItems(masterKey);
		// Join List
		List<KeyValueLineVo> joinList = JoinTestHelper.extractJoinList(expectedLines);
		List<KeyValueLineVo> masterList = JoinTestHelper.extractMasterList(expectedLines);
		// Add Join Without Master
		joinList.addAll(JoinTestHelper.getJoinList("Z", 10));
		Collections.shuffle(joinList);
		Collections.sort(joinList);
		// Do Test
		ExpectedMultiJoinVerifier<KeyValueLineVo, KeyValueLineVo> expecteJoin = new ExpectedMultiJoinVerifier<KeyValueLineVo, KeyValueLineVo>(
				masterKey.size(), expectedLines);
		doReadTest(expecteJoin, masterList, joinList);
	}

	@Test
	public void testWithJoinWithoutMasterAsManyEnd() throws Exception {
		// Master
		List<String> masterKey = Arrays.asList("C-2", "D-1", "E-0", "F-20", "I-3", "J");
		Map<KeyValueLineVo, List<KeyValueLineVo>> expectedLines = JoinTestHelper.createMasterJoinItems(masterKey);
		// Join List
		List<KeyValueLineVo> joinList = JoinTestHelper.extractJoinList(expectedLines);
		List<KeyValueLineVo> masterList = JoinTestHelper.extractMasterList(expectedLines);
		// Add Join Without Master
		joinList.addAll(JoinTestHelper.getJoinList("Y", 8));
		joinList.addAll(JoinTestHelper.getJoinList("X", 12));
		joinList.addAll(JoinTestHelper.getJoinList("Z", 10));
		Collections.shuffle(joinList);
		Collections.sort(joinList);
		// Do Test
		ExpectedMultiJoinVerifier<KeyValueLineVo, KeyValueLineVo> expecteJoin = new ExpectedMultiJoinVerifier<KeyValueLineVo, KeyValueLineVo>(
				masterKey.size(), expectedLines);
		doReadTest(expecteJoin, masterList, joinList);
	}

	public void doReadTest(ExpectedMultiJoinVerifier expecteJoin, List<KeyValueLineVo> masterList,
			List<KeyValueLineVo>... joinLists) throws Exception {
		// Sources

		ListItemReader<KeyValueLineVo> masterReader = new ListItemReader<KeyValueLineVo>(masterList);
		List<ItemReader> joinReaders = new ArrayList<ItemReader>();
		List<KeyValueLineJoinComparator> joinComparators = new ArrayList<KeyValueLineJoinComparator>();
		for (List<KeyValueLineVo> joinList : joinLists) {
			ListItemReader<KeyValueLineVo> joinReader = new ListItemReader<KeyValueLineVo>(joinList);
			joinReaders.add(joinReader);
			joinComparators.add(new KeyValueLineJoinComparator());
		}
		// Expected result

		// Create Differential
		KeyValueLineComparator comparator = new KeyValueLineComparator();
		MultiJoinItemReader<KeyValueLineVo> service = new MultiJoinItemReader<KeyValueLineVo>(masterReader, comparator,
				joinReaders, joinComparators);
		service.open(null);
		MultiJoinItemVO<KeyValueLineVo> line = (MultiJoinItemVO<KeyValueLineVo>) service.read();

		int count = 0;
		log.debug("--- Begin Reading -----------------------------------------------------------------------");
		do {
			count++;
			log.debug("Read line {} : {} ", count, line);
			// Test for master presence
			Assert.assertNotNull(line.getMaster());
			String masterKey = line.getMaster().getKey();
			if (expecteJoin != null) {
				for (int i = 0; i < expecteJoin.getJoinItems(); i++) {
					// Test Master & Join Key
					List<KeyValueLineVo> joinIdx0 = line.getJoin(i);
					if (joinIdx0 != null && !joinIdx0.isEmpty()) {
						for (KeyValueLineVo join : joinIdx0) {
							// Check Master Key
							Assert.assertEquals(masterKey, join.getKey());
						}
					}
					// Test Join Size
					int joinSize = 0;
					if (line.getJoin(i) != null) {
						joinSize = line.getJoin(i).size();
					}
					Assert.assertEquals(expecteJoin.getJoinSize(masterKey, i), joinSize);
					// Test Join Content
					List<KeyValueLineVo> joinExpectedList = new ArrayList<KeyValueLineVo>(expecteJoin.getJoins(
							masterKey, i));
					List<KeyValueLineVo> joinIdx0b = line.getJoin(i);
					if (joinIdx0b != null && !joinIdx0b.isEmpty()) {
						for (KeyValueLineVo join : joinIdx0b) {
							// Check Master Key
							Assert.assertEquals(masterKey, join.getKey());
							// Check is in expected Join
							Assert.assertTrue(joinExpectedList.contains(join));
							Assert.assertTrue(joinExpectedList.remove(join));
						}
					}
					Assert.assertEquals(0, joinExpectedList.size());
				}
			}

		} while ((line = (MultiJoinItemVO<KeyValueLineVo>) service.read()) != null);
		Assert.assertEquals(expecteJoin.getMasterSize(), count);
		// Release Dependencies
		service.close();

		// test All ListEletment was tested
		// Assert.assertEquals(0, testedExpected.size());
		// Assert.assertEquals(0, manageExpected.size());
		log.debug("--- End Reading  -----------------------------------------------------------------------");
	}
}
