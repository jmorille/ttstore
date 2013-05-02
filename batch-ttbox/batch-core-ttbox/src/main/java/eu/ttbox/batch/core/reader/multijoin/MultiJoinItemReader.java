package eu.ttbox.batch.core.reader.multijoin;

import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import eu.ttbox.batch.core.reader.multijoin.impl.MultiJoinItemVOFactory;

/**
 * 
 * @author Morille Jérôme
 * 
 * @param <JOIN>
 *            The Format of the provider Item
 * @param <MASTER>
 *            The Format of the referential Item
 */
public class MultiJoinItemReader<MASTER> implements ItemReader<MultiJoinItem<MASTER>>, ItemStream {

	private static final Logger log = LoggerFactory.getLogger(MultiJoinItemReader.class);

	private ItemReader<MASTER> masterItemReader;

	private ItemReader[] joinItemReaders;

	private int joinCount;

	private MASTER currentItemMaster;

	private Object[] currentItemJoins;

	private Comparator<MASTER> masterComparator;
	
	private JoinComparator<MASTER,   Object>[] joinComparators;

	private MultiJoinItemFactory<MASTER> joinFactory = new MultiJoinItemVOFactory<MASTER>();

	public MultiJoinItemReader() {
		super();
	}

	public MultiJoinItemReader(ItemReader<MASTER> masterItemReader,  Comparator<MASTER> masterComparator, List<ItemReader> joinItemReaders,
			List<? extends JoinComparator<MASTER, Object>> joinComparators) {
		super();
		this.masterItemReader = masterItemReader;
		this.masterComparator = masterComparator;
		this.joinCount = joinItemReaders.size();
		this.joinItemReaders = joinItemReaders.toArray(new ItemReader[joinCount]);
		this.joinComparators = joinComparators.toArray(new JoinComparator[joinCount]);
	}

	public void setItemFactory(MultiJoinItemFactory<MASTER> itemFactory) {
		this.joinFactory = itemFactory;
	}


	public void setMasterItemReader(ItemReader<MASTER> itemReaderDestination) {
		this.masterItemReader = itemReaderDestination;
	}

	
	public void setMasterComparator(Comparator<MASTER> masterComparator) {
		this.masterComparator = masterComparator;
	}

	public void setJoinItemReaders(List<ItemReader> itemReaderProviders) {
		this.joinItemReaders = itemReaderProviders.toArray(new ItemReader[itemReaderProviders.size()]);
		this.joinCount = itemReaderProviders.size();
	}


	public void setJoinComparators(List<? extends JoinComparator<MASTER,   Object>> comparator) {
		this.joinComparators = comparator.toArray(new JoinComparator[comparator.size()]);
	}

	
	
	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		// Read First Source Line
		try {
			currentItemJoins = new Object[joinCount];
			for (int i = 0; i < joinCount; i++) {
				ItemReader itemReaderJoin = joinItemReaders[i];
				if (itemReaderJoin instanceof ItemStream) {
					ItemStream itemReaderProviderStrean = (ItemStream) itemReaderJoin;
					itemReaderProviderStrean.open(executionContext);
				}
				currentItemJoins[i] = itemReaderJoin.read();
			}
		} catch (Exception e) {
			throw new ItemStreamException("Error on opening itemReaderProvider : " + e.getMessage(), e);
		}
		// Read First Destination Line
		try {
			if (masterItemReader instanceof ItemStream) {
				ItemStream itemReaderReferentialStrean = (ItemStream) masterItemReader;
				itemReaderReferentialStrean.open(executionContext);
			}

			currentItemMaster = masterItemReader.read();
		} catch (Exception e) {
			throw new ItemStreamException("Error on opening itemReaderDestination : " + e.getMessage(), e);
		}
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		for (int i = 0; i < joinCount; i++) {
			ItemReader itemReaderJoin = joinItemReaders[i];
			if (itemReaderJoin instanceof ItemStream) {
				ItemStream itemReaderProviderStrean = (ItemStream) itemReaderJoin;
				itemReaderProviderStrean.update(executionContext);
			}
		}
		if (masterItemReader instanceof ItemStream) {
			ItemStream itemReaderReferentialStrean = (ItemStream) masterItemReader;
			itemReaderReferentialStrean.update(executionContext);
		}
	}

	@Override
	public void close() throws ItemStreamException {
		for (int i = 0; i < joinCount; i++) {
			ItemReader itemReaderJoin = joinItemReaders[i];
		if (itemReaderJoin instanceof ItemStream) {
			ItemStream itemReaderProviderStrean = (ItemStream) itemReaderJoin;
			itemReaderProviderStrean.close();
		}
		}
		if (masterItemReader instanceof ItemStream) {
			ItemStream itemReaderReferentialStrean = (ItemStream) masterItemReader;
			itemReaderReferentialStrean.close();
		}
		// Release cache dependencies
		currentItemJoins = null;
		currentItemMaster = null;
	}

	@Override
	public MultiJoinItem<MASTER> read() throws Exception, UnexpectedInputException, ParseException,
			NonTransientResourceException {
		// Prepare result
		MultiJoinItem<MASTER> result = null;
		// Manage Differential
		boolean isItemMaster = currentItemMaster != null;
		if ( isItemMaster) {
			// create Master line
			result = joinFactory.createLine(currentItemMaster, joinCount);
			// Add Joins
			for (int i = 0; i < joinCount; i++) { 
				// Compare Line
				int res = compareReferentialMaster(currentItemMaster, currentItemJoins[i], i);
				while  ( res >= 0) {
					if (res==0) {
						result.addJoin(i, currentItemJoins[i]);
					} else {
						log.debug("Ignore Join for no Master Key matching : {}", currentItemJoins[i]);
					}
					incrementItemReaderJoin(i); 
					res = compareReferentialMaster(currentItemMaster, currentItemJoins[i], i);
				}
			}
			// Increments Master
			incrementItemReaderMaster();
 		}

		// Increment for next iteration
		return result;
	}

	private int compareReferentialMaster(MASTER master, Object join, int idx) {
		if (master != null && join != null) {
			return joinComparators[idx].compareMasterJoin(master, join);
		} else if (master == null && join == null) {
			return 0;
		} else if (join == null) {
			return -1;
		} else {
			return 1;
		}

	}

	private void incrementItemReaderJoin(int idx) throws UnexpectedInputException, ParseException,
			NonTransientResourceException, Exception {
		Object previous =  currentItemJoins[idx];
		currentItemJoins[idx] = joinItemReaders[idx].read();
		// Comparator previous < currentItemProvider
		if (currentItemJoins[idx] != null && joinComparators[idx].compare(previous, currentItemJoins[idx]) > 0) {
			throw new UnexpectedInputException("Un ordered ASC itemReaderProvider as expected");
		}
	}

	private void incrementItemReaderMaster() throws UnexpectedInputException, ParseException,
			NonTransientResourceException, Exception {
		MASTER previous = currentItemMaster;
		currentItemMaster = masterItemReader.read();
		// Comparator previous < currentItemReferential
		if (currentItemMaster != null && masterComparator.compare(previous, currentItemMaster) >= 0) {
			throw new UnexpectedInputException("Un ordered ASC itemReaderDestination as expected");
		}
	}

}
