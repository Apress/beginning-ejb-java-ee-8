package com.apress.ejb.chapter02;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ShopperCountClient", urlPatterns = {"/ShopperCountClient"})
public class ShopperCountClient extends HttpServlet {

    @EJB
    ShopperCountBean shopperCount;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShopperCountClient</title>");            
            out.println("</head>");
            out.println("<body>");

            out.println("<h1>Resetting Shopper Count ... </h1>");
            shopperCount.resetCounter();
            out.println("<h1>Incrementing Shopper Count ... </h1>");
            shopperCount.incrementShopperCount();
            out.println("<h1>Shopper Count: " + shopperCount.getShopperCount() + "</h1>");

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
