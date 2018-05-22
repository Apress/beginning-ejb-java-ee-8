package com.apress.ejb.chapter03.listings.listing_3_11;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class CustomerPK implements Serializable {
  Long id;
  String name;

  public void setId(Long id) { this.id = id; }
  public Long getId() { return id; }

  public void setName(String name) { this.name = name; }
  public String getName() { return name; }

  @Override
  public int hashCode() { return 0; /* Implementation here */ }
  @Override
  public boolean equals(Object obj) { return false; /* Implementation here */ }
}
