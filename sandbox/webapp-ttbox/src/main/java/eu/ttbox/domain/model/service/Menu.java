package eu.ttbox.domain.model.service;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import eu.ttbox.domain.model.IBoxPersistantModelObject;
import eu.ttbox.domain.model.audit.AuditTrail;
import eu.ttbox.domain.model.audit.Auditable;

@SuppressWarnings("serial")
@Entity
@Table(name = "B_MENU", uniqueConstraints = { @UniqueConstraint(columnNames = { "ID" }) //
})
public class Menu implements IBoxPersistantModelObject, Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	Long id;

	@Length(max=50) @NotBlank
	@Column(name = "NAME", length = 50, nullable = false)
	String name;

	@Version
	@Column(name = "VERSION", nullable = false)
	Integer version;

	@Embedded
	AuditTrail auditTrail;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "STATUS", nullable = false)
	MenuStatus status = MenuStatus.ON;
	
	@OneToMany(mappedBy = "menu", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	List<MenuArticle> articles;

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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public MenuStatus getStatus() {
		return status;
	}

	public void setStatus(MenuStatus status) {
		this.status = status;
	}

	public List<MenuArticle> getArticles() {
		return articles;
	}

	public void setArticles(List<MenuArticle> articles) {
		this.articles = articles;
	}

	@Override
	public AuditTrail getAuditTrail() {
		return auditTrail;
	}

	@Override
	public void setAuditTrail(AuditTrail auditTrail) {
		this.auditTrail = auditTrail;
	}

}
