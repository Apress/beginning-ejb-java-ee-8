package com.apress.ejb.chapter09.joined;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
  @NamedQuery(name = "Individual.findAll", query = "select o from Individual o"),
  @NamedQuery(name = "Individual.findById", query = "select o from Individual o where o.id = :id")
})
public class Individual extends Customer {
  @Column(name = "CC_EXP_DATE", length = 7)
  private String ccExpDate;
  @Column(name = "CC_NUM")
  private String ccNum;

  public Individual() {
  }

  public Individual(String firstName, String lastName, String phone, String email, Address defaultShippingAddress,
                    Address defaultBillingAddress, String ccExpDate, String ccNum) {
    super(firstName, lastName, phone, email, defaultShippingAddress, defaultBillingAddress);
    setCcExpDate(ccExpDate);
    setCcNum(ccNum);
  }

  public String getCcExpDate() {
    return ccExpDate;
  }

  public void setCcExpDate(String ccExpDate) {
    this.ccExpDate = ccExpDate;
  }

  public String getCcNum() {
    return ccNum;
  }

  public void setCcNum(String ccNum) {
    this.ccNum = ccNum;
  }
}
