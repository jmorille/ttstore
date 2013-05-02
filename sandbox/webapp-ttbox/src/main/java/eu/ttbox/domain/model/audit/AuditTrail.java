package eu.ttbox.domain.model.audit;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;

import org.apache.commons.lang.builder.ToStringBuilder;

@Embeddable
public class AuditTrail {

	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name = "VERSION_CREATED_DATE", nullable = false)
	Date createDate;

	@Column(name = "VERSION_CREATED_AGENTID", nullable = false) 
	String createAgentId;

	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name = "VERSION_DATE", nullable = false)
	Date lastModifiedDate;

	@Column(name = "VERSION_AGENTID", nullable = false) 
	String lastModifiedAgentId;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateAgentId() {
		return createAgentId;
	}

	public void setCreateAgentId(String createAgentId) {
		this.createAgentId = createAgentId;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getLastModifiedAgentId() {
		return lastModifiedAgentId;
	}

	public void setLastModifiedAgentId(String lastModifiedAgentId) {
		this.lastModifiedAgentId = lastModifiedAgentId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createAgentId == null) ? 0 : createAgentId.hashCode());
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime
				* result
				+ ((lastModifiedAgentId == null) ? 0 : lastModifiedAgentId
						.hashCode());
		result = prime
				* result
				+ ((lastModifiedDate == null) ? 0 : lastModifiedDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuditTrail other = (AuditTrail) obj;
		if (createAgentId == null) {
			if (other.createAgentId != null)
				return false;
		} else if (!createAgentId.equals(other.createAgentId))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (lastModifiedAgentId == null) {
			if (other.lastModifiedAgentId != null)
				return false;
		} else if (!lastModifiedAgentId.equals(other.lastModifiedAgentId))
			return false;
		if (lastModifiedDate == null) {
			if (other.lastModifiedDate != null)
				return false;
		} else if (!lastModifiedDate.equals(other.lastModifiedDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this);
		sb.append("lastModifiedDate", lastModifiedDate);
		sb.append("lastModifiedAgentId", lastModifiedAgentId);
		sb.append("createDate", createDate);
		sb.append("createAgentId", createAgentId);
		return sb.toString();
	}

	
	
}
