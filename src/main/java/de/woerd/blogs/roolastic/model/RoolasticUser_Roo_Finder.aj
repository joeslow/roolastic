package de.woerd.blogs.roolastic.model;

import java.lang.String;
import java.lang.SuppressWarnings;
import javax.persistence.EntityManager;
import javax.persistence.Query;

privileged aspect RoolasticUser_Roo_Finder {
    
    @SuppressWarnings("unchecked")
    public static Query RoolasticUser.findRoolasticUsersByUsername(String username) {
        if (username == null || username.length() == 0) throw new IllegalArgumentException("The username argument is required");
        EntityManager em = RoolasticUser.entityManager();
        Query q = em.createQuery("SELECT RoolasticUser FROM RoolasticUser AS roolasticuser WHERE roolasticuser.username = :username");
        q.setParameter("username", username);
        return q;
    }
    
}
