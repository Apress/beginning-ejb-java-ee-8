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
  @NamedQuery(name = "CartItem.findAll", query = "select o from CartItem o")})
@Table(name = "CART_ITEM")
public class CartItem extends WineItem {
  @Temporal(TemporalType.DATE)
  @Column(name = "CREATED_DATE")
  private Date createdDate;
  @ManyToOne
  @JoinColumn(name = "CUSTOMER_ID")
  private Customer customer;

  public CartItem() {
  }

  public CartItem(int quantity, Wine wine) {
    super(quantity, wine);
    setCreatedDate(new Date(System.currentTimeMillis()));
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customerRef) {
    this.customer = customerRef;
  }
}
