package com.apress.ejb.chapter09.singletable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.TableGenerator;

/**
 * To create ID generator table "WINE_ID_GEN": CREATE TABLE "WINE_ID_GEN" ("PRIMARY_KEY_NAME"
 * VARCHAR2(4000) PRIMARY KEY, "NEXT_ID_VALUE" NUMBER(38));
 *
 * To initialize this table with data for this entity's ID generator 'Wine.id' (starting with value
 * '0'): INSERT INTO "WINE_ID_GEN" VALUES ('Wine.id', 0);
 */
@Entity
@NamedQueries({
  @NamedQuery(name = "Wine.findAll", query = "select o from Wine o"),
  @NamedQuery(name = "Wine.findByYear", query = "select object(wine) from Wine wine where wine.year = :year"),
  @NamedQuery(name = "Wine.findByCountry", query = "select object(wine) from Wine wine where wine.country = :country"),
  @NamedQuery(name = "Wine.findByVarietal",
              query = "select object(wine) from Wine wine where wine.varietal = :varietal")
})
@TableGenerator(name = "Wine_ID_Generator", table = "WINE_ID_GEN", pkColumnName = "PRIMARY_KEY_NAME", pkColumnValue = "Wine.id",
                valueColumnName = "NEXT_ID_VALUE")
public class Wine implements Serializable {
  @Column(length = 4000)
  private String country;
  @Column(length = 4000)
  private String description;
  @Id
  @Column(nullable = false)
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "Wine_ID_Generator")
  private Integer id;
  @Column(nullable = false, length = 4000)
  private String name;
  private int rating;
  @Column(length = 4000)
  private String region;
  @Column(name = "RETAIL_PRICE")
  private Float retailPrice;
  @Column(length = 4000)
  private String varietal;
  private Integer version;
  @Column(name = "YEAR_")
  private int year;
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(name = "WINE_SUPPLIER", joinColumns = {
    @JoinColumn(name = "WINE_ID", referencedColumnName = "ID")},
             inverseJoinColumns = {
    @JoinColumn(name = "SUPPLIER_ID", referencedColumnName = "ID")})
  private List<Supplier> supplierList;

  public Wine() {
  }

  public Wine(String country, String description, String name, int rating, String region, Float retailPrice, String varietal,
              int year) { 
    setCountry(country);
    setDescription(description);
    setName(name);
    setRating(rating);
    setRegion(region);
    setRetailPrice(retailPrice);
    setVarietal(varietal);
    setYear(year);
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public Float getRetailPrice() {
    return retailPrice;
  }

  public void setRetailPrice(Float retailPrice) {
    this.retailPrice = retailPrice;
  }

  public String getVarietal() {
    return varietal;
  }

  public void setVarietal(String varietal) {
    this.varietal = varietal;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public List<Supplier> getSupplierList() {
    if (supplierList == null) {
      supplierList = new ArrayList();
    }
    return supplierList;
  }
}
