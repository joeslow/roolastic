package de.woerd.blogs.roolastic.model;

import java.lang.String;

privileged aspect Authority_Roo_ToString {
    
    public String Authority.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Version: ").append(getVersion()).append(", ");
        sb.append("Name: ").append(getName());
        return sb.toString();
    }
    
}
