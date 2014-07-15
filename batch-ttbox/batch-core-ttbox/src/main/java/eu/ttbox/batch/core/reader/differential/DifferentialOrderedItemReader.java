package eu.ttbox.batch.core.reader.differential;


import eu.ttbox.batch.core.reader.differential.impl.DifferentialItemVOFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * 
 * @author Morille Jérôme
 * 
 * @param <JOIN>
 *            The Format of the provider Item
 * @param <MASTER>
 *            The Format of the referential Item
 */
public class DifferentialOrderedItemReader<MASTER, JOIN> implements ItemReader<DifferentialItem<MASTER, JOIN>>, ItemStream, InitializingBean{


	protected Logger log = LoggerFactory.getLogger(getClass());
	
	private ItemReader<MASTER> itemReaderMaster;

	private ItemReader<JOIN> itemReaderJoin;

	private MASTER currentItemMaster;

	private JOIN currentItemJoin;

	private DifferentialComparator<MASTER, JOIN> comparator;

	private DifferentialItemFactory<MASTER, JOIN> itemFactory = new DifferentialItemVOFactory<MASTER, JOIN>();

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(itemReaderMaster, "itemReaderMaster is requiered"  );
		Assert.notNull(itemReaderJoin, "itemReaderJoin is requiered");
		Assert.notNull(comparator, "comparator is requiered");
	}
	
	public DifferentialOrderedItemReader() {
		super();
	}

	public DifferentialOrderedItemReader(ItemReader<MASTER> itemReaderDestination, ItemReader<JOIN> itemReaderProvider,
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
			currentItemJoin = itemReaderJoin.read();
		} catch (Exception e) {
			throw new ItemStreamException("Error on opening itemReaderJoin : " + e.getMessage(), e);
		}
		// Read First Destination Line
		try {
			if (itemReaderMaster instanceof ItemStream) {
				ItemStream itemReaderReferentialStrean = (ItemStream) itemReaderMaster;
				itemReaderReferentialStrean.open(executionContext);
			}
			currentItemMaster = itemReaderMaster.read();
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
		currentItemJoin = null;
		currentItemMaster = null;
	}

	@Override
	public DifferentialItem<MASTER, JOIN> read() throws Exception, UnexpectedInputException, ParseException,
			NonTransientResourceException {
		log.debug("Begin ItemReader Differential read");
		// Prepare result
		DifferentialItem<MASTER, JOIN> result = null;
		// Manage Differential
		boolean isItemJoin = currentItemJoin != null;
		boolean isItemMaster = currentItemMaster != null;
		if (isItemJoin || isItemMaster) {
			// Compare Line
			int res = compareMasterJoin(currentItemMaster, currentItemJoin);
			// Do Increment
			if (res > 0) {
				// To create mode
				result = itemFactory.createLine(DifferentialItem.CUDStatus.CREATE, null, currentItemJoin);
				// lastSrc = null;
				incrementItemReaderJoin();
			} else if (res < 0) {
				// To delete mode
				result = itemFactory.createLine(DifferentialItem.CUDStatus.DELETE, currentItemMaster, null);
				// lastDest = null;
				incrementItemReaderMaster();
			} else {
				// To Update mode
				result = itemFactory.createLine(DifferentialItem.CUDStatus.UPDATE, currentItemMaster, currentItemJoin);
				// Increment for next iteration
				incrementItemReaderJoin();
				incrementItemReaderMaster();
			}
		}
		// Return result
		log.debug("End   ItemReader Differential read");
		return result;
	}


	private void incrementItemReaderJoin() throws UnexpectedInputException, ParseException,
			NonTransientResourceException, Exception {
		JOIN previous = currentItemJoin;
		currentItemJoin = itemReaderJoin.read();
		// Comparator previous < currentItemProvider
		if (currentItemJoin != null && compareJoins(previous, currentItemJoin) > 0) {
			throw new UnexpectedInputException("Un ordered ASC itemReaderProvider as expected");
		}
	}

	private void incrementItemReaderMaster() throws UnexpectedInputException, ParseException,
			NonTransientResourceException, Exception {
		MASTER previous = currentItemMaster;
		currentItemMaster = itemReaderMaster.read();
		// Comparator previous < currentItemReferential
		if (currentItemMaster != null && compareMasters(previous, currentItemMaster) > 0) {
			throw new UnexpectedInputException("Un ordered ASC itemReaderDestination as expected");
		}
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
