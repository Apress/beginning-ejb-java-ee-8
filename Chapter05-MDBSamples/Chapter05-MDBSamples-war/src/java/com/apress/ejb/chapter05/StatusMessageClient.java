package com.apress.ejb.chapter05;

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "StatusMessageClient", urlPatterns = {"/StatusMessageClient"})
public class StatusMessageClient extends HttpServlet {

  @Resource(mappedName = "StatusMessageTopicConnectionFactory")
  private TopicConnectionFactory statusMessageTopicCF;

  @Resource(mappedName = "StatusMessageTopic")
  private Topic statusTopic;    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StatusMessageClient</title>");            
            out.println("</head>");
            out.println("<body>");
                        
            String from = "chirag.rathod@oracle.com";
            String to = "chirag.rathod@oracle.com";
            String content = "Your order has been processed If you have questions" + 
                             " call EJB3 Application with order id #1234567890";
    
            try {
                out.println("<h1>Before status TopicCF connection</h1>");
                Connection connection = statusMessageTopicCF.createConnection("guest", "guest");
                out.println("<h1>Created connection</h1>");
                connection.start();
                out.println("<h1>Started connection</h1>");
                out.println("<h1>Starting Topic Session</h1>");
                Session topicSession = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

                MessageProducer publisher = topicSession.createProducer(statusTopic);
                out.println("<h1>Created producer</h1>");
                MapMessage message = topicSession.createMapMessage();
                message.setJMSType("MailMessage");

                message.setStringProperty("from", from);
                message.setStringProperty("to", to);
                message.setStringProperty("subject", "Status of your wine order");
                message.setStringProperty("content", content);
                out.println("<h1>Before send</h1>");
                publisher.send(message);
                out.println("<h1>After send</h1>");
            }
            catch (JMSException e) {
                e.printStackTrace();
            }
            
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
