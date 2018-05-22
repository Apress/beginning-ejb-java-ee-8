package com.apress.ejb.chapter03.listings.listing_3_14;

import com.apress.ejb.chapter03.listings.listing_3_07.Customer;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CustomerService implements Serializable {
  
  public static void main(String[] args) {
    final EntityManagerFactory emf =
      Persistence.createEntityManagerFactory("Chapter03PersistenceUnit-JSE");
    final EntityManager em = emf.createEntityManager();
    final Customer cust = new Customer();
    cust.setName("Best Customer Ever");
    em.persist(cust);
  }
}
