package com.apress.ejb.chapter04.mappedsuperclass.service;

import com.apress.ejb.chapter04.mappedsuperclass.Address;
import com.apress.ejb.chapter04.mappedsuperclass.Employee;
import com.apress.ejb.chapter04.mappedsuperclass.FullTimeEmployee;
import com.apress.ejb.chapter04.mappedsuperclass.PartTimeEmployee;
import com.apress.ejb.chapter04.mappedsuperclass.Person;
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
    final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04-PersistenceIISamples-MappedSuperclass");
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

  public void removeEmployee(Employee employee) {
    employee = em.find(Employee.class, employee.getId());
    em.remove(employee);
    commitTransaction();
  }

  /**
   * <
   * code>select o from Employee o</code>
   */
  public List<Employee> getEmployeeFindAll() {
    return em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
  }

  public void removeFullTimeEmployee(FullTimeEmployee fullTimeEmployee) {
    fullTimeEmployee = em.find(FullTimeEmployee.class, fullTimeEmployee.getId());
    em.remove(fullTimeEmployee);
    commitTransaction();
  }

  /**
   * <
   * code>select o from FullTimeEmployee o</code>
   */
  public List<FullTimeEmployee> getFullTimeEmployeeFindAll() {
    return em.createNamedQuery("FullTimeEmployee.findAll", FullTimeEmployee.class).getResultList();
  }

  public void removePartTimeEmployee(PartTimeEmployee partTimeEmployee) {
    partTimeEmployee = em.find(PartTimeEmployee.class, partTimeEmployee.getId());
    em.remove(partTimeEmployee);
    commitTransaction();
  }

  /**
   * <
   * code>select o from PartTimeEmployee o</code>
   */
  public List<PartTimeEmployee> getPartTimeEmployeeFindAll() {
    return em.createNamedQuery("PartTimeEmployee.findAll", PartTimeEmployee.class).getResultList();
  }

  public void removeAddress(Address address) {
    address = em.find(Address.class, address.getId());
    em.remove(address);
    commitTransaction();
  }

  /**
   * <
   * code>select o from Address o</code>
   */
  public List<Address> getAddressFindAll() {
    return em.createNamedQuery("Address.findAll", Address.class).getResultList();
  }
}
