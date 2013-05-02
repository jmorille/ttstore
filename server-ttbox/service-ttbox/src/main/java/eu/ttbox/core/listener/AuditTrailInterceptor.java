package eu.ttbox.core.listener;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import eu.ttbox.model.audit.AuditTrail;
import eu.ttbox.model.audit.Auditable;

public class AuditTrailInterceptor {

	private static final long serialVersionUID = 8100116918901109993L;

	private String getAgentIdFromContext() {
		return "undefined";
	}

	private AuditTrail getAuditable(Auditable entity) {
		Auditable entityAuditable = (Auditable) entity;
		AuditTrail auditTrail = entityAuditable.getAuditTrail();
		if (auditTrail == null) {
			auditTrail = new AuditTrail();
			entityAuditable.setAuditTrail(auditTrail);
		}
		return auditTrail;
	}

	@PrePersist
	public void onPrePersist(Object entity) {
		if (entity instanceof Auditable) {
 			AuditTrail auditTrail = getAuditable((Auditable) entity);
			Date now = new Date();
			String agentId = getAgentIdFromContext();
			auditTrail.setCreateDate(now);
			auditTrail.setCreateAgentId(agentId);
			auditTrail.setLastModifiedDate(now);
			auditTrail.setLastModifiedAgentId(agentId);
		}
	}

	@PreUpdate
	public void onPreUpdate(Object entity) {
		if (entity instanceof Auditable) {
 			AuditTrail auditTrail = getAuditable((Auditable) entity);
			Date now = new Date();
			String agentId = getAgentIdFromContext();
 			auditTrail.setLastModifiedDate(now);
			auditTrail.setLastModifiedAgentId(agentId);
		}

	}

}
