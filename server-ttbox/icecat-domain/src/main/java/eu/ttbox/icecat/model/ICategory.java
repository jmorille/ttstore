package eu.ttbox.icecat.model;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonProperty;

import eu.ttbox.icecat.model.referential.IcecatCategory;

@JsonAutoDetect(getterVisibility=Visibility.NONE)
public interface ICategory {

	@JsonProperty
	String getName();

	String getDescription();

	IcecatCategory getParent();

	String getUnspcCategory();

	String getThumbPicture();

	String getLowDefPicture();
}
