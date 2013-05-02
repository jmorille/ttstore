package eu.ttbox.batch.icecat.elasticsearch;

public class Daniel implements IDaniel {

	private String volume;

	private String age;

	private String prix;

	public Daniel() {
		super();
	}

	public Daniel(String volume, String age, String prix) {
		super();
		this.volume = volume;
		this.age = age;
		this.prix = prix;
	}

	@Override
	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.ttbox.batch.icecat.elasticsearch.IDaniel#getAge()
	 */
	@Override
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.ttbox.batch.icecat.elasticsearch.IDaniel#getPrix()
	 */
	@Override
	public String getPrix() {
		return prix;
	}

	public void setPrix(String prix) {
		this.prix = prix;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((prix == null) ? 0 : prix.hashCode());
		result = prime * result + ((volume == null) ? 0 : volume.hashCode());
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
		Daniel other = (Daniel) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (prix == null) {
			if (other.prix != null)
				return false;
		} else if (!prix.equals(other.prix))
			return false;
		if (volume == null) {
			if (other.volume != null)
				return false;
		} else if (!volume.equals(other.volume))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Daniel [volume=" + volume + ", age=" + age + ", prix=" + prix + "]";
	}

}
