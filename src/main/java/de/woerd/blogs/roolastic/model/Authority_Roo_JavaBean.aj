package de.woerd.blogs.roolastic.model;

import java.lang.String;

privileged aspect Authority_Roo_JavaBean {
    
    public String Authority.getName() {
        return this.name;
    }
    
    public void Authority.setName(String name) {
        this.name = name;
    }
    
}
