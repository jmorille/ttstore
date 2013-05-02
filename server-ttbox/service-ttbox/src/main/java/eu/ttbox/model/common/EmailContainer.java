package eu.ttbox.model.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Email;

@Embeddable
public class EmailContainer implements Serializable {

	private static final long serialVersionUID = 7155986108775894636L;
	
	@Email
	@Column(name = "EMAIL", length = 200, nullable = true)
	private String email;
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return getEmail();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailContainer other = (EmailContainer) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	
	/**
	 * @return Returns only the name extracted part of the email address.
	 */
	public String getEmailName(String email) {
		if (StringUtils.isBlank(email)) {
			return null;
		}
		int indexPos = email.lastIndexOf('@');
		if (indexPos > -1) {
			String name = email.substring(0, indexPos);
			return name;
		}
		return null;
	}

	/**
	 * @return Returns only the domain extracted part of the email address.
	 */
	public String getEmailDomain(String email) {
		if (StringUtils.isBlank(email)) {
			return null;
		}
		int indexPos = email.lastIndexOf('@');
		if (indexPos > -1) {
			String domain = email.substring(indexPos + 1);
			return domain;
		}
		return null;
	}

	/**
	 * @return Returns the email address in a unspammable format ?
	 */
	public String getUnspammableEmail(String email) {
		String name = getEmailName(email);
		String domain = getEmailDomain(email);
		if ((name == null) || (domain == null)) {
			return null;
		}
		StringBuffer result = new StringBuffer();
		result.append(name).append("AT").append(domain);
		return result.toString();
	}
}
