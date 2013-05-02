package eu.ttbox.batch.icecat.elasticsearch;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonAutoDetect(getterVisibility = Visibility.NONE)
public interface IDaniel {

	@JsonProperty
	String getVolume();

	@JsonProperty
	String getAge();

	@JsonProperty
	String getPrix();

}