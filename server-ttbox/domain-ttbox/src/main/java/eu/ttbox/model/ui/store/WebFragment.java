package eu.ttbox.model.ui.store;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import eu.ttbox.model.IBoxPersistantModelObject;

//@Entity
@Cacheable
@Table(name = "S_WEBFRAG") 
public class WebFragment  implements IBoxPersistantModelObject {

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "seqWebFragment")
	@GenericGenerator(name = "seqWebFragment", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQ_WEBFRAG")) 
    @Column(name = "ID", nullable = false, unique = true, scale = 0)
    Integer id;

    @Version
    @Column(name = "VERSION", nullable = false)
    Integer version;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "WEBPAGE_ID", nullable = false)
    WebPage page;

    public WebFragment() {
     }

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

    public WebPage getPage() {
        return page;
    }

    public void setPage(WebPage page) {
        this.page = page;
    }
}
