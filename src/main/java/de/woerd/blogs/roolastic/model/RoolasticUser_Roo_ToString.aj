package de.woerd.blogs.roolastic.model;

import java.lang.String;

privileged aspect RoolasticUser_Roo_ToString {
    
    public String RoolasticUser.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AccountNonExpired: ").append(isAccountNonExpired()).append(", ");
        sb.append("AccountNonLocked: ").append(isAccountNonLocked()).append(", ");
        sb.append("CredentialsNonExpired: ").append(isCredentialsNonExpired()).append(", ");
        sb.append("Enabled: ").append(isEnabled()).append(", ");
        sb.append("Authorities: ").append(getAuthorities() == null ? "null" : getAuthorities().size()).append(", ");
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Version: ").append(getVersion()).append(", ");
        sb.append("Username: ").append(getUsername()).append(", ");
        sb.append("Password: ").append(getPassword());
        return sb.toString();
    }
    
}
