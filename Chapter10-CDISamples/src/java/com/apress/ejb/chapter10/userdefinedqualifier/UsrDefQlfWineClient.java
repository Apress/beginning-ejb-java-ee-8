package com.apress.ejb.chapter10.userdefinedqualifier;

import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UsrDefQlfWineClient", urlPatterns = {"/UsrDefQlfWineClient"})
class UsrDefQlfWineClient extends HttpServlet {

   // Instantiating the classical way
   private Wine oldWine = new RedWine();

   // Instantiating via field dependency injection.
   // If we comment out the @Red annotation then the container will instantiate
   // the WhiteWine class. 
   // Try commenting and uncommenting the @Red annotation and see the difference.
   // Try adding @White annotation instead of @Red and see the difference.
   @Inject
   @Red
   private Wine newWine;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UsrDefQlfWineClient</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Creating a new Red Wine the classical way : " + oldWine.getColor() + "</h2>");
            out.println("<h4>(Note: For instantiating oldWine we had to use new RedWine() resulting in tight coupling.) </h4>");
            out.println("<h2>Creating a new Red Wine using dependency injection: " + newWine.getColor() + "</h2>");
            out.println("<h4>(Note: Using DI, we use the interface name and the container instantiates the class for us.) </h4>");
      
            out.println("</body>");
            out.println("</html>");
        } finally {            
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
