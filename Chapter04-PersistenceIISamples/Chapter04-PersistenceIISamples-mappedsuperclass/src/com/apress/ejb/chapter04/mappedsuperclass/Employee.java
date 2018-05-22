package com.apress.ejb.chapter04.mappedsuperclass;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/*
 * Employee:  The root of an inheritance hierarchy. Extends Person, a Mapped Superclass.
 */
@Entity
@NamedQueries({
  @NamedQuery(name = "Employee.findAll", query = "select o from Employee o")})
@Table(name = "CH04_MS_EMPLOYEE")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Employee extends Person implements Serializable {
  @SuppressWarnings("compatibility:276774077273820023")
  private static final long serialVersionUID = -8529011412038476148L;
  @Column(length = 400)
  private String department;
  @Column(length = 400)
  private String email;
  @ManyToOne
  @JoinColumn(name = "MANAGER")
  private FullTimeEmployee manager;

  public Employee() {
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public FullTimeEmployee getManager() {
    return manager;
  }

  public void setManager(FullTimeEmployee manager) {
    this.manager = manager;
  }
}
