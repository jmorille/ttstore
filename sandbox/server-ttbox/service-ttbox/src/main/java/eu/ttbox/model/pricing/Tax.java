package eu.ttbox.model.pricing;

import java.math.BigDecimal;

import javax.persistence.Cacheable;
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
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import eu.ttbox.model.salespoint.Salespoint;

@Entity
@Cacheable
@Table(name = "B_TAX")
public class Tax {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqPricing")
	@SequenceGenerator(name = "seqPricing", sequenceName = "SEQ_PRICING", initialValue = 100)
	@Column(name = "ID", nullable = false, unique = true, scale = 0)
	Long id;

	@Column(name = "ENTITY_UID", unique = true, nullable = false, updatable = false, length = 36)
	String uid;

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

}
