package com.apress.ejb.chapter04.mappedsuperclass;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

/**
 * Person: A Mapped Superclass, and the base class (but not the root entity) 
 * of an inheritance hierarchy
 * 
 * To create ID generator table "CH04_MS_PERSON_ID_GEN": CREATE TABLE
 * "CH04_MS_PERSON_ID_GEN" ("PRIMARY_KEY_NAME" VARCHAR2(4000) PRIMARY KEY,
 * "NEXT_ID_VALUE" NUMBER(38));
 *
 * To initialize this table with data for this entity's ID generator 'Person.id'
 * (starting with value '0'): INSERT INTO "CH04_MS_PERSON_ID_GEN" VALUES
 * ('Person.id', 0);
 */
@MappedSuperclass
@TableGenerator(name = "Person_ID_Generator", table = "CH04_MS_PERSON_ID_GEN",
                pkColumnName = "PRIMARY_KEY_NAME", pkColumnValue = "Person.id",
                valueColumnName = "NEXT_ID_VALUE")
public abstract class Person implements Serializable {
  @SuppressWarnings("compatibility:-7074714881275658754")
  private static final long serialVersionUID = 5291172566067954515L;
  @Id
  @Column(nullable = false)
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "Person_ID_Generator")
  private Integer id;
  @Column(name = "FIRST_NAME", length = 400)
  private String firstName;
  @Column(name = "LAST_NAME", length = 400)
  private String lastName;
  @OneToOne(cascade=CascadeType.ALL)
  @JoinColumn(name = "HOME_ADDRESS")
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

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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
