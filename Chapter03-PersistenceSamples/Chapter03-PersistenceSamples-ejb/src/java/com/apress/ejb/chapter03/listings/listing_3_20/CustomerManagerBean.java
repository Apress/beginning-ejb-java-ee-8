package com.apress.ejb.chapter03.listings.listing_3_20;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CustomerManagerBean {

  @PersistenceContext(unitName = "Chapter03PersistenceUnit")
  private EntityManager em;

  /**
   * Perform a bulk delete of fulfilled CustomerOrder items
   */
  public int bulkDeleteFulfilledOrders() {
    return em.createQuery("delete from CustomerOrder o where o.status = 'FULFILLED'").executeUpdate();
  }
  
  // ...
}
