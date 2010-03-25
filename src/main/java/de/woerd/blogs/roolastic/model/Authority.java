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
public class Authority {
	
	public Authority(){}
	
	public Authority(String name) {
		this.name = name;
	}
	
    @NotNull
    @Size(min = 5, max = 20)
    private String name;
}
