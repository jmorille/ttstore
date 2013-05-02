package eu.ttbox.model.user;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import eu.ttbox.model.IBoxPersistantModelObject;

@Entity
@Cacheable
@Table(name = "B_USER", uniqueConstraints = {@UniqueConstraint(columnNames = {"MATRICULE"}) //
})

@NamedQuery(name = "User.findByEmail", query = "FROM User WHERE email=:email") 
public class User implements UserDetails, IBoxPersistantModelObject, Serializable {

    private static final long serialVersionUID = 5893908421589986725L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqUser")
    @SequenceGenerator(name = "seqUser", sequenceName = "SEQ_USER", initialValue = 100)
    @Column(name = "ID", nullable = false, unique = true, scale = 0)
    Long id;

 

    @Version
    @Column(name = "VERSION", nullable = false)
    Integer version;

    @Basic
    @NotNull
    @Size(max = 100)
    @Column(name = "LASTNAME", length = 100, nullable = true)
    String lastName;

    @Basic
    @Size(max = 50, min = 2)
    @Column(name = "FIRSTNAME", length = 100, nullable = true)
    String firstName;

    @Basic
    @Column(name = "MATRICULE", length = 20, nullable = true)
    @Size(max = 20)
    String matricule;

    @Basic
    @Size(max = 100)
    @Email
    @Column(name = "EMAIL", length = 100, nullable = true)
    String email;

    @Basic
    @Size(max = 25)
    @Column(name = "PASSWORD", length = 25, nullable = true)
    String password;

    /**
     * Indicates whether the user's account has expired. An expired account
     * cannot be authenticated.
     *
     * @return <code>true</code> if the user's account is valid (ie
     * non-expired), <code>false</code> if no longer valid (ie expired)
     */
    @Basic
    @Column(name = "SEC_ACCOUNT_NON_EXPIRED", nullable = false)
    boolean accountNonExpired = true;

    /**
     * Indicates whether the user is locked or unlocked. A locked user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is not locked, <code>false</code>
     * otherwise
     */
    @Basic
    @Column(name = "SEC_ACCOUNT_NON_LOCKED", nullable = false)
    boolean accountNonLocked = true;

    /**
     * Indicates whether the user's credentials (password) has expired. Expired
     * credentials prevent authentication.
     *
     * @return <code>true</code> if the user's credentials are valid (ie
     * non-expired), <code>false</code> if no longer valid (ie expired)
     */
    @Basic
    @Column(name = "SEC_CREDENTIAL_NON_EXPIRED", nullable = false)
    boolean credentialsNonExpired = true;

    /**
     * Indicates whether the user is enabled or disabled. A disabled user cannot
     * be authenticated.
     *
     * @return <code>true</code> if the user is enabled, <code>false</code>
     * otherwise
     */
    @Basic
    @Column(name = "SEC_ENABLED", nullable = false)
    boolean enabled = true;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = GroupRole.class, cascade = {CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinTable(name = "B_USER_GROUP", joinColumns = {@JoinColumn(name = "USER_ID")}, inverseJoinColumns = @JoinColumn(name = "GROUP_ID")//
            , uniqueConstraints = {@UniqueConstraint(columnNames = {"USER_ID", "GROUP_ID"})})
    List<GroupRole> groups;

    @Override
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<GroupRole> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupRole> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        ToStringBuilder sb = new ToStringBuilder(this);
        sb.append("id", id);
        sb.append("lastname", lastName);
        sb.append("firstname", firstName);
        sb.append("version", version);
        return sb.toString();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        List<GroupRole> groupRoles = getGroups();
        for (GroupRole group : groupRoles) {
            roles.addAll(group.getRoles());
        }
        return roles;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }


}
