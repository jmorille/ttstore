package eu.ttbox.batch.core.reader.differential;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.ListItemReader;

public class DifferentialItemReaderTest {
	private static final Logger log = LoggerFactory.getLogger(DifferentialItemReaderTest.class);

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testContinuousRead() throws Exception {
		List<String> listSrc = Arrays.asList("A", "B", "C", "D", "E", "I", "J");
		List<String> listDest = Arrays.asList("D", "E", "F", "G", "H");
		ExpectedCrud expecteCrud = new ExpectedCrud( //
				Arrays.asList("A", "B", "C", "I", "J"), // Create
				Arrays.asList("D", "E"), // Update
				Arrays.asList("F", "G", "H") // Delete
		);
		doReadTest(listSrc, listDest, expecteCrud);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testReadCreate() throws Exception {
		// --- All ---
		// ------------------
		try {
			List<String> listSrc = Arrays.asList("A", "B", "C", "D", "E", "I", "J");
			List<String> listDest = Collections.EMPTY_LIST;
			ExpectedCrud expecteCrud = new ExpectedCrud( //
					listSrc, // Create
					Collections.EMPTY_LIST, // Update
					Collections.EMPTY_LIST // Delete
			);
			doReadTest(listSrc, listDest, expecteCrud);
		} finally {
			// Nothing to do
		}
		// --- One First ---
		// ------------------
		try {
			List<String> listSrc = Arrays.asList("A", "B", "C", "D", "E", "I", "J");
			List<String> listDest = Arrays.asList("B", "C", "D", "E", "I", "J");
			ExpectedCrud expecteCrud = new ExpectedCrud( //
					Arrays.asList("A"), // Create
					Arrays.asList("B", "C", "D", "E", "I", "J"), // Update
					Collections.EMPTY_LIST// Delete
			);
			doReadTest(listSrc, listDest, expecteCrud);
		} finally {
			// Nothing to do
		}
		// --- One Last ---
		// ----------------
		try {
			List<String> listSrc = Arrays.asList("A", "B", "C", "D", "E", "I", "J");
			List<String> listDest = Arrays.asList("A", "B", "C", "D", "E", "I");
			ExpectedCrud expecteCrud = new ExpectedCrud( //
					Arrays.asList("J"), // Create
					Arrays.asList("A", "B", "C", "D", "E", "I"), // Update
					Collections.EMPTY_LIST // Delete
			);
			doReadTest(listSrc, listDest, expecteCrud);
		} finally {
			// Nothing to do
		}
		// --- One First and One Last ---
		// ------------------------------
		try {
			List<String> listSrc = Arrays.asList("A", "B", "C", "D", "E", "I", "J");
			List<String> listDest = Arrays.asList("B", "C", "D", "E", "I");
			ExpectedCrud expecteCrud = new ExpectedCrud( //
					Arrays.asList("A", "J"), // Create
					Arrays.asList("B", "C", "D", "E", "I"), // Update
					Collections.EMPTY_LIST // Delete
			);
			doReadTest(listSrc, listDest, expecteCrud);
		} finally {
			// Nothing to do
		}

	}

	@Test
	@SuppressWarnings("unchecked")
	public void testReadDeletedAll() throws Exception {
		// --- All ---
		// ------------------
		List<String> listSrc = Collections.EMPTY_LIST;
		List<String> listDest = Arrays.asList("A", "B", "C", "D", "E", "I", "J");
		ExpectedCrud expecteCrud = new ExpectedCrud( //
				Collections.EMPTY_LIST, // Create
				Collections.EMPTY_LIST, // Update
				listDest // Delete
		);
		doReadTest(listSrc, listDest, expecteCrud);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testReadDeletedOneFirst() throws Exception {
		List<String> listSrc = Arrays.asList("B", "C", "D", "E", "I", "J");
		List<String> listDest = Arrays.asList("A", "B", "C", "D", "E", "I", "J");
		ExpectedCrud expecteCrud = new ExpectedCrud( //
				Collections.EMPTY_LIST, // Create
				Arrays.asList("B", "C", "D", "E", "I", "J"), // Update
				Arrays.asList("A") // Delete
		);
		doReadTest(listSrc, listDest, expecteCrud);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testReadDeletedOneLast() throws Exception {

		// --- One Last ---
		// ----------------
		List<String> listSrc = Arrays.asList("A", "B", "C", "D", "E", "I");
		List<String> listDest = Arrays.asList("A", "B", "C", "D", "E", "I", "J");
		ExpectedCrud expecteCrud = new ExpectedCrud( //
				Collections.EMPTY_LIST, // Create
				Arrays.asList("A", "B", "C", "D", "E", "I"), // Update
				Arrays.asList("J") // Delete
		);
		doReadTest(listSrc, listDest, expecteCrud);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testReadDeletedOneFirstAndOneLast() throws Exception {

		// --- One First and One Last ---
		// ------------------------------
		List<String> listSrc = Arrays.asList("B", "C", "D", "E", "I");
		List<String> listDest = Arrays.asList("A", "B", "C", "D", "E", "I", "J");
		ExpectedCrud expecteCrud = new ExpectedCrud( //
				Collections.EMPTY_LIST, // Create
				Arrays.asList("B", "C", "D", "E", "I"), // Update
				Arrays.asList("A", "J") // Delete
		);
		doReadTest(listSrc, listDest, expecteCrud);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testReadAllUpdated() throws Exception {
		// --- All ---
		// ------------------
		try {
			List<String> listSrc = Arrays.asList("A", "B", "C", "D", "E", "I", "J");
			List<String> listDest = listSrc;
			ExpectedCrud expecteCrud = new ExpectedCrud( //
					Collections.EMPTY_LIST, // Create
					listSrc, // Update
					Collections.EMPTY_LIST // Delete
			);
			doReadTest(listSrc, listDest, expecteCrud);
		} finally {
			// Nothing to do
		}
		// --- One First and One Last ---
		// ------------------------------
		try {
			List<String> listSrc = Arrays.asList("A", "B", "C", "D", "E", "I", "J");
			List<String> listDest = Arrays.asList("A", "J");
			ExpectedCrud expecteCrud = new ExpectedCrud( //
					Arrays.asList("B", "C", "D", "E", "I"), // Create
					Arrays.asList("A", "J"), // Update
					Collections.EMPTY_LIST // Delete
			);
			doReadTest(listSrc, listDest, expecteCrud);
		} finally {
			// Nothing to do
		}
		// --- One First, Last and Middle ---
		// ------------------------------
		try {
			List<String> listSrc = Arrays.asList("A", "B", "C", "D", "E", "I", "J");
			List<String> listDest = Arrays.asList("A", "D", "J");
			ExpectedCrud expecteCrud = new ExpectedCrud( //
					Arrays.asList("B", "C", "E", "I"), // Create
					Arrays.asList("A", "D", "J"), // Update
					Collections.EMPTY_LIST // Delete
			);
			doReadTest(listSrc, listDest, expecteCrud);
		} finally {
			// Nothing to do
		}
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testReadAllUnorderSource() throws Exception {
		List<String> listDest = Collections.EMPTY_LIST;
		// Middle
		try {
			List<String> listSrc = Arrays.asList("A", "B", "C", "D", "Z", "E", "I", "J");
			doReadTest(listSrc, listDest, null);
			Assert.assertTrue("Need to throw an exception before to go here", false);
		} catch (UnexpectedInputException e) {
			// Check The Exception
			Assert.assertNotNull(e.getMessage());
		}
		// Begin
		try {
			List<String> listSrc = Arrays.asList("Z", "A", "B", "C", "D", "E", "I", "J");
			doReadTest(listSrc, listDest, null);
			Assert.assertTrue("Need to throw an exception before to go here", false);
		} catch (UnexpectedInputException e) {
			// Check The Exception
			Assert.assertNotNull(e.getMessage());
		}
		// End
		try {
			List<String> listSrc = Arrays.asList("A", "B", "C", "D", "E", "I", "J", "Z", "A");
			doReadTest(listSrc, listDest, null);
			Assert.assertTrue("Need to throw an exception before to go here", false);
		} catch (UnexpectedInputException e) {
			// Check The Exception
			Assert.assertNotNull(e.getMessage());
		}
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testReadAllUnorderDest() throws Exception {
		List<String> listSrc = Collections.EMPTY_LIST;
		// Middle
		try {
			List<String> listDest = Arrays.asList("A", "B", "C", "D", "Z", "E", "I", "J");
			doReadTest(listSrc, listDest, null);
			Assert.assertTrue("Need to throw an exception before to go here", false);
		} catch (UnexpectedInputException e) {
			// Check The Exception
			Assert.assertNotNull(e.getMessage());
		}
		// Begin
		try {
			List<String> listDest = Arrays.asList("Z", "A", "B", "C", "D", "E", "I", "J");
			doReadTest(listSrc, listDest, null);
			Assert.assertTrue("Need to throw an exception before to go here", false);
		} catch (UnexpectedInputException e) {
			// Check The Exception
			Assert.assertNotNull(e.getMessage());
		}
		// End
		try {
			List<String> listDest = Arrays.asList("A", "B", "C", "D", "E", "I", "J", "Z", "A");
			doReadTest(listSrc, listDest, null);
			Assert.assertTrue("Need to throw an exception before to go here", false);
		} catch (UnexpectedInputException e) {
			// Check The Exception
			Assert.assertNotNull(e.getMessage());
		}
	}

	public void doReadTest(List<String> listSrc, List<String> listDest, ExpectedCrud expecteCrud) throws Exception {
		// Sources

		ListItemReader<String> joinReader = new ListItemReader<String>(listSrc);
		ListItemReader<String> masterReader = new ListItemReader<String>(listDest);
		// Expected result

		// Expected Tested
		Set<String> testedExpected = new HashSet<String>();
		if (expecteCrud != null) {
			testedExpected.addAll(expecteCrud.getCreateExpected());
			testedExpected.addAll(expecteCrud.getUpdateExpected());
			testedExpected.addAll(expecteCrud.getDeleteExpected());
		}
		// Expected Managed
		Set<String> manageExpected = new HashSet<String>();
		manageExpected.addAll(listSrc);
		manageExpected.addAll(listDest);

		// Create Differential
		StringDifferentialComparator comparator = new StringDifferentialComparator();
		DifferentialItemReader<String, String> service = new DifferentialItemReader<String, String>(masterReader,
				joinReader, comparator);
		service.open(null);

		DifferentialItem<String, String> line = service.read();

		int count = 0;
		log.debug("--- Begin Reading -----------------------------------------------------------------------");
		do {
			log.debug("Read line {} : {} ", ++count, line);
			switch (line.getStatus()) {
			case CREATE:
				Assert.assertNotNull(line.getJoinItem());
				Assert.assertNull(line.getMasterItem());
				if (expecteCrud != null) {
					Assert.assertTrue("Not an expected created for " + line,
							expecteCrud.getCreateExpected().contains(line.getJoinItem()));
					testedExpected.remove(line.getJoinItem());
				}
				manageExpected.remove(line.getJoinItem());
				break;
			case UPDATE:
				Assert.assertNotNull(line.getJoinItem());
				Assert.assertNotNull(line.getMasterItem());
				if (expecteCrud != null) {
					Assert.assertTrue("Not an expected updated for " + line,
							expecteCrud.getUpdateExpected().contains(line.getJoinItem()));
					testedExpected.remove(line.getJoinItem());
				}
				manageExpected.remove(line.getJoinItem());
				break;
			case DELETE:
				Assert.assertNull(line.getJoinItem());
				Assert.assertNotNull(line.getMasterItem());
				if (expecteCrud != null) {
					Assert.assertTrue("Not an expected deleted for " + line,
							expecteCrud.getDeleteExpected().contains(line.getMasterItem()));
					testedExpected.remove(line.getMasterItem());
				}
				manageExpected.remove(line.getMasterItem());
				break;
			default:
				break;
			}
		} while ((line = service.read()) != null);
		// Release Dependencies
		service.close();

		// test All ListEletment was tested
		Assert.assertEquals(0, testedExpected.size());
		Assert.assertEquals(0, manageExpected.size());
		log.debug("--- End Reading  -----------------------------------------------------------------------");
	}

}
