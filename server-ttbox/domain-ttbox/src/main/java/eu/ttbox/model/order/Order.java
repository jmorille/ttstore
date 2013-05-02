package eu.ttbox.model.order;

import java.math.BigDecimal;

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
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import eu.ttbox.model.IBoxPersistantModelObject;
import eu.ttbox.model.salespoint.Salespoint;

@Entity
@Table(name = "B_ORDER")
public class Order implements IBoxPersistantModelObject {

	@Id
	@GeneratedValue(generator = "seqOrder")
	@GenericGenerator(name = "seqOrder", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQ_ORDER"))   
	@Column(name = "ID", nullable = false, unique = true, scale = 0)
	Integer id;

	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;
 
	@Size(max = 32)
	@Column(name = "ORDER_NUM", length = 32, nullable = true, unique = true)
	String orderNumber;

	@Column(name = "PRICE_HT", nullable = true, precision = 20, scale = 3)
	BigDecimal priceSumHT;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "SALESPOINT_ID", nullable = true)
	Salespoint salespoint;

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

	public BigDecimal getPriceSumHT() {
		return priceSumHT;
	}

	public void setPriceSumHT(BigDecimal priceSumHT) {
		this.priceSumHT = priceSumHT;
	}
 

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Salespoint getSalespoint() {
		return salespoint;
	}

	public void setSalespoint(Salespoint salespoint) {
		this.salespoint = salespoint;
	}

}
