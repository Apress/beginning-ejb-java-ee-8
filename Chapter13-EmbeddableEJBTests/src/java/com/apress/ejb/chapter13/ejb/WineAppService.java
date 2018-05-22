package com.apress.ejb.chapter13.ejb;

import com.apress.ejb.chapter07.entities.Address;
import com.apress.ejb.chapter07.entities.CartItem;
import com.apress.ejb.chapter07.entities.Customer;
import com.apress.ejb.chapter07.entities.CustomerOrder;
import com.apress.ejb.chapter07.entities.Individual;
import com.apress.ejb.chapter07.entities.OrderItem;
import com.apress.ejb.chapter07.entities.Wine;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;

@Stateful
@LocalBean
public class WineAppService {
  @EJB
  private IndividualFacade indFacade;
  @EJB
  private CustomerFacade custFacade;
  @EJB
  private CustomerOrderFacade custOrdFacade;

  public Individual createIndividual(String firstName, String lastName, String email) {
    final Address a = new Address("San Mateo", "CA", "425 Sonora Dr", null, "94402");
    final Individual i = new Individual(firstName, lastName, "510-555-1212", email, a, a, "12345", "12345");
    indFacade.persist(i);

    return i;
  }

  public CustomerOrder createCustomerOrder(Customer customer) {
    //  Create a new CustomerOrder from the customer's cart items
    CustomerOrder customerOrder = new CustomerOrder();
    for (CartItem cartItem : customer.getCartItemList()) {
      Wine wine = cartItem.getWine();
      //  Add 10% discount for a wine when buying 10 or more bottles
      Float discount = cartItem.getQuantity() >= 10 ? wine.getRetailPrice() / 10 : 0f;
      Float discountPrice = wine.getRetailPrice() - discount;
      Date date = new Date(System.currentTimeMillis());
      OrderItem orderItem =
          new OrderItem(cartItem.getQuantity(), wine, date,
                        discountPrice, null, null, customerOrder);
      customerOrder.addOrderItem(orderItem);
    }
    customer.addCustomerOrder(customerOrder);
    custFacade.merge(customer);

    return customerOrder;
  }

  public Customer findCustomerByEmail(String email) {
    return custFacade.findCustomerByEmail(email);
  }
}
