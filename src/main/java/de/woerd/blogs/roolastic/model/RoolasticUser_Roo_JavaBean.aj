package de.woerd.blogs.roolastic.model;

import de.woerd.blogs.roolastic.model.Authority;
import java.lang.String;
import java.util.Set;

privileged aspect RoolasticUser_Roo_JavaBean {
    
    public String RoolasticUser.getUsername() {
        return this.username;
    }
    
    public void RoolasticUser.setUsername(String username) {
        this.username = username;
    }
    
    public String RoolasticUser.getPassword() {
        return this.password;
    }
    
    public void RoolasticUser.setPassword(String password) {
        this.password = password;
    }
    
    public void RoolasticUser.setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
    
}
