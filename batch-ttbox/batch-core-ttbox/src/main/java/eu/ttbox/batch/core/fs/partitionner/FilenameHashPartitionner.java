package eu.ttbox.batch.core.fs.partitionner;

import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import eu.ttbox.batch.core.fs.FilePartitionner;
import eu.ttbox.batch.core.fs.partitionner.stat.ItemPartitionStat;

public class FilenameHashPartitionner implements FilePartitionner, InitializingBean {

	private int numPartition = 10;

	private String partitionPattern = "/p{1}/{0}";

	private MessageFormat partitionFormat = null;

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull("Madatory partitionPattern", partitionPattern);
		partitionFormat = new MessageFormat(partitionPattern);
	}

	public int getNumPartition() {
		return numPartition;
	}

	public void setNumPartition(int numPartition) {
		this.numPartition = numPartition;
	}

	@Override
	public File relocate(File file) {
		String filename = file.getName();
		int partitionId = computePartition(filename);
		File relocatedFile = relocate(file.getParentFile(), filename, partitionId);
		return relocatedFile;
	}

	public int computePartition(String filename) {
		int hashCode = filename.hashCode();
		if (hashCode < 0) {
			hashCode = hashCode * -1;
		}
		int partitionId = hashCode % numPartition;
		return partitionId;
	}

	protected File relocate(File folder, String filename, int partitionId) {
		String partitionedFilename = partitionFormat.format(new Object[] { filename, partitionId });
		File relocatedFile = new File(folder, partitionedFilename);
		return relocatedFile;
	}

	public List<ItemPartitionStat> getPartitionStat(File parentFolder) {
		List<ItemPartitionStat> stats = new ArrayList<ItemPartitionStat>(numPartition);
		for (int partitionId = 0; partitionId <= numPartition; partitionId++) {
			File part = relocate(parentFolder, "", partitionId);
			if (part.exists()) {
				String partKey = part.getName();
				int itemCount = part.list().length;
				ItemPartitionStat stat = new ItemPartitionStat(partKey, itemCount);
				stats.add(stat);
			}
		}
		return stats;
	}

	
}
