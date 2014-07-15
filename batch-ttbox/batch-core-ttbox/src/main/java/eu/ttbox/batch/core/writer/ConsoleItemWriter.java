package eu.ttbox.batch.core.writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class ConsoleItemWriter<T> implements ItemWriter<T> {

	private static Logger log = LoggerFactory.getLogger(ConsoleItemWriter.class);
	
	@Override
	public void write(List<? extends T> items) throws Exception {
		int i = 0;
		int size = items.size();
		for (T item : items) {
			log.info("{} / {} : {}", new Object[] {++i , size, item} );
//			System.out.println(" " + ++i + " / " + size + " : " + item);
		}

	}

}
