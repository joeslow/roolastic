package de.woerd.blogs.roolastic.model;

import de.woerd.blogs.roolastic.model.Image;
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

privileged aspect Image_Roo_Entity {
    
    @PersistenceContext
    transient EntityManager Image.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Image.id;
    
    @Version
    @Column(name = "version")
    private Integer Image.version;
    
    public Long Image.getId() {
        return this.id;
    }
    
    public void Image.setId(Long id) {
        this.id = id;
    }
    
    public Integer Image.getVersion() {
        return this.version;
    }
    
    public void Image.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void Image.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Image.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Image attached = this.entityManager.find(Image.class, this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Image.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Image.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Image merged = this.entityManager.merge(this);
        this.entityManager.flush();
        this.id = merged.getId();
    }
    
    public static final EntityManager Image.entityManager() {
        EntityManager em = new Image().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Image.countImages() {
        return (Long) entityManager().createQuery("select count(o) from Image o").getSingleResult();
    }
    
    public static List<Image> Image.findAllImages() {
        return entityManager().createQuery("select o from Image o").getResultList();
    }
    
    public static Image Image.findImage(Long id) {
        if (id == null) return null;
        return entityManager().find(Image.class, id);
    }
    
    public static List<Image> Image.findImageEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from Image o").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
