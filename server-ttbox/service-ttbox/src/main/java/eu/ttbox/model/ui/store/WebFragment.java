package eu.ttbox.model.ui.store;

import javax.persistence.Cacheable;
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

import eu.ttbox.model.IBoxPersistantModelObject;

@Entity
@Cacheable
@Table(name = "B_WEBFRAG") 
public class WebFragment  implements IBoxPersistantModelObject {

        @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqWebFragment")
    @SequenceGenerator(name = "seqWebFragment", sequenceName = "SEQ_WEBFRAG", initialValue = 100)
    @Column(name = "ID", nullable = false, unique = true, scale = 0)
    Long id;

    @Version
    @Column(name = "VERSION", nullable = false)
    Integer version;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "WEBPAGE_ID", nullable = false)
    WebPage page;

    public WebFragment() {
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

    public WebPage getPage() {
        return page;
    }

    public void setPage(WebPage page) {
        this.page = page;
    }
}
