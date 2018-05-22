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
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.AroundInvoke;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.Interceptors;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@Stateful(name = "OrderProcessorBMT", mappedName = "Chapter08-TransactionSamples-OrderProcessorBMT")
@TransactionManagement(TransactionManagementType.BEAN)
@Interceptors(OrderProcessorBMTBeanTxnInterceptor.class)
public class OrderProcessorBMTBean {
  @Resource
  SessionContext sessionContext;
  @PersistenceContext(unitName = "Chapter08-TransactionSamples-JTA", type = PersistenceContextType.EXTENDED)
  private EntityManager em;

  /**
   * Remove any existing Customers with email 'wineapp@yahoo.com' and any
   * existing Wine with country 'United States'
   */
  public String initialize() throws HeuristicMixedException,
      HeuristicRollbackException,
      RollbackException,
      SystemException 
  {
    StringBuffer strBuf = new StringBuffer();
    strBuf.append("Removed ");
    int i = 0;

    //  Filter the data by removing any existing Customers with email
    //  'wineapp@yahoo.com' (or whatever is defined in the user.properties file).
    //  The first call to a transactional method on OrderProcessorBMT will begin a
    //  transaction.
    for (Customer customer
        : getCustomerFindByEmail(PopulateDemoData.TO_EMAIL_ADDRESS)) {
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

    //  Apply these changes, committing the entity removal operations
    commitTransaction();

    return strBuf.toString();
  }

  /**
   * Create a new CustomerOrder from the items in a Customer's cart. Creates a
   * new CustomerOrder entity, and then creates a new OrderItem entity for each
   * CartItem found in the Customer's cart.
   *
   * Using CMT w/ the default Required xaction attribute, if this method is
   * invoked without a transaction context, a new transaction will be created by
   * the EJB container upon invoking the method, and committed upon successfully
   * completing the method.
   *
   * @return a status message (plain text)
   */
  public CustomerOrder createCustomerOrder(Customer customer) throws Exception {
    if (customer == null) {
      throw new IllegalArgumentException("OrderProcessingBean.createCustomerOrder():  Customer not specified");
    }

    //  Ensure we are working with a managed Customer object
    customer = em.find(Customer.class, customer.getId());

    CustomerOrder customerOrder = new CustomerOrder();
    customer.addCustomerOrder(customerOrder);
    final Timestamp orderDate = new Timestamp(System.currentTimeMillis());
    //  Clone the CartItem list so we remove the CartItem entries from the Customer
    //  without causing a ConcurrentModificationException on the iterator.
    final List<CartItem> cartItemList = new ArrayList(customer.getCartItemList());
    for (CartItem cartItem : cartItemList) {
      //  Create a new OrderItem for this CartItem
      final OrderItem orderItem = new OrderItem();
      orderItem.setOrderDate(orderDate);
      orderItem.setPrice(cartItem.getWine().getRetailPrice());
      orderItem.setQuantity(cartItem.getQuantity());
      orderItem.setStatus("Order Created");
      orderItem.setWine(cartItem.getWine());
      customerOrder.addOrderItem(orderItem);

      //  Remove the CartItem.  Note that the 'orphanRemoval' flag will ensure
      //  that the cartItem is removed from the database once it is disassociated
      //  from a customer.
      customer.removeCartItem(cartItem);
    }

    //  The Cascade rules on Customer will cause the CustomerOrder to be
    //  persisted when the Customer is merged
    em.merge(customer);

    return customerOrder;
  }

  @ExcludeClassInterceptors
  public void commitTransaction() throws HeuristicMixedException, HeuristicRollbackException, RollbackException, SystemException {
    final UserTransaction txn = sessionContext.getUserTransaction();
    if (txn.getStatus() == Status.STATUS_ACTIVE) {
      txn.commit();
    }
  }

  @ExcludeClassInterceptors
  public void rollbackTransaction() throws SystemException {
    final UserTransaction txn = sessionContext.getUserTransaction();
    if (txn.getStatus() == Status.STATUS_ACTIVE) {
      txn.rollback();
    }
  }

  @ExcludeClassInterceptors
  public boolean isTransactionDirty() throws SystemException {
    final UserTransaction txn = sessionContext.getUserTransaction();
    return Boolean.valueOf(txn.getStatus() == Status.STATUS_ACTIVE);
  }

  @ExcludeClassInterceptors
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

  public <T> void removeEntity(T entity) {
    em.remove(em.merge(entity));
  }

  @ExcludeClassInterceptors
  public <T> List<T> findAll(Class<T> entityClass) {
    CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
    cq.select(cq.from(entityClass));
    return em.createQuery(cq).getResultList();
  }

  @ExcludeClassInterceptors
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
  @ExcludeClassInterceptors
  public List<Customer> getCustomerFindByEmail(String email) {
    return em.createNamedQuery("Customer.findByEmail", Customer.class).setParameter("email", email).getResultList();
  }

  /**
   * <code>select object(wine) from Wine wine where wine.year = :year</code>
   */
  @ExcludeClassInterceptors
  public List<Wine> getWineFindByYear(Integer year) {
    return em.createNamedQuery("Wine.findByYear", Wine.class).setParameter("year", year).getResultList();
  }

  /**
   * <code>select object(wine) from Wine wine where wine.country = :country</code>
   */
  @ExcludeClassInterceptors
  public List<Wine> getWineFindByCountry(String country) {
    return em.createNamedQuery("Wine.findByCountry", Wine.class).setParameter("country", country).getResultList();
  }

  /**
   * <code>select object(wine) from Wine wine where wine.varietal = :varietal</code>
   */
  @ExcludeClassInterceptors
  public List<Wine> getWineFindByVarietal(String varietal) {
    return em.createNamedQuery("Wine.findByVarietal", Wine.class).setParameter("varietal", varietal).getResultList();
  }

  /**
   * <code>select o from InventoryItem o where o.wine = :wine</code>
   */
  @ExcludeClassInterceptors
  public List<InventoryItem> getInventoryItemFindItemByWine(Object wine) {
    return em.createNamedQuery("InventoryItem.findItemByWine", InventoryItem.class).setParameter("wine", wine).getResultList();
  }
}

class OrderProcessorBMTBeanTxnInterceptor {
  public OrderProcessorBMTBeanTxnInterceptor() {
  }

  @AroundInvoke
  Object beginTrans(InvocationContext invocationContext) throws Exception {
    final OrderProcessorBMTBean orderProcessorBMTBean = (OrderProcessorBMTBean) invocationContext.getTarget();
    final UserTransaction txn = orderProcessorBMTBean.sessionContext.getUserTransaction();
    if (txn.getStatus() == Status.STATUS_NO_TRANSACTION) {
      txn.begin();
    }
    return invocationContext.proceed();
  }
}
