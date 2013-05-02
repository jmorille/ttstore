package eu.ttbox.model.pricing;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;

import eu.ttbox.model.IBoxPersistantModelObject;
import eu.ttbox.model.salespoint.Salespoint;

@Entity
@Cacheable
@Table(name = "B_PRICING") 
public class Pricing implements IBoxPersistantModelObject {

	 
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(generator = "seqPricing")
	@GenericGenerator(name = "seqPricing", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQ_PRICING")) 
	@Column(name = "ID", nullable = false, unique = true, scale = 0)
	Integer id;
 

	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "SALESPOINT_ID", nullable = true)
	Salespoint salespoint;

	@Size(max = 25)
	@Column(name = "NAME", length = 25, nullable = true)
	String name;

	@Column(name = "COEFF", nullable = true, precision = 20, scale = 20)
	BigDecimal marginCoeff;

	@Min(0)
	@Column(name = "FIXED_AMOUNT", nullable = true, precision = 20, scale = 3)
	BigDecimal fixedAmount;

	@Min(0)
	@Column(name = "MIN_AMOUNT", nullable = true, precision = 20, scale = 3)
	BigDecimal minAmountMargin;

	@Min(0)
	@Column(name = "MAX_AMOUNT", nullable = true, precision = 20, scale = 3)
	BigDecimal maxAmountMargin;

	@NotNull
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "TYPE", nullable = false)
	PricingTypeEnum type = PricingTypeEnum.COMPUTE_SUPPLIER_PRICE;

	@Column(name = "IS_CONTROL_LOSS", nullable = false)
	boolean controlSellLoss = true;

	private void resetField(PricingTypeEnum type) {
		if (PricingTypeEnum.COMPUTE_SUPPLIER_PRICE == type) {
			this.marginCoeff = null;
			this.minAmountMargin = null;
			this.maxAmountMargin = null;
			this.fixedAmount = null;
		} else if (PricingTypeEnum.COMPUTE_SUPPLIER_PRICE == type) {
			this.fixedAmount = null;
		} else if (PricingTypeEnum.FIXED_PRICE == type) {
			this.marginCoeff = null;
		} else if (PricingTypeEnum.PUBLIC_PRICE == type) {
			this.marginCoeff = null;
			this.minAmountMargin = null;
			this.maxAmountMargin = null;
			this.fixedAmount = null;
		}
	}

	private BigDecimal checkMargin(BigDecimal margin) {
		BigDecimal checkedMargin = margin;
		// Check Minimum Margin
		if (minAmountMargin != null) {
			checkedMargin = minAmountMargin.max(checkedMargin);
		}
		// Check Maximum Margin
		if (maxAmountMargin != null) {
			checkedMargin = maxAmountMargin.min(checkedMargin);
		}
		return checkedMargin;
	}

	public BigDecimal getPriceHT(BigDecimal supplierPriceHT, BigDecimal publicPriceHT) {

		BigDecimal priceHT = null;
		BigDecimal margin = null;
		if (PricingTypeEnum.NOT_SELL == type) {
			return null;
		} else if (PricingTypeEnum.COMPUTE_SUPPLIER_PRICE == type) {
			if (supplierPriceHT == null) {
				return null;
			}
			margin = getComputeMargin(supplierPriceHT);
			margin = checkMargin(margin);
			priceHT = supplierPriceHT.add(margin);
			return priceHT;
		} else if (PricingTypeEnum.FIXED_PRICE == type) {
			priceHT = fixedAmount;
			margin = priceHT.subtract(supplierPriceHT);
			margin = checkMargin(margin);
		} else if (PricingTypeEnum.PUBLIC_PRICE == type) {
			priceHT = publicPriceHT;
			margin = priceHT.subtract(supplierPriceHT);
			margin = checkMargin(margin);
		}
		// Check Marging
		if (minAmountMargin != null) {
			priceHT = priceHT.max(minAmountMargin.add(supplierPriceHT));
		}
		if (maxAmountMargin != null) {
			priceHT = priceHT.min(maxAmountMargin.add(supplierPriceHT));
		}
		// Controle des ventes a perte
		if (controlSellLoss) {
			if ((supplierPriceHT == null) || (priceHT == null) || (supplierPriceHT.compareTo(priceHT)) > 0) {
				priceHT = null;
			}
		}
		return priceHT;
	}

	private BigDecimal getComputeMargin(BigDecimal supplierPriceHT) {
		BigDecimal margin = BigDecimal.ZERO;
		if (marginCoeff != null) {
			margin = supplierPriceHT.multiply(marginCoeff);
		}
		margin = margin.setScale(2, RoundingMode.CEILING);
		return margin;
	}

	@Override
	public String toString() {
		ToStringHelper sb =  Objects.toStringHelper(this);
		sb.add("id", id);
		sb.add("version", version);
		sb.add("type", type);
		sb.add("name", name);
		sb.add("marginCoeff", marginCoeff);
		sb.add("minAmountMargin", minAmountMargin);
		sb.add("maxAmountMargin", maxAmountMargin);
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Pricing other = (Pricing) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
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

	public BigDecimal getMarginCoeff() {
		return marginCoeff;
	}

	public void setMarginCoeff(BigDecimal marginCoeff) {
		this.marginCoeff = marginCoeff;
	}

	public BigDecimal getMinAmountMargin() {
		return minAmountMargin;
	}

	public void setMinAmountMargin(BigDecimal minAmountMargin) {
		this.minAmountMargin = minAmountMargin;
	}

	public BigDecimal getMaxAmountMargin() {
		return maxAmountMargin;
	}

	public void setMaxAmountMargin(BigDecimal maxAmountMargin) {
		this.maxAmountMargin = maxAmountMargin;
	}

	public PricingTypeEnum getType() {
		return type;
	}

	public void setType(PricingTypeEnum type) {
		this.type = type;
	}

}
