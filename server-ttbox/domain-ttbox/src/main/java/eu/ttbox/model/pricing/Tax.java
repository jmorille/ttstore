package eu.ttbox.model.pricing;

import java.math.BigDecimal;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import eu.ttbox.model.salespoint.Salespoint;

@Entity
@Cacheable
@Table(name = "B_TAX")
public class Tax {

	@Id
	@GeneratedValue(generator = "seqTax")
	@GenericGenerator(name = "seqTax", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQ_TAX"))  
	@Column(name = "ID", nullable = false, unique = true, scale = 0)
	Long id;
 
	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "SALESPOINT_ID", nullable = true)
	Salespoint salespoint;

	@Column(name = "COEFF", nullable = true, precision = 20, scale = 20)
	BigDecimal taxCoeff;

	public BigDecimal getPriceTTC(BigDecimal priceHT) {
		BigDecimal priceTTC = priceHT.multiply(taxCoeff);
		return priceTTC;
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

	public Salespoint getSalespoint() {
		return salespoint;
	}

	public void setSalespoint(Salespoint salespoint) {
		this.salespoint = salespoint;
	}

	public BigDecimal getTaxCoeff() {
		return taxCoeff;
	}

	public void setTaxCoeff(BigDecimal taxCoeff) {
		this.taxCoeff = taxCoeff;
	}

	
	
}
