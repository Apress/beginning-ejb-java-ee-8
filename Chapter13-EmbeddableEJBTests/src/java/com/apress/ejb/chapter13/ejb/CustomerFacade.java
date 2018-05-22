package com.apress.ejb.chapter13.ejb;

import com.apress.ejb.chapter07.entities.Customer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CustomerFacade extends AbstractFacade<Customer> {
  @PersistenceContext(unitName = "Chapter13-EmbeddableEJBTests-JTA")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public CustomerFacade() {
    super(Customer.class);
  }
  
  public Customer findCustomerByEmail(String email) {
    return em.createNamedQuery("Customer.findByEmail", Customer.class).setParameter("email", email).getSingleResult();
  }
  
}
