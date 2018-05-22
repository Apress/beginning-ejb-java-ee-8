package com.apress.ejb.chapter03.ejb;

import com.apress.ejb.chapter03.entities.Address;
import com.apress.ejb.chapter03.entities.Customer;
import com.apress.ejb.chapter03.entities.CustomerOrder;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CustomerOrderManager {

  @PersistenceContext(unitName = "Chapter03PersistenceUnit")
  private EntityManager em;

  public CustomerOrderManager() {
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

  /** <code>select o from Customer o</code> */
  public List<Customer> getCustomerFindAll() {
    return em.createNamedQuery("Customer.findAll", Customer.class).getResultList();
  }

  public void removeAddress(Address address) {
    address = em.find(Address.class, address.getId());
    em.remove(address);
  }

  /** <code>select o from Address o</code> */
  public List<Address> getAddressFindAll() {
    return em.createNamedQuery("Address.findAll", Address.class).getResultList();
  }

  public void removeCustomerOrder(CustomerOrder customerOrder) {
    customerOrder = em.find(CustomerOrder.class, customerOrder.getId());
    em.remove(customerOrder);
  }

  /** <code>select o from CustomerOrder o</code> */
  public List<CustomerOrder> getCustomerOrderFindAll() {
    return em
      .createNamedQuery("CustomerOrder.findAll", CustomerOrder.class)
      .getResultList();
  }

  /** <code>select o from CustomerOrder o where o.email = :email</code> */
  public List<CustomerOrder> getCustomerOrderFindByEmail(String email) {
    return em
      .createNamedQuery("CustomerOrder.findByEmail", CustomerOrder.class)
      .setParameter("email", email)
      .getResultList();
  }
}
