package com.apress.ejb.chapter03.listings.listing_3_04;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity(name = "Listing_3_04_Address")
public class Address implements Serializable {

  @Id
  private BigDecimal addressId;
  @Column(name = "ZIP")
  private int zipCodeInternal;
  @Transient
  private String zipCode;

  public BigDecimal getAddressId() { return addressId; }
  public void setAddressId(BigDecimal addressId) { this.addressId = addressId; }

  public String getZipCode() {
    if (zipCode == null && zipCodeInternal > 0) {
      zipCode = convertToStr(zipCodeInternal);
    }
    return zipCode;
  }

  public void setZipCode(String zipCode) throws IllegalArgumentException {
    //  Validate the zipcode String, to make sure it reduces cleanly to
    //  either a 5- or 9- digit integer, and assign it to the internal
    //  persistent 'zipCodeInternal' class field
    //  ... <validation code here>...
    this.zipCode = zipCode;
    zipCodeInternal = convertToInt(zipCode);
  }

  private int convertToInt(String zipCode) {
    return new Integer(zipCode).intValue();
  }

  private String convertToStr(int zipCode) {
    return new Integer(zipCode).toString();
  }
}
