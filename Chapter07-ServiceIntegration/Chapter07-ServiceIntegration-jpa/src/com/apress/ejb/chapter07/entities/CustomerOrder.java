package com.apress.ejb.chapter07.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * To create ID generator table "CUSTOMER_ORDER_ID_GEN": CREATE TABLE
 * "CUSTOMER_ORDER_ID_GEN" ("PRIMARY_KEY_NAME" VARCHAR2(4000) PRIMARY KEY,
 * "NEXT_ID_VALUE" NUMBER(38));
 *
 * To initialize this table with data for this entity's ID generator
 * 'CustomerOrder.id' (starting with value '0'): INSERT INTO
 * "CUSTOMER_ORDER_ID_GEN" VALUES ('CustomerOrder.id', 0);
 */
@Entity
@NamedQueries({
  @NamedQuery(name = "CustomerOrder.findAll", query = "select o from CustomerOrder o")})
@Table(name = "CUSTOMER_ORDER")
@TableGenerator(name = "CustomerOrder_ID_Generator", table = "CUSTOMER_ORDER_ID_GEN", pkColumnName = "PRIMARY_KEY_NAME",
    pkColumnValue = "CustomerOrder.id", valueColumnName = "NEXT_ID_VALUE")
public class CustomerOrder implements Serializable {
  @Temporal(TemporalType.DATE)
  @Column(name = "CREATION_DATE")
  private Date creationDate;
  @Id
  @Column(nullable = false)
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "CustomerOrder_ID_Generator")
  private Integer id;
  private String status;
  @Version
  private Integer version;
  @ManyToOne
  @JoinColumn(name = "CUSTOMER_ID")
  private Customer customer;
  @OneToMany(mappedBy = "customerOrder", cascade = {CascadeType.ALL}, orphanRemoval = true)
  private List<OrderItem> orderItemList;

  public CustomerOrder() {
  }

  public CustomerOrder(String status, Customer customer) {
    setCreationDate(new Date(System.currentTimeMillis()));
    setStatus(status);
    if (customer != null) {
      customer.addCustomerOrder(this);
    }
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer1) {
    this.customer = customer1;
  }

  public List<OrderItem> getOrderItemList() {
    if (orderItemList == null) {
      orderItemList = new ArrayList<OrderItem>();
    }
    return orderItemList;
  }

  public void setOrderItemList(List<OrderItem> orderitemList) {
    this.orderItemList = orderitemList;
  }

  public OrderItem addOrderItem(OrderItem orderItem) {
    getOrderItemList().add(orderItem);
    orderItem.setCustomerOrder(this);
    return orderItem;
  }

  public OrderItem removeOrderItem(OrderItem orderItem) {
    getOrderItemList().remove(orderItem);
    orderItem.setCustomerOrder(null);
    return orderItem;
  }
}
