package com.apress.ejb.chapter07.ejb;

import com.apress.ejb.chapter07.entities.Customer;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name = "CustomerFacade", mappedName = "Chapter07-IntegratedSamples-Chapter07-ServiceIntegration-ejb-CustomerFacade")
public class CustomerFacadeBean implements CustomerFacadeLocal {
  @PersistenceContext(unitName = "Chapter07-WineAppUnit-JTA")
  private EntityManager em;

  public CustomerFacadeBean() {
  }

  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
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
   * <
   * code>select o from Customer o</code>
   */
  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
  public List<Customer> getCustomerFindAll() {
    return em.createNamedQuery("Customer.findAll", Customer.class).getResultList();
  }

  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
  public Customer getCustomerFindById(BigDecimal id) {
    return em.find(Customer.class, id);
  }

  /**
   * <
   * code>select o from Customer o where o.email = :email</code>
   */
  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
  public Customer getCustomerFindByEmail(String email) {
    try {
      return em.createNamedQuery("Customer.findByEmail", Customer.class).setParameter("email", email).getSingleResult();      
    }
    catch(NoResultException e) {
      return null;
    }
  }

  public Customer registerCustomer(Customer customer) {
    return persistEntity(customer);
  }
}
