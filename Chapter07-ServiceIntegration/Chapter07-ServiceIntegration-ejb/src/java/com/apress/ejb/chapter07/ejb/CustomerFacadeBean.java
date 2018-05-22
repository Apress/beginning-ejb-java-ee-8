package com.apress.ejb.chapter07.ejb;

import com.apress.ejb.chapter07.entities.Customer;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name = "CustomerFacade", mappedName = "Chapter07-IntegratedSamples-Chapter07-ServiceIntegration-ejb-CustomerFacade")
public class CustomerFacadeBean {
  @PersistenceContext(unitName = "Chapter07-WineAppUnit-JTA")
  private EntityManager em;

  public <T> T persistEntity(T entity) {
    em.persist(entity);
    return entity;
  }

  public <T> T mergeEntity(T entity) {
    return em.merge(entity);
  }

  public void removeCustomer(Customer customer) {
    customer = em.find(Customer.class, customer.getId());
    em.remove(customer);
  }

  /**
   * <code>select o from Customer o</code>
   */
  public List<Customer> getCustomerFindAll() {
    return em.createNamedQuery("Customer.findAll", Customer.class).getResultList();
  }

  public Customer getCustomerFindById(Integer id) {
    return em.find(Customer.class, id);
  }

  /**
   * <code>select o from Customer o where o.email = :email</code>
   */
  public Customer getCustomerFindByEmail(String email) {
    return em.createNamedQuery("Customer.findByEmail", Customer.class).setParameter("email", email).getSingleResult();
  }

  public Customer registerCustomer(Customer customer) {
    return persistEntity(customer);
  }
}
