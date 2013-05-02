package eu.ttbox.model.customer.company;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;

import eu.ttbox.model.IBoxPersistantModelObject;

@Entity
@Table(name = "B_COMPANY")
public class Company implements IBoxPersistantModelObject {

	@Id
	@GeneratedValue(generator = "seqCompany")
	@GenericGenerator(name = "seqCompany", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQ_COMPANY"))
	@Column(name = "ID", nullable = false, unique = true, scale = 0)
	Integer id;

	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;

	@NotNull
	@Column(name = "NAME", length = 100, nullable = false)
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

}
