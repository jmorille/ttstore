package eu.ttbox.batch.core.reader.join;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.ListItemReader;

import eu.ttbox.batch.core.reader.join.impl.JoinItemVO;

public class JoinItemReaderTest {

	private static final Logger log = LoggerFactory
			.getLogger(JoinItemReaderTest.class);

	@Before
	public void setUp() throws Exception {
	}

	   

	@Test
	public void testReadAllUnorderMasterSource() throws Exception {
		// Master
		List<String> masterKey = Arrays.asList("A-3", "B-3", "C-3", "D-3",
				"E-3", "F-3", "I-3", "J-3");
		Map<KeyValueLineVo, List<KeyValueLineVo>> expectedLines = JoinTestHelper.createMasterJoinItems(masterKey);
		// Join List
		List<KeyValueLineVo> joinList = JoinTestHelper.extractJoinList(expectedLines);
		List<KeyValueLineVo> masterList = JoinTestHelper.extractMasterList(expectedLines);
		Collections.shuffle(masterList);
		//
		ExpectedJoinVerifier<KeyValueLineVo, KeyValueLineVo> expecteJoin = new ExpectedJoinVerifier<KeyValueLineVo, KeyValueLineVo>(
				expectedLines, masterKey.size());
		boolean asException = false;
		try {
			doReadTest(masterList, joinList, null);
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
		List<String> masterKey = Arrays.asList("A-3", "B-3", "C-3", "D-3",
				"E-3", "F-3", "I-3", "J-3");
		Map<KeyValueLineVo, List<KeyValueLineVo>> expectedLines = JoinTestHelper.createMasterJoinItems(masterKey);
		// Join List
		List<KeyValueLineVo> joinList = JoinTestHelper.extractJoinList(expectedLines);
		List<KeyValueLineVo> masterList = JoinTestHelper.extractMasterList(expectedLines);
		Collections.shuffle(joinList);
		//
		ExpectedJoinVerifier<KeyValueLineVo, KeyValueLineVo> expecteJoin = new ExpectedJoinVerifier<KeyValueLineVo, KeyValueLineVo>(
				expectedLines, masterKey.size());
		boolean asException = false;
		try {
			doReadTest(masterList, joinList, null);
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
		List<String> masterKey = Arrays.asList("A", "B", "C", "D", "E", "F",
				"I", "J");
		Map<KeyValueLineVo, List<KeyValueLineVo>> expectedLines = JoinTestHelper.createMasterJoinItems(masterKey);
		// Join List
		List<KeyValueLineVo> joinList = JoinTestHelper.extractJoinList(expectedLines);
		List<KeyValueLineVo> masterList = JoinTestHelper.extractMasterList(expectedLines);
		//
		ExpectedJoinVerifier<KeyValueLineVo, KeyValueLineVo> expecteJoin = new ExpectedJoinVerifier<KeyValueLineVo, KeyValueLineVo>(
				expectedLines, masterKey.size());
		doReadTest(masterList, joinList, expecteJoin);
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
		ExpectedJoinVerifier<KeyValueLineVo, KeyValueLineVo> expecteJoin = new ExpectedJoinVerifier<KeyValueLineVo, KeyValueLineVo>(
				expectedLines, masterKey.size());
		doReadTest(masterList, joinList, expecteJoin);
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
		ExpectedJoinVerifier<KeyValueLineVo, KeyValueLineVo> expecteJoin = new ExpectedJoinVerifier<KeyValueLineVo, KeyValueLineVo>(
				expectedLines, masterKey.size());
		doReadTest(masterList, joinList, expecteJoin);
	}

	@Test
	public void testWithConstantJoin() throws Exception {
		// Master
		List<String> masterKey = Arrays.asList("A-3", "B-3", "C-3", "D-3",
				"E-3", "F-3", "I-3", "J-3");
		Map<KeyValueLineVo, List<KeyValueLineVo>> expectedLines = JoinTestHelper.createMasterJoinItems(masterKey);
		// Join List
		List<KeyValueLineVo> joinList = JoinTestHelper.extractJoinList(expectedLines);
		List<KeyValueLineVo> masterList = JoinTestHelper.extractMasterList(expectedLines);
		//
		ExpectedJoinVerifier<KeyValueLineVo, KeyValueLineVo> expecteJoin = new ExpectedJoinVerifier<KeyValueLineVo, KeyValueLineVo>(
				expectedLines, masterKey.size());
		doReadTest(masterList, joinList, expecteJoin);
	}

	@Test
	public void testWithHeterousJoin() throws Exception {
		// Master
		List<String> masterKey = Arrays.asList("A-10", "B-5", "C-2", "D-1",
				"E-0", "F-20", "I-3", "J-30");
		Map<KeyValueLineVo, List<KeyValueLineVo>> expectedLines = JoinTestHelper.createMasterJoinItems(masterKey);
		// Join List
		List<KeyValueLineVo> joinList = JoinTestHelper.extractJoinList(expectedLines);
		List<KeyValueLineVo> masterList = JoinTestHelper.extractMasterList(expectedLines);
		//
		ExpectedJoinVerifier<KeyValueLineVo, KeyValueLineVo> expecteJoin = new ExpectedJoinVerifier<KeyValueLineVo, KeyValueLineVo>(
				expectedLines, masterKey.size());
		doReadTest(masterList, joinList, expecteJoin);
	}

	@Test
	public void testWithHeterousJoinLastNoJoin() throws Exception {
		// Master
		List<String> masterKey = Arrays.asList("A", "B-5", "C-2", "D-1", "E-0",
				"F-20", "I-3", "J");
		Map<KeyValueLineVo, List<KeyValueLineVo>> expectedLines = JoinTestHelper.createMasterJoinItems(masterKey);
		// Join List
		List<KeyValueLineVo> joinList = JoinTestHelper.extractJoinList(expectedLines);
		List<KeyValueLineVo> masterList = JoinTestHelper.extractMasterList(expectedLines);
		//
		ExpectedJoinVerifier<KeyValueLineVo, KeyValueLineVo> expecteJoin = new ExpectedJoinVerifier<KeyValueLineVo, KeyValueLineVo>(
				expectedLines, masterKey.size());
		doReadTest(masterList, joinList, expecteJoin);
	}

	@Test
	public void testWithJoinWithoutMaster() throws Exception {
		// Master
		List<String> masterKey = Arrays.asList("A", "C-2", "D-1", "E-0",
				"F-20", "I-3", "J");
		Map<KeyValueLineVo, List<KeyValueLineVo>> expectedLines = JoinTestHelper.createMasterJoinItems(masterKey);
		// Join List
		List<KeyValueLineVo> joinList = JoinTestHelper.extractJoinList(expectedLines);
		List<KeyValueLineVo> masterList = JoinTestHelper.extractMasterList(expectedLines);
		// Add Join Without Master
		joinList.addAll(JoinTestHelper.getJoinList("B", 3));
		Collections.shuffle(joinList);
		Collections.sort(joinList);
		// Do Test
		ExpectedJoinVerifier<KeyValueLineVo, KeyValueLineVo> expecteJoin = new ExpectedJoinVerifier<KeyValueLineVo, KeyValueLineVo>(
				expectedLines, masterKey.size());
		doReadTest(masterList, joinList, expecteJoin);
	}

	@Test
	public void testWithJoinWithoutMasterAsBegin() throws Exception {
		// Master
		List<String> masterKey = Arrays.asList("C-2", "D-1", "E-0", "F-20",
				"I-3", "J");
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
		ExpectedJoinVerifier<KeyValueLineVo, KeyValueLineVo> expecteJoin = new ExpectedJoinVerifier<KeyValueLineVo, KeyValueLineVo>(
				expectedLines, masterKey.size());
		doReadTest(masterList, joinList, expecteJoin);
	}

	@Test
	public void testWithJoinWithoutMasterAsEnd() throws Exception {
		// Master
		List<String> masterKey = Arrays.asList("C-2", "D-1", "E-0", "F-20",
				"I-3", "J");
		Map<KeyValueLineVo, List<KeyValueLineVo>> expectedLines = JoinTestHelper.createMasterJoinItems(masterKey);
		// Join List
		List<KeyValueLineVo> joinList = JoinTestHelper.extractJoinList(expectedLines);
		List<KeyValueLineVo> masterList = JoinTestHelper.extractMasterList(expectedLines);
		// Add Join Without Master
		joinList.addAll(JoinTestHelper.getJoinList("Z", 10));
		Collections.shuffle(joinList);
		Collections.sort(joinList);
		// Do Test
		ExpectedJoinVerifier<KeyValueLineVo, KeyValueLineVo> expecteJoin = new ExpectedJoinVerifier<KeyValueLineVo, KeyValueLineVo>(
				expectedLines, masterKey.size());
		doReadTest(masterList, joinList, expecteJoin);
	}

	@Test
	public void testWithJoinWithoutMasterAsManyEnd() throws Exception {
		// Master
		List<String> masterKey = Arrays.asList("C-2", "D-1", "E-0", "F-20",
				"I-3", "J");
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
		ExpectedJoinVerifier<KeyValueLineVo, KeyValueLineVo> expecteJoin = new ExpectedJoinVerifier<KeyValueLineVo, KeyValueLineVo>(
				expectedLines, masterKey.size());
		doReadTest(masterList, joinList, expecteJoin);
	}

	public void doReadTest(List<KeyValueLineVo> masterList,
			List<KeyValueLineVo> joinList, ExpectedJoinVerifier expecteJoin)
			throws Exception {
		// Sources

		ListItemReader<KeyValueLineVo> masterReader = new ListItemReader<KeyValueLineVo>(
				masterList);
		ListItemReader<KeyValueLineVo> joinReader = new ListItemReader<KeyValueLineVo>(
				joinList);
		// Expected result

		// Create Differential
		KeyValueLineDifferentialComparator comparator = new KeyValueLineDifferentialComparator();
		JoinItemReader<KeyValueLineVo, KeyValueLineVo> service = new JoinItemReader<KeyValueLineVo, KeyValueLineVo>(
				masterReader, joinReader, comparator);
		service.open(null);

		JoinItemVO<KeyValueLineVo, KeyValueLineVo> line = (JoinItemVO<KeyValueLineVo, KeyValueLineVo>) service.read();

		int count = 0;
		log.debug("--- Begin Reading -----------------------------------------------------------------------");
		do {
			count++;
			log.debug("Read line {} : {} ", count, line);
			// Test for master presence
			Assert.assertNotNull(line.getMaster());
			String masterKey = line.getMaster().getKey();
			if (expecteJoin != null) {
				// Test Master & Join Key
				if (line.getJoins() != null && !line.getJoins().isEmpty()) {
					for (KeyValueLineVo join : line.getJoins()) {
						// Check Master Key
						Assert.assertEquals(masterKey, join.getKey()); 
					}
				}
				// Test Join Size
				int joinSize = 0;
				if (line.getJoins() != null) {
					joinSize = line.getJoins().size();
				}
				Assert.assertEquals(expecteJoin.getJoinSize(masterKey),
						joinSize);
				// Test Join Content
				List<KeyValueLineVo> joinExpectedList = new ArrayList<KeyValueLineVo>(
						expecteJoin.getJoins(masterKey));
				if (line.getJoins() != null && !line.getJoins().isEmpty()) {
					for (KeyValueLineVo join : line.getJoins()) {
						// Check Master Key
						Assert.assertEquals(masterKey, join.getKey());
						// Check is in expected Join
						Assert.assertTrue(joinExpectedList.contains(join));
						Assert.assertTrue(joinExpectedList.remove(join));
					}
				}
				Assert.assertEquals(0, joinExpectedList.size());
			}

		} while ((line =(JoinItemVO<KeyValueLineVo, KeyValueLineVo>) service.read()) != null);
		Assert.assertEquals(expecteJoin.getMasterSize(), count);
		// Release Dependencies
		service.close();

		// test All ListEletment was tested
		// Assert.assertEquals(0, testedExpected.size());
		// Assert.assertEquals(0, manageExpected.size());
		log.debug("--- End Reading  -----------------------------------------------------------------------");
	}

}
