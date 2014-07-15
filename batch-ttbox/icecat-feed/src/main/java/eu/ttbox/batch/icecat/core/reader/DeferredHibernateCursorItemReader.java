package eu.ttbox.batch.icecat.core.reader;

import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.batch.item.*;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

/**
 * This class defers opening of Hibernate session just before the first items is read. It relays on the fact, that opening of the stream occurs outside the
 * transaction and reading -- inside the transaction.<br>
 * Note: this approach is the only one in case your entities have lazily loading properties (e.g. collections).
 * 
 * @see http ://forum.springsource.org/showthread.php?49544-HibernateItemWriter-lazy -loading-problem/page2
 */
public class DeferredHibernateCursorItemReader<T> extends ItemStreamSupport implements ItemReader<T> {

	private boolean initialized = false;

	private SessionFactory sessionFactory;

	private Session session;

	private String queryString;

	private ScrollableResults cursor;

	private boolean useStatelessSession = false;

	public void setUseStatelessSession(boolean useStatelessSession) {
		this.useStatelessSession = useStatelessSession;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void setQueryString(String queryName) {
		this.queryString = queryName;
	}

	/**
	 * @see org.springframework.batch.item.ItemReader#read()
	 */
	public T read() throws Exception, UnexpectedInputException, NonTransientResourceException {
		if (!initialized) {
			session = SessionFactoryUtils.getSession(sessionFactory, true);
			initialized = true;
			cursor = session.createQuery(queryString).scroll(ScrollMode.FORWARD_ONLY);
		}

		if (cursor.next()) {
			Object[] data = cursor.get();

			@SuppressWarnings("unchecked")
			T item = (T) data[0];
			return item;
		}

		return null;
	}

	/**
	 * @see org.springframework.batch.item.ItemStream#close()
	 */
	@Override
	public void close() throws ItemStreamException {
		SessionFactoryUtils.releaseSession(session, sessionFactory);
	}

}
