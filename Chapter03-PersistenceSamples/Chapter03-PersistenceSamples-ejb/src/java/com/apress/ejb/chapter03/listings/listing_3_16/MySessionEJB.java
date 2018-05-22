package com.apress.ejb.chapter03.listings.listing_3_16;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MySessionEJB {

  @PersistenceContext(unitName = "Chapter03PersistenceUnit")
  private EntityManager em;

  public void persistEntity(Object entity) {
    em.persist(entity);
  }
}
