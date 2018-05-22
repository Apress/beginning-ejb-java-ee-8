package com.apress.ejb.chapter03.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

/**
 * To create ID generator table "ADDRESS_ID_GENERATOR": CREATE TABLE
 * "CH03_ADDRESS_ID_GEN" ("PRIMARY_KEY_NAME" VARCHAR(4000) PRIMARY KEY,
 * "NEXT_ID_VALUE" BIGINT);
 *
 * To initialize this table with data for this entity's ID generator
 * 'Address.id' (starting with value '0'): INSERT INTO "ADDRESS_ID_GENERATOR"
 * VALUES ('Address.id', 0);
 */
@Entity
@NamedQueries({
  @NamedQuery(name = "Address.findAll", 
              query = "select o from Address o")})
@Table(name = "CH03_ADDRESS")
@TableGenerator(name = "Address_ID_Generator", 
                table = "CH03_ADDRESS_ID_GEN", 
                pkColumnName = "PRIMARY_KEY_NAME",
                pkColumnValue = "Address.id", 
                valueColumnName = "NEXT_ID_VALUE")
public class Address implements Serializable {

  @Column(length = 4000)
  private String city;
  @Id
  @Column(nullable = false)
  @GeneratedValue(strategy = GenerationType.TABLE, 
                  generator = "Address_ID_Generator")
  private BigDecimal id;
  private String state;
  @Column(length = 4000)
  private String street1;
  @Column(length = 4000)
  private String street2;
  @Version
  private Integer version;
  @Column(name = "ZIP_CODE")
  private int zipCode;

  public String getCity() { return city; }
  public void setCity(String city) { this.city = city; }

  public BigDecimal getId() { return id; }
  public void setId(BigDecimal id) { this.id = id; }

  public String getState() { return state; }
  public void setState(String state) { this.state = state; }

  public String getStreet1() { return street1; }
  public void setStreet1(String street1) { this.street1 = street1; }

  public String getStreet2() { return street2; }
  public void setStreet2(String street2) { this.street2 = street2; }

  public Integer getVersion() { return version; }
  public void setVersion(Integer version) { this.version = version; }

  public int getZipCode() { return zipCode; }
  public void setZipCode(int zipCode) { this.zipCode = zipCode; }
}
