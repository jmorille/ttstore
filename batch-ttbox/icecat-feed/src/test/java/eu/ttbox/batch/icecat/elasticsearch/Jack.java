package eu.ttbox.batch.icecat.elasticsearch;

public class Jack // implements IJackJson
{

	private String firstname;

	private String lastname;

	private String optional;

	private String hidden = "YOU DON'T NEED TO SHOW THAT";

	private Daniel daniel;

	public Jack() {
		super();
	}

	public Jack(String firstname, String lastname, Daniel daniel) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.daniel = daniel;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getOptional() {
		return optional;
	}

	public void setOptional(String optional) {
		this.optional = optional;
	}

	public Daniel getDaniel() {
		return daniel;
	}

	public void setDaniel(Daniel daniel) {
		this.daniel = daniel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
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
		Jack other = (Jack) obj;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Jack [firstname=" + firstname + ", lastname=" + lastname + ", optional=" + optional + ", daniel=" + daniel + "]";
	}

	public String getHidden() {
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

}
