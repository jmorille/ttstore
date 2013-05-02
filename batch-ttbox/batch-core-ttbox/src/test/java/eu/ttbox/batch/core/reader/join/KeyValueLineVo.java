package eu.ttbox.batch.core.reader.join;

import java.io.Serializable;

public class KeyValueLineVo implements Serializable, Comparable<KeyValueLineVo> {

	private String key;
	
	private String value;

	 
	public KeyValueLineVo(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int compareTo(KeyValueLineVo o) {
		int result =  getKey().compareTo(o.getKey());
		if (result==0) {
			result =  getValue().compareTo(o.getValue());
		}
		return result;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		KeyValueLineVo other = (KeyValueLineVo) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[key=" + key + ", value=" + value + "]";
	}
	
	
	
}
