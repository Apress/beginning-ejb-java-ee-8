package com.apress.ejb.chapter10.producers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProducerWineClient", urlPatterns = {"/ProducerWineClient"})
class ProducerWineClient extends HttpServlet {

   @Inject
   @RandomSelector
   private Wine randomWine;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProducerWineClient</title>");            
            out.println("</head>");
            out.println("<body>");
 
            out.println("<h2>Re-deploy and run this servlet couple of times to see the random change in wine color: " + randomWine.getColor()+ "</h2>");
            out.println("<h4>(Make sure the page is not cached. Try redeploying the project itself.)</h4>");
            
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
