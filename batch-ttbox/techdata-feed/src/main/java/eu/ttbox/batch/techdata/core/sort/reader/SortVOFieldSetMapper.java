package eu.ttbox.batch.techdata.core.sort.reader;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import eu.ttbox.batch.techdata.core.sort.SortVO;

public class SortVOFieldSetMapper implements FieldSetMapper<SortVO> {

	String[] keys  = null;;
	
	String line = null;;
	  
	public void setKeys(String[] keys) {
		this.keys = keys;
	}
 

	public void setLine(String line) {
		this.line = line;
	}


	@Override
	public SortVO mapFieldSet(FieldSet fs) throws BindException {
		if (fs == null) {
			return null;
		}
		// Compute Key
		int keySize = keys.length;
		String[] lineKeys = new String[keySize];
		for (int i=0; i<keySize; i++) {
			lineKeys[i] = fs.readString(keys[i]);

		}
		// Extract Line
		String lineContent = fs.readRawString(line);
		// Construct VO
		SortVO stock = new SortVO(lineKeys, lineContent);
		return stock;
	}

}
