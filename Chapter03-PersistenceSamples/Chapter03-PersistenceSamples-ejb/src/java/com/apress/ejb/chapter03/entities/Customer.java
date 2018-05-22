package com.apress.ejb.chapter03.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

/**
 * To create ID generator table "CUSTOMER_ID_GENERATOR": CREATE TABLE
 * "CH03_CUSTOMER_ID_GEN" ("PRIMARY_KEY_NAME" VARCHAR(4000) PRIMARY KEY,
 * "NEXT_ID_VALUE" BIGINT);
 *
 * To initialize this table with data for this entity's ID generator
 * 'Customer.id' (starting with value '0'): INSERT INTO "CH03_CUSTOMER_ID_GEN"
 * VALUES ('Customer.id', 0);
 */
@Entity
@NamedQueries({
  @NamedQuery(name = "Customer.findAll", 
              query = "select o from Customer o"),
  @NamedQuery(name = "Customer.findByEmail", 
              query = "select o from Customer o where o.email = :email")})
@Table(name = "CH03_CUSTOMER")
@TableGenerator(name = "Customer_ID_Generator", 
                table = "CH03_CUSTOMER_ID_GEN", 
                pkColumnName = "PRIMARY_KEY_NAME", 
                pkColumnValue = "Customer.id", 
                valueColumnName = "NEXT_ID_VALUE")
public class Customer implements Serializable {

  @Id
  @Column(nullable = false)
  @GeneratedValue(strategy = GenerationType.TABLE, 
                  generator = "Customer_ID_Generator")
  private BigDecimal id;
  @Version
  private int version;
  @Column(length = 4000)
  private String email;
  @OneToMany(mappedBy = "customer", cascade = {CascadeType.ALL})
  private List<CustomerOrder> customerOrders;
  @OneToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "BILLING_ADDRESS")
  private Address billingAddress;
  @OneToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "SHIPPING_ADDRESS")
  private Address shippingAddress;

  public BigDecimal getId() { return id; }
  public void setId(BigDecimal id) { this.id = id; }

  public int getVersion() { return version; }
  public void setVersion(int version) { this.version = version; }

  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }

  public List<CustomerOrder> getCustomerOrders() { return customerOrders; }
  public void setCustomerOrders(List<CustomerOrder> customerOrders) {
    this.customerOrders = customerOrders; 
  }

  public CustomerOrder addCustomerOrder(CustomerOrder customerOrder) {
    if (customerOrders == null) {
      customerOrders = new ArrayList<CustomerOrder>();
    }
    customerOrders.add(customerOrder);
    customerOrder.setCustomer(this);
    return customerOrder;
  }

  public CustomerOrder removeCustomerOrder(CustomerOrder customerOrder) {
    getCustomerOrders().remove(customerOrder);
    customerOrder.setCustomer(null);
    return customerOrder;
  }

  public Address getBillingAddress() { return billingAddress; }
  public void setBillingAddress(Address billingAddress) {
    this.billingAddress = billingAddress; 
  }

  public Address getShippingAddress() { return shippingAddress; }
  public void setShippingAddress(Address shippingAddress) { 
    this.shippingAddress = shippingAddress; 
  }
}
