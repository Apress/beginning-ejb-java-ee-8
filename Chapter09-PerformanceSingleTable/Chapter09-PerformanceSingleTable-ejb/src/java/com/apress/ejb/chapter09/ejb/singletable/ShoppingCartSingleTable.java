package com.apress.ejb.chapter09.ejb.singletable;

import com.apress.ejb.chapter09.singletable.Wine;
import com.apress.ejb.chapter09.singletable.CustomerOrder;
import com.apress.ejb.chapter09.singletable.Individual;
import com.apress.ejb.chapter09.singletable.OrderItem;
import com.apress.ejb.chapter09.singletable.InventoryItem;
import com.apress.ejb.chapter09.singletable.Address;
import com.apress.ejb.chapter09.singletable.CartItem;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateful;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful
public class ShoppingCartSingleTable {
  @PersistenceContext(unitName = "Chapter09-SingleTable-JTA")
  private EntityManager em;
  private Individual customer;

  public void addWineItem(Wine wine, Integer quantity) {
    CartItem cartItem = new CartItem();
    cartItem.setQuantity(quantity);
    wine = em.find(Wine.class, wine.getId());
    cartItem.setWine(wine);
    cartItem.setCreatedDate(new Timestamp(System.currentTimeMillis()));

    if (em.contains(getCustomer())) {
      customer.addCartItem(cartItem);
    } else {
      customer = em.merge(customer);
      customer.addCartItem(cartItem);
    }

    customer = em.merge(customer);
  }

  public void addWineToCart(Object wineId, Integer quantity) {
    getCustomer().addCartItem(new CartItem(quantity, em.find(Wine.class, wineId)));
    customer = em.merge(customer);
  }

  public void removeItemFromCart(CartItem cartItem) {
    getCustomer().removeCartItem(cartItem);
    customer = em.merge(customer);
  }

  public String ProcessOrder() {
    Individual customer = getCustomer();
    CustomerOrder order = new CustomerOrder();
    order.setCreationDate(new Timestamp(System.currentTimeMillis()));
    customer.addCustomerOrder(order);

    try {
      List<CartItem> cartList = customer.getCartItemList();
      final List<CartItem> cartItems = new ArrayList(cartList);
      for (CartItem cItem : cartItems) {
        Wine wine = cItem.getWine();
        Float p = wine.getRetailPrice();
        Integer qty = cItem.getQuantity();
        Float price = p * qty;

        OrderItem oItem = new OrderItem();
        oItem.setOrderDate(new Timestamp(System.currentTimeMillis()));
        oItem.setWine(wine);
        oItem.setQuantity(qty);
        oItem.setPrice(price);

        order.addOrderItem(oItem);

        deductInventory(wine, qty);
        customer.removeCartItem(cItem);
      }
      customer = em.merge(customer);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return "success";
  }

  private void deductInventory(Wine tempWine, Integer deductQty) {
    InventoryItem iItem = findInventoryItemByWine(tempWine);

    Integer newQty = iItem.getQuantity() - deductQty;
    iItem.setQuantity(newQty);
    em.merge(iItem);
  }

  private Individual getCustomer() {
    if (customer == null) {
      /*
       * Create a customer that will be used for each stateful bean session
       */
      customer = new Individual();
      Address address = new Address();
      address.setStreet1("200" + "opy" + " Avenue " + "opy");
      address.setCity("San Mateo");
      address.setState("CA");
      address.setZipCode("94065");

      customer.setDefaultBillingAddress(address);
      customer.setCcExpDate("08/2010");
      customer.setCcNum("123456789");

      customer.setEmail("userNN@wineapp.com");
      customer.setFirstName("firstname");
      customer.setLastName("lastname");
      customer.setPhone("18008008000");
      customer.setDefaultShippingAddress(address);

      em.persist(customer);
    }
    return customer;
  }

  /**
   * <
   * code>select object(o) from Wine o</code>
   */
  public List<Wine> findAllWine() {
    return em.createNamedQuery("Wine.findAll").getResultList();
  }

  public void setCustomer(Individual customer) {
    this.customer = customer;
  }

  public InventoryItem findInventoryItemByWine(Wine wine) {
    return em.createNamedQuery("InventoryItem.findItemByWine", InventoryItem.class).setParameter("wine", wine).getSingleResult();
  }
}