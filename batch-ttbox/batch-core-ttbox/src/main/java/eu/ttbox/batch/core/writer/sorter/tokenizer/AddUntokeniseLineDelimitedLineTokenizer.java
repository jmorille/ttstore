package eu.ttbox.batch.core.writer.sorter.tokenizer;

import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;

import java.util.List;

public class AddUntokeniseLineDelimitedLineTokenizer extends DelimitedLineTokenizer {


	@Override
	protected List<String> doTokenize(String line) {
		List<String>  tokens = super.doTokenize( line);
		tokens.add(line);
		return tokens;
	}
	
}
