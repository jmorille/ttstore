package eu.ttbox.model.catalog;

import java.math.BigDecimal;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Cacheable
@Table(name = "B_TAX" )
public class Tax {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqTax")
	@SequenceGenerator(name = "seqTax", sequenceName = "SEQ_TAX", initialValue = 100)
	@Column(name = "ID", nullable = false, unique=true, scale=0)
	Long id;

 
	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;

	public BigDecimal getPriceTTC(BigDecimal priceHT){
		return priceHT;
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
	
	
	
	
}
