package com.apress.ejb.chapter07.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

/**
 * To create ID generator table "BUSINESS_CONTACT_ID_GEN": CREATE TABLE
 * "BUSINESS_CONTACT_ID_GEN" ("PRIMARY_KEY_NAME" VARCHAR2(4000) PRIMARY KEY,
 * "NEXT_ID_VALUE" NUMBER(38));
 *
 * To initialize this table with data for this entity's ID generator
 * 'BusinessContact.id' (starting with value '0'): INSERT INTO
 * "BUSINESS_CONTACT_ID_GEN" VALUES ('BusinessContact.id', 0);
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
  @NamedQuery(name = "BusinessContact.findAll", query = "select o from BusinessContact o")})
@Table(name = "BUSINESS_CONTACT")
@TableGenerator(name = "BusinessContact_ID_Generator", table = "BUSINESS_CONTACT_ID_GEN", pkColumnName = "PRIMARY_KEY_NAME",
    pkColumnValue = "BusinessContact.id", valueColumnName = "NEXT_ID_VALUE")
public class BusinessContact implements Serializable {
  @Column(name = "FIRST_NAME", length = 4000)
  private String firstName;
  @Id
  @Column(nullable = false)
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "BusinessContact_ID_Generator")
  private Integer id;
  @Column(name = "LAST_NAME", length = 4000)
  private String lastName;
  @Column(length = 15)
  private String phone;
  @Version
  private Integer version;

  public BusinessContact() {
  }

  public BusinessContact(String firstName, String lastName, String phone) {
    setFirstName(firstName);
    setLastName(lastName);
    setPhone(phone);
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

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }
}
