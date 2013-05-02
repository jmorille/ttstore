package eu.ttbox.domain.model.service;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;

import eu.ttbox.domain.model.IBoxPersistantModelObject;
import eu.ttbox.domain.model.audit.AuditTrail;
import eu.ttbox.domain.model.audit.Auditable;
import eu.ttbox.domain.model.product.Product;

@SuppressWarnings("serial")
@Entity
@Table(name = "B_MENU_ARTICLE", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"MENU_ID", "PRODUCT_ID" }) //
})
public class MenuArticle implements IBoxPersistantModelObject, Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	Long id;

	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;

	@Embedded
	AuditTrail auditTrail;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MENU_ID", nullable = false)
	private Menu menu;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	private Product product;

	@Override
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this);
		sb.append("id", id);
		sb.append("version", version);
		sb.append("product", product); 
		sb.append("menu", menu); 
		return sb.toString();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Auditable getMenu() {
		return menu;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public AuditTrail getAuditTrail() {
		return auditTrail;
	}

	@Override
	public void setAuditTrail(AuditTrail auditTrail) {
		this.auditTrail = auditTrail;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	
	
}
