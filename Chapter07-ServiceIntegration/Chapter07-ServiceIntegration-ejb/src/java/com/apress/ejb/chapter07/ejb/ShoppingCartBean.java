package com.apress.ejb.chapter07.ejb;

import com.apress.ejb.chapter07.entities.CartItem;
import com.apress.ejb.chapter07.entities.Customer;
import com.apress.ejb.chapter07.entities.Wine;
import java.sql.Timestamp;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Stateful(name = "ShoppingCart", mappedName = "Chapter07-IntegratedSamples-Chapter07-ServiceIntegration-ejb-ShoppingCart")
public class ShoppingCartBean implements ShoppingCartLocal {
  @PersistenceContext(unitName = "Chapter07-WineAppUnit-JTA", type = PersistenceContextType.EXTENDED)
  private EntityManager em;
  private Customer customer;
  @EJB
  private CustomerFacadeBean customerFacade;
  @EJB
  private OrderProcessFacadeBean orderProcessFacade;

  public Customer getCustomer() {
    return customer;
  }

  public void addWineItem(Wine wine, int quantity) {
    CartItem cartItem = new CartItem(quantity, wine);
    customer.addCartItem(cartItem);
  }

  public void addWineItem(Wine wine) {
    CartItem cartItem = new CartItem();
    cartItem.setQuantity(20);
    cartItem.setWine(wine);
    cartItem.setCreatedDate(new Timestamp(System.currentTimeMillis()));
    customer.addCartItem(cartItem);
  }

  public void removeWineItem(CartItem cartItem) {
    customer.removeCartItem(cartItem);
  }

  public void addCartItemsTemporarily() {
    List<Wine> wines = em.createNamedQuery("findAllWine").getResultList();
    for (Wine wine : wines) {
      final CartItem cartItem = new CartItem();
      cartItem.setCreatedDate(new Timestamp(System.currentTimeMillis()));
      cartItem.setQuantity(20);
      cartItem.setWine(wine);
      customer.addCartItem(cartItem);
    }
  }

  public Customer findCustomer(String email) {
    customer = customerFacade.getCustomerFindByEmail(email);
    return customer;
  }

  public String sendOrderToOPC() {
    String result = null;
    try {
      orderProcessFacade.processOrder(customer);
      result = "Your Order has been submitted - you will be notified about the status via email";
    } catch (Exception ex) {
      ex.printStackTrace();
      result = "An error occurred while processing your order.  Please contact Customer Service.";
    }

    return result;
  }

  public <T> T persistEntity(T entity) {
    em.persist(entity);
    return entity;
  }

  public <T> T mergeEntity(T entity) {
    return em.merge(entity);
  }

  public void removeCartItem(CartItem cartItem) {
    cartItem = em.find(CartItem.class, cartItem.getId());
    em.remove(cartItem);
  }

  public List<CartItem> getCartItems() {
    return customer.getCartItemList();
  }

  public void removeWine(Wine wine) {
    wine = em.find(Wine.class, wine.getId());
    em.remove(wine);
  }

  /**
   * <code>select object(o) from Wine o</code>
   */
  public List<Wine> getWineFindAll() {
    return em.createNamedQuery("Wine.findAll", Wine.class).getResultList();
  }

  /**
   * <code>select object(wine) from Wine wine where wine.year = :year</code>
   */
  public List<Wine> getWineFindByYear(Integer year) {
    return em.createNamedQuery("Wine.findByYear", Wine.class).setParameter("year", year).getResultList();
  }

  /**
   * <code>select object(wine) from Wine wine where wine.country = :country</code>
   */
  public List<Wine> getWineFindByCountry(String country) {
    return em.createNamedQuery("Wine.findByCountry", Wine.class).setParameter("country", country).getResultList();
  }

  /**
   * <code>select object(wine) from Wine wine where wine.varietal = :varietal</code>
   */
  public List<Wine> getWineFindByVarietal(String varietal) {
    return em.createNamedQuery("Wine.findByVarietal", Wine.class).setParameter("varietal", varietal).getResultList();
  }
}
