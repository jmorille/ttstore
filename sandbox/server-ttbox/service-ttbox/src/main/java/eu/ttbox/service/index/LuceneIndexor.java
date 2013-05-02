package eu.ttbox.service.index;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.granite.messaging.amf.io.util.externalizer.annotation.IgnoredProperty;
 //import org.hibernate.search.jpa.FullTextEntityManager;
//import org.hibernate.search.jpa.Search;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("luceneIndexor")
@Transactional
@ManagedResource(objectName = "ttbox:index=LuceneIndexor", description = "Lucene Indexor manager")
public class LuceneIndexor {

	@IgnoredProperty
	Logger log = LoggerFactory.getLogger(getClass());

	@IgnoredProperty
	@PersistenceContext
	EntityManager entityManager;

	@Transactional(propagation=Propagation.REQUIRED)
	@ManagedOperation(description = "Create All Lucene Search Indexes")
	public void createAllIndexes() throws InterruptedException {
	//	FullTextEntityManager fullTextEntityManager = Search
	//			.getFullTextEntityManager(entityManager);
	//	fullTextEntityManager.createIndexer().startAndWait();
	}
	
	
}
