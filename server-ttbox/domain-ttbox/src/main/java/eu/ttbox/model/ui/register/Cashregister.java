package eu.ttbox.model.ui.register;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;

import eu.ttbox.model.IBoxPersistantModelObject;
import eu.ttbox.model.salespoint.Salespoint;

 
//@Entity
@Table(name = "B_CASHREGISTER" , uniqueConstraints = { 
		@UniqueConstraint(columnNames = { "SALESPOINT_ID", "NAME" }) //
})

public class Cashregister  implements IBoxPersistantModelObject  {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "seqCashRegister")
	@GenericGenerator(name = "seqCashRegister", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQ_CASHREGISTER"))   
	@Column(name = "ID", nullable = false, unique=true, scale=0)
	Integer id;

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
		ToStringHelper sb =  Objects.toStringHelper(this);
		sb.add("id", id);
		sb.add("name", name);
		sb.add("version", version);
		return sb.toString();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
