package eu.ttbox.model.supplier;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;

@Embeddable
public class SupplierPromoDetail {


	@Temporal(TemporalType.DATE)
	@Column(name = "PROMO_BEGIN")
	Date promoBegin;
 	
	@Temporal(TemporalType.DATE)
	@Column(name = "PROMO_END")
	Date promoEnd;
 
	@Column(name = "PROMO_MIN_QTY")
	Integer promoMinQuantity;
 
	@Column(name = "PROMO_FREE_QTY")
	Integer promoFreeQuantity;

	@Column(name = "PROMO_FREE_SKU")
	String promoFreeProductSku;
  
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "PROMO_FREE_SUPPLIERPRICE_ID", nullable = true)
	SupplierPrice promoFreeProduct;

	@Override
	public String toString() {
		ToStringHelper sb = Objects.toStringHelper(this);
		if (promoBegin != null) {
			sb.add("promoBegin", promoBegin);
		}
		if (promoEnd != null) {
			sb.add("promoEnd", promoEnd);
		}
		if (promoMinQuantity != null) {
			sb.add("promoMinQuantity", promoMinQuantity);
		}
		if (promoFreeQuantity != null) {
			sb.add("promoFreeQuantity", promoFreeQuantity);
		}
		if (promoFreeProductSku != null) {
			sb.add("promoFreeProductSku", promoFreeProductSku);
		}
		return sb.toString();
	}
	public Date getPromoBegin() {
		return promoBegin;
	}

	public void setPromoBegin(Date promoBegin) {
		this.promoBegin = promoBegin;
	}

	public Date getPromoEnd() {
		return promoEnd;
	}

	public void setPromoEnd(Date promoEnd) {
		this.promoEnd = promoEnd;
	}

	public Integer getPromoMinQuantity() {
		return promoMinQuantity;
	}

	public void setPromoMinQuantity(Integer promoMinQuantity) {
		this.promoMinQuantity = promoMinQuantity;
	}

	public Integer getPromoFreeQuantity() {
		return promoFreeQuantity;
	}

	public void setPromoFreeQuantity(Integer promoFreeQuantity) {
		this.promoFreeQuantity = promoFreeQuantity;
	}

	public SupplierPrice getPromoFreeProduct() {
		return promoFreeProduct;
	}

	public void setPromoFreeProduct(SupplierPrice promoFreeProduct) {
		this.promoFreeProduct = promoFreeProduct;
	}

	public String getPromoFreeProductSku() {
		return promoFreeProductSku;
	}

	public void setPromoFreeProductSku(String promoFreeProductSku) {
		this.promoFreeProductSku = promoFreeProductSku;
	}
}
