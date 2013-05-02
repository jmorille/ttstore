package eu.ttbox.model.customer.company;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;

import eu.ttbox.model.IBoxPersistantModelObject;

 
@Entity
@Table(name = "B_COMPANY" )

public class Company  implements IBoxPersistantModelObject  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCompany")
	@SequenceGenerator(name = "seqCompany", sequenceName = "SEQ_COMPANY", initialValue = 100)
	@Column(name = "ID", nullable = false, unique=true, scale=0)
	Long id;

 
	
	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;
	 

	@NotNull
	@Column(name = "NAME", length=100,nullable = false)
	String name;

	@OneToMany(mappedBy = "company", fetch = FetchType.LAZY, orphanRemoval = true, cascade = { CascadeType.ALL })
	List<CompanyProfilTransaction> profiles;

	@Override
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this);
		sb.append("id", id);
		sb.append("name", name);
		sb.append("version", version);
		return sb.toString();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
 
 

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CompanyProfilTransaction> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<CompanyProfilTransaction> profiles) {
		this.profiles = profiles;
	}

	public void addProfile(CompanyProfilTransaction profile) {
		if (getProfiles()== null) {
		this.profiles = new ArrayList<CompanyProfilTransaction>();
		}
		profile.setCompany(this);
		this.profiles.add(profile);
	}

	
	
	
}
