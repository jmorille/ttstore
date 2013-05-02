package eu.ttbox.model.ui.store.fragment;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
@Cacheable
@DiscriminatorValue("101")
public class BlockHtmlText extends AbstractBlock  {

    @Size(max = 100)
    @Column(name = "KEY", length = 100, nullable = true)
    String title;

    @Size(max = 2000)
    @Column(name = "VALUE", length = 2000, nullable = true)
    String content;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String value) {
        this.content = value;
    }
}
