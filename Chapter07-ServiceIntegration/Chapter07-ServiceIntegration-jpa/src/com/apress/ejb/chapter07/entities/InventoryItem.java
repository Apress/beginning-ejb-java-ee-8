package com.apress.ejb.chapter07.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
  @NamedQuery(name = "InventoryItem.findAll", query = "select o from InventoryItem o"),
  @NamedQuery(name = "InventoryItem.findItemByWine", query = "select o from InventoryItem o where o.wine = :wine")})
@Table(name = "INVENTORY_ITEM")
public class InventoryItem extends WineItem {
  @Temporal(TemporalType.DATE)
  @Column(name = "STOCK_DATE")
  private Date stockDate;
  @Column(name = "WHOLESALE_PRICE")
  private Float wholesalePrice;

  public InventoryItem() {
  }

  public InventoryItem(int quantity, Wine wine, Date stockDate, Float wholesalePrice) {
    super(quantity, wine);
    setStockDate(stockDate);
    setWholesalePrice(wholesalePrice);
  }

  public Date getStockDate() {
    return stockDate;
  }

  public void setStockDate(Date stockDate) {
    this.stockDate = stockDate;
  }

  public Float getWholesalePrice() {
    return wholesalePrice;
  }

  public void setWholesalePrice(Float wholesalePrice) {
    this.wholesalePrice = wholesalePrice;
  }
}
