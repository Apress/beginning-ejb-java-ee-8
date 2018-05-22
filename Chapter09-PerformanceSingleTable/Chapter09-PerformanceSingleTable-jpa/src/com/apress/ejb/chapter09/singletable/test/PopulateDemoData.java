package com.apress.ejb.chapter09.singletable.test;

import com.apress.ejb.chapter09.singletable.Address;
import com.apress.ejb.chapter09.singletable.BusinessContact;
import com.apress.ejb.chapter09.singletable.Customer;
import com.apress.ejb.chapter09.singletable.CustomerOrder;
import com.apress.ejb.chapter09.singletable.Individual;
import com.apress.ejb.chapter09.singletable.InventoryItem;
import com.apress.ejb.chapter09.singletable.Wine;
import com.apress.ejb.chapter09.singletable.WineItem;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PopulateDemoData {
  private JavaServiceFacade facade;

  public static void main(String[] args) {
    PopulateDemoData.resetData("Chapter09-SingleTable-ResourceLocal", System.out);
  }

  public static void resetData(String persistenceUnit, PrintStream out) {
    PopulateDemoData pdd = null;
    try {
      pdd = new PopulateDemoData(persistenceUnit);

      out.println("Reporting existing data...");
      pdd.showDataCount(out);

      out.println("Removing data...");
      pdd.removeAllDemoData();

      out.println("Reporting data after removal...");
      pdd.showDataCount(out);

      out.println("Populating data...");
      pdd.populateDemoCustomer();
      pdd.populateWines();

      out.println("Reporting final data...");
      pdd.showDataCount(out);
    } finally {
      if (pdd != null) {
        pdd.releaseEntityManager();
      }
    }
  }

  private PopulateDemoData(String persistenceUnit) {
    facade = new JavaServiceFacade(persistenceUnit);
  }

  private void removeAllDemoData() {
    for (CustomerOrder co : facade.findAll(CustomerOrder.class)) {
      facade.removeEntity(co);
    }
    for (BusinessContact bc : facade.findAll(BusinessContact.class)) {
      facade.removeEntity(bc);
    }
    for (WineItem wi : facade.findAll(WineItem.class)) {
      facade.removeEntity(wi);
    }
    for (Wine w : facade.findAll(Wine.class)) {
      facade.removeEntity(w);
    }
  }

  private Customer populateDemoCustomer() {
    Address a = new Address("Redwood Shores", "CA", "200 Oracle Pkwy", null, "94065");
    Individual i = new Individual("James", "Brown", "800.888.8000", "joined@yahoo.com", a, a, "04/14", "123");

    facade.persistEntity(i);

    return i;
  }

  private InventoryItem populateWines() {
    InventoryItem ii = null;
    for (int i = 0; i < 6; i++) {
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

  private void showDataCount(PrintStream out) {
    out.println(facade.getCount(Address.class) + " Addresses found");
    out.println(facade.getCount(BusinessContact.class) + " Business Contacts found");
    out.println(facade.getCount(CustomerOrder.class) + " Customer Orders found");
    out.println(facade.getCount(Wine.class) + " Wines found");
    out.println(facade.getCount(WineItem.class) + " Wine Items found");
  }

  private void releaseEntityManager() {
    if (facade != null) {
      facade.close();
    }
  }
}