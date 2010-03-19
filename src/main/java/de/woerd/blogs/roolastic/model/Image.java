package de.woerd.blogs.roolastic.model;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Type;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.web.multipart.MultipartFile;

@Entity
@RooJavaBean
@RooToString
@RooEntity
@JsonIgnoreProperties({"upload", "ajc$interField$de_woerd_blogs_roolastic_model_Image_Roo_Entity$id", "ajc$interField$de_woerd_blogs_roolastic_model_Image_Roo_Entity$version"})
public class Image {

    @NotNull
    @Size(min = 3)
    private String title;

    @Size(max = 500)
    private String caption;

    @NotNull
    @Size(max = 255)
    private String file;

    private String photographer;

    @Transient
    private MultipartFile upload;
    
    @Type(type = "text")
    private String metadata;
}
