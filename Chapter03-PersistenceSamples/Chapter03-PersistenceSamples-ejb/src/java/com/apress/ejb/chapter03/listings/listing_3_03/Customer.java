package com.apress.ejb.chapter03.listings.listing_3_03;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="Listing_3_03_Customer")
@Table(name="LISTING_3_03_CUSTOMER")
public class Customer implements Serializable {
  @Id
  @Column(name="CUSTOMERID", table="LISTING_3_03_CUSTOMER", unique=true,
          nullable=false, insertable=true, updatable=true)
  private long customerId;

  @Basic(fetch=FetchType.EAGER)
  @Column(name="NAME", table="CUSTOMER")
  private String name;

  public long getCustomerId() { return customerId; }
  public void setCustomerId(long customerId) { this.customerId = customerId; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
}
