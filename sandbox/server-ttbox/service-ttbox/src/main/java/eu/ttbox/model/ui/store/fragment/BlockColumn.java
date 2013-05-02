package eu.ttbox.model.ui.store.fragment;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Cacheable
@DiscriminatorValue("100")
public class BlockColumn extends AbstractBlock  {

    @Size(max = 100)
    @Column(name = "KEY", length = 100, nullable = true)
    String title;


    @OrderColumn(name="orders_index")
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = AbstractBlock.class)
    @JoinTable(
        name="B_WEBLOCK_COLUMN",
        joinColumns=@JoinColumn(name="WEBBLOCK_ID"),
        inverseJoinColumns=@JoinColumn(name="WEBBLOCK_SLAVE_ID")
    )
    List blocks=  new ArrayList(0);

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List getBlocks() {
        return blocks;
    }

    public void setBlocks(List blocks) {
        this.blocks = blocks;
    }
    

}
