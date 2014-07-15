package eu.ttbox.batch.core.elasticsearch.writer;

import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ElasticPersonItem  implements Serializable {

	
	String index;
	
	String type;
	
	String id;

	XContentBuilder content;

	public ElasticPersonItem(String index, String type, String id,
			XContentBuilder content) {
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
