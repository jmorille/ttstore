package eu.ttbox.model.ui.store;

import eu.ttbox.core.listener.BoxUUIDInterceptor;
import eu.ttbox.model.IBoxPersistantModelObject;
import eu.ttbox.model.salespoint.Salespoint;
import eu.ttbox.model.ui.store.fragment.AbstractBlock;
import eu.ttbox.model.ui.store.header.AbstractWebHeader;
import eu.ttbox.model.ui.store.header.WebHeaderMeta;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.granite.tide.data.DataPublishListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Cacheable
@Table(name = "B_WEBSITE", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"SALESPOINT_ID", "NAME"}) //
})
@NamedQuery(name = "WebSite.findByHost", query = "SELECT e FROM WebSite e where e.hostname=:hostname")
@EntityListeners({DataPublishListener.class, BoxUUIDInterceptor.class})
public class WebSite implements IBoxPersistantModelObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqWebSite")
    @SequenceGenerator(name = "seqWebSite", sequenceName = "SEQ_WEBSITE", initialValue = 100)
    @Column(name = "ID", nullable = false, unique = true, scale = 0)
    Long id;

    @Version
    @Column(name = "VERSION", nullable = false)
    Integer version;

    @NotNull
    @Size(max = 20)
    @Column(name = "HOSTNAME", length = 20, nullable = false, unique = true)
    String hostname;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SALESPOINT_ID", nullable = false)
    Salespoint salespoint;


    @NotNull
    @Size(max = 20)
    @Column(name = "NAME", length = 20, nullable = true, unique = false)
    String name;

    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "STATUS", length = 1, nullable = false, unique = false)
    WebSiteStatus status = WebSiteStatus.ON;

    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "ACCESS_MODE", length = 1, nullable = false, unique = false)
    WebSiteAccessMode accessMode = WebSiteAccessMode.PUBLIC;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "site")
    List<WebPage> pages = new ArrayList<WebPage>(0);

    @OneToMany(fetch = FetchType.LAZY, targetEntity = AbstractWebHeader.class)
    @JoinColumn(name = "WEBSITE_ID")
    List headers = new ArrayList(0);

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="BLOCK_BODY_ID", nullable = true)
    AbstractBlock blockBody;

    @Override
    public String toString() {
        ToStringBuilder sb = new ToStringBuilder(this);
        sb.append("id", id);
        sb.append("name", name);
        sb.append("status", status);
        sb.append("version", version);
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

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Salespoint getSalespoint() {
        return salespoint;
    }

    public void setSalespoint(Salespoint salespoint) {
        this.salespoint = salespoint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WebSiteAccessMode getAccessMode() {
        return accessMode;
    }

    public void setAccessMode(WebSiteAccessMode accessMode) {
        this.accessMode = accessMode;
    }

    public WebSiteStatus getStatus() {
        return status;
    }

    public void setStatus(WebSiteStatus status) {
        this.status = status;
    }


    public List<WebPage> getPages() {
        return pages;
    }

    public void setPages(List<WebPage> pages) {
        this.pages = pages;
    }

    public List getHeaders() {
        return headers;
    }

    public void setHeaders(List  headers) {
        this.headers = headers;
    }

    public AbstractBlock getBlockBody() {
        return blockBody;
    }

    public void setBlockBody(AbstractBlock blockBody) {
        this.blockBody = blockBody;
    }
}
