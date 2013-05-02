package eu.ttbox.model.ui.store.header;

import eu.ttbox.model.IBoxPersistantModelObject;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Cacheable
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "B_WEBFRAG_HEADER")
@DiscriminatorColumn(name = "TYPE_HEADER", discriminatorType = DiscriminatorType.STRING)
public class AbstractWebHeader implements Serializable, IBoxPersistantModelObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqWebFragment")
    @SequenceGenerator(name = "seqWebFragment", sequenceName = "SEQ_WEBFRAG", initialValue = 100)
    @Column(name = "ID", nullable = false, unique = true, scale = 0)
    Long id;

    @Version
    @Column(name = "VERSION", nullable = false)
    Integer version;

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
}
