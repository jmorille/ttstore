package eu.ttbox.model.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@Embeddable
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "Address" )
public class AddressPMO implements Serializable {

	private static final long serialVersionUID = 7155986108775894636L;
	
	@XmlElement
	@Column(name = "ADDR_STREET_NAME", length = 50)
	private String streetName;

	@XmlElement
	@Column(name = "ADDR_POSTAL_CODE", length = 50)
	private String postalCode;

	@XmlElement
	@Column(name = "ADDR_CITY_NAME", length = 50)
	private String cityName;

	@XmlElement
	@Column(name = "ADDR_COUNTRY_NAME", length = 50)
	private String countryName;

	@XmlElement
	@Column(name = "ADDR_CMP_01", length = 50)
	private String addressComplementary01;
	
	@XmlTransient
	@Column(name = "CITY_ID", nullable = true, length = 32)
	private String cityId;
	
	@XmlTransient
	@Column(name = "SUB_REGION_ID", nullable = true, length = 32)
	private String sebRegionId;
	
	@XmlTransient
	@Column(name = "REGION_ID", nullable = true, length = 32)
	private String regionId;

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getAddressComplementary01() {
		return addressComplementary01;
	}

	public void setAddressComplementary01(String addressComplementary01) {
		this.addressComplementary01 = addressComplementary01;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getSebRegionId() {
		return sebRegionId;
	}

	public void setSebRegionId(String sebRegionId) {
		this.sebRegionId = sebRegionId;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	
	
	
	
	
}
