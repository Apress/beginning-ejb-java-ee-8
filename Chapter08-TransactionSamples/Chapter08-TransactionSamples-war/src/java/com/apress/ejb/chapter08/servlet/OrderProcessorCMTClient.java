package com.apress.ejb.chapter08.servlet;

import com.apress.ejb.chapter07.entities.CartItem;
import com.apress.ejb.chapter07.entities.CustomerOrder;
import com.apress.ejb.chapter07.entities.Individual;
import com.apress.ejb.chapter07.entities.OrderItem;
import com.apress.ejb.chapter07.entities.Wine;
import com.apress.ejb.chapter07.entities.test.PopulateDemoData;
import com.apress.ejb.chapter08.ejb.OrderProcessorCMTBean;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Timestamp;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "OrderProcessorCMTClient", urlPatterns = {"/OrderProcessorCMTClient"})
public class OrderProcessorCMTClient extends HttpServlet {
  @EJB
  OrderProcessorCMTBean orderProcessorCMT;

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
    response.setContentType("text/html;charset=UTF-8");
    OutputStream rOut = response.getOutputStream();
    PrintStream out = new PrintStream(rOut);
    try {
      /* TODO output your page here. You may use following sample code. */
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet OrderProcessorCMTClient</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Servlet OrderProcessorCMTClient at " + request.getContextPath() + "</h1>");
      out.println("</body>");
      out.println("</html>");

      //  Create and persist a bunch of JPA entities, populating the database with data
      out.print("<h2>Populating Demo Data... ");
      PopulateDemoData.resetData("Chapter07-WineAppUnit-ResourceLocal", System.out);
      out.println("done</h2>");

      //  Filter the data by removing any existing Customers with email
      //  'wineapp@yahoo.com' (or whatever is defined in the user.properties file).
      //  The first call to a transactional method on OrderProcessorBMT will begin a
      //  transaction.
      out.print("<h2>Filtering Demo Data... ");
      System.out.println(orderProcessorCMT.initialize());
      out.println("done</h2>");

      //  Create a Customer and add some CartItems and their associated Wines
      Individual customer = new Individual();
      customer.setFirstName("Transaction");
      customer.setLastName("Head");
      customer.setEmail(PopulateDemoData.TO_EMAIL_ADDRESS);
      for (int i = 0; i < 5; i++) {
        final Wine wine = new Wine();
        wine.setCountry("United States");
        wine.setDescription("Delicious wine");
        wine.setName("Xacti");
        wine.setRegion("Dry Creek Valley");
        wine.setRetailPrice(new Float(20.00D + i));
        wine.setVarietal("Zinfandel");
        wine.setYear(2000 + i);
        orderProcessorCMT.persistEntity(wine);

        final CartItem cartItem = new CartItem();
        cartItem.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        cartItem.setCustomer(customer);
        cartItem.setQuantity(12);
        cartItem.setWine(wine);

        customer.addCartItem(cartItem);
      }

      //  Persist the Customer, relying on the cascade settings to persist all
      //  related CartItem entities as well. After the call, the Customer
      //  instance will have an ID value that was assigned by the EJB container
      //  when it was persisted.
      orderProcessorCMT.persistEntity(customer);

      //  Create a customer order and create OrderItems from the CartItems
      final CustomerOrder customerOrder =
        orderProcessorCMT.createCustomerOrder(customer);

      out.print("<h2>Retrieving Customer Order Items... ");
      for (OrderItem orderItem: customerOrder.getOrderItemList()) {
        final Wine wine = orderItem.getWine();
        out.println(wine.getName() + " with ID " + wine.getId());
      }
      out.println("done</h2>");
    } finally {
      rOut.close();
      out.close();
    }
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
