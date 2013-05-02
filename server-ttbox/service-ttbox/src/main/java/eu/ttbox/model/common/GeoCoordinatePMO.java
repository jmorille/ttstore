package eu.ttbox.model.common;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Coordinate persistant model object.
 * 
 * @author Morille Jerome
 * 
 */
@Embeddable
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "GeoCoordinate" )
public class GeoCoordinatePMO implements Serializable /* , GeoCoordinate, */   {
    
    private static final long serialVersionUID = 6942810589426014805L;

    @Min(value=-90)
    @Max(value=90)
    @Column(name = "WGS84_LATITUDE", precision=30, scale=25 )
    @XmlElement
    private BigDecimal latitude;
    
    @Min(value=-180)
    @Max(value=180)
    @Column(name = "WGS84_LONGITUDE", precision=30, scale=25 )
    @XmlElement
    private BigDecimal longitude;

    @Column(name = "WGS84_ELEVATION", precision=30, scale=25 )
    @XmlElement
    private BigDecimal elevation;
    
    /**	
         * Default Constructor.
         */
    public GeoCoordinatePMO() {
	super();
    }

//    /**
//     * Business constructor.
//     * @param geoCoordinate A GeoCoordinate implementation.
//     */
//    public GeoCoordinatePMO(GeoCoordinate geoCoordinate) {
//	super();
//	this.latitude = geoCoordinate.getLatitude();
//	this.longitude = geoCoordinate.getLongitude();
//	this.elevation = geoCoordinate.getElevation();
//    }
    
    /**
     * Business constructor.
     * @param latitude The Wgs84 Latitude
     * @param longitude The Wgs84 Longitude
     * @param elevation The Wgs84 Elevation
     */
    public GeoCoordinatePMO(double latitude, double longitude, double elevation) {
	super();
	this.latitude = new BigDecimal( latitude);
	this.longitude = new BigDecimal(longitude );
	this.elevation = new BigDecimal(elevation );
    }

    /**
     * Business Constructor
     * 
     * @param latLongElevation
     *                The concatenation with coma separator of
     *                (Latitude,Loingitude,Elevation)
     * 
     */
    public GeoCoordinatePMO(String latLongElevation) {
	super();
	String[] xy = latLongElevation.split(","); 
	this.latitude = new BigDecimal(xy[0]);
	this.longitude = new BigDecimal(xy[1]);
	if (xy.length>2) {
	    this.elevation = new BigDecimal(xy[2]);
	} else {
	    this.elevation = BigDecimal.ZERO;
	}
    }
    
    /**
     * Business constructor.
     * @param latitude The Wgs84 Latitude
     * @param longitude The Wgs84 Longitude
     * @param elevation The Wgs84 Elevation
     */
    public GeoCoordinatePMO(BigDecimal latitude, BigDecimal longitude, BigDecimal elevation) {
	super();
	this.latitude = latitude;
	this.longitude = longitude;
	this.elevation = elevation;
    }
     
    
    /**
     * @return the elevation
     */
    public BigDecimal getElevation() {
        return this.elevation;
    }

    /**
     * @param elevation the elevation to set
     */
    public void setElevation(BigDecimal elevation) {
        this.elevation = elevation;
    }

    /**
     * @return the latitude
     */
    public BigDecimal getLatitude() {
        return this.latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public BigDecimal getLongitude() {
        return this.longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }


    @Override
    public int hashCode() {
	final int PRIME = 31;
	int result = 1;
	result = PRIME * result + ((this.elevation == null) ? 0 : this.elevation.hashCode());
	result = PRIME * result + ((this.latitude == null) ? 0 : this.latitude.hashCode());
	result = PRIME * result + ((this.longitude == null) ? 0 : this.longitude.hashCode());
	return result;
    }


    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final GeoCoordinatePMO other = (GeoCoordinatePMO) obj;
	if (this.elevation == null) {
	    if (other.elevation != null)
		return false;
	} else if (!this.elevation.equals(other.elevation))
	    return false;
	if (this.latitude == null) {
	    if (other.latitude != null)
		return false;
	} else if (!this.latitude.equals(other.latitude))
	    return false;
	if (this.longitude == null) {
	    if (other.longitude != null)
		return false;
	} else if (!this.longitude.equals(other.longitude))
	    return false;
	return true;
    }


    public String toString() {
    	StringBuffer buffer = new StringBuffer();
    	buffer.append("[Coordinate:"); 
    	buffer.append(" latitude: ");
    	buffer.append(latitude);
    	buffer.append(" longitude: ");
    	buffer.append(longitude);
    	buffer.append(" elevation: ");
    	buffer.append(elevation);
    	buffer.append("]");
    	return buffer.toString();
        }
 

}
