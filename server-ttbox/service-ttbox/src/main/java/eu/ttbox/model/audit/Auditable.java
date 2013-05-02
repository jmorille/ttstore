package eu.ttbox.model.audit;


public interface Auditable {

	AuditTrail getAuditTrail();

	void setAuditTrail(AuditTrail auditTrail);
}