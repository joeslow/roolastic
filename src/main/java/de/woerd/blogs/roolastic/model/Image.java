package de.woerd.blogs.roolastic.model;

import javax.persistence.Entity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@RooJavaBean
@RooToString
@RooEntity
public class Image {

    @NotNull
    @Size(min = 3)
    private String title;

    @Size(max = 500)
    private String caption;
    
    @NotNull
    @Size(max = 255)
    private String file;
}
