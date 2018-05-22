package com.apress.ejb.chapter09.joined;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * To create ID generator table "WINE_ITEM_ID_GEN": CREATE TABLE
 * "WINE_ITEM_ID_GEN" ("PRIMARY_KEY_NAME" VARCHAR2(4000) PRIMARY
 * KEY, "NEXT_ID_VALUE" NUMBER(38));
 *
 * To initialize this table with data for this entity's ID generator
 * 'WineItem.id' (starting with value '0'): INSERT INTO
 * "WINE_ITEM_ID_GEN" VALUES ('WineItem.id', 0);
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
  @NamedQuery(name = "WineItem.findAll", query = "select o from WineItem o")})
@Table(name = "WINE_ITEM")
@TableGenerator(name = "WineItem_ID_Generator", table = "WINE_ITEM_ID_GEN", pkColumnName = "PRIMARY_KEY_NAME",
                pkColumnValue = "WineItem.id", valueColumnName = "NEXT_ID_VALUE")
public abstract class WineItem implements Serializable {
  @Id
  @Column(nullable = false)
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "WineItem_ID_Generator")
  private Integer id;
  private int quantity;
  private Integer version;
  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn(name = "WINE_ID")
  private Wine wine;

  public WineItem() {
  }

  public WineItem(int quantity, Wine wine) {
    this.setQuantity(quantity);
    this.setWine(wine);
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public Wine getWine() {
    return wine;
  }

  public void setWine(Wine wine) {
    this.wine = wine;
  }
}
