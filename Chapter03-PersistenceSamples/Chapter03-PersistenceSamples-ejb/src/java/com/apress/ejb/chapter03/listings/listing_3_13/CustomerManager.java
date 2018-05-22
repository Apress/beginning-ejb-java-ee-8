package com.apress.ejb.chapter03.listings.listing_3_13;

import com.apress.ejb.chapter03.listings.listing_3_07.Customer;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name="Listing_3_13_CustomerManager")
public class CustomerManager implements Serializable {

  @PersistenceContext(unitName="Chapter03PersistenceUnit")
  private EntityManager em;

  public void createCustomer() {
    final Customer cust = new Customer();
    cust.setName("Moneybags MgGee");
    em.persist(cust);
  }
}
