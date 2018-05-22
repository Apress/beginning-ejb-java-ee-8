package com.apress.ejb.chapter07.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * To create ID generator table "CUSTOMER_ID_GEN": CREATE TABLE "CUSTOMER_ID_GEN"
 * ("PRIMARY_KEY_NAME" VARCHAR2(4000) PRIMARY KEY, "NEXT_ID_VALUE" NUMBER(38));
 *
 * To initialize this table with data for this entity's ID generator 'Customer.id' (starting with
 * value '0'): INSERT INTO "CUSTOMER_ID_GEN" VALUES ('Customer.id', 0);
 */
@Entity
@NamedQueries({
  @NamedQuery(name = "Customer.findAll", query = "select o from Customer o"),
  @NamedQuery(name = "Customer.findByEmail", query = "select o from Customer o where o.email = :email")})
public abstract class Customer extends BusinessContact {
  @Column(length = 4000)
  private String email;
  @OneToMany(mappedBy = "customer", cascade = {CascadeType.ALL})
  private List<CartItem> cartItemList;
  @OneToMany(mappedBy = "customer", cascade = {CascadeType.ALL})
  private List<CustomerOrder> customerOrderList;
  @OneToMany(cascade = {CascadeType.ALL})
  @JoinTable(name = "CUSTOMER_BILLING_ADDRESS", joinColumns =
  @JoinColumn(name = "CUSTOMER_ID"),
             inverseJoinColumns =
  @JoinColumn(name = "ADDRESS_ID"))
  protected List<Address> billingAddressList;
  @OneToMany(cascade = {CascadeType.ALL})
  @JoinTable(name = "CUSTOMER_SHIPPING_ADDRESS", joinColumns =
  @JoinColumn(name = "CUSTOMER_ID"),
             inverseJoinColumns =
  @JoinColumn(name = "ADDRESS_ID"))
  protected List<Address> shippingAddressList;
  @OneToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "DEFAULT_SHIPPING_ADDRESS")
  private Address defaultShippingAddress;
  @OneToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "DEFAULT_BILLING_ADDRESS")
  private Address defaultBillingAddress;

  public Customer() {
  }

  public Customer(String firstName, String lastName, String phone, String email, Address defaultShippingAddress,
                  Address defaultBillingAddress) {
    super(firstName, lastName, phone);
    setEmail(email);
    setDefaultBillingAddress(defaultBillingAddress);
    setDefaultShippingAddress(defaultShippingAddress);
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<CartItem> getCartItemList() {
    if (cartItemList == null) {
      cartItemList = new ArrayList<CartItem>();
    }
    return cartItemList;
  }

  public void setCartItemList(List<CartItem> cartitemList) {
    this.cartItemList = cartitemList;
  }

  public CartItem addCartItem(CartItem cartItem) {
    getCartItemList().add(cartItem);
    cartItem.setCustomer(this);
    return cartItem;
  }

  public CartItem removeCartItem(CartItem cartItem) {
    getCartItemList().remove(cartItem);
    cartItem.setCustomer(null);
    return cartItem;
  }

  public List<CustomerOrder> getCustomerOrderList() {
    if (customerOrderList == null) {
      customerOrderList = new ArrayList<CustomerOrder>();
    }
    return customerOrderList;
  }

  public void setCustomerOrderList(List<CustomerOrder> customerorderList) {
    this.customerOrderList = customerorderList;
  }

  public CustomerOrder addCustomerOrder(CustomerOrder customerOrder) {
    getCustomerOrderList().add(customerOrder);
    customerOrder.setCustomer(this);
    return customerOrder;
  }

  public CustomerOrder removeCustomerOrder(CustomerOrder customerOrder) {
    getCustomerOrderList().remove(customerOrder);
    customerOrder.setCustomer(null);
    return customerOrder;
  }

  public List<Address> getBillingAddressList() {
    if (billingAddressList == null) {
      billingAddressList = new ArrayList<Address>();
    }
    return billingAddressList;
  }

  public void setBillingAddressList(List<Address> billingAddressList) {
    this.billingAddressList = billingAddressList;
  }

  public Address addBillingAddress(Address billingAddress) {
    getBillingAddressList().add(billingAddress);
    return billingAddress;
  }

  public Address removeBillingAddress(Address billingAddress) {
    getBillingAddressList().remove(billingAddress);
    return billingAddress;
  }

  public List<Address> getShippingAddressList() {
    if (shippingAddressList == null) {
      shippingAddressList = new ArrayList<Address>();
    }
    return shippingAddressList;
  }

  public void setShippingAddressList(List<Address> shippingAddressList) {
    this.shippingAddressList = shippingAddressList;
  }

  public Address addShippingAddress(Address shippingAddress) {
    getShippingAddressList().add(shippingAddress);
    return shippingAddress;
  }

  public Address removeShippingAddress(Address shippingAddress) {
    getShippingAddressList().remove(shippingAddress);
    return shippingAddress;
  }

  public Address getDefaultShippingAddress() {
    return defaultShippingAddress;
  }

  public void setDefaultShippingAddress(Address address1) {
    this.defaultShippingAddress = address1;
  }

  public Address getDefaultBillingAddress() {
    return defaultBillingAddress;
  }

  public void setDefaultBillingAddress(Address address3) {
    this.defaultBillingAddress = address3;
  }
}
