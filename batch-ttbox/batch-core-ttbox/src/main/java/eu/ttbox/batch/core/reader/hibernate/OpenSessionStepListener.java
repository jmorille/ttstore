package eu.ttbox.batch.core.reader.hibernate;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.orm.hibernate3.HibernateAccessor;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class OpenSessionStepListener extends HibernateAccessor  implements StepExecutionListener {

	private static final Logger LOG = LoggerFactory.getLogger(OpenSessionStepListener.class);
	
	
	private boolean existingTransaction = false;
	
	@Override
	public void beforeStep(StepExecution stepExecution) {
		LOG.warn("************************** Open Sessions ********************" );
		LOG.warn("************************** Open Sessions ********************" );
		LOG.warn("************************** Open Sessions ********************" );
		LOG.warn("************************** Open Sessions ********************" );
		LOG.warn("************************** Open Sessions ********************" );
		Session session = getSession();
		SessionHolder sessionHolder =
				(SessionHolder) TransactionSynchronizationManager.getResource(getSessionFactory());

		existingTransaction = (sessionHolder != null && sessionHolder.containsSession(session));
		if (existingTransaction) {
			logger.debug("Found thread-bound Session for HibernateInterceptor");
		}
		else {
			if (sessionHolder != null) {
				sessionHolder.addSession(session);
			}
			else {
				TransactionSynchronizationManager.bindResource(getSessionFactory(), new SessionHolder(session));
			}
		}
		// Prepare Invoke 
		 FlushMode previousFlushMode = applyFlushMode(session, existingTransaction);
		enableFilters(session);
		String sessionString = org.springframework.orm.hibernate3.SessionFactoryUtils.toString(session);
		LOG.warn("************************** Open Sessions {} ********************", sessionString );
		LOG.warn("************************** Open Sessions {} ********************", sessionString );
		LOG.warn("************************** Open Sessions {} ********************", sessionString );
		LOG.warn("************************** Open Sessions {} ********************", sessionString );

	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		Session session = getSession();
		String sessionString = SessionFactoryUtils.toString(session);
		LOG.error("************************** Close Sessions {} ********************", sessionString );
		LOG.error("************************** Close Sessions {} ********************", sessionString );
		LOG.error("************************** Close Sessions {} ********************", sessionString );
		LOG.error("************************** Close Sessions {} ********************", sessionString );
		flushIfNecessary(session, existingTransaction);
		return null;
	}

	/**
	 * @see SessionFactoryUtils#getSession
	 */
	protected Session getSession() {
		return SessionFactoryUtils.getSession(
				getSessionFactory(), getEntityInterceptor(), getJdbcExceptionTranslator());
	}
	 
}
