package eu.ttbox.domain.model.audit;


public interface Auditable {

	AuditTrail getAuditTrail();

	void setAuditTrail(AuditTrail auditTrail);
}