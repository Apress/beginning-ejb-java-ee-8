/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.ejb.chapter04.client;

import com.apress.ejb.chapter04.singletable.Address;
import com.apress.ejb.chapter04.singletable.Employee;
import com.apress.ejb.chapter04.singletable.FullTimeEmployee;
import com.apress.ejb.chapter04.singletable.PartTimeEmployee;
import com.apress.ejb.chapter04.singletable.Person;
import com.apress.ejb.chapter04.singletable.service.JavaServiceFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jwetherb
 */
@WebServlet(name = "SingleTableInheritanceClient", urlPatterns = {"/SingleTableInheritanceClient"})
public class SingleTableInheritanceClient extends HttpServlet {
  /**
   * Processes requests for both HTTP
   * <code>GET</code> and
   * <code>POST</code> methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    try {
      /* TODO output your page here. You may use following sample code. */
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet SingleTableInheritanceClient</title>");
      out.println("</head>");
      out.println("<body>");
      outH1(out, "Servlet SingleTableInheritanceClient at " + request.getContextPath());

      final JavaServiceFacade javaServiceFacade = new JavaServiceFacade();

      //-----------------------------------------------------------------------------------
      //  Clear out any previous test data. Due to “cascade” settings on the
      //  “Person.homeAddress” relationship field, removing a Person will remove its
      //  Address as well.
      //-----------------------------------------------------------------------------------
      cleanTables(out, javaServiceFacade);

      //-----------------------------------------------------------------------------------
      //  Create FullTimeEmployee and PartTimeEmployee instances, along with their Address
      //  objects, and persist them in the database.
      //-----------------------------------------------------------------------------------
      createData(out, javaServiceFacade);

      //-----------------------------------------------------------------------------------
      //  Retrieve the entities through their type-specific JPQL queries and print them out
      //-----------------------------------------------------------------------------------
      printData(out, javaServiceFacade);

