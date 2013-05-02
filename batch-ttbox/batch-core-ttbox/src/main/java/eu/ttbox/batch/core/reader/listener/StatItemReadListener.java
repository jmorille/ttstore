package eu.ttbox.batch.core.reader.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.annotation.AfterRead;
import org.springframework.batch.core.annotation.BeforeRead;

public class StatItemReadListener<T> {

	private static Logger log = LoggerFactory.getLogger(StatItemReadListener.class);

	int readCount = 0;

	int logInfoPeriod = 1000;

	public void setLogInfoPeriod(int logInfoPeriod) {
		this.logInfoPeriod = logInfoPeriod;
	}

	@BeforeRead
	public void beforeRead() {
		
	}
	
	@AfterRead
	public void afterRead(T item) {
		readCount++;
//		log.info("Read Item {} : {}",readCount, item );	
		if (readCount % logInfoPeriod == 0) {
//			log.info("Read Item {} of class {} ", readCount, item.getClass().getName());
			log.info("Read Item {}", readCount );
		}

	}

}
