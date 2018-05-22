package com.apress.ejb.chapter04.joined;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/*
 * PartTimeEmployee:  A concrete leaf entity
 */
@Entity
@NamedQueries({
  @NamedQuery(name = "PartTimeEmployee.findAll", query = "select o from PartTimeEmployee o")})
@Table(name = "CH04_JOIN_PT_EMPLOYEE")
public class PartTimeEmployee extends Employee implements Serializable {
  @SuppressWarnings("compatibility:-4882346458268010846")
  private static final long serialVersionUID = 4017999239159878209L;
  @Column(name = "HOURLY_WAGE")
  private double hourlyWage;

  public PartTimeEmployee() {
  }

  public double getHourlyWage() {
    return hourlyWage;
  }

  public void setHourlyWage(double hourlyWage) {
    this.hourlyWage = hourlyWage;
  }
}
