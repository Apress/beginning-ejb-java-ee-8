/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.ejb.chapter04.client;

import com.apress.ejb.chapter04.embeddedid.Address;
import com.apress.ejb.chapter04.embeddedid.MyIdClass;
import com.apress.ejb.chapter04.embeddedid.Person;
import com.apress.ejb.chapter04.embeddedid.service.JavaServiceFacade;
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
@WebServlet(name = "EmbeddedIdClient", urlPatterns = {"/EmbeddedIdClient"})
public class EmbeddedIdClient extends HttpServlet {
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
      out.println("<title>Servlet EmbeddedIdClient</title>");
      out.println("</head>");
      out.println("<body>");
      outH1(out, "Servlet EmbeddedIdClient at " + request.getContextPath());

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
    outH3(out, "Deleting any existing Person hierarchy data");

    for (Person person : (List<Person>) javaServiceFacade.getPersonFindAll()) {
      outH4(out, "Deleting person (and related objects) " + person.getMyId());
      javaServiceFacade.removePerson(person);
    }
  }

  private void createData(final PrintWriter out, final JavaServiceFacade javaServiceFacade) {
    outH3(out, "Creating and persisting new MyIdClass for Person");
    MyIdClass id = createMyIdClass();
    outH3(out, "Creating and persisting new Address for Person");
    Address add = createAddress();
    outH3(out, "Creating and persisting new FullTimeEmployee");
    createPerson(id, add, javaServiceFacade);
  }

  private Address createAddress() {
    Address add = new Address();
    add.setCity("San Mateo");
    add.setState("CA");
    add.setStreet1("1301 Ashwood Ct");
    add.setZipCode("94402");
    return add;
  }

  private MyIdClass createMyIdClass() {
    MyIdClass id = new MyIdClass();
    id.setFirstName("Brian");
    id.setLastName("Jones");
    return id;
  }

  private Person createPerson(MyIdClass id, Address add, final JavaServiceFacade javaServiceFacade) {
    Person p = new Person();
    p.setMyId(id);
    p.setHomeAddress(add);
    p = javaServiceFacade.persistEntity(p);
    return p;
  }

  private void printData(PrintWriter out, final JavaServiceFacade javaServiceFacade) {
    outH1(out, "\nPersons:\n");
    for (Person person : (List<Person>) javaServiceFacade.getPersonFindAll()) {
      printPerson(out, person);
    }
  }

  private static void printPerson(final PrintWriter out, final Person person) {
    out.println("<table border=\"1\">");
    out.println("<caption><b>" + person.getClass().getSimpleName() + "</b></>");

    printAttribute(out, "myId", person.getMyId());
    printAttribute(out, "homeAddress", person.getHomeAddress());
    printAttribute(out, "version", person.getVersion());

    out.println("</table>");
  }

  private static void printAddress(final PrintWriter out, final Address address) {
    out.println("<table border=\"1\">");
    out.println("<caption><b>" + address.getClass().getSimpleName() + "</b></>");

    printAttribute(out, "city", address.getCity());
    printAttribute(out, "state", address.getState());
    printAttribute(out, "street1", address.getStreet1());
    printAttribute(out, "street2", address.getStreet2());
    printAttribute(out, "zipCode", address.getZipCode());

    out.println("</table>");
  }

  private static void printMyId(final PrintWriter out, final MyIdClass myId) {
    out.println("<table border=\"1\">");
    out.println("<caption><b>" + myId.getClass().getSimpleName() + "</b></>");

    printAttribute(out, "firstName", myId.getFirstName());
    printAttribute(out, "lastName", myId.getLastName());

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
    } else if (val instanceof MyIdClass) {
      printMyId(out, (MyIdClass) val);
    } else if (val instanceof List) {
      for (Object obj : (List) val) {
        if (obj instanceof Address) {
          printAddress(out, (Address) obj);
          if (obj instanceof MyIdClass) {
            printMyId(out, (MyIdClass) obj);
          } else if (obj instanceof Person) {
            printPerson(out, (Person) obj);
          } else {
            out.print(obj);
          }
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
