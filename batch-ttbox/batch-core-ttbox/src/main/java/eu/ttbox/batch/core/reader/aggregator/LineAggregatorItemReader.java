package eu.ttbox.batch.core.reader.aggregator;

import eu.ttbox.batch.core.reader.aggregator.impl.LineAggregatorItemVOFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.*;

import java.util.Comparator;


/**
 * 
 * @author Morille J�r�me
 * 
 * @param <JOIN>
 *            The Format of the provider Item
 * @param <MASTER>
 *            The Format of the referential Item
 */
public class LineAggregatorItemReader<MASTER> implements
		ItemReader<LineAggregatorItem<MASTER>>, ItemStream {

	private static final Logger log = LoggerFactory
			.getLogger(LineAggregatorItemReader.class);

	private ItemReader<MASTER> itemReader;

	private MASTER currentItem;

	private Comparator<MASTER> comparator;

	private LineAggregatorItemFactory<MASTER> itemFactory = new LineAggregatorItemVOFactory<MASTER>();
	
	public LineAggregatorItemReader() {
		super();
	}

	public LineAggregatorItemReader(ItemReader<MASTER> itemReader,
			Comparator<MASTER> comparator) {
		super();
		this.itemReader = itemReader;
		this.comparator = comparator;
	}

	public void setItemReader(ItemReader<MASTER> itemReader) {
		this.itemReader = itemReader;
	}

	public void setComparator(Comparator<MASTER> comparator) {
		this.comparator = comparator;
	}

	
	
	public void setItemFactory(LineAggregatorItemFactory<MASTER> itemFactory) {
		this.itemFactory = itemFactory;
	}

	@Override
	public void open(ExecutionContext executionContext)
			throws ItemStreamException {

		// Read First Destination Line
		try {
			if (itemReader instanceof ItemStream) {
				ItemStream itemReaderReferentialStrean = (ItemStream) itemReader;
				itemReaderReferentialStrean.open(executionContext);
			}

			currentItem = itemReader.read();
		} catch (Exception e) {
			throw new ItemStreamException(
					"Error on opening itemReaderDestination : "
							+ e.getMessage(), e);
		}
	}

	@Override
	public void update(ExecutionContext executionContext)
			throws ItemStreamException {
		if (itemReader instanceof ItemStream) {
			ItemStream itemReaderReferentialStrean = (ItemStream) itemReader;
			itemReaderReferentialStrean.update(executionContext);
		}
	}

	@Override
	public void close() throws ItemStreamException {
		if (itemReader instanceof ItemStream) {
			ItemStream itemReaderReferentialStrean = (ItemStream) itemReader;
			itemReaderReferentialStrean.close();
		}
		// Release cache dependencies
		currentItem = null;
	}

	@Override
	public LineAggregatorItem<MASTER> read() throws Exception,
			UnexpectedInputException, ParseException,
			NonTransientResourceException {
		// Prepare result
		LineAggregatorItem<MASTER> result = null;
		// Manage Differential
		boolean isItemMaster = currentItem != null;
		if (isItemMaster) {
			// Icrement Join utils find a matching Master
			result =  itemFactory.createLine(); ;
			int res = 0;
			while (isItemMaster && res == 0) { 
				result.addLine(currentItem);
				MASTER lastRead = currentItem;
				// Check For next
				incrementItemReaderMaster();
				// Prepare Next It
				isItemMaster = currentItem != null;
				res = compareReferentialMaster(lastRead, currentItem);
			}
		}
		// Increment for next iteration
		return result;
	}

	private int compareReferentialMaster(MASTER master, MASTER join) {
		if (master != null && join != null) {
			return comparator.compare(master, join);
		} else if (master == null && join == null) {
			return 0;
		} else if (join == null) {
			return -1;
		} else {
			return 1;
		}

	}

	private void incrementItemReaderMaster() throws UnexpectedInputException,
			ParseException, NonTransientResourceException, Exception {
		MASTER previous = currentItem;
		currentItem = itemReader.read();
		// Comparator previous < currentItemReferential
		if (currentItem != null
				&& comparator.compare(previous, currentItem) > 0) {
			throw new UnexpectedInputException(
					"Un ordered ASC itemReaderDestination as expected");
		}
	}

}
