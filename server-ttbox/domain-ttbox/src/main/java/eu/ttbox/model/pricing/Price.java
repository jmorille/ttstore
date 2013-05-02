package eu.ttbox.model.pricing;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.google.common.base.Objects;

@Embeddable
public class Price implements Serializable  {
 
	private static final long serialVersionUID = 1L;

	@Min(0)
	@NotNull
	@Digits(integer=10, fraction=2)
	@Column(name = "AMOUNT", nullable = true, precision = 10, scale = 2)
	private BigDecimal amount;
	
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "CURRENCY", length = 3, nullable = true)
	CurrencyEnum currency;

	 
	public Price() {
		super();
	}

	public Price(BigDecimal priceAmount, CurrencyEnum priceCurrency) {
		super();
		this.amount = priceAmount;
		this.currency = priceCurrency;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal price) {
		this.amount = price;
	}

	public CurrencyEnum getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyEnum currency) {
		this.currency = currency;
	}

	public boolean isSame(BigDecimal priceAmount, CurrencyEnum priceCurrency) {
		boolean isSame = Objects.equal(this.currency, priceCurrency);
		if (isSame) {
			isSame = this.amount == priceAmount || (this.amount != null && (this.amount.compareTo(priceAmount)==0) );
		}
		return isSame;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Price other = (Price) obj;
		if (currency != other.currency)
			return false;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (amount.compareTo(other.amount)!=0)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("amount", amount).add("currency", currency).toString();
//		StringBuilder sb = new StringBuilder();
//		sb.append("Price [amount=").append(amount);
//		sb.append(", currency=").append(currency);
//		sb.append( "]");
//		return sb.toString(); 
	}
	
	
	
}
