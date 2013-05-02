package eu.ttbox.model.ui.store;

import eu.ttbox.core.listener.BoxUUIDInterceptor;
import eu.ttbox.model.IBoxPersistantModelObject;
import org.granite.tide.data.DataPublishListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Cacheable
@Table(name = "B_WEBFRAG")
@EntityListeners({DataPublishListener.class, BoxUUIDInterceptor.class})
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
