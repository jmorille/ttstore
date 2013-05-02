package eu.ttbox.model.customer.company;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.granite.tide.data.DataPublishListener;

import eu.ttbox.core.listener.BoxUUIDInterceptor;
import eu.ttbox.model.IBoxPersistantModelObject;
import eu.ttbox.model.IBoxUUID;

 
@Entity
@Table(name = "B_COMPANY_PROFIL_TRANSAC")
@EntityListeners({ DataPublishListener.class, BoxUUIDInterceptor.class })
public class CompanyProfilTransaction implements IBoxPersistantModelObject, IBoxUUID  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCompanyProfilTransaction")
	@SequenceGenerator(name = "seqCompanyProfilTransaction", sequenceName = "SEQ_COMPANY_PROFIL_TRANSAC", initialValue = 100)
	@Column(name = "ID", nullable = false, unique=true, scale=0)
	Long id;

	@Column(name = "ENTITY_UID", unique = true, nullable = false, updatable = false, length = 36)
	String uid;

	
	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;
 

	@Size(max = 100)
	@NotNull
	@Column(name = "NAME", length = 100, nullable = false)
	String name;

	@NotNull 
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "COMPANY_ID", nullable = false)
	Company company;

	@OneToMany(mappedBy = "profile", fetch = FetchType.LAZY, orphanRemoval = true, cascade = { CascadeType.ALL })
	List<CompanyTransaction> transactions;

		
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

	 

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<CompanyTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<CompanyTransaction> transactions) {
		this.transactions = transactions;
	}

	public void addTransaction( CompanyTransaction  transaction) {
		if (getTransactions()==null) {
		  this.transactions = new ArrayList<CompanyTransaction>();
		}
		transaction.setProfile(this);
		this.transactions.add(transaction);
	}
	
}
