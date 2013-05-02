package eu.ttbox.icecat.model;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonProperty;


@JsonAutoDetect(getterVisibility=Visibility.NONE)
public interface IBrand {

	@JsonProperty
	String getName();

	String getLowPicture();

	String getThumbPicture();
	
	
}
