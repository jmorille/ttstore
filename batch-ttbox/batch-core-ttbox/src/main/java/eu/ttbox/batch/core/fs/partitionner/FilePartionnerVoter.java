package eu.ttbox.batch.core.fs.partitionner;

import eu.ttbox.batch.core.fs.FilePartitionner;
import eu.ttbox.batch.core.voter.DecisionManager;

import java.io.File;

public class FilePartionnerVoter implements FilePartitionner {

	FilePartitionner filePartitionner;
 
	DecisionManager<File> decisionManager;
	
	public void setFilePartitionner(FilePartitionner filePartitionner) {
		this.filePartitionner = filePartitionner;
	}
  

	public void setDecisionManager(DecisionManager<File> decisionManager) {
		this.decisionManager = decisionManager;
	}

 
	@Override
	public File relocate(File file) {
		boolean voteToRePart = false;
		if (decisionManager!=null) {
			voteToRePart= decisionManager.decide(file);
		}
		if (voteToRePart) {
			return filePartitionner.relocate(file);
		} else {
			return file;
		}
	}

}
