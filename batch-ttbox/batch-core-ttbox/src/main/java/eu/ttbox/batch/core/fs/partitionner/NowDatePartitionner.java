package eu.ttbox.batch.core.fs.partitionner;

import java.io.File;
import java.text.MessageFormat;
import java.util.Date;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import eu.ttbox.batch.core.fs.FilePartitionner;

public class NowDatePartitionner implements FilePartitionner, InitializingBean {

	private String partitionPattern = "/p{1,date,yyyy-MM-dd}/{0}";

	private MessageFormat partitionFormat = null;
 
	
	public void setPartitionPattern(String partitionPattern) {
		this.partitionPattern = partitionPattern;
	}



	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull("Madatory partitionPattern", partitionPattern);
		partitionFormat = new MessageFormat(partitionPattern);
	}

 

	@Override
	public File relocate(File file) {
		String filename = file.getName();
		Date datePartionner = new Date();
		String partitionedFilename = partitionFormat.format(new Object[] { filename, datePartionner });
		File relocatedFile = new File(file.getParent(), partitionedFilename);
		return relocatedFile;
	}

}
