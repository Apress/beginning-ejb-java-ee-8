package com.apress.ejb.chapter03.listings.listing_3_19;

import com.apress.ejb.chapter03.entities.Customer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CustomerManagerBean {

  @PersistenceContext(unitName = "Chapter03PersistenceUnit")
  private EntityManager em;
  
  /** <code>select object(o) from Customer o</code> */
  public List<Customer> findAllCustomers() {
    return em.createQuery("select o from Customer o").getResultList();
  }
  
  // ...
}
