package eu.ttbox.batch.icecat.dao.audit;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("auditInterceptor2")
public class AuditInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = -6588151337766547612L;

	protected Logger log = LoggerFactory.getLogger(getClass());

	private int deletes;
	private int updates;
	private int creates;
	private int loads;

	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		deletes++;
	}

	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {

		updates++;
		return false;
	}

	public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		loads++;
		return false;
	}

	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {

		creates++;
		return false;
	}

	// public void postFlush(Iterator entities) {
	// System.out.println("Creations: " + creates + ", Updates: " + updates);
	// }

	public void afterTransactionCompletion(Transaction tx) {
		if (tx.wasCommitted()) {
			toLog();
		}
		reset();
	}

	public void toLog() {
		if (log.isDebugEnabled()) {
			log.debug("CUDR = ({}, {}, {}, {})", new Object[] { creates, updates, deletes, loads });
		}
	}

	public void reset() {
		updates = 0;
		creates = 0;
		deletes = 0;
		loads = 0;
	}

}
