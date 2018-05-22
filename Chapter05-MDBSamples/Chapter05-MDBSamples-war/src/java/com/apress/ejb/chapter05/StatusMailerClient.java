package com.apress.ejb.chapter05;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "StatusMailerClient", urlPatterns = {"/StatusMailerClient"})
public class StatusMailerClient extends HttpServlet {

    @EJB
    OrderProcessingBean orderProcessing;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StatusMailerClient</title>");            
            out.println("</head>");
            out.println("<body>");

            out.println("<h1>OrderProcessing session bean lookup to be done</h1>");
            out.println("<h1>Invoking SendOrderStatus() business method now</h1>");
            out.println("<h1>" + orderProcessing.SendOrderStatus() + "</h1>");            
            out.println("<h1>Done !!!</h1>");            
            
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
