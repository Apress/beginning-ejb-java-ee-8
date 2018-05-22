package com.apress.ejb.chapter03.listings.listing_3_06;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Listing_3_06_Customer")
public class Customer implements Serializable {

  @Id
  private Integer id;
  private String name;

  public Customer() {}
  public Customer(Integer id) { this.id = id; }
  
  public Integer getId() { return id; }
  public void setId(Integer id) { this.id = id; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
}
