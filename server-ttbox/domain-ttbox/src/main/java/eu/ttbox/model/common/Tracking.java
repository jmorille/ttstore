package eu.ttbox.model.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Embeddable
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "Tracking" )
public class Tracking implements Serializable {

	private static final long serialVersionUID = 7155986108775894636L;
	
	@XmlElement
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATION_DATE")
	private Date creationDate;

	/** Adresse ip au moment de la creation du compte */
	@XmlElement
	@Column(name="IP_CREATE", length=20, nullable=true)
	private String createIp;
	
	/** Chaine x-forwarded-for du header HTTP au moment de la creation du compte */
	@XmlElement
	@Column(name="IP_X_FWD_FOR", length=2000, nullable=true)
	private String createXForwardedFor;

	
	@XmlElement
	@Column(name="REFERER", length=2000, nullable=true)
	private String referer;


	public Date getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}


	public String getCreateIp() {
		return createIp;
	}


	public void setCreateIp(String createIp) {
		this.createIp = createIp;
	}


	public String getCreateXForwardedFor() {
		return createXForwardedFor;
	}


	public void setCreateXForwardedFor(String createXForwardedFor) {
		this.createXForwardedFor = createXForwardedFor;
	}


	public String getReferer() {
		return referer;
	}


	public void setReferer(String referer) {
		this.referer = referer;
	}
	
	
	
	
}
