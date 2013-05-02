package eu.ttbox.model.ui.store.fragment;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderColumn;
import javax.validation.constraints.Size;

@Entity
@Cacheable
@DiscriminatorValue("100")
public class BlockColumn extends AbstractBlock  {

	private static final long serialVersionUID = 1L;

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
    List<AbstractBlock> blocks=  new ArrayList<AbstractBlock>(0);

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<? extends AbstractBlock> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<AbstractBlock> blocks) {
        this.blocks = blocks;
    }
    

}
