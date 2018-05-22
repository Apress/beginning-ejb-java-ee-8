package com.apress.ejb.chapter02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SearchFacadeClient", urlPatterns = {"/SearchFacadeClient"})
public class SearchFacadeClient extends HttpServlet {

    @EJB
    SearchFacadeBean searchFacade;

    @EJB
    ShopperCountBean shopperCount;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SearchFacadeClient</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Starting Search Facade test ... </h1>");
            
            out.println("<h1>SearchFacade Lookup</h1>");
            out.println("<h1>Searching wines</h1>");
            
            List winesList = searchFacade.wineSearch("Red");            
            out.println("<h1>Printing wines list</h1>");

            for (String wine:(List<String>)winesList ){
                out.println("<h1>" + wine + "</h1>");
            }

            System.out.println("Printing Shopper Count after incrementing it ...");
            shopperCount.incrementShopperCount();
            out.println("<h1>" + shopperCount.getShopperCount() + "</h1>");
            
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
