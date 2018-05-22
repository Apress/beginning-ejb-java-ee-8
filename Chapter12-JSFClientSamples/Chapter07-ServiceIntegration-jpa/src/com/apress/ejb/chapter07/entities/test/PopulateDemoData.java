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
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PopulateDemoData {
  public static final String FROM_EMAIL_ADDRESS;
  public static final String TO_EMAIL_ADDRESS;

  static {
    Properties properties = new Properties();
    InputStream is = null;
    try {
      is = PopulateDemoData.class.getClassLoader().getResourceAsStream("user.properties");
      properties.load(is);
      FROM_EMAIL_ADDRESS = properties.getProperty("from_email_address");
      TO_EMAIL_ADDRESS = properties.getProperty("to_email_address");
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      if (is != null) {
        try {
          is.close();
        } catch (IOException ex) {
          Logger.getLogger(PopulateDemoData.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
  }
  private JavaServiceFacade facade;

  public static void main(String[] args) {
    new PopulateDemoData("Chapter07-WineAppUnit-ResourceLocal").resetData();
  }

  public PopulateDemoData(String persistenceUnit) {
    facade = new JavaServiceFacade(persistenceUnit);
  }

  public void resetData() {
    removeAllDemoData();
    Customer cust = populateDemoCustomer();
    InventoryItem invItem = populateWines();
    populateCustomerOrder(cust, invItem);
  }

  private void removeAllDemoData() {
    int i = 0;
    for (Individual ind : facade.getIndividualFindAll()) {
      facade.removeIndividual(ind);
      i++;
    }
    System.out.println("Removed " + i + " Individuals");

    i = 0;
    for (Supplier sup : facade.getSupplierFindAll()) {
      facade.removeSupplier(sup);
      i++;
    }
    System.out.println("Removed " + i + " Suppliers");

    i = 0;
    for (Distributor d : facade.getDistributorFindAll()) {
      facade.removeDistributor(d);
      i++;
    }
    System.out.println("Removed " + i + " Distributors");

    i = 0;
    for (WineItem wi : facade.getWineItemFindAll()) {
      facade.removeWineItem(wi);
      i++;
    }
    System.out.println("Removed " + i + " WineItems");

    i = 0;
    for (Wine w : facade.getWineFindAll()) {
      facade.removeWine(w);
      i++;
    }
    System.out.println("Removed " + i + " Wines");

    i = 0;
    for (CustomerOrder co : facade.getCustomerOrderFindAll()) {
      facade.removeCustomerOrder(co);
      i++;
    }
    System.out.println("Removed " + i + " CustomerOrders");

    i = 0;
    for (Address a : facade.getAddressFindAll()) {
      facade.removeAddress(a);
      i++;
    }
    System.out.println("Removed " + i + " Addresses");
  }

  private Customer populateDemoCustomer() {
    Address a = new Address("Redwood Shores", "CA", "200 Oracle Pkwy", null, "94065");
    Individual i = new Individual("Demo", "Individual", "800.888.8000", TO_EMAIL_ADDRESS, a, a, "04/14", "123");
    facade.persistEntity(i);

    return i;
  }

  private InventoryItem populateWines() {
    InventoryItem ii = null;
    for (int i = 0; i < 4; i++) {
        Wine w = new Wine("USA", "Fine Wine - ranked #" + i, "Yerba Buena " + i, 90, "Napa Valley", new Float(10 + i), "Zinfandel", 2000 + i);
        ii = new InventoryItem(10 + i, w, new java.util.Date(System.currentTimeMillis()), new Float(1 + i));
        facade.persistEntity(ii);
      }
    for (int i = 4; i < 10; i++) {
         Wine w = new Wine("France", "Fine Wine - ranked #" + i, "Chateau Brown " + i, 90, "Loire Valley ", new Float(10 + i), "Zinfandel", 2000 + i);
        ii = new InventoryItem(10 + i, w, new java.util.Date(System.currentTimeMillis()), new Float(1 + i));
        facade.persistEntity(ii);
      }

    return ii;
  }

  private void populateCustomerOrder(Customer customer, InventoryItem invItem) {
    CustomerOrder co = new CustomerOrder("PENDING", customer);
    OrderItem oi = new OrderItem((short) 1, invItem.getWine(), new Date(System.currentTimeMillis()), new Float(10), null, "PENDING", co);
    co.addOrderItem(oi);
    facade.persistEntity(co);

    CartItem ci = new CartItem((short) 1, invItem.getWine());
    facade.persistEntity(ci);
  }
}