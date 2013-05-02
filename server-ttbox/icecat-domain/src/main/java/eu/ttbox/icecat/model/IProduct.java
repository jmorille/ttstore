package eu.ttbox.icecat.model;

import org.codehaus.jackson.annotate.JsonProperty;

import eu.ttbox.icecat.model.product.IcecatProductLine;
import eu.ttbox.icecat.model.referential.IcecatBrand;

//@JsonAutoDetect(getterVisibility = Visibility.NONE)
public interface IProduct {

//	@JsonProperty
	String getName();

//	@JsonProperty
	IcecatProductLine getLine();

	@JsonProperty
	String getDescription();

//	@JsonProperty
	String getShortDescription();

	@JsonProperty
	String getMarketingDescription();

//	@JsonProperty
	IcecatBrand getBrand();

	String getLowDefPicture();

	String getHighDefPicture();

	String getThumbPicture();

	String getImageURI();

}
