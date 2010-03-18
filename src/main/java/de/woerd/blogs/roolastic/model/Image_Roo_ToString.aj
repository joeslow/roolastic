package de.woerd.blogs.roolastic.model;

import java.lang.String;

privileged aspect Image_Roo_ToString {
    
    public String Image.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Version: ").append(getVersion()).append(", ");
        sb.append("Title: ").append(getTitle()).append(", ");
        sb.append("Caption: ").append(getCaption()).append(", ");
        sb.append("File: ").append(getFile());
        return sb.toString();
    }
    
}
