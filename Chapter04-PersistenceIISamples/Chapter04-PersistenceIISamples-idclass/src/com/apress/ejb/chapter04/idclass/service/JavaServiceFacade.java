package com.apress.ejb.chapter04.idclass.service;

import com.apress.ejb.chapter04.idclass.Address;
import com.apress.ejb.chapter04.idclass.MyIdClass;
import com.apress.ejb.chapter04.idclass.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JavaServiceFacade {
  private final EntityManager em;

  public JavaServiceFacade() {
    //  To support an non-JavaEE environment, we avoid injection and create an EntityManagerFactory
    //  for the desired persistence unit.  From this factory we then create the EntityManager.
    final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04-PersistenceIISamples-IdClass");
    em = emf.createEntityManager();
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

  public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
    Query query = em.createQuery(jpqlStmt);
    if (firstResult > 0) {
      query = query.setFirstResult(firstResult);
    }
    if (maxResults > 0) {
      query = query.setMaxResults(maxResults);
    }
    return query.getResultList();
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

  public void removePerson(Person person) {
    person = em.find(Person.class, new MyIdClass(person.getFirstName(), person.getLastName()));
    em.remove(person);
    commitTransaction();
  }

  /**
   * <code>select o from Person o</code>
   */
  public List<Person> getPersonFindAll() {
    return em.createNamedQuery("Person.findAll", Person.class).getResultList();
  }
}
