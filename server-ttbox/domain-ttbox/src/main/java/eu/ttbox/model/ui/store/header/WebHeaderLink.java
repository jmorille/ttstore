package eu.ttbox.model.ui.store.header;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//@Entity
@Cacheable
@DiscriminatorValue("1")
public class WebHeaderLink extends AbstractWebHeader  {

	private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 100)
    @Column(name = "KEY", length = 100, nullable = true)
    String rel;

    @NotNull
    @Size(max = 100)
    @Column(name = "VALUE", length = 100, nullable = true)
    String href;

    @NotNull
    @Size(max = 20)
    @Column(name = "TYPE", length = 20, nullable = true)
    String type;


    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
