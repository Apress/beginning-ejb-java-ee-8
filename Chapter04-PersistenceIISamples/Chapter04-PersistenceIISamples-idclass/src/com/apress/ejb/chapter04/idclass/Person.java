package com.apress.ejb.chapter04.idclass;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Person: A concrete entity that uses an
 *
 * @EmbeddedId and an
 * @Embedded field
 */
@Entity
@NamedQueries({
  @NamedQuery(name = "Person.findAll", query = "select o from Person o")})
@Table(name = "CH04_IDCLASS_PERSON")
@IdClass(MyIdClass.class)
public class Person implements Serializable {
  @SuppressWarnings("compatibility:-7074714881275658754")
  private static final long serialVersionUID = 5291172566067954515L;
  @Id
  @Column(name = "FIRST_NAME", length = 400)
  private String firstName;
  @Id
  @Column(name = "LAST_NAME", length = 400)
  private String lastName;
  @Embedded
  private Address homeAddress;
  @Version
  private Integer version;

  public Person() {
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

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public Address getHomeAddress() {
    return homeAddress;
  }

  public void setHomeAddress(Address homeAddress) {
    this.homeAddress = homeAddress;
  }
}