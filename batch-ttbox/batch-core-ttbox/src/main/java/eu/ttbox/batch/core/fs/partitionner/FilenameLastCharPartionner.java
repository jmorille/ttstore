package eu.ttbox.batch.core.fs.partitionner;

import eu.ttbox.batch.core.fs.FilePartitionner;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.io.File;
import java.text.MessageFormat;

public class FilenameLastCharPartionner implements FilePartitionner, InitializingBean {

	private int numLastCharPartition = 2;

	private String partitionPattern = "/p{1}/{0}";

	private MessageFormat partitionFormat = null;

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull("Madatory partitionPattern", partitionPattern);
		partitionFormat = new MessageFormat(partitionPattern);
	}

	public int getNumLastCharPartition() {
		return numLastCharPartition;
	}

	public void setNumLastCharPartition(int numPartition) {
		this.numLastCharPartition = numPartition;
	}

	@Override
	public File relocate(File file) {
		String filename = file.getName();
		int extIdx = filename.indexOf("."); 
		String partitionId =filename.substring(Math.max(0, extIdx-numLastCharPartition), extIdx);
		String partitionedFilename = partitionFormat.format(new Object[] { filename, partitionId });
		File relocatedFile = new File(file.getParent(), partitionedFilename);
		return relocatedFile;
	}

}
