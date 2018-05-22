package com.apress.ejb.chapter03.listings.listing_3_05;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Listing_3_05_Address")
public class Address implements Serializable {

  private BigDecimal addressId;
  private int zipCode;
  private String city;

  @Id
  public BigDecimal getAddressId() { return addressId; }
  public void setAddressId(BigDecimal addressId) { this.addressId = addressId; }

  public int getZipCode() { return zipCode; }
  public void setZipCode(int zipCode) { 
    if (zipCode != this.zipCode)
    {
      city = null; // Force city to be lazily re-derived
      this.zipCode = zipCode; 
    }
  }

  public String getCity() {
    //  Derive the city from the zipcode property, if available
    if (city == null && zipCode > 0) {
      city = deriveCityFromZip();
    }
    return city;
  }
  public void setCity(String city) {
    this.city = city;
  }

  private String deriveCityFromZip() {
    /* Implementation here... */ 
    return null; 
  }
}
