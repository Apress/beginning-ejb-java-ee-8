package com.apress.ejb.chapter04.mappedsuperclass;

import java.io.Serializable;
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
 * Address: A standalone entity
 *
 * To create ID generator table "CH04_MS_ADDRESS_ID_GEN": CREATE TABLE "CH04_MS_ADDRESS_ID_GEN"
 * ("PRIMARY_KEY_NAME" VARCHAR2(4000) PRIMARY KEY, "NEXT_ID_VALUE" NUMBER(38));
 *
 * To initialize this table with data for this entity's ID generator 'Address.id' (starting with
 * value '0'): INSERT INTO "CH04_MS_ADDRESS_ID_GEN" VALUES ('Address.id', 0);
 */
@Entity
@NamedQueries({
  @NamedQuery(name = "Address.findAll", query = "select o from Address o")})
@Table(name = "CH04_MS_ADDRESS")
@TableGenerator(name = "Address_ID_Generator", table = "CH04_MS_ADDRESS_ID_GEN", pkColumnName = "PRIMARY_KEY_NAME",
                pkColumnValue = "Address.id", valueColumnName = "NEXT_ID_VALUE")
public class Address implements Serializable {
  @SuppressWarnings("compatibility:-5340972441524875330")
  private static final long serialVersionUID = -5279408726470732092L;

  @Id
  @Column(nullable = false)
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "Address_ID_Generator")
  private Integer id;
  @Column(length = 400)
  private String city;
  @Column(length = 2)
  private String state;
  @Column(length = 400)
  private String street1;
  @Column(length = 400)
  private String street2;
  @Version
  private Integer version;
  @Column(name = "ZIP_CODE")
  private String zipCode;

  public Address()
  {
  }

  public String getCity()
  {
    return city;
  }

  public void setCity(String city)
  {
    this.city = city;
  }

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

  public String getState()
  {
    return state;
  }

  public void setState(String state)
  {
    this.state = state;
  }

  public String getStreet1()
  {
    return street1;
  }

  public void setStreet1(String street1)
  {
    this.street1 = street1;
  }

  public String getStreet2()
  {
    return street2;
  }

  public void setStreet2(String street2)
  {
    this.street2 = street2;
  }

  public Integer getVersion()
  {
    return version;
  }

  public void setVersion(Integer version)
  {
    this.version = version;
  }

  public String getZipCode()
  {
    return zipCode;
  }

  public void setZipCode(String zipCode)
  {
    this.zipCode = zipCode;
  }
}
