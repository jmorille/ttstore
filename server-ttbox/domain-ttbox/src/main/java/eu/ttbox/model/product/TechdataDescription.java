package eu.ttbox.model.product;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Embeddable
public class TechdataDescription implements Serializable {

 
	private static final long serialVersionUID = 1L;

	
	@Size(max = 100)
	@Column(name = "TECH_NAME", length = 100, nullable = false)
	String name;

	@Size(max = 2000)
	@Column(name = "TECH_DESC", length = 2000, nullable = true)
	String description;
	
	@Basic
	@Temporal(TemporalType.DATE)
	@Column(name = "TECH_CREATION_DATE", nullable = true)
	Date articleCreationDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getArticleCreationDate() {
		return articleCreationDate;
	}

	public void setArticleCreationDate(Date articleCreationDate) {
		this.articleCreationDate = articleCreationDate;
	}

	
	
	
}
