package com.apress.ejb.chapter08.ejb;

import com.apress.ejb.chapter07.entities.CartItem;
import com.apress.ejb.chapter07.entities.Customer;
import com.apress.ejb.chapter07.entities.CustomerOrder;
import com.apress.ejb.chapter07.entities.InventoryItem;
import com.apress.ejb.chapter07.entities.OrderItem;
import com.apress.ejb.chapter07.entities.Wine;
import com.apress.ejb.chapter07.entities.test.PopulateDemoData;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

@Stateless(name = "OrderProcessorCMT", mappedName = "Chapter08-TransactionSamples-OrderProcessorCMT")
public class OrderProcessorCMTBean {
  @Resource
  SessionContext sessionContext;
  @PersistenceContext(unitName = "Chapter08-TransactionSamples-JTA")
  private EntityManager em;

  /**
   * Remove any existing Customers with email 'wineapp@yahoo.com' and any existing Wine with
   * country 'United States'.  The EJB container will ensure that this work is performed in
   * a transactional context.
   */
  public String initialize() {
    StringBuffer strBuf = new StringBuffer();
    strBuf.append("Removed ");
    int i = 0;

    //  Filter the data by removing any existing Customers with email
    //  'wineapp@yahoo.com' (or whatever is defined in the user.properties file).
    for (Customer customer :
         getCustomerFindByEmail(PopulateDemoData.TO_EMAIL_ADDRESS)) {
      em.remove(customer);
      i++;
    }
    strBuf.append(i);
    strBuf.append(" Customer(s) and ");

    //  Remove any existing Wine with country 'United States'
    i = 0;
    for (Wine wine : getWineFindByCountry("United States")) {
      em.remove(wine);
      i++;
    }
    strBuf.append(i);
    strBuf.append(" Wine(s)");
    return strBuf.toString();
  }

  /**
   * Create a new CustomerOrder from the items in a Customer's cart. Creates a new CustomerOrder
   * entity, and then creates a new OrderItem entity for each CartItem found in the Customer's cart.
   *
   * Using CMT w/ the default REQUIRED xaction attribute, if this method is invoked without a
   * transaction context, a new transaction will be created by the EJB container upon invoking the
   * method, and committed upon successfully completing the method.
   *
   * @return a status message (plain text)
   */
  public CustomerOrder createCustomerOrder(Customer customer) {
    return createCustomerOrderUsingSupports(customer);
  }

  @TransactionAttribute(TransactionAttributeType.SUPPORTS)
  public CustomerOrder createCustomerOrderUsingSupports(Customer customer) {
    if (customer == null) {
      throw new IllegalArgumentException("OrderProcessingBean.createCustomerOrder():  Customer not specified");
    }

    if (!em.contains(customer)) {
      customer = em.merge(customer);
    }

    final CustomerOrder customerOrder = new CustomerOrder();
    customer.addCustomerOrder(customerOrder);

    final Timestamp orderDate = new Timestamp(System.currentTimeMillis());
    final List<CartItem> cartItemList =
            new ArrayList(customer.getCartItemList());
    for (CartItem cartItem : cartItemList) {
      //  Create a new OrderItem for this CartItem
      final OrderItem orderItem = new OrderItem();
      orderItem.setOrderDate(orderDate);
      orderItem.setPrice(cartItem.getWine().getRetailPrice());
      orderItem.setQuantity(cartItem.getQuantity());
      orderItem.setStatus("Order Created");
      orderItem.setWine(cartItem.getWine());
      customerOrder.addOrderItem(orderItem);

      //  Remove the CartItem
      customer.removeCartItem(cartItem);
    }

    return persistEntity(customerOrder);
  }

  public <T> T persistEntity(T entity) {
    em.persist(entity);
    return entity;
  }

  public <T> T mergeEntity(T entity) {
    return em.merge(entity);
  }

  public <T> void removeEntity(T entity) {
    em.remove(em.merge(entity));
  }

  public <T> List<T> findAll(Class<T> entityClass) {
    CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
    cq.select(cq.from(entityClass));
    return em.createQuery(cq).getResultList();
  }

  public <T> List<T> findAllByRange(Class<T> entityClass, int[] range) {
    CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
    cq.select(cq.from(entityClass));
    Query q = em.createQuery(cq);
    q.setMaxResults(range[1] - range[0]);
    q.setFirstResult(range[0]);
    return q.getResultList();
  }

  /**
   * <code>select o from Customer o where o.email = :email</code>
   */
  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
  public List<Customer> getCustomerFindByEmail(String email) {
    return em.createNamedQuery("Customer.findByEmail", Customer.class).setParameter("email", email).getResultList();
  }

  /**
   * <code>select object(wine) from Wine wine where wine.country = :country</code>
   */
  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
  public List<Wine> getWineFindByCountry(String country) {
    return em.createNamedQuery("Wine.findByCountry", Wine.class).setParameter("country", country).getResultList();
  }
}
