package com.apress.ejb.chapter07.ejb;

import com.apress.ejb.chapter06.services.client.CreditCheckEndpointBean;
import com.apress.ejb.chapter06.services.client.CreditService;
import com.apress.ejb.chapter07.entities.CartItem;
import com.apress.ejb.chapter07.entities.Customer;
import com.apress.ejb.chapter07.entities.CustomerOrder;
import com.apress.ejb.chapter07.entities.Distributor;
import com.apress.ejb.chapter07.entities.Individual;
import com.apress.ejb.chapter07.entities.OrderItem;
import com.apress.ejb.chapter07.entities.Wine;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.ws.WebServiceRef;

@Stateless(name = "OrderProcessFacade", mappedName = "OrderProcessFacade")
public class OrderProcessFacadeBean {
  @PersistenceContext(unitName = "Chapter07-WineAppUnit-JTA")
  private EntityManager em;
  @Resource(mappedName = "poTopicConnectionFactory")
  private TopicConnectionFactory poTopicCF;
  @Resource(mappedName = "PurchaseOrderTopic")
  private Topic poTopic;
  @WebServiceRef(type = CreditService.class)
  CreditService service;

  public Object mergeEntity(Object entity) {
    return em.merge(entity);
  }

  public Object persistEntity(Object entity) {
    em.persist(entity);
    return entity;
  }

  public void createNewOrder(CustomerOrder newOrder) {
    persistEntity(newOrder);
  }

  private boolean performCreditCheck(Individual individual) {
    String ccnum = individual.getCcNum().toString();
    CreditCheckEndpointBean creditService = service.getCreditCheckEndpointBeanPort();
    return creditService.creditCheck(ccnum);
  }

  public String processOrder(Customer customer) {
    String processStatus = null;
    if (!em.contains(customer)) {
      customer = em.merge(customer);
    }

    if (customer instanceof Individual) {
      if (!performCreditCheck((Individual) customer)) {
        processStatus = "Invalid Credit Card number or credit check failed";
      }
    } else if (customer instanceof Distributor) {
      if (!"APPROVED".equals(((Distributor) customer).getMemberStatus())) {
        processStatus = "Distributor credit check rejected";
      }
    }

    if (processStatus == null) {
      CustomerOrder order = new CustomerOrder();
      order.setCreationDate(new Timestamp(System.currentTimeMillis()));
      em.persist(order);

      List<CartItem> cartItems = customer.getCartItemList();
      if (cartItems != null) {
        List<CartItem> tempCartItems = new ArrayList<CartItem>();
        for (CartItem cItem : cartItems) {
          OrderItem oItem = new OrderItem();
          int qty = cItem.getQuantity();
          oItem.setQuantity(qty);
          oItem.setOrderDate(new Timestamp(System.currentTimeMillis()));
          oItem.setWine(cItem.getWine());
          Wine tempWine = cItem.getWine();
          Float d = tempWine.getRetailPrice();
          Float price = d * cItem.getQuantity();
          oItem.setPrice(price);
          order.addOrderItem(oItem);
          tempCartItems.add(cItem);
        }
        for (CartItem cartItem : tempCartItems) {
          customer.removeCartItem(cartItem);
          em.remove(cartItem);
        }
      }
      customer.addCustomerOrder(order);

      PurchaseOrder po = new PurchaseOrder();
      po.setCustomer(customer);
      po.setCustomerOrder(order);
      
      sendPOtoMDB(po);
      processStatus = "Purchase Order sent for processing to the process queue";
    }
    return processStatus;
  }

  private void sendPOtoMDB(PurchaseOrder po) {
    //send PO to MDB now
    Connection connection = null;
    Session session = null;
    try {
      connection = poTopicCF.createConnection();
      connection.start();
      session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
      MessageProducer producer = session.createProducer(poTopic);
      ObjectMessage objMessage = session.createObjectMessage();
      objMessage.setObject(po);
      producer.send(objMessage);
    } catch (JMSException e) {
      e.printStackTrace();
    } finally {
      if (session != null) {
        try {
          session.close();
        } catch (JMSException ex) {
          Logger.getLogger(OrderProcessFacadeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      if (connection != null) {
        try {
          connection.close();
        } catch (JMSException ex) {
          Logger.getLogger(OrderProcessFacadeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
  }
}
