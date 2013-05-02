package eu.ttbox.batch.techdata.core.sort.reader;

import java.util.List;

import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;

public class AddUntokeniseLineDelimitedLineTokenizer extends DelimitedLineTokenizer {


	@Override
	protected List<String> doTokenize(String line) {
		List<String>  tokens = super.doTokenize( line);
		tokens.add(line);
		return tokens;
	}
	
}
