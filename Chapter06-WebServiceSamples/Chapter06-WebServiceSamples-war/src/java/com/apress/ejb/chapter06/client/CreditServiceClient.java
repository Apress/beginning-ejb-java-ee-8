package com.apress.ejb.chapter06.client;

import com.apress.ejb.chapter06.services.client.CreditCheckEndpointBean;
import com.apress.ejb.chapter06.services.client.CreditService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

@WebServlet(name = "CreditServiceClient", urlPatterns = {"/CreditServiceClient"})
public class CreditServiceClient extends HttpServlet {

      @WebServiceRef(wsdlLocation = "http://localhost:8080/CreditService/CreditCheckEndpointBean?WSDL")
      CreditService service;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreditServiceClient</title>");            
            out.println("</head>");
            out.println("<body>");
            CreditCheckEndpointBean creditService = service.getCreditCheckEndpointBeanPort();
            out.println("<h1>Credit Check returned: " + creditService.creditCheck("12345678") + "</h1>");
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
