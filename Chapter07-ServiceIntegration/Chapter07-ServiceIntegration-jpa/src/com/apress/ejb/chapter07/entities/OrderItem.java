package com.apress.ejb.chapter07.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
  @NamedQuery(name = "OrderItem.findAll", query = "select o from OrderItem o")})
@Table(name = "ORDER_ITEM")
public class OrderItem extends WineItem {
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "ORDER_DATE")
  private Date orderDate;
  private Float price;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "SHIP_DATE")
  private Date shipDate;
  @Column(length = 4000)
  private String status;
  @ManyToOne
  @JoinColumn(name = "CUSTOMER_ORDER_ID")
  private CustomerOrder customerOrder;

  public OrderItem() {
  }

  public OrderItem(int quantity, Wine wine, Date orderDate, Float price, Date shipDate, String status,
      CustomerOrder customerOrder) {
    super(quantity, wine);
    setOrderDate(orderDate);
    setPrice(price);
    setShipDate(shipDate);
    setStatus(status);
    setCustomerOrder(customerOrder);
  }

  public Date getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
  }

  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  public Date getShipDate() {
    return shipDate;
  }

  public void setShipDate(Date shipDate) {
    this.shipDate = shipDate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public CustomerOrder getCustomerOrder() {
    return customerOrder;
  }

  public void setCustomerOrder(CustomerOrder customerOrder) {
    this.customerOrder = customerOrder;
  }
}
