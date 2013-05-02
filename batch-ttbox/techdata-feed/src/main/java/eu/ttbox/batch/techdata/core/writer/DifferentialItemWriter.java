package eu.ttbox.batch.techdata.core.writer;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import eu.ttbox.batch.core.reader.differential.DifferentialItem;
import eu.ttbox.batch.techdata.dao.TTBoxDAO;

@Service("ttboxDifferentialItemWriter")
public class DifferentialItemWriter<MASTER, JOIN> implements ItemWriter<DifferentialItem<MASTER, JOIN>> {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("ttboxDAO")
	TTBoxDAO ttboxDAO;

	@Override
	public void write(List<? extends DifferentialItem<MASTER, JOIN>> items) throws Exception {
		int totalCount = items.size();
		if (totalCount<1) {
			return;
		}
		// Rooting lines
		StopWatch stopWatch = new StopWatch("Material writing");
		stopWatch.start("Rooting lines");
		int idx = 0;
		List<MASTER> toCreates = new ArrayList<MASTER>();
		List<MASTER> toUpdates = new ArrayList<MASTER>();
		List<MASTER> toDelete = new ArrayList<MASTER>();
		// int maxPartNumber = 0;
		for (DifferentialItem<MASTER, JOIN> item : items) {
			switch (item.getStatus()) {
			case DELETE:
				toDelete.add(item.getMasterItem());
				break;
			case UPDATE:
				toUpdates.add(item.getMasterItem());
				break;
			case CREATE:  
				toCreates.add(item.getMasterItem());
				break;
			default:
				throw new RuntimeException("Unmanaged enum status " + item.getStatus());
			}

			// log.info("line {} \t/ {}: status {} for {} ",
			// new Object[] { ++idx, totalCount, item.getStatus(),
			// item.getJoinItem() });
		}
		// System.err.println("****************** " + maxPartNumber );
		log.info("CUD ({}, {}, {}) ", new Object[] { toCreates.size(), toUpdates.size(), toDelete.size() });
		stopWatch.stop();

		// Updates Lines
		if (!toUpdates.isEmpty()) {
			stopWatch.start( String.format("Update %s products", toUpdates.size()));
			ttboxDAO.saveOrUpdateAll(toUpdates);
			stopWatch.stop();
			log.info(stopWatch.prettyPrint());
		}
		// Create Lines
		if (!toCreates.isEmpty()) {
			stopWatch.start( String.format("Create %s products", toCreates.size())); 
			ttboxDAO.saveOrUpdateAll(toCreates);
			stopWatch.stop();
			log.info(stopWatch.prettyPrint());
		}

		// Delete Lines
		if (!toDelete.isEmpty()) {
			stopWatch.start( String.format("Delete %s products", toDelete.size()));  
			 ttboxDAO.deleteAll(toDelete);
			stopWatch.stop();
			log.info(stopWatch.prettyPrint());
		}
		ttboxDAO.flushAndClear();

	}

}
