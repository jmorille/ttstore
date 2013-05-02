package eu.ttbox.model.order;

import java.math.BigDecimal;

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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import eu.ttbox.model.IBoxPersistantModelObject;
import eu.ttbox.model.catalog.Offer;

@Entity
@Table(name = "B_ORDER_ITEM")
public class OrderItem implements IBoxPersistantModelObject {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqOrderItem")
	@SequenceGenerator(name = "seqOrderItem", sequenceName = "SEQ_ORDER_ITEM", initialValue = 100)
	@Column(name = "ID", nullable = false, unique = true, scale = 0)
	Long id;

	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;

 
	@Column(name = "QTY", nullable = true, precision = 20, scale = 3)
	Long quantity;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "OFFER_ID", nullable = false)
	Offer offer;

	@Column(name = "PRICE_UNIT_HT", nullable = true, precision = 20, scale = 3)
	BigDecimal priceUnitHT;

	@Column(name = "PRODUCT_ID", nullable = false)
	Long productId;

	@Override
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

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantityUnit) {
		this.quantity = quantityUnit;
	}

	public BigDecimal getPriceUnitHT() {
		return priceUnitHT;
	}

	public void setPriceUnitHT(BigDecimal priceUnitHT) {
		this.priceUnitHT = priceUnitHT;
	}

	public Offer getOfferId() {
		return offer;
	}

	public void setOfferId(Offer offer) {
		this.offer = offer;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

 

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

}
