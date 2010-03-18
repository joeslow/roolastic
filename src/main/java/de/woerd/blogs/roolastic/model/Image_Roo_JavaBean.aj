package de.woerd.blogs.roolastic.model;

import java.lang.String;
import org.springframework.web.multipart.MultipartFile;

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
    
    public String Image.getPhotographer() {
        return this.photographer;
    }
    
    public void Image.setPhotographer(String photographer) {
        this.photographer = photographer;
    }
    
    public MultipartFile Image.getUpload() {
        return this.upload;
    }
    
    public void Image.setUpload(MultipartFile upload) {
        this.upload = upload;
    }
    
}
