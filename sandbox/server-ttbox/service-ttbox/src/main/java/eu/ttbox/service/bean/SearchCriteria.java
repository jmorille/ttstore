package eu.ttbox.service.bean;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.granite.messaging.amf.io.util.externalizer.DefaultExternalizer;
import org.granite.messaging.amf.io.util.externalizer.annotation.ExternalizedBean;

@ExternalizedBean(type = DefaultExternalizer.class)
public class SearchCriteria implements Serializable {

	private static final long serialVersionUID = 1L;

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public boolean isCriteriaSet(){
		return StringUtils.isNotBlank(this.value);
	}

	@Override
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this);
		sb.append("value", value);
		return sb.toString();
	}

}
