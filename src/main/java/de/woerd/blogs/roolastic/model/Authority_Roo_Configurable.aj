package de.woerd.blogs.roolastic.model;

import org.springframework.beans.factory.annotation.Configurable;

privileged aspect Authority_Roo_Configurable {
    
    declare @type: Authority: @Configurable;
    
}
