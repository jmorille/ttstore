package eu.ttbox.batch.icecat.dao.audit;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("auditInterceptor")
public class EntityAuditInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = -6588151337766547612L;

	protected Logger log = LoggerFactory.getLogger(getClass());

	@SuppressWarnings("unchecked")
	private Map<Class, EntityCrudReporting> reports = new HashMap<Class, EntityCrudReporting>();

	@SuppressWarnings("unchecked")
	private EntityCrudReporting getReport(Class entityClass) {
		EntityCrudReporting report = reports.get(entityClass);
		if (report == null) {
			report = new EntityCrudReporting(entityClass);
			reports.put(entityClass, report);
		}
		return report;
	}

	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {

		EntityCrudReporting report = getReport(entity.getClass());
		report.addDelete();
	}

	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {

		EntityCrudReporting report = getReport(entity.getClass());
		report.addUpdate();

		return false;
	}

	public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {

		EntityCrudReporting report = getReport(entity.getClass());
		report.addLoad();

		return false;
	}

	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {

		EntityCrudReporting report = getReport(entity.getClass());
		report.addCreate();

		return false;
	}

	// public void postFlush(Iterator entities) {
	// System.out.println("Creations: " + creates + ", Updates: " + updates);
	// }

	public void afterTransactionCompletion(Transaction tx) {
		if (tx.wasCommitted()) {
			// toLog();
		}
		// reset();
	}

	public void toLog() {
		for (EntityCrudReporting report : reports.values()) {
			log.info("{}", report);
		}
	}

	public void reset() {
		reports.clear();
		// for (EntityCrudReporting report : reports.values()) {
		// report.reset();
		// }
	}
}
