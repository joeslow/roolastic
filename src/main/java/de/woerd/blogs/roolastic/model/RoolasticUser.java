package de.woerd.blogs.roolastic.model;

import javax.persistence.Entity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;
import de.woerd.blogs.roolastic.model.Authority;
import java.util.HashSet;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
import javax.persistence.Transient;

@Entity
@RooJavaBean
@RooToString
@RooEntity(finders = { "findRoolasticUsersByUsername" })
public class RoolasticUser implements UserDetails {

    @NotNull
    @Size(min = 3)
    @Column(unique = true)
    private String username;

    @NotNull
    @Size(min = 4)
    private String password;
    
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Authority> authorities = new HashSet<Authority>();
    
    public void addAuthority(Authority authority) {
    	this.authorities.add(authority);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    @Transient
    public Collection<GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
        for (Authority auth : authorities) {
            auths.add(new GrantedAuthorityImpl(auth.getName()));
        }
        return auths;
    }
}
