package eu.ttbox.batch.icecat.indexor;

import java.io.Serializable;

import org.elasticsearch.common.xcontent.XContentBuilder;

@SuppressWarnings("serial")
public class ElasticItem implements Serializable {

	String index;

	String type;

	String id;

	XContentBuilder content;

	public ElasticItem(String index, String type, String id, XContentBuilder content) {
		super();
		this.index = index;
		this.type = type;
		this.id = id;
		this.content = content;
	}

	public String getIndex() {
		return index;
	}

	public String getType() {
		return type;
	}

	public String getId() {
		return id;
	}

	public XContentBuilder getContent() {
		return content;
	}

}
