package com.apress.ejb.chapter12.view.managed;

public class SearchWines
{
  public SearchWines() {
  }

  public String year;
  public String varietal;
  public String country;

  public void setYear(String year) {
    this.year = year;
  }

  public String getYear() {
    return year;
  }

  public void setVarietal(String varietal) {
    this.varietal = varietal;
  }

  public String getVarietal() {
    return varietal;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCountry() {
    return country;
  }
}
