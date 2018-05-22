/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.ejb.chapter04.embeddedid;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * MyIdClass: An embeddable class suitable for use as an
 *
 * @EmbeddedId
 */
@Embeddable
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

  /**
   * @return the firstName
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * @param firstName the firstName to set
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * @return the lastName
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * @param lastName the lastName to set
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
