package com.apress.ejb.chapter05;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@MessageDriven(activationConfig = 
               { @ActivationConfigProperty(propertyName = "destinationName", 
                                           propertyValue = "StatusMessageTopic"), 
                 @ActivationConfigProperty(propertyName = "destinationType",
                                           propertyValue = "javax.jms.Topic")
               },
               mappedName = "StatusMessageTopic")
public class StatusMailerBean implements javax.jms.MessageListener
{

  @Resource(name = "mail/wineappMail")
  private javax.mail.Session ms;

  public void onMessage(Message message) {
    try {
      if (message instanceof MapMessage) {
        MapMessage orderMessage = (MapMessage)message;
        String from = orderMessage.getStringProperty("from");
        String to = orderMessage.getStringProperty("to");
        String subject = orderMessage.getStringProperty("subject");
        String content = orderMessage.getStringProperty("content");
        javax.mail.Message msg = new MimeMessage(ms);
        msg.setFrom(new InternetAddress(from));
        InternetAddress[] address = { new InternetAddress(to) };
        msg.setRecipients(javax.mail.Message.RecipientType.TO, address);
        msg.setSubject(subject);
        msg.setSentDate(new java.util.Date());
        msg.setContent(content, "text/html");
        System.out.println("MDB: Sending Message...");
        Transport.send(msg);
        System.out.println("MDB: Message Sent");
      }
      else {
        System.out.println("Invalid message ");
      }

    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
