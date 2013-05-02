package eu.ttbox.model.ui.store.fragment;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import eu.ttbox.model.IBoxPersistantModelObject;
import eu.ttbox.model.salespoint.Salespoint;

@Entity
@Cacheable
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "B_WEBLOCK")
@DiscriminatorColumn(name = "TYPE", discriminatorType = DiscriminatorType.INTEGER)
public abstract class AbstractBlock implements Serializable, IBoxPersistantModelObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqWebBlock")
    @SequenceGenerator(name = "seqWebBlock", sequenceName = "B_WEBLOCK", initialValue = 100)
    @Column(name = "ID", nullable = false, unique = true, scale = 0)
    Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SALESPOINT_ID", nullable = false)
    Salespoint salespoint;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Salespoint getSalespoint() {
        return salespoint;
    }

    public void setSalespoint(Salespoint salespoint) {
        this.salespoint = salespoint;
    }

}
