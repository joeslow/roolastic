package de.woerd.blogs.roolastic.model;

import org.springframework.beans.factory.annotation.Configurable;

privileged aspect RoolasticUser_Roo_Configurable {
    
    declare @type: RoolasticUser: @Configurable;
    
}
