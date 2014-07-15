package eu.ttbox.batch.techdata.core.converter;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MappingCount implements Serializable, Comparable<MappingCount> {

	private static final long serialVersionUID = 1L;

	private int count = 0;

	private List<MappingCountAttribute> attributes;

	public MappingCount() {
		super();
	}

	public MappingCount(int count) {
		super();
		this.count = count;
	}

	public int addCount() {
		return count++;
	}

	public int getCount() {
		return count;
	}

	@Override
	public String toString() {
		ToStringHelper sb = Objects.toStringHelper(this).add("count", count);
		if (attributes != null) {
			sb.add("attributes", attributes);
		}
		return sb.toString();
	}

	public List<MappingCountAttribute> getAttributes() {
		return attributes;
	}

	public MappingCount addAttribute(String key, String value) {
		MappingCountAttribute attribute = new MappingCountAttribute(key, value);
		return addAttribute(attribute);
	}

	public MappingCount addAttribute(MappingCountAttribute attribute) {
		if (this.attributes == null) {
			this.attributes = new ArrayList<MappingCountAttribute>();
		}
		if (!this.attributes.contains(attribute)) {
			this.attributes.add(attribute);
		}
		return this;
	}

	@Override
	public int compareTo(MappingCount other) {
		int thisVal = this.count;
		int anotherVal = other.count;
		return (thisVal<anotherVal ? -1 : (thisVal==anotherVal ? 0 : 1));
	}

}
