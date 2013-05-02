package eu.ttbox.model.ui.store.fragment;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
@Cacheable
@DiscriminatorValue("102")
public class BlockFlash extends AbstractBlock  {

	private static final long serialVersionUID = 1L;

    @Size(max = 100)
    @Column(name = "KEY", length = 100, nullable = true)
    String title;

    @Size(max = 500)
    @Column(name = "VALUE", length = 500, nullable = true)
    String url;


    @Column(name = "WIDTH", nullable = true)
    String width;

    @Column(name = "HEIGHT", nullable = true)
    String height;
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String value) {
        this.url = value;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
    
    
}
