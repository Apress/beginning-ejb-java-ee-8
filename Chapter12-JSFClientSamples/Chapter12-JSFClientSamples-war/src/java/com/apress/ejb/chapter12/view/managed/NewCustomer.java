package com.apress.ejb.chapter12.view.managed;

import com.apress.ejb.chapter07.ejb.CustomerFacadeLocal;
import com.apress.ejb.chapter07.entities.Address;
import com.apress.ejb.chapter07.entities.Individual;
import javax.ejb.EJB;

public class NewCustomer
{
  private String firstName;
  private String lastName;
  private String phone;
  private String email;
  private String streetOne;
  private String streetTwo;
  private String city;
  private String state;
  private String zipCode;
  private String ccnum;
  private String ccexpDate;

 @EJB
 CustomerFacadeLocal customerFacade;

  public NewCustomer() {
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getPhone() {
    return phone;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public void setStreetOne(String streetOne) {
    this.streetOne = streetOne;
  }

  public String getStreetOne() {
    return streetOne;
  }

  public void setStreetTwo(String streetTwo) {
    this.streetTwo = streetTwo;
  }

  public String getStreetTwo() {
    return streetTwo;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCity() {
    return city;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getState() {
    return state;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setCcnum(String ccnum) {
    this.ccnum = ccnum;
  }

  public String getCcnum() {
    return ccnum;
  }

  public void setCcexpDate(String ccexpDate) {
    this.ccexpDate = ccexpDate;
  }

  public String getCcexpDate() {
    return ccexpDate;
  }

  public String AddNewCustomer() {
    Individual customer = new Individual();
    customer.setFirstName(firstName);
    customer.setLastName(lastName);
    customer.setPhone(phone);
    customer.setEmail(email);

    Address address = new Address();
    address.setStreet1(streetOne);
    address.setStreet2(streetTwo);
    address.setState(state);
    address.setCity(city);
    address.setZipCode(zipCode);

    customer.setDefaultBillingAddress(address);
    customer.setCcNum(ccnum);
    customer.setCcExpDate(ccexpDate);

    if (customerFacade != null) {
      customerFacade.registerCustomer(customer);        
    }

    return "success";
  }
}