package com.apress.ejb.chapter09.joined;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
  @NamedQuery(name = "Supplier.findAll", query = "select o from Supplier o")})
public class Supplier extends BusinessContact {
  @ManyToOne
  @JoinColumn(name = "PAYMENT_ADDRESS")
  private Address paymentAddress;
  @ManyToMany(mappedBy = "supplierList")
  protected List<Wine> wineList;

  public Supplier() {
  }

  public Supplier(String firstName, String lastName, String phone, Address paymentAddress) {
    super(firstName, lastName, phone);
    setPaymentAddress(paymentAddress);
  }

  public Address getPaymentAddress() {
    return paymentAddress;
  }

  public void setPaymentAddress(Address paymentAddress) {
    this.paymentAddress = paymentAddress;
  }

  public List<Wine> getWineList() {
    if (wineList == null) {
      wineList = new ArrayList();
    }
    return wineList;
  }
}
