package eu.ttbox.batch.icecat.core.writer;

import eu.ttbox.batch.core.reader.differential.DifferentialItem;
import eu.ttbox.batch.icecat.dao.IcecatDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;

@Service("icecatDifferentialItemWriter")
public class DifferentialItemWriter<MASTER, JOIN> implements ItemWriter<DifferentialItem<MASTER, JOIN>> {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("icecatDAO")
	IcecatDAO icecatDAO;

	@Override
	public void write(List<? extends DifferentialItem<MASTER, JOIN>> items) throws Exception {
		int itemSize = items.size();
		if (itemSize < 1) {
			icecatDAO.flushAndClear();
			log.info("-------------------- DifferentialItemWriter end nothing");
			return;
		}
		log.info("-------------------- DifferentialItemWriter begin");

		StopWatch stopWatch = new StopWatch("Material writing");
		// Rooting lines
		stopWatch.start("Rooting lines");
		List<MASTER> toCreates = new ArrayList<MASTER>();
		List<MASTER> toUpdates = new ArrayList<MASTER>();
		List<MASTER> toDeletes = new ArrayList<MASTER>();
		// int maxPartNumber = 0;
		for (DifferentialItem<MASTER, JOIN> item : items) {
			switch (item.getStatus()) {
			case DELETE:
				toDeletes.add(item.getMasterItem());
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

		// log.info("CUD ({}, {}, {}) ", new Object[] { toCreates.size(), toUpdates.size(), toDelete.size() });

		stopWatch.stop();

		// Updates Lines
		if (!toUpdates.isEmpty()) {
			stopWatch.start(String.format("Update %s products", toUpdates.size()));
			icecatDAO.saveObjects(toUpdates);
			stopWatch.stop();
			log.info(stopWatch.prettyPrint());
		}
		// Create Lines
		if (!toCreates.isEmpty()) {
			stopWatch.start(String.format("Create %s products", toCreates.size()));
			for (MASTER toCreate : toCreates) {
				log.debug("Prepare insert for {}", toCreate);
				icecatDAO.insertObject(toCreate);
			}
			stopWatch.stop();
			log.info(stopWatch.prettyPrint());
		}

		// Delete Lines
		if (!toDeletes.isEmpty()) {
			stopWatch.start(String.format("Delete %s products", toDeletes.size()));
			icecatDAO.deleteAll(toDeletes);
			stopWatch.stop();
			log.info(stopWatch.prettyPrint());
		}
		icecatDAO.flushAndClear();
		log.info("-------------------- DifferentialItemWriter end");

	}

}
