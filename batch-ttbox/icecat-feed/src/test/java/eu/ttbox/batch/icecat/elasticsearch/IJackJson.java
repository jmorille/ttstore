package eu.ttbox.batch.icecat.elasticsearch;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonAutoDetect(getterVisibility = Visibility.NONE)
public interface IJackJson {

	@JsonProperty("prenomRenamed")
	String getFirstname();

	@JsonProperty
	String getLastname();

	@JsonProperty
	IDaniel getDaniel();
}