package eu.ttbox.model.ui.store.header;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//@Entity
@Cacheable
@DiscriminatorValue("0")
public class WebHeaderMeta extends AbstractWebHeader  {

	private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 100)
    @Column(name = "KEY", length = 100, nullable = true)
    String name;

    @NotNull
    @Size(max = 100)
    @Column(name = "VALUE", length = 100, nullable = true)
    String content;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
