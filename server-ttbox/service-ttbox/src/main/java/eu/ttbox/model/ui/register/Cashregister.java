package eu.ttbox.model.ui.register;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.ToStringBuilder;

import eu.ttbox.model.IBoxPersistantModelObject;
import eu.ttbox.model.salespoint.Salespoint;

 
@Entity
@Table(name = "B_CASHREGISTER" , uniqueConstraints = { 
		@UniqueConstraint(columnNames = { "SALESPOINT_ID", "NAME" }) //
})

public class Cashregister  implements IBoxPersistantModelObject  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCashRegister")
	@SequenceGenerator(name = "seqCashRegister", sequenceName = "SEQ_CASHREGISTER", initialValue = 100)
	@Column(name = "ID", nullable = false, unique=true, scale=0)
	Long id;

	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;
 
  
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "SALESPOINT_ID", nullable = true)
	Salespoint salespoint;

	@NotNull
	@Size(max=50)
	@Column(name = "NAME", length=50,nullable = false)
	String name;

 
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

	public Salespoint getSalespoint() {
		return salespoint;
	}

	public void setSalespoint(Salespoint salespoint) {
		this.salespoint = salespoint;
	}

  	
	
	
}
