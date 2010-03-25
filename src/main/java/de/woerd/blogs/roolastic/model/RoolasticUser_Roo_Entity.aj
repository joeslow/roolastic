package de.woerd.blogs.roolastic.model;

import de.woerd.blogs.roolastic.model.RoolasticUser;
import java.lang.Integer;
import java.lang.Long;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;
import org.springframework.transaction.annotation.Transactional;

privileged aspect RoolasticUser_Roo_Entity {
    
    @PersistenceContext
    transient EntityManager RoolasticUser.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long RoolasticUser.id;
    
    @Version
    @Column(name = "version")
    private Integer RoolasticUser.version;
    
    public Long RoolasticUser.getId() {
        return this.id;
    }
    
    public void RoolasticUser.setId(Long id) {
        this.id = id;
    }
    
    public Integer RoolasticUser.getVersion() {
        return this.version;
    }
    
    public void RoolasticUser.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void RoolasticUser.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void RoolasticUser.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            RoolasticUser attached = this.entityManager.find(RoolasticUser.class, this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void RoolasticUser.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void RoolasticUser.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        RoolasticUser merged = this.entityManager.merge(this);
        this.entityManager.flush();
        this.id = merged.getId();
    }
    
    public static final EntityManager RoolasticUser.entityManager() {
        EntityManager em = new RoolasticUser().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long RoolasticUser.countRoolasticUsers() {
        return (Long) entityManager().createQuery("select count(o) from RoolasticUser o").getSingleResult();
    }
    
    public static List<RoolasticUser> RoolasticUser.findAllRoolasticUsers() {
        return entityManager().createQuery("select o from RoolasticUser o").getResultList();
    }
    
    public static RoolasticUser RoolasticUser.findRoolasticUser(Long id) {
        if (id == null) return null;
        return entityManager().find(RoolasticUser.class, id);
    }
    
    public static List<RoolasticUser> RoolasticUser.findRoolasticUserEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from RoolasticUser o").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
