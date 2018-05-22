package com.apress.ejb.chapter07.servlet;

import com.apress.ejb.chapter07.ejb.SearchFacadeLocal;
import com.apress.ejb.chapter07.ejb.ShoppingCartLocal;
import com.apress.ejb.chapter07.entities.Customer;
import com.apress.ejb.chapter07.entities.Wine;
import com.apress.ejb.chapter07.entities.test.PopulateDemoData;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ShoppingCartClient", urlPatterns = {"/ShoppingCartClient"})
public class ShoppingCartClient extends HttpServlet {
  @EJB
  private SearchFacadeLocal searchFacade;
  @EJB
  private ShoppingCartLocal shoppingCart;

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
    OutputStream rOut = response.getOutputStream();
    PrintStream out = new PrintStream(rOut);
    try {
      /* TODO output your page here. You may use following sample code. */
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet ShoppingCartClient</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Servlet ShoppingCartClient at " + request.getContextPath() + "</h1>");
      out.println("</body>");
      out.println("</html>");

      out.print("<h2>Populating Demo Data... ");
      new PopulateDemoData("Chapter07-WineAppUnit-ResourceLocal").resetData(System.out);
      out.println("done</h2>");

      out.print("<h2>Calling the ShoppingCart to find and cache customer with email address " + PopulateDemoData.TO_EMAIL_ADDRESS + "... ");
      final Customer customer = shoppingCart.findCustomer(PopulateDemoData.TO_EMAIL_ADDRESS);
      out.println("found " + customer.getFirstName() + " " + customer.getLastName() + "</h2>");

      out.println("<h2>Calling the SearchFacade to find wines from 2004</h2>");
      List<Wine> yearWineList = searchFacade.getWineFindByYear(2004);
      if (yearWineList != null) {
        for (Wine wine : yearWineList) {
          shoppingCart.addWineItem(wine, 20);
          out.println("<h3>Added cart item for 20 bottles of " + wine.getName() + " " + wine.getYear() + "</h3>");
        }
      }


      out.print("<h2>Calling the ShoppingCart to send the order to the Order Processing Center... ");
      shoppingCart.sendOrderToOPC();
      out.println("done</h2>");
    } catch (Exception ex) {
      ex.printStackTrace();
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
