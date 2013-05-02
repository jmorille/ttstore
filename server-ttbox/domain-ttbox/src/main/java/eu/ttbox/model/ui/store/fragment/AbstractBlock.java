package eu.ttbox.model.ui.store.fragment;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import eu.ttbox.model.IBoxPersistantModelObject;
import eu.ttbox.model.salespoint.Salespoint;

@Entity
@Cacheable
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "S_WEBLOCK")
@DiscriminatorColumn(name = "TYPE", discriminatorType = DiscriminatorType.INTEGER)
public abstract class AbstractBlock implements Serializable, IBoxPersistantModelObject {

	private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(generator = "seqWebBlock")
	@GenericGenerator(name = "seqWebBlock", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQ_WEBLOCK"))  
    @Column(name = "ID", nullable = false, unique = true, scale = 0)
    Integer id;

    @Version
	@Column(name = "VERSION", nullable = false)
	Integer version;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SALESPOINT_ID", nullable = false)
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

	public Salespoint getSalespoint() {
        return salespoint;
    }

    public void setSalespoint(Salespoint salespoint) {
        this.salespoint = salespoint;
    }

}
