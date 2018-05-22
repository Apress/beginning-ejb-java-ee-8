package com.apress.ejb.chapter03.listings.listing_3_09;

import java.io.Serializable;

public class CustomerPK implements Serializable {

  private Integer id;
  private String name;

  public void setId(Integer id) { this.id = id; }
  public Integer getId() { return id; }

  public void setName(String name) { this.name = name; }
  public String getName() { return name; }

  @Override
  public int hashCode() { return 0; /* Implementation here */ }
  @Override
  public boolean equals(Object obj) { return false; /* Implementation here */ }
}
