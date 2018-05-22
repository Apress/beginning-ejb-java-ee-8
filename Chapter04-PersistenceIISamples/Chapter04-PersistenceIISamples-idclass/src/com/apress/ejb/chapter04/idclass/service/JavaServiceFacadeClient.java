package com.apress.ejb.chapter04.idclass.service;

import com.apress.ejb.chapter04.idclass.Address;
import com.apress.ejb.chapter04.idclass.MyIdClass;
import com.apress.ejb.chapter04.idclass.Person;
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
      for (Person person : (List<Person>) javaServiceFacade.getPersonFindAll()) {
        javaServiceFacade.removePerson(person);
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

      Person p = new Person();
      p.setFirstName("Brian");
      p.setLastName("Jones");
      p.setHomeAddress(add);
      javaServiceFacade.persistEntity(p);

      //-----------------------------------------------------------------------------------
      //  Retrieve the entities through their type-specific JPQL queries and print them out
      //-----------------------------------------------------------------------------------
      System.out.println("\nPersons:\n");
      for (Person person : (List<Person>) javaServiceFacade.getPersonFindAll()) {
        printPerson(person);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private static void printPerson(Person person) {
    System.out.println("version = " + person.getVersion());
    System.out.println("firstName = "  + person.getFirstName());
    System.out.println("lastName = "  + person.getLastName());
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
