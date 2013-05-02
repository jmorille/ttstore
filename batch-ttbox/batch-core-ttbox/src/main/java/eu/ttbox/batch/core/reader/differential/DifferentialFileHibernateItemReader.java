package eu.ttbox.batch.core.reader.differential;

import java.util.Map;

import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.database.HibernateCursorItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

public class DifferentialFileHibernateItemReader<MASTER, JOIN> extends DifferentialItemReader<MASTER, JOIN> implements InitializingBean {


	private HibernateCursorItemReader<MASTER> itemReaderResourceMaster;

	private ResourceAwareItemReaderItemStream<JOIN> itemReaderResourceJoin;
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		super.afterPropertiesSet();
		Assert.notNull(itemReaderResourceMaster, "itemReaderResourceMaster is requiered"  );
		Assert.notNull(itemReaderResourceJoin, "itemReaderResourceJoin is requiered");
	}
	
	public DifferentialFileHibernateItemReader() {
		super(); 
	}

	public DifferentialFileHibernateItemReader(HibernateCursorItemReader<MASTER> itemReaderDestination, ResourceAwareItemReaderItemStream<JOIN> itemReaderProvider,
			DifferentialComparator<MASTER, JOIN> comparator) {
		super(itemReaderDestination, itemReaderProvider, comparator); 
		this.itemReaderResourceJoin = itemReaderProvider;
		this.itemReaderResourceMaster = itemReaderDestination;
	}

	public void setItemReaderResourceJoin(ResourceAwareItemReaderItemStream<JOIN> itemReaderProvider) {
		super.setItemReaderJoin(itemReaderProvider);
		this.itemReaderResourceJoin = itemReaderProvider;
	}

	public void setItemReaderResourceMaster(HibernateCursorItemReader<MASTER> itemReaderDestination) {
		super.setItemReaderMaster(itemReaderDestination);
		this.itemReaderResourceMaster = itemReaderDestination;
	}
	
	public void setJoinResource(Resource resource) {
		itemReaderResourceJoin.setResource(resource);
	}

	public void setMasterResource(Map<String, Object> parameterValues) {
		itemReaderResourceMaster.setParameterValues(parameterValues);
	}
	
	@Override
	public void close() throws ItemStreamException {
		super.close();
//		this.itemReaderResourceMaster = null;
//		this.itemReaderResourceJoin = null; 
	}

	
}
