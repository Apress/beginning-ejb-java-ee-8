package com.apress.ejb.chapter03.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * To create ID generator table "CUSTOMERORDER_ID_GENERATOR": CREATE TABLE
 * "CH03_CUSTOMER_ORDER_ID_GEN" ("PRIMARY_KEY_NAME" VARCHAR(4000) PRIMARY KEY,
 * "NEXT_ID_VALUE" BIGINT);
 *
 * To initialize this table with data for this entity's ID generator
 * 'CustomerOrder.id' (starting with value '0'): INSERT INTO
 * "CH03_CUSTOMER_ORDER_ID_GEN" VALUES ('CustomerOrder.id', 0);
 */
@Entity
@NamedQueries({
  @NamedQuery(name = "CustomerOrder.findAll", 
              query = "select o from CustomerOrder o")})
@Table(name = "CH03_CUSTOMER_ORDER")
@TableGenerator(name = "CustomerOrder_ID_Generator", 
                table = "CH03_CUSTOMER_ORDER_ID_GEN",
pkColumnName = "PRIMARY_KEY_NAME", pkColumnValue = "CustomerOrder.id",
valueColumnName = "NEXT_ID_VALUE")
public class CustomerOrder implements Serializable {

  @Id
  @Column(nullable = false)
  @GeneratedValue(strategy = GenerationType.TABLE,
                  generator = "CustomerOrder_ID_Generator")
  private BigDecimal id;
  @Version
  private int version;
  @Temporal(TemporalType.DATE)
  @Column(name = "CREATION_DATE")
  private Date creationDate;
  private String status;
  @ManyToOne
  @JoinColumn(name = "CUSTOMER_ID")
  private Customer customer;

  public Date getCreationDate() { return creationDate; }
  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public BigDecimal getId() { return id; }
  public void setId(BigDecimal id) { this.id = id; }

  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }

  public int getVersion() { return version; }
  public void setVersion(int version) { this.version = version; }

  public Customer getCustomer() { return customer; }
  public void setCustomer(Customer customer) { this.customer = customer; }
}
