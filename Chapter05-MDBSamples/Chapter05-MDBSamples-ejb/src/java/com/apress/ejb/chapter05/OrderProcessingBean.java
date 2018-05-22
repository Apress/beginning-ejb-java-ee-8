package com.apress.ejb.chapter05;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;

@Stateless(name = "OrderProcessing")
public class OrderProcessingBean
{
    public OrderProcessingBean() {
    }

    @Resource(mappedName = "StatusMessageTopicConnectionFactory")
    private TopicConnectionFactory statusMessageTopicCF;

    @Resource(mappedName = "StatusMessageTopic")
    private Topic statusTopic;

    public String SendOrderStatus() {
        String from = "wineapp@yahoo.com";
        String to = "wineapp@yahoo.com";
        String content = 
        "Your order has been processed " + "If you have questions" + 
        " call EJB Application with order id # " + "1234567890";
        
        try {
        System.out.println("Before status TopicCF connection");
        Connection connection = statusMessageTopicCF.createConnection();
        System.out.println("Created connection");
        connection.start();
        System.out.println("statted connection");
        System.out.println("Starting Topic Session");
        Session topicSession = 
            connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        
        MessageProducer publisher = topicSession.createProducer(statusTopic);
        System.out.println("created producer");
        MapMessage message = topicSession.createMapMessage();
        message.setStringProperty("from", from);
        message.setStringProperty("to", to);
        message.setStringProperty("subject", "Status of your wine order");
        message.setStringProperty("content", content);
        System.out.println("before send");
        publisher.send(message);
        System.out.println("after send");
        }
        catch (JMSException e) {
            e.printStackTrace();
        }

        return "Created a MapMessage and sent it to StatusTopic";
    }
}
