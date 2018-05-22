package com.apress.ejb.chapter02;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton (name = "ShopperCount")
@Startup
public class ShopperCountBean {
   private int shopperCounter;

   // Increment number of shopper counter
   public void incrementShopperCount() {
       shopperCounter++;
   }

   // Return number of shoppers
   public int getShopperCount() {
       return shopperCounter;
   }

   // Reset counter
   public void resetCounter() {
       shopperCounter = 0;
   }

   // Reset counter
   @PostConstruct
   public void applicationStartup() {
       System.out.println("From applicationStartup method.");
       resetCounter();
   }

   @PreDestroy
   public void applicationShutdown() {
      System.out.println("From applicationShutdown method.");
   }
}
