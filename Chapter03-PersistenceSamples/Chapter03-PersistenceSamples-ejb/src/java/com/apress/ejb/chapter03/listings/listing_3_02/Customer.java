package com.apress.ejb.chapter03.listings.listing_3_02;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Listing_3_02_Customer")
public class Customer implements Serializable {
  @Id
  private long customerId;
  private String name;

  public long getCustomerId() { return customerId; }
  public void setCustomerId(long customerId) { this.customerId = customerId; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
}
