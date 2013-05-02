package eu.ttbox.batch.core.fs.relocator;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.ttbox.batch.core.fs.partitionner.FilenameHashPartitionner;
import eu.ttbox.batch.core.fs.partitionner.stat.ItemPartitionStat;

public class FilenameHashRelocatorTest {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Test
	public void testRelocate() throws Exception {
		for (int numPart : new int[] { 2, 3, 5, 7, 10, 11, 15, 20 }) {

			FilenameHashPartitionner relocator = new FilenameHashPartitionner();
			relocator.setNumPartition(numPart);
			relocator.afterPropertiesSet();
			HashSet<String> parents = new HashSet<String>();
			for (String f1 : new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
					"p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" }) {
				StringBuilder sb = new StringBuilder("/tmp/repo-test/");
				sb.append(f1);
				sb.append(".txt");
				File file = new File(sb.toString());
				File relocatorFile = relocator.relocate(file);
				parents.add(relocatorFile.getParent());
				// System.out.println("relocatorFile : " + relocatorFile);
			}
			Assert.assertEquals(numPart, parents.size());
		}
	}

	@Test
	public void testBigPartition() throws Exception {
		for (int numPart : new int[] { 2, 3, 5, 7, 10, 11, 15, 20, 100, 1000 }) {
			log.debug("Test for {} partitions", numPart);
			FilenameHashPartitionner relocator = new FilenameHashPartitionner();
			relocator.setNumPartition(numPart);
			relocator.afterPropertiesSet();
			// HashSet<String> parents = new HashSet<String>();
			Map<String, ItemPartitionStat> parents = new HashMap<String, ItemPartitionStat>();
			String[] text = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
					"p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8",
					"9" };
			long count = 0;

			for (String f3 : text) {
				for (String f2 : text) {
					for (String f1 : text) {
						count++;
						StringBuilder sb = new StringBuilder("/tmp/repo-test/");
						sb.append(f1).append(f2).append(f3);
						sb.append(".txt");
						File file = new File(sb.toString());
						File relocatorFile = relocator.relocate(file);
						// parents.add(relocatorFile.getParent());
						String partKey = relocatorFile.getParent();
						ItemPartitionStat counter = parents.get(partKey);
						if (counter == null) {
							counter = new ItemPartitionStat(partKey);
							parents.put(partKey, counter);
						}
						counter.addItemCount();
					}
				}
			}

			log.debug("Test partition for {} items dispatched in {} parts", count, parents.size());
			Assert.assertEquals(numPart, parents.size());
			// Check repartition
			List<ItemPartitionStat> listParts = new ArrayList<ItemPartitionStat>(parents.values());
			Collections.sort(listParts);
			double expectedItemByPart = count / numPart;
			double delta = Math.max(50d, expectedItemByPart * 0.1d);
			log.debug("For {} partition, expected by partition {} items (delta of {} items)", new Object[] { numPart,
					expectedItemByPart, delta });
			for (ItemPartitionStat listPart : listParts) {
				// log.debug(" * partition id {} contains {} items (delta of {} items)",
				// new Object[] {listPart.getPartitionId(),
				// listPart.getItemCount(),
				// expectedItemByPart-listPart.getItemCount() });
				Assert.assertEquals(expectedItemByPart, listPart.getItemCount(), delta);
			}
		}
	}

	@Test
	public void testComputePartition() throws Exception {
		for (int numPart : new int[] { 2, 3, 5, 7, 10, 11, 15, 20, 100, 1000, 10000 }) {
			log.debug("Test for {} partitions", numPart);
			FilenameHashPartitionner relocator = new FilenameHashPartitionner();
			relocator.setNumPartition(numPart);
			relocator.afterPropertiesSet();
			// HashSet<Integer> parents = new HashSet<Integer>();
			Map<Integer, ItemPartitionStat> parents = new HashMap<Integer, ItemPartitionStat>();
			String[] text = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
					"p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8",
					"9" };
			long count = 0;

			for (String f4 : text) {
				for (String f3 : text) {
					for (String f2 : text) {
						for (String f1 : text) {
							count++;
							StringBuilder sb = new StringBuilder("/tmp/repo-test/");
							sb.append(f1).append(f2).append(f3).append(f4);
							// sb.append(f5);
							sb.append(".txt");
							int partition = relocator.computePartition(sb.toString());
							Integer partKey = Integer.valueOf(partition);
							ItemPartitionStat counter = parents.get(partKey);
							if (counter == null) {
								counter = new ItemPartitionStat(partKey.toString());
								parents.put(partKey, counter);
							}
							counter.addItemCount();
							// parents.add(Integer.valueOf(partition));
						}
					}
				}

			}

			log.debug("Test partition for {} items dispatched in {} parts", count, parents.size());
			Assert.assertEquals(numPart, parents.size());
			// Check repartition
			List<ItemPartitionStat> listParts = new ArrayList<ItemPartitionStat>(parents.values());
			Collections.sort(listParts);
			double expectedItemByPart = count / numPart;
			double delta = Math.max(50d, expectedItemByPart * 0.1d);
			log.debug("For {} partition, expected by partition {} items (delta of {} items)", new Object[] { numPart,
					expectedItemByPart, delta });
			for (ItemPartitionStat listPart : listParts) {
				// log.debug(" * partition id {} contains {} items (delta of {} items)",
				// new Object[] {listPart.getPartitionId(),
				// listPart.getItemCount(),
				// expectedItemByPart-listPart.getItemCount() });
				Assert.assertEquals(expectedItemByPart, listPart.getItemCount(), delta);
			}
		}
	}

	/**
	 * Icecat declare  1 030 509  products
	 * @throws Exception
	 */
	@Test
	public void testComputePartitionForIcecatCount() throws Exception {
		for (int numPart : new int[] { 1000 }) {
			log.debug("Test for {} partitions", numPart);
			FilenameHashPartitionner relocator = new FilenameHashPartitionner();
			relocator.setNumPartition(numPart);
			relocator.afterPropertiesSet();
			// HashSet<Integer> parents = new HashSet<Integer>();
			Map<Integer, ItemPartitionStat> parents = new HashMap<Integer, ItemPartitionStat>();
			String[] text = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
					"p"
//					, "q"
//					, "r"
//					, "s", "t", "u"
//					, "v", "w", "x", "y", "z"  
					//,"0", "1", "2", "3", "4", "5", "6", "7", "8", "9" 
					};
			long count = 0;

			for (String f5 : text) {
				for (String f4 : text) {
					for (String f3 : text) {
						for (String f2 : text) {
							for (String f1 : text) {
								count++;
								StringBuilder sb = new StringBuilder("/tmp/repo-test/");
								sb.append(f1).append(f2).append(f3).append(f4);
								sb.append(f5);
								sb.append(".txt");
								int partition = relocator.computePartition(sb.toString());
								Integer partKey = Integer.valueOf(partition);
								ItemPartitionStat counter = parents.get(partKey);
								if (counter == null) {
									counter = new ItemPartitionStat(partKey.toString());
									parents.put(partKey, counter);
								}
								counter.addItemCount();
								// parents.add(Integer.valueOf(partition));
							}
						}
					}
				}
			}

			log.debug("Test partition for {} items dispatched in {} parts", count, parents.size());
			Assert.assertEquals(numPart, parents.size());
			// Check repartition
			List<ItemPartitionStat> listParts = new ArrayList<ItemPartitionStat>(parents.values());
			Collections.sort(listParts);
			double expectedItemByPart = count / numPart;
			double delta = Math.max(50d, expectedItemByPart * 0.1d);
			log.debug("For {} partition, expected by partition {} items (delta of {} items)", new Object[] { numPart,
					expectedItemByPart, delta });
			for (ItemPartitionStat listPart : listParts) {
				// log.debug(" * partition id {} contains {} items (delta of {} items)",
				// new Object[] {listPart.getPartitionId(),
				// listPart.getItemCount(),
				// expectedItemByPart-listPart.getItemCount() });
				Assert.assertEquals(expectedItemByPart, listPart.getItemCount(), delta);
			}
		}
	}

}
