package eu.ttbox.batch.core.fs.partitionner;

import eu.ttbox.batch.core.fs.FilePartitionner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.io.File;
import java.util.List;

public class FsRelocatorPartionner implements FilePartitionner, InitializingBean {

	private static Logger log = LoggerFactory.getLogger(FsRelocatorPartionner.class);

	private FilePartitionner filePartitionner;

	private List<FilePartitionner> previousPartitionner;

	public void setFilePartitionner(FilePartitionner mainRelocator) {
		this.filePartitionner = mainRelocator;
	}

	public void setPreviousPartitionner(List<FilePartitionner> secondaryRelocator) {
		this.previousPartitionner = secondaryRelocator;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (previousPartitionner != null && previousPartitionner.isEmpty()) {
			previousPartitionner = null;
		}
	}

	@Override
	public File relocate(File file) {
		File relocatedFile = file;
		if (filePartitionner != null) {
			relocatedFile = filePartitionner.relocate(relocatedFile);
			// Manage Unrelocated file
			if (file.exists() && !file.equals(relocatedFile)) {
				renameFileToRelocatedFile(file, relocatedFile);
			}
		}
		// Manage Secondary relocator
		if (previousPartitionner != null && !previousPartitionner.isEmpty()) {
			for (FilePartitionner rel : previousPartitionner) {
				File secRel = rel.relocate(file);
				renameFileToRelocatedFile(secRel, relocatedFile);
			}
		}
		return relocatedFile;
	}

	private boolean renameFileToRelocatedFile(File previousFile, File relocatedFile) {
		boolean isDoSomething = false;
		if (previousFile != null && previousFile.exists() && !previousFile.equals(relocatedFile)) {
			if (relocatedFile.exists()) {
				isDoSomething = previousFile.delete();
			} else {
				isDoSomething = previousFile.renameTo(relocatedFile);
				if (isDoSomething) {
					File previousParent = previousFile.getParentFile();
					if (previousParent.list().length < 1) {
						boolean isPreviousParentDel = previousParent.delete();
						log.info("Delete empty parent directory {} : {}" , previousParent, isPreviousParentDel);
					}
				}
			}
		}
		return isDoSomething;
	}

}
