package de.woerd.blogs.roolastic.model;

import java.lang.String;

privileged aspect Image_Roo_JavaBean {
    
    public String Image.getTitle() {
        return this.title;
    }
    
    public void Image.setTitle(String title) {
        this.title = title;
    }
    
    public String Image.getCaption() {
        return this.caption;
    }
    
    public void Image.setCaption(String caption) {
        this.caption = caption;
    }
    
    public String Image.getFile() {
        return this.file;
    }
    
    public void Image.setFile(String file) {
        this.file = file;
    }
    
}
