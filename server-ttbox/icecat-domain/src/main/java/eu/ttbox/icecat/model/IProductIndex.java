package eu.ttbox.icecat.model;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import eu.ttbox.icecat.model.referential.IcecatBrand;
import eu.ttbox.icecat.model.referential.IcecatCategory;

@JsonSerialize(include=Inclusion.NON_NULL)
@JsonAutoDetect(getterVisibility = Visibility.NONE) 
public interface IProductIndex {

	@JsonProperty
	String getName();

	@JsonProperty
	Date getPathVersionDate();

	@JsonProperty
	String getPath();

	@JsonProperty
	String getHighDefPicture();

	@JsonProperty
	String getEditor();

	@JsonProperty
	boolean isOnMarket();

	@JsonProperty
	String getPartNumber();

	@JsonProperty
	List<String> getAlternativeMFPN();

	@JsonProperty
	List<String> getEans();

	@JsonProperty
	IcecatBrand getBrand();
	
	@JsonProperty
	IcecatCategory getCategory();
	
	

}
