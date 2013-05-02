package eu.ttbox.feed.core.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class ConsoleItemWriter<T> implements ItemWriter<T> {

	@Override
	public void write(List<? extends T> items) throws Exception {
		int i = 0;
		int size = items.size();
		for (T item : items) {
			System.out.println(" " + ++i + " / " + size + " : " + item);
		}
	}

}
