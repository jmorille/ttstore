package eu.ttbox.model.ui.store;

import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.ToStringBuilder;

import eu.ttbox.model.IBoxPersistantModelObject;
import eu.ttbox.model.ui.store.fragment.AbstractBlock;
import eu.ttbox.model.ui.store.header.AbstractWebHeader;

@Entity
@Cacheable
@Table(name = "B_WEBPAGE") 
public class WebPage implements IBoxPersistantModelObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqWebPage")
    @SequenceGenerator(name = "seqWebPage", sequenceName = "SEQ_WEBPAGE", initialValue = 100)
    @Column(name = "ID", nullable = false, unique = true, scale = 0)
    Long id;

    @Version
    @Column(name = "VERSION", nullable = false)
    Integer version;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "WEBSITE_ID", nullable = false)
    WebSite site;

    @NotNull
    @Size(max = 10)
    @Column(name = "TYPE", length = 10, nullable = true)
    String type;

    @Transient
    List<? extends AbstractWebHeader> headers = new ArrayList<AbstractWebHeader>(0);

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="BLOCK_BODY_ID", nullable = true)
    AbstractBlock blockBody;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="BLOCK_SIDE_LEFT_ID", nullable = true)
    AbstractBlock blockLeftSide;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="BLOCK_SIDE_RIGHT_ID", nullable = true)
    AbstractBlock blockRightSide;

    @Override
    public String toString() {
        ToStringBuilder sb = new ToStringBuilder(this);
        sb.append("id", id);
        sb.append("site", site);
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

    public WebSite getSite() {
        return site;
    }

    public void setSite(WebSite site) {
        this.site = site;
    }


    public List<? extends AbstractWebHeader> getHeaders() {
        return headers;
    }

    public void setHeaders(List<? extends AbstractWebHeader> headers) {
        this.headers = headers;
    }

    public AbstractBlock getBlockBody() {
        return blockBody;
    }

    public void setBlockBody(AbstractBlock blockBody) {
        this.blockBody = blockBody;
    }

    public AbstractBlock getBlockLeftSide() {
        return blockLeftSide;
    }

    public void setBlockLeftSide(AbstractBlock blockLeftSide) {
        this.blockLeftSide = blockLeftSide;
    }

    public AbstractBlock getBlockRightSide() {
        return blockRightSide;
    }

    public void setBlockRightSide(AbstractBlock blockRightSide) {
        this.blockRightSide = blockRightSide;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}