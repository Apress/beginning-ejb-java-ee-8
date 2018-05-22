/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.ejb.chapter04.idclass;

import java.io.Serializable;
import javax.persistence.Column;

/**
 * MyIdClass: An ordinary class suitable for use as an IdClass
 */
public class MyIdClass implements Serializable {
  @Column(name = "FIRST_NAME", length = 400)
  private String firstName;
  @Column(name = "LAST_NAME", length = 400)
  private String lastName;

  public MyIdClass() {
  }

  public MyIdClass(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof MyIdClass && firstName.equals(((MyIdClass) obj).getFirstName()) && lastName.equals(((MyIdClass) obj).getLastName());
  }

  @Override
  public int hashCode() {
    return System.identityHashCode(this);
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
