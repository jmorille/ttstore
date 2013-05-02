package eu.ttbox.batch.core.reader.join;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import eu.ttbox.batch.core.reader.differential.DifferentialComparator;
import eu.ttbox.batch.core.reader.join.impl.JoinItemVOFactory;

/**
 * 
 * @author Morille Jérôme
 * 
 * @param <JOIN>
 *            The Format of the provider Item
 * @param <MASTER>
 *            The Format of the referential Item
 */
public class JoinItemReader<MASTER, JOIN> implements
		ItemReader<JoinItem<MASTER, JOIN>>, ItemStream {

	private static final Logger log = LoggerFactory
			.getLogger(JoinItemReader.class);

	private ItemReader<MASTER> itemReaderMaster;

	private ItemReader<JOIN> itemReaderJoin;

	private MASTER currentItemMaster;

	private JOIN currentItemJoin;

	private DifferentialComparator<MASTER, JOIN> comparator;

	private JoinItemFactory<MASTER, JOIN>  joinItemFactory  = new JoinItemVOFactory<MASTER, JOIN>();
	
	public JoinItemReader() {
		super();
	}

	
	
	public JoinItemReader(ItemReader<MASTER> itemReaderDestination,
			ItemReader<JOIN> itemReaderProvider,
			DifferentialComparator<MASTER, JOIN> comparator) {
		super();
		this.itemReaderJoin = itemReaderProvider;
		this.itemReaderMaster = itemReaderDestination;
		this.comparator = comparator;
	}
 
	
	public void setJoinItemFactory(JoinItemFactory<MASTER, JOIN> joinItemFactory) {
		this.joinItemFactory = joinItemFactory;
	}



	public void setItemReaderJoin(ItemReader<JOIN> itemReaderProvider) {
		this.itemReaderJoin = itemReaderProvider;
	}

	public void setItemReaderMaster(ItemReader<MASTER> itemReaderDestination) {
		this.itemReaderMaster = itemReaderDestination;
	}

	public void setComparator(DifferentialComparator<MASTER, JOIN> comparator) {
		this.comparator = comparator;
	}

	@Override
	public void open(ExecutionContext executionContext)
			throws ItemStreamException {
		// Read First Source Line
		try {
			if (itemReaderJoin instanceof ItemStream) {
				ItemStream itemReaderProviderStrean = (ItemStream) itemReaderJoin;
				itemReaderProviderStrean.open(executionContext);
			}
			currentItemJoin = itemReaderJoin.read();
		} catch (Exception e) {
			throw new ItemStreamException(
					"Error on opening itemReaderProvider : " + e.getMessage(),
					e);
		}
		// Read First Destination Line
		try {
			if (itemReaderMaster instanceof ItemStream) {
				ItemStream itemReaderReferentialStrean = (ItemStream) itemReaderMaster;
				itemReaderReferentialStrean.open(executionContext);
			}

			currentItemMaster = itemReaderMaster.read();
		} catch (Exception e) {
			throw new ItemStreamException(
					"Error on opening itemReaderDestination : "
							+ e.getMessage(), e);
		}
	}

	@Override
	public void update(ExecutionContext executionContext)
			throws ItemStreamException {
		if (itemReaderJoin instanceof ItemStream) {
			ItemStream itemReaderProviderStrean = (ItemStream) itemReaderJoin;
			itemReaderProviderStrean.update(executionContext);
		}
		if (itemReaderMaster instanceof ItemStream) {
			ItemStream itemReaderReferentialStrean = (ItemStream) itemReaderMaster;
			itemReaderReferentialStrean.update(executionContext);
		}
	}

	@Override
	public void close() throws ItemStreamException {
		if (itemReaderJoin instanceof ItemStream) {
			ItemStream itemReaderProviderStrean = (ItemStream) itemReaderJoin;
			itemReaderProviderStrean.close();
		}
		if (itemReaderMaster instanceof ItemStream) {
			ItemStream itemReaderReferentialStrean = (ItemStream) itemReaderMaster;
			itemReaderReferentialStrean.close();
		}
		// Release cache dependencies
		currentItemJoin = null;
		currentItemMaster = null;
	}

	@Override
	public JoinItem<MASTER, JOIN> read() throws Exception,
			UnexpectedInputException, ParseException,
			NonTransientResourceException {
		// Prepare result
		JoinItem<MASTER, JOIN> result = null;
		// Manage Differential
		boolean isItemJoin = currentItemJoin != null;
		boolean isItemMaster = currentItemMaster != null;
		if (isItemJoin || isItemMaster) {
			// Compare Line
			int res = compareReferentialMaster(currentItemMaster,
					currentItemJoin);
			// Icrement Join utils find a matching Master
			while (isItemJoin && res > 0) {
				log.debug("Ignore Join for no Master Key matching : {}", currentItemJoin);
				incrementItemReaderJoin();
				isItemJoin = currentItemJoin != null;
				res = compareReferentialMaster(currentItemMaster,
						currentItemJoin);
			}
			if (isItemMaster&& res <= 0) {
				// Manage Increment Join
				result = joinItemFactory.createLine( currentItemMaster);
				while (isItemJoin&& res == 0) { 
					// Add Join
					result.addJoin(currentItemJoin);
					incrementItemReaderJoin();
					isItemJoin = currentItemJoin != null;
					// Prepare Nex It
					res = compareReferentialMaster(currentItemMaster,
							currentItemJoin);
				}
				incrementItemReaderMaster();
			}  
		}

		// Increment for next iteration
		return result;
	}

	private int compareReferentialMaster(MASTER master, JOIN join) {
		if (master != null && join != null) {
			return comparator.compareMasterJoin(master, join);
		} else if (master == null && join == null) {
			return 0;
		} else if (join == null) {
			return -1;
		} else {
			return 1;
		}

	}

	private void incrementItemReaderJoin() throws UnexpectedInputException,
			ParseException, NonTransientResourceException, Exception {
		JOIN previous = currentItemJoin;
		currentItemJoin = itemReaderJoin.read();
		// Comparator previous < currentItemProvider
		if (currentItemJoin != null
				&& comparator.compareJoins(previous, currentItemJoin) > 0) {
			throw new UnexpectedInputException(
					"Un ordered ASC itemReaderProvider as expected");
		}
	}

	private void incrementItemReaderMaster() throws UnexpectedInputException,
			ParseException, NonTransientResourceException, Exception {
		MASTER previous = currentItemMaster;
		currentItemMaster = itemReaderMaster.read();
		// Comparator previous < currentItemReferential
		if (currentItemMaster != null
				&& comparator.compareMasters(previous, currentItemMaster) > 0) {
			throw new UnexpectedInputException(
					"Un ordered ASC itemReaderDestination as expected");
		}
	}

}
