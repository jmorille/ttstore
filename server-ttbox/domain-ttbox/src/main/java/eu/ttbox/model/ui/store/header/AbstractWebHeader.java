package eu.ttbox.model.ui.store.header;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import eu.ttbox.model.IBoxPersistantModelObject;

//@Entity
@Cacheable
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "B_WEBFRAG_HEADER")
@DiscriminatorColumn(name = "TYPE_HEADER", discriminatorType = DiscriminatorType.STRING)
public class AbstractWebHeader implements Serializable, IBoxPersistantModelObject {

	private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(generator = "seqWebFragmentHeader")
	@GenericGenerator(name = "seqWebFragmentHeader", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "SEQ_WEBFRAG_HEADER"))  
    @Column(name = "ID", nullable = false, unique = true, scale = 0)
    Integer id;

    @Version
    @Column(name = "VERSION", nullable = false)
    Integer version;

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
}
