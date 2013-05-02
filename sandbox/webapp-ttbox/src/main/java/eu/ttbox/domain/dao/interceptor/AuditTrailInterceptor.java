package eu.ttbox.domain.dao.interceptor;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.stereotype.Repository;

import eu.ttbox.domain.model.audit.AuditTrail;
import eu.ttbox.domain.model.audit.Auditable;

@Repository("auditTrailInterceptor")
public class AuditTrailInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 8100116918901109993L;

	private String getAgentIdFromContext() {
		return "undefined";
	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		if (entity instanceof Auditable) {
			Auditable entityAuditable = (Auditable) entity;
			AuditTrail auditTrail = entityAuditable.getAuditTrail();
			if (auditTrail == null) {
				auditTrail = new AuditTrail();
				entityAuditable.setAuditTrail(auditTrail);
			}
			auditTrail.setCreateDate(new Date());
			auditTrail.setCreateAgentId(getAgentIdFromContext());
			return true;
		}
		return false;
	}

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		if (entity instanceof Auditable) {
			Auditable entityAuditable = (Auditable) entity;
			AuditTrail auditTrail = entityAuditable.getAuditTrail();
			if (auditTrail == null) {
				auditTrail = new AuditTrail();
				entityAuditable.setAuditTrail(auditTrail);
			}
			auditTrail.setLastModifiedDate(new Date());
			auditTrail.setLastModifiedAgentId(getAgentIdFromContext());
			return true;
		}
		return false;
	}

	// private AuditTrail getAuditTrail(Object[] state) {
	// AuditTrail auditTrail = null;
	// for (Object o : state) {
	// if (o instanceof AuditTrail)
	// auditTrail = (AuditTrail) o;
	// }
	// return auditTrail;
	// }
}
