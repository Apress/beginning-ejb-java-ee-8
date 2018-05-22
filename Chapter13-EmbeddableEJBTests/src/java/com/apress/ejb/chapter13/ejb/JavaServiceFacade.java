package com.apress.ejb.chapter13.ejb;

import com.apress.ejb.chapter07.entities.Wine;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class JavaServiceFacade {
  private final EntityManagerFactory emf;
  private final EntityManager em;

  public JavaServiceFacade() {
    this("Chapter13-EmbeddableEJBTests-ResourceLocal");
  }

  public JavaServiceFacade(String persistenceUnit) {
    emf = Persistence.createEntityManagerFactory(persistenceUnit);
    em = emf.createEntityManager();
  }

  public void close() {
    if (em != null && em.isOpen()) {
      em.close();
    }
    if (emf != null && emf.isOpen()) {
      emf.close();
    }
  }

  /**
   * All changes that have been made to the managed entities in the persistence context are applied
   * to the database and committed.
   */
  private void commitTransaction() {
    final EntityTransaction entityTransaction = em.getTransaction();
    if (!entityTransaction.isActive()) {
      entityTransaction.begin();
    }
    entityTransaction.commit();
  }

  public <T> T persistEntity(T entity) {
    em.persist(entity);
    commitTransaction();
    return entity;
  }

  public <T> T mergeEntity(T entity) {
    entity = em.merge(entity);
    commitTransaction();
    return entity;
  }

  public <T> void removeEntity(T entity) {
    em.remove(em.merge(entity));
    commitTransaction();
  }

  public <T> List<T> findAll(Class<T> entityClass) {
    CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
    cq.select(cq.from(entityClass));
    return em.createQuery(cq).getResultList();
  }

  public <T> int getCount(Class<T> entityClass) {
    CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
    Root<T> rt = cq.from(entityClass);
    cq.select(em.getCriteriaBuilder().count(rt));
    javax.persistence.Query q = em.createQuery(cq);
    return ((Long) q.getSingleResult()).intValue();
  }

  /**
   * <code>select object(wine) from Wine wine where wine.year = :year</code>
   */
  public List<Wine> getWineFindByYear(int year) {
    return em.createNamedQuery("Wine.findByYear", Wine.class).setParameter("year", year).getResultList();
  }

  /**
   * <code>select object(wine) from Wine wine where wine.country = :country</code>
   */
  public List<Wine> getWineFindByCountry(String country) {
    return em.createNamedQuery("Wine.findByCountry", Wine.class).setParameter("country", country).getResultList();
  }

  /**
   * <code>select object(wine) from Wine wine where wine.varietal = :varietal</code>
   */
  public List<Wine> getWineFindByVarietal(String varietal) {
    return em.createNamedQuery("Wine.findByVarietal", Wine.class).setParameter("varietal", varietal).getResultList();
  }
}