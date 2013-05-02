package eu.ttbox.model.catalog;

import java.math.BigDecimal;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.granite.tide.data.DataPublishListener;

import eu.ttbox.core.listener.BoxUUIDInterceptor;

@Entity
@Cacheable
@Table(name = "B_TAX" )
@EntityListeners({ DataPublishListener.class, 
		BoxUUIDInterceptor.class })
public class Tax {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqTax")
	@SequenceGenerator(name = "seqTax", sequenceName = "SEQ_TAX", initialValue = 100)
	@Column(name = "ID", nullable = false, unique=true, scale=0)
	Long id;

	@Column(name = "ENTITY_UID", unique = true, nullable = false, updatable = false, length = 36)
	String uid;

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

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	
	
	
}