      outH3(out, "</body>");
      outH3(out, "</html>");
    } finally {
      out.close();
    }
  }

  private void cleanTables(final PrintWriter out, final JavaServiceFacade javaServiceFacade) {
    outH3(out, "Deleting any existing Address and Person hierarchy data");

    for (PartTimeEmployee parttimeemployee : (List<PartTimeEmployee>) javaServiceFacade.getPartTimeEmployeeFindAll()) {
      outH4(out, "Deleting parttimeemployee (and related objects) " + parttimeemployee.getId());
      javaServiceFacade.removePartTimeEmployee(parttimeemployee);
    }
    for (FullTimeEmployee fulltimeemployee : (List<FullTimeEmployee>) javaServiceFacade.getFullTimeEmployeeFindAll()) {
      outH4(out, "Deleting fulltimeemployee (and related objects) " + fulltimeemployee.getId());
      javaServiceFacade.removeFullTimeEmployee(fulltimeemployee);
    }
  }

  private void createData(final PrintWriter out, final JavaServiceFacade javaServiceFacade) {
    outH3(out, "Creating and persisting new Address for FullTimeEmployee");
    Address add = createFTAddress(javaServiceFacade);
    outH3(out, "Creating and persisting new FullTimeEmployee");
    FullTimeEmployee ft = createFTEmployee(add, javaServiceFacade);
    outH3(out, "Creating and persisting new Address for PartTimeEmployee");
    add = createPTAddress(javaServiceFacade);
    outH3(out, "Creating and persisting new PartTimeEmployee");
    createPTEmployee(add, ft, javaServiceFacade);
  }

  private Address createFTAddress(final JavaServiceFacade javaServiceFacade) {
    Address add = new Address();
    add.setCity("San Mateo");
    add.setState("CA");
    add.setStreet1("1301 Ashwood Ct");
    add.setZipCode("94402");
    javaServiceFacade.persistEntity(add);
    return add;
  }

  private FullTimeEmployee createFTEmployee(Address add, final JavaServiceFacade javaServiceFacade) {
    FullTimeEmployee ft = new FullTimeEmployee();
    ft.setAnnualSalary(1000D);
    ft.setDepartment("HQ");
    ft.setEmail("x@y.com");
    ft.setFirstName("Brian");
    ft.setLastName("Jones");
    ft.setHomeAddress(add);
    ft = javaServiceFacade.persistEntity(ft);
    return ft;
  }

  private Address createPTAddress(final JavaServiceFacade javaServiceFacade) {
    Address add;
    add = new Address();
    add.setCity("San Francisco");
    add.setState("CA");
    add.setStreet1("53 Surrey St");
    add.setZipCode("94131");
    javaServiceFacade.persistEntity(add);
    return add;
  }

  private void createPTEmployee(Address add, FullTimeEmployee ft, final JavaServiceFacade javaServiceFacade) {
    final PartTimeEmployee pt = new PartTimeEmployee();
    pt.setHourlyWage(100D);
    pt.setDepartment("SALES");
    pt.setEmail("a@b.com");
    pt.setFirstName("David");
    pt.setLastName("Holmes");
    pt.setHomeAddress(add);
    pt.setManager(ft);
    javaServiceFacade.persistEntity(pt);
  }

  private void printData(PrintWriter out, final JavaServiceFacade javaServiceFacade) {
    outH1(out, "\nPersons:\n");
    for (Person person : (List<Person>) javaServiceFacade.getPersonFindAll()) {
      printPerson(out, person);
    }
    outH1(out, "\nEmployees:\n");
    for (Employee employee : (List<Employee>) javaServiceFacade.getEmployeeFindAll()) {
      printEmployee(out, employee);
    }
    outH1(out, "\nPartTimeEmployees:\n");
    for (PartTimeEmployee parttimeemployee : (List<PartTimeEmployee>) javaServiceFacade.getPartTimeEmployeeFindAll()) {
      printPartTimeEmployee(out, parttimeemployee);
    }
    outH1(out, "\nFullTimeEmployees:\n");
    for (FullTimeEmployee fulltimeemployee : (List<FullTimeEmployee>) javaServiceFacade.getFullTimeEmployeeFindAll()) {
      printFullTimeEmployee(out, fulltimeemployee);
    }
    outH1(out, "\nAddresses:\n");
    for (Address address : (List<Address>) javaServiceFacade.getAddressFindAll()) {
      printAddress(out, address);
    }
  }

  private static void printEmployee(final PrintWriter out, final Employee employee) {
    out.println("<table border=\"1\">");
    out.println("<caption><b>" + employee.getClass().getSimpleName() + "</b></>");

    printAttribute(out, "dept", employee.getDepartment());
    printAttribute(out, "email", employee.getEmail());
    printAttribute(out, "manager", employee.getManager());
    printAttribute(out, "firstName", employee.getFirstName());
    printAttribute(out, "id", employee.getId());
    printAttribute(out, "lastName", employee.getLastName());
    printAttribute(out, "version", employee.getVersion());
    printAttribute(out, "homeAddress", employee.getHomeAddress());

    out.println("</table>");
  }

  private static void printFullTimeEmployee(final PrintWriter out, final FullTimeEmployee fulltimeemployee) {
    out.println("<table border=\"1\">");
    out.println("<caption><b>" + fulltimeemployee.getClass().getSimpleName() + "</b></>");

    printAttribute(out, "annualSalary", fulltimeemployee.getAnnualSalary());
    printAttribute(out, "managedEmployees", fulltimeemployee.getManagedEmployees());
    printAttribute(out, "dept", fulltimeemployee.getDepartment());
    printAttribute(out, "email", fulltimeemployee.getEmail());
    printAttribute(out, "manager", fulltimeemployee.getManager());
    printAttribute(out, "firstName", fulltimeemployee.getFirstName());
    printAttribute(out, "id", fulltimeemployee.getId());
    printAttribute(out, "lastName", fulltimeemployee.getLastName());
    printAttribute(out, "version", fulltimeemployee.getVersion());
    printAttribute(out, "homeAddress", fulltimeemployee.getHomeAddress());

    out.println("</table>");
  }

  private static void printPartTimeEmployee(final PrintWriter out, final PartTimeEmployee parttimeemployee) {
    out.println("<table border=\"1\">");
    out.println("<caption><b>" + parttimeemployee.getClass().getSimpleName() + "</b></>");

    printAttribute(out, "hourlyWage", parttimeemployee.getHourlyWage());
    printAttribute(out, "dept", parttimeemployee.getDepartment());
    printAttribute(out, "email", parttimeemployee.getEmail());
    printAttribute(out, "manager", parttimeemployee.getManager());
    printAttribute(out, "firstName", parttimeemployee.getFirstName());
    printAttribute(out, "id", parttimeemployee.getId());
    printAttribute(out, "lastName", parttimeemployee.getLastName());
    printAttribute(out, "version", parttimeemployee.getVersion());
    printAttribute(out, "homeAddress", parttimeemployee.getHomeAddress());

    out.println("</table>");
  }

  private static void printPerson(final PrintWriter out, final Person person) {
    out.println("<table border=\"1\">");
    out.println("<caption><b>" + person.getClass().getSimpleName() + "</b></>");

    printAttribute(out, "firstName", person.getFirstName());
    printAttribute(out, "id", person.getId());
    printAttribute(out, "lastName", person.getLastName());
    printAttribute(out, "version", person.getVersion());
    printAttribute(out, "homeAddress", person.getHomeAddress());

    out.println("</table>");
  }

  private static void printAddress(final PrintWriter out, final Address address) {
    out.println("<table border=\"1\">");
    out.println("<caption><b>" + address.getClass().getSimpleName() + "</b></>");

    printAttribute(out, "id", address.getId());
    printAttribute(out, "version", address.getVersion());
    printAttribute(out, "city", address.getCity());
    printAttribute(out, "state", address.getState());
    printAttribute(out, "street1", address.getStreet1());
    printAttribute(out, "street2", address.getStreet2());
    printAttribute(out, "version", address.getVersion());
    printAttribute(out, "zipCode", address.getZipCode());

    out.println("</table>");
  }

  private static void outH1(PrintWriter out, String text) {
    out.println("<h1>" + text + "</h1>");
  }

  private static void outH3(PrintWriter out, String text) {
    out.println("<h3>" + text + "</h3>");
  }

  private static void outH4(PrintWriter out, String text) {
    out.println("<h4>" + text + "</h4>");
  }

  private static void printAttribute(PrintWriter out, String attr, Object val) {
    out.println("<tr>");
    out.println("<td>" + attr + "</td>");
    out.println("<td>");
    if (val instanceof Address) {
      printAddress(out, (Address) val);
    } else if (val instanceof Person) {
      printPerson(out, (Person) val);
    } else if (val instanceof Employee) {
      printEmployee(out, (Employee) val);
    } else if (val instanceof FullTimeEmployee) {
      printFullTimeEmployee(out, (FullTimeEmployee) val);
    } else if (val instanceof PartTimeEmployee) {
      printPartTimeEmployee(out, (PartTimeEmployee) val);
    } else if (val instanceof List) {
      for (Object obj : (List) val) {
        if (obj instanceof Address) {
          printAddress(out, (Address) obj);
        } else if (obj instanceof Employee) {
          printEmployee(out, (Employee) obj);
        } else if (obj instanceof FullTimeEmployee) {
          printFullTimeEmployee(out, (FullTimeEmployee) obj);
        } else if (obj instanceof PartTimeEmployee) {
          printPartTimeEmployee(out, (PartTimeEmployee) obj);
        } else {
          out.print(obj);
        }
      }
    } else {
      out.print(val);
    }
    out.println("</td>");
    out.println("</tr>");
  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP
   * <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Handles the HTTP
   * <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>
}
