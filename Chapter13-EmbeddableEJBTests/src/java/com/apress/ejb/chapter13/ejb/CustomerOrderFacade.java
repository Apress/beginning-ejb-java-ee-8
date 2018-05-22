package com.apress.ejb.chapter13.ejb;

import com.apress.ejb.chapter07.entities.CustomerOrder;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CustomerOrderFacade extends AbstractFacade<CustomerOrder> {
  @PersistenceContext(unitName = "Chapter13-EmbeddableEJBTests-JTA")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public CustomerOrderFacade() {
    super(CustomerOrder.class);
  }
}
