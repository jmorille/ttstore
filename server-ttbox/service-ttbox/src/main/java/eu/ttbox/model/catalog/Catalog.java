package eu.ttbox.model.catalog;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.ToStringBuilder;

import eu.ttbox.model.IBoxPersistantModelObject;
import eu.ttbox.model.salespoint.Salespoint;

@Entity
@Table(name = "B_CATALOG", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"SALESPOINT_ID", "NAME" }) //
}) 
public class Catalog implements IBoxPersistantModelObject  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCatalog")
	@SequenceGenerator(name = "seqCatalog", sequenceName = "SEQ_CATALOG", initialValue = 100)
	@Column(name = "ID", nullable = false, unique = true, scale = 0)
	Long id;

	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;

 
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "SALESPOINT_ID", nullable = false)
	Salespoint salespoint;

	@NotNull
	@Size(max = 50)
	@Column(name = "NAME", length = 50, nullable = false)
	String name;

	@NotNull
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "STATUS", nullable = false)
	CatalogStatus status = CatalogStatus.ON;

	@ManyToMany(fetch = FetchType.LAZY, targetEntity = Offer.class, cascade = { CascadeType.PERSIST })
	@JoinTable(name = "B_CATALOG_OFFER", joinColumns = { @JoinColumn(name = "CATALOG_ID") }, inverseJoinColumns = @JoinColumn(name = "OFFER_ID"), uniqueConstraints = { @UniqueConstraint(columnNames = {
			"CATALOG_ID", "OFFER_ID" }) })
	List<Offer> offers;

	@Override
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this);
		sb.append("id", id);
		sb.append("version", version);
		sb.append("name", name);
		return sb.toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public CatalogStatus getStatus() {
		return status;
	}

	public void setStatus(CatalogStatus status) {
		this.status = status;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> articles) {
		this.offers = articles;
	}

	public Salespoint getSalespoint() {
		return salespoint;
	}

	public void setSalespoint(Salespoint salespoint) {
		this.salespoint = salespoint;
	}

}
