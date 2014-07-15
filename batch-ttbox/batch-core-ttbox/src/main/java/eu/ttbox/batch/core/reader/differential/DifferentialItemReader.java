package eu.ttbox.batch.core.reader.differential;

import eu.ttbox.batch.core.reader.differential.impl.DifferentialItemVOFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Morille Jérôme
 * 
 * @param <MASTER>
 *            The Format of the referential Item
 * @param <JOIN>
 *            The Format of the provider Item
 */
public class DifferentialItemReader<MASTER, JOIN> implements ItemReader<DifferentialItem<MASTER, JOIN>>, ItemStream , InitializingBean {

	protected Logger log = LoggerFactory.getLogger(getClass());

	private ItemReader<MASTER> itemReaderMaster;

	private ItemReader<JOIN> itemReaderJoin;

	private Deque<JOIN> currentItemJoins = new LinkedList<JOIN>();

	private JOIN previousReadItemJoin = null;
	private MASTER previousReadItemMaster = null;

	private DifferentialComparator<MASTER, JOIN> comparator;

	private DifferentialItemFactory<MASTER, JOIN> itemFactory = new DifferentialItemVOFactory<MASTER, JOIN>();

	
	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(itemReaderMaster, "itemReaderMaster is requiered"  );
		Assert.notNull(itemReaderJoin, "itemReaderJoin is requiered");
		Assert.notNull(comparator, "comparator is requiered");
	}
	
	public DifferentialItemReader() {
		super();
	}

	public DifferentialItemReader(ItemReader<MASTER> itemReaderDestination, ItemReader<JOIN> itemReaderProvider,
			DifferentialComparator<MASTER, JOIN> comparator) {
		super();
		this.itemReaderJoin = itemReaderProvider;
		this.itemReaderMaster = itemReaderDestination;
		this.comparator = comparator;
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

	public void setItemFactory(DifferentialItemFactory<MASTER, JOIN> itemFactory) {
		this.itemFactory = itemFactory;
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		// Read First Source Line
		try {
			if (itemReaderJoin instanceof ItemStream) {
				ItemStream itemReaderProviderStrean = (ItemStream) itemReaderJoin;
				itemReaderProviderStrean.open(executionContext);
			}
		} catch (Exception e) {
			throw new ItemStreamException("Error on opening itemReaderJoin : " + e.getMessage(), e);
		}
		// Read First Destination Line
		try {
			if (itemReaderMaster instanceof ItemStream) {
				ItemStream itemReaderReferentialStrean = (ItemStream) itemReaderMaster;
				itemReaderReferentialStrean.open(executionContext);
			}

		} catch (Exception e) {
			throw new ItemStreamException("Error on opening itemReaderMaster : " + e.getMessage(), e);
		}
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
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
		currentItemJoins.clear();
//		= null;
	}

	private void addToNotYetManagedItemJoins(JOIN currentItemJoin) {
		if (currentItemJoin != null) {
			this.currentItemJoins.add(currentItemJoin);
			log.debug("Add currentItemJoin to list with {} entities NotYetManagedItemJoins", currentItemJoins.size());
		}
	}

	@Override
	public DifferentialItem<MASTER, JOIN> read() throws Exception, UnexpectedInputException, ParseException,
			NonTransientResourceException {
		// Prepare result
		DifferentialItem<MASTER, JOIN> result = null;
		// Test previous
		// Increments
		MASTER currentItemMaster = incrementItemReaderMaster();
		JOIN currentItemJoin;
		if (!this.currentItemJoins.isEmpty()) {
			currentItemJoin = this.currentItemJoins.pollFirst();
		} else {
			currentItemJoin = incrementItemReaderJoin();
		}

		// Manage Differential
		boolean isItemJoin = currentItemJoin != null;
		boolean isItemMaster = currentItemMaster != null;
		if (isItemJoin || isItemMaster) {
			// Compare Line
			int res = compareMasterJoin(currentItemMaster, currentItemJoin);
			if (isItemMaster) {
				while (res > 0) {
					// Store the precious current join item for next turn
					addToNotYetManagedItemJoins(currentItemJoin);
					// prepare Next Step
					currentItemJoin = incrementItemReaderJoin();
					res = compareMasterJoin(currentItemMaster, currentItemJoin);
				}
			}
			// Do Increment
			if (res == 0) {
				result = itemFactory.createLine(DifferentialItem.CUDStatus.UPDATE, currentItemMaster, currentItemJoin);
			} else if (res < 0) {
				result = itemFactory.createLine(DifferentialItem.CUDStatus.DELETE, currentItemMaster, null);
				addToNotYetManagedItemJoins(currentItemJoin);
			} else {
				// To create mode
				result = itemFactory.createLine(DifferentialItem.CUDStatus.CREATE, null, currentItemJoin);
			}
		}
		return result;
	}

	private int compareMasterJoin(MASTER master, JOIN join) {
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

	private JOIN incrementItemReaderJoin() throws UnexpectedInputException, ParseException,
			NonTransientResourceException, Exception {
		JOIN previous = previousReadItemJoin;
		JOIN currentItemJoin = itemReaderJoin.read();
		// Prepare Next Iteration
		this.previousReadItemJoin = currentItemJoin;
		// Comparator previous < currentItemProvider
		if (previous != null && currentItemJoin != null && compareJoins(previous, currentItemJoin) > 0) {
			throw new UnexpectedInputException("Un ordered ASC Join itemReader, as expected(" + previous + " !< " + currentItemJoin + ")");
		}
		return currentItemJoin;
	}

	private MASTER incrementItemReaderMaster() throws UnexpectedInputException, ParseException,
			NonTransientResourceException, Exception {
		MASTER previous = this.previousReadItemMaster;
		MASTER currentItemMaster = itemReaderMaster.read();
		// Prepare Next Iteration
		this.previousReadItemMaster = currentItemMaster;
		// Comparator previous < currentItemReferential
		if (previous != null && currentItemMaster != null && compareMasters(previous, currentItemMaster) > 0) {
			throw new UnexpectedInputException("Un ordered ASC Master itemReader, as expected (" + previous + " !< " + currentItemMaster + ")");
		}
		return currentItemMaster;
	}

	private int compareJoins(JOIN join1, JOIN join2) {
		if (join1 != null && join2 != null) {
			return comparator.compareJoins(join1, join2);
		} else if (join1 == null && join2 == null) {
			return 0;
		} else if (join2 == null) {
			return -1;
		} else {
			return 1;
		}
	}

	private int compareMasters(MASTER master1, MASTER master2) {
		if (master1 != null && master2 != null) {
			return comparator.compareMasters(master1, master2);
		} else if (master1 == null && master2 == null) {
			return 0;
		} else if (master2 == null) {
			return -1;
		} else {
			return 1;
		}
	}

}
