package eu.ttbox.batch.core.fs.partitionner;

import eu.ttbox.batch.core.fs.FilePartitionner;
import eu.ttbox.batch.core.fs.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

public class AliasFilenamePartitionner implements FilePartitionner {

	private static Logger log = LoggerFactory.getLogger(AliasFilenamePartitionner.class);

	private List<String> suffixAliases;
	private List<String> prefixAliases;
	private List<String> extAliases;

	private FilePartitionner filePartitionner;

 	
	public void setSuffixAliases(List<String> suffixAliases) {
		this.suffixAliases = suffixAliases;
	}

	public List<String> getPrefixAliases() {
		return prefixAliases;
	}

	public void setPrefixAliases(List<String> prefixAliases) {
		this.prefixAliases = prefixAliases;
	}

	public void setFilePartitionner(FilePartitionner filePartitionner) {
		this.filePartitionner = filePartitionner;
	}
 	
	public List<String> getSuffixAliases() {
		return suffixAliases;
	}
 	
	public List<String> getExtAliases() {
		return extAliases;
	}

	public void setExtAliases(List<String> extAliases) {
		this.extAliases = extAliases;
	}

	public FilePartitionner getFilePartitionner() {
		return filePartitionner;
	}
 	
	@Override
	public File relocate(File file) {
		return relocate(file, this.prefixAliases, this.suffixAliases, this.extAliases);
	}
	
 	public File relocate(File file, List<String> prefixAliases, List<String> suffixAliases, List<String> extAliases) {
		File testedFile = file;
		if (filePartitionner != null) {
			testedFile = filePartitionner.relocate(testedFile);
		}
 		// Test For the alternative files
		if (testedFile != null && !testedFile.exists()) {
			// Check for extension files
			File extFile = isExistFileExt(testedFile, extAliases);
			if (extFile!=null){
				return extFile;
			}
			// Check For Alias
			boolean isSuffixAliases = suffixAliases != null && !suffixAliases.isEmpty();
			boolean isPrefixAliases = prefixAliases != null && !prefixAliases.isEmpty();
			if (isSuffixAliases || isPrefixAliases) {
				// Suffixed
				File fileAlias = isExistFileSuffixed(testedFile, suffixAliases, extAliases);
				// Prefixed
				if (isPrefixAliases && fileAlias == null) {
					fileAlias = isExistFilePrefixedAndSuffixed(testedFile, prefixAliases, suffixAliases, extAliases);
				}
				// Manage Result of analysis
				if (fileAlias != null) {
					log.debug("use Alias {} for original File {}", fileAlias, file);
					return fileAlias;
				}
			}
		}
		return testedFile;
	}

	private File isExistFileSuffixed(File testedFile, List<String> suffixAliases, List<String> extAliases) {
		if (suffixAliases == null || suffixAliases.isEmpty()) {
			return null;
		}
		for (String alias : suffixAliases) {
			File fileAlias = FileUtils.getSuffixedFile(testedFile, alias, false);
			if (fileAlias.exists()) {
				return fileAlias;
			}
			fileAlias = isExistFileExt(fileAlias, extAliases); 
			if (fileAlias!=null){
				return fileAlias;
			}
		}
		return null;
	}
	
	private File isExistFileExt(File testedFile, List<String> extAliases){
		if (extAliases==null || extAliases.isEmpty()) {
			return null;
		}
		for (String extAlias : extAliases) {
			File fileAliasExt = FileUtils.getExtAliasFile(testedFile, extAlias);
			if (fileAliasExt.exists()) {
				return fileAliasExt;
			}
		}
		return null;
	}

	private File isExistFilePrefixedAndSuffixed(File testedFile, List<String> prefixAliases, List<String> suffixAliases, List<String> extAliases) {
		if (prefixAliases==null|| prefixAliases.isEmpty()) {
			return null;
		}
		for (String prefix : prefixAliases) {
			File prefixedFile = getPrefixedFile(testedFile, prefix);
			if (prefixedFile.exists()) {
				return prefixedFile;
			}
			// Test Extension
			File extAlias = isExistFileExt(prefixedFile, extAliases); 
			if (extAlias!=null){
				return extAlias;
			}
			// Test Prefix and suffixed
			if (suffixAliases != null) {
				for (String alias : suffixAliases) {
					File fileAlias = FileUtils.getSuffixedFile(prefixedFile, alias, false);
					if (fileAlias.exists()) {
						return fileAlias;
					}
					// Test Extension
					File sufixedExtAlias = isExistFileExt(fileAlias, extAliases); 
					if (sufixedExtAlias!=null){
						return sufixedExtAlias;
					}
				}
			}
		}
		return null;
	}

	private File getPrefixedFile(File testedFile, String prefix) {
		File parentFile = testedFile.getParentFile();
		String filename = testedFile.getName();
		StringBuffer sb = new StringBuffer(prefix.length() + filename.length());
		sb.append(prefix).append(filename);
		File prefixedFile = new File(parentFile, sb.toString());
		return prefixedFile;
	}

}
