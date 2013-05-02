package eu.ttbox.batch.core.reader.differential;

import java.io.Serializable;

public class CRUDStatusReport implements Serializable {

	private static final long serialVersionUID = 1L;

	String id;

	int retrieveCount = 0;
	int createCount = 0;
	int updateCount = 0;
	int deleteCount = 0;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRetrieveCount() {
		return retrieveCount;
	}

	public void addRetrieve() {
		this.retrieveCount++;
	}

	public int getCreateCount() {
		return createCount;
	}

	public void addCreateCount() {
		this.createCount++;
	}

	public int getUpdateCount() {
		return updateCount;
	}

	public void addUpdateCount() {
		this.updateCount++;
	}

	public int getDeleteCount() {
		return deleteCount;
	}

	public void addDeleteCount() {
		this.deleteCount++;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		CRUDStatusReport other = (CRUDStatusReport) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

 

	
	
}
