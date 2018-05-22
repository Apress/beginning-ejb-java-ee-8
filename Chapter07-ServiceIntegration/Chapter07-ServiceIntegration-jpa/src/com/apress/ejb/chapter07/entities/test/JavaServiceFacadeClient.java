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

public class JavaServiceFacadeClient {
  public static void main(String[] args) {
    try {
      final JavaServiceFacade javaServiceFacade = new JavaServiceFacade();

      insertData(javaServiceFacade);

      for (Wine wine : (List<Wine>) javaServiceFacade.getWineFindByYear(2006)) {
        printWine(wine);
      }
      for (Wine wine : (List<Wine>) javaServiceFacade.getWineFindByCountry("Argentina")) {
        printWine(wine);
      }
      for (Wine wine : (List<Wine>) javaServiceFacade.getWineFindByVarietal("Malbec")) {
        printWine(wine);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private static void insertData(final JavaServiceFacade javaServiceFacade) {
    //  Creatable types
    Address a;
    CartItem ci;
    Customer c;
    CustomerOrder co;
    Distributor d;
    Individual i;
    InventoryItem ii;
    OrderItem oi;
    Supplier s;
    Wine w;

    a = new Address("San Mateo", "CA", "1301 Ashwood Ct", null, "94402");
    d = new Distributor("Brian", "Henson", "(650) 555-1212", "a@b", a, a, "Acme Plumbing", new Integer(1), "PREFERRED");
    i = new Individual("Andy", "Mockler", "(650) 555-1212", "a@b", a, a, "0914", "555544443333l");
    w = new Wine("Argentina", "Malbec", "Michel Torino", 91, "Mendoza", new Float(100), "Malbec", 2006);
    co = new CustomerOrder("PROCESSING", i);
    s = new Supplier("Parker", "Quillen", "(415) 999-0817", a);
    s.getWineList().add(w);
    w.getSupplierList().add(s);
    ci = new CartItem(10, w);
    i.addBillingAddress(a);
    i.addShippingAddress(a);
    d.addCartItem(ci);
    javaServiceFacade.persistEntity(d);
    javaServiceFacade.persistEntity(i);
    javaServiceFacade.persistEntity(s);
  }

  private static void printDistributor(Distributor distributor) {
    System.out.println("companyName = " + distributor.getCompanyName());
    System.out.println("discount = " + distributor.getDiscount());
    System.out.println("memberStatus = " + distributor.getMemberStatus());
    System.out.println("email = " + distributor.getEmail());
    System.out.println("cartItemList = " + distributor.getCartItemList());
    System.out.println("customerOrderList = " + distributor.getCustomerOrderList());
    System.out.println("billingAddressList = " + distributor.getBillingAddressList());
    System.out.println("shippingAddressList = " + distributor.getShippingAddressList());
    System.out.println("defaultShippingAddress = " + distributor.getDefaultShippingAddress());
    System.out.println("defaultBillingAddress = " + distributor.getDefaultBillingAddress());
    System.out.println("firstName = " + distributor.getFirstName());
    System.out.println("id = " + distributor.getId());
    System.out.println("lastName = " + distributor.getLastName());
    System.out.println("phone = " + distributor.getPhone());
    System.out.println("version = " + distributor.getVersion());
  }

  private static void printOrderItem(OrderItem orderitem) {
    System.out.println("orderDate = " + orderitem.getOrderDate());
    System.out.println("price = " + orderitem.getPrice());
    System.out.println("shipDate = " + orderitem.getShipDate());
    System.out.println("status = " + orderitem.getStatus());
    System.out.println("customerOrder = " + orderitem.getCustomerOrder());
    System.out.println("id = " + orderitem.getId());
    System.out.println("quantity = " + orderitem.getQuantity());
    System.out.println("version = " + orderitem.getVersion());
    System.out.println("wine = " + orderitem.getWine());
  }

  private static void printSupplier(Supplier supplier) {
    System.out.println("paymentAddress = " + supplier.getPaymentAddress());
    System.out.println("wineList = " + supplier.getWineList());
    System.out.println("firstName = " + supplier.getFirstName());
    System.out.println("id = " + supplier.getId());
    System.out.println("lastName = " + supplier.getLastName());
    System.out.println("phone = " + supplier.getPhone());
    System.out.println("version = " + supplier.getVersion());
  }

  private static void printWineItem(WineItem wineitem) {
    System.out.println("id = " + wineitem.getId());
    System.out.println("quantity = " + wineitem.getQuantity());
    System.out.println("version = " + wineitem.getVersion());
    System.out.println("wine = " + wineitem.getWine());
  }

  private static void printIndividual(Individual individual) {
    System.out.println("ccExpDate = " + individual.getCcExpDate());
    System.out.println("ccNum = " + individual.getCcNum());
    System.out.println("email = " + individual.getEmail());
    System.out.println("cartItemList = " + individual.getCartItemList());
    System.out.println("customerOrderList = " + individual.getCustomerOrderList());
    System.out.println("billingAddressList = " + individual.getBillingAddressList());
    System.out.println("shippingAddressList = " + individual.getShippingAddressList());
    System.out.println("defaultShippingAddress = " + individual.getDefaultShippingAddress());
    System.out.println("defaultBillingAddress = " + individual.getDefaultBillingAddress());
    System.out.println("firstName = " + individual.getFirstName());
    System.out.println("id = " + individual.getId());
    System.out.println("lastName = " + individual.getLastName());
    System.out.println("phone = " + individual.getPhone());
    System.out.println("version = " + individual.getVersion());
  }

  private static void printCustomer(Customer customer) {
    System.out.println("email = " + customer.getEmail());
    System.out.println("cartItemList = " + customer.getCartItemList());
    System.out.println("customerOrderList = " + customer.getCustomerOrderList());
    System.out.println("billingAddressList = " + customer.getBillingAddressList());
    System.out.println("shippingAddressList = " + customer.getShippingAddressList());
    System.out.println("defaultShippingAddress = " + customer.getDefaultShippingAddress());
    System.out.println("defaultBillingAddress = " + customer.getDefaultBillingAddress());
    System.out.println("firstName = " + customer.getFirstName());
    System.out.println("id = " + customer.getId());
    System.out.println("lastName = " + customer.getLastName());
    System.out.println("phone = " + customer.getPhone());
    System.out.println("version = " + customer.getVersion());
  }

  private static void printAddress(Address address) {
    System.out.println("city = " + address.getCity());
    System.out.println("id = " + address.getId());
    System.out.println("state = " + address.getState());
    System.out.println("street1 = " + address.getStreet1());
    System.out.println("street2 = " + address.getStreet2());
    System.out.println("version = " + address.getVersion());
    System.out.println("zipCode = " + address.getZipCode());
  }

  private static void printInventoryItem(InventoryItem inventoryitem) {
    System.out.println("stockDate = " + inventoryitem.getStockDate());
    System.out.println("wholesalePrice = " + inventoryitem.getWholesalePrice());
    System.out.println("id = " + inventoryitem.getId());
    System.out.println("quantity = " + inventoryitem.getQuantity());
    System.out.println("version = " + inventoryitem.getVersion());
    System.out.println("wine = " + inventoryitem.getWine());
  }

  private static void printCartItem(CartItem cartitem) {
    System.out.println("createdDate = " + cartitem.getCreatedDate());
    System.out.println("customer = " + cartitem.getCustomer());
    System.out.println("id = " + cartitem.getId());
    System.out.println("quantity = " + cartitem.getQuantity());
    System.out.println("version = " + cartitem.getVersion());
    System.out.println("wine = " + cartitem.getWine());
  }

  private static void printWine(Wine wine) {
    System.out.println("country = " + wine.getCountry());
    System.out.println("description = " + wine.getDescription());
    System.out.println("id = " + wine.getId());
    System.out.println("name = " + wine.getName());
    System.out.println("rating = " + wine.getRating());
    System.out.println("region = " + wine.getRegion());
    System.out.println("retailPrice = " + wine.getRetailPrice());
    System.out.println("varietal = " + wine.getVarietal());
    System.out.println("version = " + wine.getVersion());
    System.out.println("year = " + wine.getYear());
    System.out.println("supplierList = " + wine.getSupplierList());
  }

  private static void printBusinessContact(BusinessContact businesscontact) {
    System.out.println("firstName = " + businesscontact.getFirstName());
    System.out.println("id = " + businesscontact.getId());
    System.out.println("lastName = " + businesscontact.getLastName());
    System.out.println("phone = " + businesscontact.getPhone());
    System.out.println("version = " + businesscontact.getVersion());
  }

  private static void printCustomerOrder(CustomerOrder customerorder) {
    System.out.println("creationDate = " + customerorder.getCreationDate());
    System.out.println("id = " + customerorder.getId());
    System.out.println("status = " + customerorder.getStatus());
    System.out.println("version = " + customerorder.getVersion());
    System.out.println("customer = " + customerorder.getCustomer());
    System.out.println("orderItemList = " + customerorder.getOrderItemList());
  }
}
