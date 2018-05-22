package com.apress.ejb.chapter07.entities.test;

import com.apress.ejb.chapter07.entities.Address;
import com.apress.ejb.chapter07.entities.BusinessContact;
import com.apress.ejb.chapter07.entities.CartItem;
import com.apress.ejb.chapter07.entities.Customer;
import com.apress.ejb.chapter07.entities.CustomerOrder;
import com.apress.ejb.chapter07.entities.Distributor;
import com.apress.ejb.chapter07.entities.Individual;
import com.apress.ejb.chapter07.entities.InventoryItem;
import com.apress.ejb.chapter07.entities.OrderItem;
import com.apress.ejb.chapter07.entities.Supplier;
import com.apress.ejb.chapter07.entities.Wine;
import com.apress.ejb.chapter07.entities.WineItem;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JavaServiceFacade {
  private final EntityManager em;

  public JavaServiceFacade() {
    this("Chapter07-WineAppUnit-ResourceLocal");
  }

  public JavaServiceFacade(String persistenceUnit) {
    final EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit);
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

  public void persistEntities(List entities) {
    if (entities != null) {
      for (Object ent : entities) {
        em.persist(ent);
      }
      commitTransaction();
    }
  }

  public <T> T mergeEntity(T entity) {
    entity = em.merge(entity);
    commitTransaction();
    return entity;
  }

  public void removeDistributor(Distributor distributor) {
    distributor = em.find(Distributor.class, distributor.getId());
    em.remove(distributor);
    commitTransaction();
  }

  /**
   * <
   * code>select o from Distributor o</code>
   */
  public List<Distributor> getDistributorFindAll() {
    return em.createNamedQuery("Distributor.findAll", Distributor.class).getResultList();
  }

  public void removeOrderItem(OrderItem orderItem) {
    orderItem = em.find(OrderItem.class, orderItem.getId());
    em.remove(orderItem);
    commitTransaction();
  }

  /**
   * <
   * code>select o from OrderItem o</code>
   */
  public List<OrderItem> getOrderItemFindAll() {
    return em.createNamedQuery("OrderItem.findAll", OrderItem.class).getResultList();
  }

  public void removeSupplier(Supplier supplier) {
    supplier = em.find(Supplier.class, supplier.getId());
    em.remove(supplier);
    commitTransaction();
  }

  /**
   * <
   * code>select o from Supplier o</code>
   */
  public List<Supplier> getSupplierFindAll() {
    return em.createNamedQuery("Supplier.findAll", Supplier.class).getResultList();
  }

  public void removeWineItem(WineItem wineItem) {
    wineItem = em.find(WineItem.class, wineItem.getId());
    em.remove(wineItem);
    commitTransaction();
  }

  /**
   * <
   * code>select o from WineItem o</code>
   */
  public List<WineItem> getWineItemFindAll() {
    return em.createNamedQuery("WineItem.findAll", WineItem.class).getResultList();
  }

  public void removeIndividual(Individual individual) {
    individual = em.find(Individual.class, individual.getId());
    em.remove(individual);
    commitTransaction();
  }

  /**
   * <
   * code>select o from Individual o</code>
   */
  public List<Individual> getIndividualFindAll() {
    return em.createNamedQuery("Individual.findAll", Individual.class).getResultList();
  }

  public void removeCustomer(Customer customer) {
    customer = em.find(Customer.class, customer.getId());
    em.remove(customer);
    commitTransaction();
  }

  /**
   * <
   * code>select o from Customer o</code>
   */
  public List<Customer> getCustomerFindAll() {
    return em.createNamedQuery("Customer.findAll", Customer.class).getResultList();
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

  public void removeInventoryItem(InventoryItem inventoryItem) {
    inventoryItem = em.find(InventoryItem.class, inventoryItem.getId());
    em.remove(inventoryItem);
    commitTransaction();
  }

  /**
   * <
   * code>select o from InventoryItem o</code>
   */
  public List<InventoryItem> getInventoryItemFindAll() {
    return em.createNamedQuery("InventoryItem.findAll", InventoryItem.class).getResultList();
  }

  public void removeCartItem(CartItem cartItem) {
    cartItem = em.find(CartItem.class, cartItem.getId());
    em.remove(cartItem);
    commitTransaction();
  }

  /**
   * <
   * code>select o from CartItem o</code>
   */
  public List<CartItem> getCartItemFindAll() {
    return em.createNamedQuery("CartItem.findAll", CartItem.class).getResultList();
  }

  public void removeWine(Wine wine) {
    wine = em.find(Wine.class, wine.getId());
    em.remove(wine);
    commitTransaction();
  }

  /**
   * <
   * code>select object(o) from Wine o</code>
   */
  public List<Wine> getWineFindAll() {
    return em.createNamedQuery("Wine.findAll", Wine.class).getResultList();
  }

  /**
   * <
   * code>select object(wine) from Wine wine where wine.year = :year</code>
   */
  public List<Wine> getWineFindByYear(int year) {
    return em.createNamedQuery("Wine.findByYear", Wine.class).setParameter("year", year).getResultList();
  }

  /**
   * <
   * code>select object(wine) from Wine wine where wine.country = :country</code>
   */
  public List<Wine> getWineFindByCountry(String country) {
    return em.createNamedQuery("Wine.findByCountry", Wine.class).setParameter("country", country).getResultList();
  }

  /**
   * <
   * code>select object(wine) from Wine wine where wine.varietal = :varietal</code>
   */
  public List<Wine> getWineFindByVarietal(String varietal) {
    return em.createNamedQuery("Wine.findByVarietal", Wine.class).setParameter("varietal", varietal).getResultList();
  }

  public void removeBusinessContact(BusinessContact businessContact) {
    businessContact = em.find(BusinessContact.class, businessContact.getId());
    em.remove(businessContact);
    commitTransaction();
  }

  /**
   * <
   * code>select o from BusinessContact o</code>
   */
  public List<BusinessContact> getBusinessContactFindAll() {
    return em.createNamedQuery("BusinessContact.findAll", BusinessContact.class).getResultList();
  }

  public void removeCustomerOrder(CustomerOrder customerOrder) {
    customerOrder = em.find(CustomerOrder.class, customerOrder.getId());
    em.remove(customerOrder);
    commitTransaction();
  }

  /**
   * <
   * code>select o from CustomerOrder o</code>
   */
  public List<CustomerOrder> getCustomerOrderFindAll() {
    return em.createNamedQuery("CustomerOrder.findAll", CustomerOrder.class).getResultList();
  }
}
