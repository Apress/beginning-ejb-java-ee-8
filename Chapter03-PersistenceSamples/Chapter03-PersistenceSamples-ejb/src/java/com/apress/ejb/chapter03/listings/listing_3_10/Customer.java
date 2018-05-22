package com.apress.ejb.chapter03.listings.listing_3_10;

import com.apress.ejb.chapter03.listings.listing_3_11.CustomerPK;
import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "Listing_3_10_Customer")
public class Customer implements Serializable {
  @EmbeddedId
  private CustomerPK customerId;

  public CustomerPK getCustomerId() { return customerId; }
  public void setCustomerId(CustomerPK customerId) {
    this.customerId = customerId; 
  }
  
  // ...
}
