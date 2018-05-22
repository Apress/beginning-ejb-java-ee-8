package com.apress.ejb.chapter04.embeddable.service;

import com.apress.ejb.chapter04.embeddable.Address;
import com.apress.ejb.chapter04.embeddable.Employee;
import com.apress.ejb.chapter04.embeddable.FullTimeEmployee;
import com.apress.ejb.chapter04.embeddable.PartTimeEmployee;
import com.apress.ejb.chapter04.embeddable.Person;
import java.math.BigDecimal;
import java.util.List;

public class JavaServiceFacadeClient {
  public static void main(String[] args) {
    try {
      final JavaServiceFacade javaServiceFacade = new JavaServiceFacade();

      //-----------------------------------------------------------------------------------
      //  Clear out any previous test data. Due to “cascade” settings on the
      //  “Person.homeAddress” relationship field, removing a Person will remove its
      //  Address as well.
      //-----------------------------------------------------------------------------------
      for (PartTimeEmployee parttimeemployee : (List<PartTimeEmployee>) javaServiceFacade.getPartTimeEmployeeFindAll()) {
        javaServiceFacade.removePartTimeEmployee(parttimeemployee);
      }
      for (FullTimeEmployee fulltimeemployee : (List<FullTimeEmployee>) javaServiceFacade.getFullTimeEmployeeFindAll()) {
        javaServiceFacade.removeFullTimeEmployee(fulltimeemployee);
      }

      //-----------------------------------------------------------------------------------
      //  Create FullTimeEmployee and PartTimeEmployee instances, along with their Address
      //  objects, and persist them in the database.
      //-----------------------------------------------------------------------------------
      Address add = new Address();
      add.setCity("San Mateo");
      add.setState("CA");
      add.setStreet1("1301 Ashwood Ct");
      add.setZipCode("94402");
      //  Note that we don't persist the Address, since it is embedded in the Person object

      FullTimeEmployee ft = new FullTimeEmployee();
      ft.setAnnualSalary(1000D);
      ft.setDepartment("HQ");
      ft.setEmail("x@y.com");
      ft.setFirstName("Brian");
      ft.setLastName("Jones");
      ft.setHomeAddress(add);
      ft = javaServiceFacade.persistEntity(ft);

      add = new Address();
      add.setCity("San Francisco");
      add.setState("CA");
      add.setStreet1("53 Surrey St");
      add.setZipCode("94131");
      //  Note that we don't persist the Address, since it is embedded in the Person object

      final PartTimeEmployee pt = new PartTimeEmployee();
      pt.setHourlyWage(100D);
      pt.setDepartment("SALES");
      pt.setEmail("a@b.com");
      pt.setFirstName("David");
      pt.setLastName("Holmes");
      pt.setHomeAddress(add);
      pt.setManager(ft);
      javaServiceFacade.persistEntity(pt);

      //-----------------------------------------------------------------------------------
      //  Retrieve the entities through their type-specific JPQL queries and print them out
      //-----------------------------------------------------------------------------------
      System.out.println("\nPersons:\n");
      for (Person person : (List<Person>) javaServiceFacade.getPersonFindAll()) {
        printPerson(person);
      }
      System.out.println("\nEmployees:\n");
      for (Employee employee : (List<Employee>) javaServiceFacade.getEmployeeFindAll()) {
        printEmployee(employee);
      }
      System.out.println("\nPartTimeEmployees:\n");
      for (PartTimeEmployee parttimeemployee : (List<PartTimeEmployee>) javaServiceFacade.getPartTimeEmployeeFindAll()) {
        printPartTimeEmployee(parttimeemployee);
      }
      System.out.println("\nFullTimeEmployees:\n");
      for (FullTimeEmployee fulltimeemployee : (List<FullTimeEmployee>) javaServiceFacade.getFullTimeEmployeeFindAll()) {
        printFullTimeEmployee(fulltimeemployee);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private static void printEmployee(Employee employee) {
    System.out.println("dept = " + employee.getDepartment());
    System.out.println("email = " + employee.getEmail());
    System.out.println("manager = " + employee.getManager());
    System.out.println("firstName = " + employee.getFirstName());
    System.out.println("id = " + employee.getId());
    System.out.println("lastName = " + employee.getLastName());
    System.out.println("version = " + employee.getVersion());
    printAddress(employee.getHomeAddress());
  }

  private static void printFullTimeEmployee(FullTimeEmployee fulltimeemployee) {
    System.out.println("annualSalary = " + fulltimeemployee.getAnnualSalary());
    System.out.println("managedEmployees = " + fulltimeemployee.getManagedEmployees());
    System.out.println("dept = " + fulltimeemployee.getDepartment());
    System.out.println("email = " + fulltimeemployee.getEmail());
    System.out.println("manager = " + fulltimeemployee.getManager());
    System.out.println("firstName = " + fulltimeemployee.getFirstName());
    System.out.println("id = " + fulltimeemployee.getId());
    System.out.println("lastName = " + fulltimeemployee.getLastName());
    System.out.println("version = " + fulltimeemployee.getVersion());
    printAddress(fulltimeemployee.getHomeAddress());
  }

  private static void printPartTimeEmployee(PartTimeEmployee parttimeemployee) {
    System.out.println("hourlyWage = " + parttimeemployee.getHourlyWage());
    System.out.println("dept = " + parttimeemployee.getDepartment());
    System.out.println("email = " + parttimeemployee.getEmail());
    System.out.println("manager = " + parttimeemployee.getManager());
    System.out.println("firstName = " + parttimeemployee.getFirstName());
    System.out.println("id = " + parttimeemployee.getId());
    System.out.println("lastName = " + parttimeemployee.getLastName());
    System.out.println("version = " + parttimeemployee.getVersion());
    printAddress(parttimeemployee.getHomeAddress());
  }

  private static void printPerson(Person person) {
    System.out.println("firstName = " + person.getFirstName());
    System.out.println("id = " + person.getId());
    System.out.println("lastName = " + person.getLastName());
    System.out.println("version = " + person.getVersion());
    printAddress(person.getHomeAddress());
  }

  private static void printAddress(Address address) {
    if (address == null)
    {
      return;
    }
    System.out.println("city = " + address.getCity());
    System.out.println("state = " + address.getState());
    System.out.println("street1 = " + address.getStreet1());
    System.out.println("street2 = " + address.getStreet2());
    System.out.println("zipCode = " + address.getZipCode());
  }
}
