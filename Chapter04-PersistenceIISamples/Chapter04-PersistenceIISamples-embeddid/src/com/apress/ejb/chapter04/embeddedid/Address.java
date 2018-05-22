package com.apress.ejb.chapter04.embeddedid;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Address: An embeddable non-entity class
 */
@Embeddable
public class Address implements Serializable {
  @SuppressWarnings("compatibility:-5340972441524875330")
  private static final long serialVersionUID = -5279408726470732092L;
  @Column(length = 400)
  private String city;
  @Column(length = 2)
  private String state;
  @Column(length = 400)
  private String street1;
  @Column(length = 400)
  private String street2;
  @Column(name = "ZIP_CODE")
  private String zipCode;

  public Address() {
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getStreet1() {
    return street1;
  }

  public void setStreet1(String street1) {
    this.street1 = street1;
  }

  public String getStreet2() {
    return street2;
  }

  public void setStreet2(String street2) {
    this.street2 = street2;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }
}
